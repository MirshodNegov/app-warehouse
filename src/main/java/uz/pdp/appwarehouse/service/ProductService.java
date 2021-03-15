package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(),productDto.getCategoryId());
        if (exists)
            return new Result("Bunday nomdagi product mavjud",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas !",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjud emas !",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("BUnday o'lchov birligi mavjud emas !",false);
        Category category = optionalCategory.get();
        Attachment attachment = optionalAttachment.get();
        Measurement measurement = optionalMeasurement.get();
        Product product=new Product();
        product.setName(productDto.getName());
        product.setCode("1");
        product.setCategory(category);
        product.setPhoto(attachment);
        product.setMeasurement(measurement);
        Product save = productRepository.save(product);
        return new Result("Product saqlandi !",true,save.getId());
    }

    public Page<Product> get(int page) {
        Pageable pageable= PageRequest.of(page,5);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    public Page<Product> getOne(int page, Integer id) {
        Pageable pageable= PageRequest.of(page,5);
        Page<Product> productPage = productRepository.findAllById(id, pageable);
        return productPage;
    }

    public Result edit(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product not found !",false);
        Product product = optionalProduct.get();
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas !",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjud emas !",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("BUnday o'lchov birligi mavjud emas !",false);
        Category category = optionalCategory.get();
        Attachment attachment = optionalAttachment.get();
        Measurement measurement = optionalMeasurement.get();
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPhoto(attachment);
        product.setMeasurement(measurement);
        Product save = productRepository.save(product);
        return new Result("Product saqlandi !",true,save.getId());
    }

    public Result delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product not found !",false);
        productRepository.deleteById(id);
        return new Result("Product deleted !",true);
    }
}
