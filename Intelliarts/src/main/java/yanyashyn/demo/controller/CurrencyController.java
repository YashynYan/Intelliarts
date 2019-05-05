package yanyashyn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yanyashyn.demo.dto.request.CurrencyRequest;
import yanyashyn.demo.entities.Currency;
import yanyashyn.demo.repository.CurrencyRepository;
import yanyashyn.demo.service.CurrencyService;

@CrossOrigin
@RestController
@RequestMapping("/currency")
public class CurrencyController {
@Autowired
private CurrencyService currencyService;

    @PostMapping("/findorsave")
    public Currency findOrSave (@RequestBody CurrencyRequest currencyRequest){
        return currencyService.findOrSave(currencyRequest.getCurrency());
    }
}
