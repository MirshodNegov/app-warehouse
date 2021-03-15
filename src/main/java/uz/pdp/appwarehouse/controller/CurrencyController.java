package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;
import uz.pdp.appwarehouse.service.WarehouseService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result add(@RequestBody Currency currency){
        Result result = currencyService.add(currency);
        return result;
    }

    @GetMapping
    public Page<Currency> get(@RequestParam int page){
        Page<Currency> currencyPage = currencyService.get(page);
        return currencyPage;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Currency currency){
        Result result = currencyService.edit(id, currency);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = currencyService.delete(id);
        return result;
    }
}
