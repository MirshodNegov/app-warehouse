package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;


    public Result add(Warehouse warehouse) {
        Warehouse warehouse1 = warehouseRepository.save(warehouse);
        return new Result("Ombor qo'shildi !",true,warehouse1.getId());
    }

    public Page<Warehouse> get(int page){
        Pageable pageable= PageRequest.of(page,5);
        Page<Warehouse> warehousePage = warehouseRepository.findAll(pageable);
        return warehousePage;
    }

    public Result edit(Integer id,Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found",false);
        Warehouse warehouse1 = optionalWarehouse.get();
        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse.isActive());
        warehouseRepository.save(warehouse1);
        return new Result("Warehouse edited !",true);
    }

    public Result delete(Integer id) {
        boolean exists = warehouseRepository.existsById(id);
        if (!exists)
            return new Result("Warehouse not found !",false);
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted !",true);
    }
}
