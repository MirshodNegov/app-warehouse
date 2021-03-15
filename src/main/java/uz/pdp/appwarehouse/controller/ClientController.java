package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public Page<Client> page(@RequestParam Integer page){
        return clientService.page(page);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.getById(id);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return clientService.delete(id);
    }

    @PostMapping
    public Client add(@RequestBody Client client){
        return clientService.add(client);
    }

    @PutMapping("/{id}")
    public Client edit(@PathVariable Integer id, @RequestBody Client client){
        return clientService.edit(id, client);
    }

}
