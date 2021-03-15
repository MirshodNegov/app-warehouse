package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Page<User> page(@RequestParam Integer page){
        return userService.page(page);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return userService.delete(id);
    }

    @PostMapping
    public User add(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }
}
