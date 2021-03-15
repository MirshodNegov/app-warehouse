package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.Optional;


@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository wareHouseService;

    @Autowired
    SupplierRepository supplierService;

    @Autowired
    CurrencyService currencyService;

    public Page<Input> page(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAll(pageable);
    }

    public Input getById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }

    public Result delete(Integer id){
        return new Result();
        //code
    }

}
