package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return result;
    }

    @GetMapping
    public Page<Product> get(@RequestParam int page){
        Page<Product> productPage = productService.get(page);
        return productPage;
    }

    @GetMapping("/{id}")
    public Page<Product> getOne(@RequestParam int page,@PathVariable Integer id){
        Page<Product> productPage = productService.getOne(page, id);
        return productPage;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result = productService.edit(id, productDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result delete = productService.delete(id);
        return delete;
    }

}
