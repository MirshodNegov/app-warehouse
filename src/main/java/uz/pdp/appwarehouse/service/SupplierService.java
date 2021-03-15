package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;


    public Result add(Supplier supplier) {
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists)
            return new Result("This phone number already exist !",false);
        supplierRepository.save(supplier);
        return new Result("Supplier saved !",true);
    }

    public Page<Supplier> get(int page) {
        Pageable pageable= PageRequest.of(page,10);
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        return supplierPage;
    }

    public Supplier getOne(Integer id) {
        Optional<Supplier> optionalSupplier =
                supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Supplier();
        Supplier supplier = optionalSupplier.get();
        return supplier;
    }

    public Result delete(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found !",false);
        Supplier supplier = optionalSupplier.get();
        supplierRepository.deleteById(supplier.getId());
        return new Result("Supplier deleted !",true);
    }


}
