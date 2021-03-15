package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputProductService;
@RestController
@RequestMapping("/outputproduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public Page<OutputProduct> page(@RequestParam Integer page){
        return outputProductService.page(page);
    }

    @GetMapping("/{id}")
    public OutputProduct getById(@PathVariable Integer id){
        return outputProductService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return outputProductService.delete(id);
    }

    @PostMapping
    public OutputProduct add(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.add(outputProductDto);
    }

    @PutMapping("/{id}")
    public OutputProduct edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.edit(id, outputProductDto);
    }
}
