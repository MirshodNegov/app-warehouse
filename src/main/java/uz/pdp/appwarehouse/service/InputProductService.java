package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.InputProductRepository;

import java.util.Optional;


@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductService productService;

    @Autowired
    uz.pdp.appwarehouse.service.InputService inputService;

    public Page<InputProduct> page(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        return inputProductRepository.findAll(pageable);
    }

    public InputProduct getById(Integer id){
        Optional<InputProduct> optionalInput = inputProductRepository.findById(id);
        return optionalInput.orElseGet(InputProduct::new);
    }

    public Result delete(Integer id){
        return new Result();
        //code
    }

    public InputProduct add(InputProductDto inputProductDto){

        Optional<Product> byId1 = productService.productRepository.findById(inputProductDto.getProductId());
        if (!byId1.isPresent()) {
            return new InputProduct();
        }

        Optional<Input> byId2 = inputService.inputRepository.findById(inputProductDto.getInputId());
        if (!byId2.isPresent()) {
            return new InputProduct();
        }

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(byId1.get());
        inputProduct.setInput(byId2.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setAmount(inputProductDto.getAmount());

        return inputProductRepository.save(inputProduct);
    }

    public InputProduct edit(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> inputProductOptional = inputProductRepository.findById(id);

        if (inputProductOptional.isPresent()){
            Optional<Product> byId1 = productService.productRepository.findById(inputProductDto.getProductId());
            if (!byId1.isPresent()) {
                return new InputProduct();
            }

            Optional<Input> byId2 = inputService.inputRepository.findById(inputProductDto.getInputId());
            if (!byId2.isPresent()) {
                return new InputProduct();
            }

            InputProduct inputProduct = inputProductOptional.get();
            inputProduct.setProduct(byId1.get());
            inputProduct.setInput(byId2.get());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setAmount(inputProductDto.getAmount());

            return inputProductRepository.save(inputProduct);
        }

        return new InputProduct();
    }
}
