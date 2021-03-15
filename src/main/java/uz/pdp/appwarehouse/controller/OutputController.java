package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.payload.OutputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;


    @GetMapping
    public Page<Output> page(@RequestParam Integer page){
        return outputService.page(page);
    }

    @GetMapping("/{id}")
    public Output getById(@PathVariable Integer id){
        return outputService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return outputService.delete(id);
    }

    @PostMapping
    public Output add(@RequestBody OutputDTO outputDTO){
        return outputService.add(outputDTO);
    }

    @PutMapping("/{id}")
    public Output edit(@PathVariable Integer id, @RequestBody OutputDTO outputDTO){
        return outputService.edit(id, outputDTO);
    }
}
