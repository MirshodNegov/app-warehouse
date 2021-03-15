package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.OutputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.OutputRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    uz.pdp.appwarehouse.service.ClientService clientService;

    @Autowired
    CurrencyService currencyService;

    public Page<Output> page(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        return outputRepository.findAll(pageable);
    }

    public Output getById(Integer id){
        Optional<Output> optionalInput = outputRepository.findById(id);
        return optionalInput.orElseGet(Output::new);
    }

    public Result delete(Integer id){
        return new Result();
        //code
    }

    public Output add(OutputDTO outputDTO){
        Optional<Warehouse> byId = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!byId.isPresent()) {
            return new Output();
        }

        Optional<Client> byId1 = clientService.clientRepository.findById(outputDTO.getClientId());
        if (!byId1.isPresent()) {
            return new Output();
        }

        Optional<Currency> byId2 = currencyService.currencyRepository.findById(outputDTO.getCurrencyId());
        if (!byId2.isPresent()) {
            return new Output();
        }

        Output output = new Output();
        output.setCurrency(byId2.get());
        output.setClient(byId1.get());
        output.setWarehouse(byId.get());

        UUID uuid = UUID.randomUUID();
        output.setCode(uuid.toString());

        return outputRepository.save(output);
    }

    public Output edit(Integer id, OutputDTO outputDTO){
        Optional<Output> optionalOutput = outputRepository.findById(id);

        if (optionalOutput.isPresent()){
            Optional<Warehouse> byId = warehouseRepository.findById(outputDTO.getWarehouseId());
            if (!byId.isPresent()) {
                return new Output();
            }

            Optional<Client> byId1 = clientService.clientRepository.findById(outputDTO.getClientId());
            if (!byId1.isPresent()) {
                return new Output();
            }

            Optional<Currency> byId2 = currencyService.currencyRepository.findById(outputDTO.getCurrencyId());
            if (!byId2.isPresent()) {
                return new Output();
            }

            Output output = optionalOutput.get();
            output.setCurrency(byId2.get());
            output.setClient(byId1.get());
            output.setWarehouse(byId.get());

            UUID uuid = UUID.randomUUID();
            output.setCode(uuid.toString());

            return outputRepository.save(output);
        }

        return new Output();
    }

}
