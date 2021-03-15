package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // add get getOne edit delete

    @PostMapping
    public Result add(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.add(categoryDto);
        return result;
    }

    @GetMapping
    public Page<Category> get(@RequestParam int page){
        Page<Category> categories = categoryService.get(page);
        return categories;
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable Integer id){
        Category category = categoryService.getOne(id);
        return category;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.edit(id, categoryDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = categoryService.delete(id);
        return result;
    }
}
