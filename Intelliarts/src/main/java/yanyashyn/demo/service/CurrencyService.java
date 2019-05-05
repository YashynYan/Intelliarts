package yanyashyn.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanyashyn.demo.entities.Currency;
import yanyashyn.demo.exceptions.WrongInputDataException;
import yanyashyn.demo.repository.CurrencyRepository;

@Service
public class CurrencyService {
    @Autowired
private CurrencyRepository currencyRepository;

    public Currency findByName (String currencyName) {
    return currencyRepository.findByCurrency(currencyName).orElse(null);
    }

    public Currency findOrSave (String currencyName){
    if (findByName(currencyName) != null){
    return findByName(currencyName);
    }else {
        Currency currency = new Currency();
        currency.setCurrency(currencyName);
        currencyRepository.save(currency);
        return currency;
    }
    }
}
