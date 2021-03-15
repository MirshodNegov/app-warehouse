package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(Currency currency) {
        Currency currency1 = currencyRepository.save(currency);
        return new Result("Currency qo'shildi !",true,currency1.getId());
    }

    public Page<Currency> get(int page){
        Pageable pageable= PageRequest.of(page,5);
        Page<Currency> currencyPage = currencyRepository.findAll(pageable);
        return currencyPage;
    }

    public Result edit(Integer id,Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found",false);
        Currency currency1 = optionalCurrency.get();
        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());
        currencyRepository.save(currency1);
        return new Result("Currency edited !",true);
    }

    public Result delete(Integer id) {
        boolean exists = currencyRepository.existsById(id);
        if (!exists)
            return new Result("Currency not found !",false);
        currencyRepository.deleteById(id);
        return new Result("Currency deleted !",true);
    }
}
