package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result add(@RequestBody Supplier supplier){
        Result result = supplierService.add(supplier);
        return result;
    }

    @GetMapping
    public Page<Supplier> get(@RequestParam int page){
        Page<Supplier> supplierPage = supplierService.get(page);
        return supplierPage;
    }

    @GetMapping("/{id}")
    public Supplier getOne(@PathVariable Integer id){
        Supplier supplier = supplierService.getOne(id);
        return supplier;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = supplierService.delete(id);
        return result;
    }
}
