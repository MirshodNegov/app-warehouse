package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result add(@RequestBody Warehouse warehouse){
        Result result = warehouseService.add(warehouse);
        return result;
    }

    @GetMapping
    public Page<Warehouse> get(@RequestParam int page){
        Page<Warehouse> warehousePage = warehouseService.get(page);
        return warehousePage;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        Result result = warehouseService.edit(id, warehouse);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = warehouseService.delete(id);
        return result;
    }
}
