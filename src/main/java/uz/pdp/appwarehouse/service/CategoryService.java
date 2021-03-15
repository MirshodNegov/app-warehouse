package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Result add(CategoryDto categoryDto){
            Category category=new Category();
            category.setName(categoryDto.getName());
            if (categoryDto.getParentCategoryId() != null){
                Optional<Category> optionalCategory =
                        categoryRepository.findById(categoryDto.getParentCategoryId());
                if (!optionalCategory.isPresent()){
                    return new Result("Parent category id wrong !",false);
                }
                Category parentCategory = optionalCategory.get();
                 category.setParentCategory(parentCategory);
            }
            categoryRepository.save(category);
            return new Result("Category saved !",true);
    }

    public Page<Category> get(int page) {
        Pageable pageable= PageRequest.of(page,5);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage;
    }

    public Category getOne(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return null;
        Category category = optionalCategory.get();
        return category;
    }

    public Result delete(Integer id){
        boolean exists = categoryRepository.existsById(id);
        if (!exists)
            return new Result("Category not found",false);
        categoryRepository.deleteById(id);
        return new Result("Category deleted !",true);
    }

    public Result edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory1 = categoryRepository.findById(id);
        if (!optionalCategory1.isPresent())
            return new Result("Category not found !",false);
        Category category = optionalCategory1.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalCategory =
                    categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent()){
                return new Result("Parent category id wrong !",false);
            }
            Category parentCategory = optionalCategory.get();
            category.setParentCategory(parentCategory);
        }
        categoryRepository.save(category);
        return new Result("Category edited !",true);
    }
}
