package yanyashyn.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class ProductRequest {
//    @NotBlank

    private String date;

//    @NotBlank
    private Double money;

//    @NotBlank
    private String name;

//    @NotBlank
    private String currencyName;
}
