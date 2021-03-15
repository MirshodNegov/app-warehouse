package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.OutputProductRepository;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductService productService;

    @Autowired
    uz.pdp.appwarehouse.service.OutputService outputService;

    public Page<OutputProduct> page(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        return outputProductRepository.findAll(pageable);
    }

    public OutputProduct getById(Integer id){
        Optional<OutputProduct> outputProduct = outputProductRepository.findById(id);
        return outputProduct.orElseGet(OutputProduct::new);
    }

    public Result delete(Integer id){
        return new Result();
        //code
    }

    public OutputProduct add(OutputProductDto outputProductDto){

        Optional<Product> byId1 = productService.productRepository.findById(outputProductDto.getProductId());
        if (!byId1.isPresent()) {
            return new OutputProduct();
        }

        Optional<Output> byId2 = outputService.outputRepository.findById(outputProductDto.getOutputId());
        if (!byId2.isPresent()) {
            return new OutputProduct();
        }

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(byId1.get());
        outputProduct.setOutput(byId2.get());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setAmount(outputProductDto.getAmount());
        return outputProductRepository.save(outputProduct);
    }

    public OutputProduct edit(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> outputProductOptional = outputProductRepository.findById(id);

        if (outputProductOptional.isPresent()){
            Optional<Product> byId1 = productService.productRepository.findById(outputProductDto.getProductId());
            if (!byId1.isPresent()) {
                return new OutputProduct();
            }

            Optional<Output> byId2 = outputService.outputRepository.findById(outputProductDto.getOutputId());
            if (!byId2.isPresent()) {
                return new OutputProduct();
            }

            OutputProduct outputProduct = outputProductOptional.get();
            outputProduct.setProduct(byId1.get());
            outputProduct.setOutput(byId2.get());
            outputProduct.setPrice(outputProductDto.getPrice());
            outputProduct.setAmount(outputProductDto.getAmount());
            return outputProductRepository.save(outputProduct);
        }

        return new OutputProduct();
    }
}
