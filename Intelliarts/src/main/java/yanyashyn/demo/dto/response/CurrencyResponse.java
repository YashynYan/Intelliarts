package yanyashyn.demo.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yanyashyn.demo.entities.Currency;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyResponse {
    private Long id;

    private String currencyName;

    public CurrencyResponse (Currency currency){
    id = currency.getId();
    currencyName = currency.getCurrency();
    }
}
