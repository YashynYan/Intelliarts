package yanyashyn.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yanyashyn.demo.entities.Product;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Long id;

    private Double money;

    private String date;

    private String name;

    private String currencyName;

    public ProductResponse (Product product){
        id = product.getId();
        money = product.getMoney();
        name = product.getName();
        date = product.getDate();
        currencyName = product.getCurrency().getCurrency();
    }
}
