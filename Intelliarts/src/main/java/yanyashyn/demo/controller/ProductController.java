package yanyashyn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import yanyashyn.demo.dto.request.ProductRequest;
import yanyashyn.demo.dto.response.ProductResponse;
import yanyashyn.demo.entities.Product;
import yanyashyn.demo.exceptions.WrongInputDataException;
import yanyashyn.demo.service.ProductService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
@Autowired
    private ProductService productService;

@PostMapping ("/save")
public ProductResponse save (@RequestBody ProductRequest productRequest) throws WrongInputDataException {
return productService.save(productRequest);
}

@GetMapping ("/getall")
    public List<ProductResponse> getAll (){
    return productService.findAll();
}

@DeleteMapping("/delete")
    public void delete (@RequestParam String date){productService.delete(date);}

@GetMapping ("/report")
    public List<ProductResponse> getReport (@RequestParam String year){
    return productService.makeReport(year);
}
}
