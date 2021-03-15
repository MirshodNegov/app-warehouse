package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;
@RestController
@RequestMapping("/inputproduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public Page<InputProduct> page(@RequestParam Integer page){
        return inputProductService.page(page);
    }

    @GetMapping("/{id}")
    public InputProduct getById(@PathVariable Integer id){
        return inputProductService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return inputProductService.delete(id);
    }

    @PostMapping
    public InputProduct add(@RequestBody InputProductDto inputProductDto){
        return inputProductService.add(inputProductDto);
    }

    @PutMapping("/{id}")
    public InputProduct edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.edit(id, inputProductDto);
    }
}
