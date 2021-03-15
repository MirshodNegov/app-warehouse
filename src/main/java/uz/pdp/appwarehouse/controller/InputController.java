package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;


    @GetMapping
    public Page<Input> page(@RequestParam Integer page){
        return inputService.page(page);
    }

    @GetMapping("/{id}")
    public Input getById(@PathVariable Integer id){
        return inputService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return inputService.delete(id);
    }

    @PostMapping
    public Input add(@RequestBody InputDTO inputDTO){
        return new Input();
    }

    @PutMapping("/{id}")
    public Input edit(@PathVariable Integer id, @RequestBody InputDTO inputDTO){
        return new Input();
    }
}
