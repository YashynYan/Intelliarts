package yanyashyn.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class CurrencyRequest {
    @NotBlank
    private String currency;
}
