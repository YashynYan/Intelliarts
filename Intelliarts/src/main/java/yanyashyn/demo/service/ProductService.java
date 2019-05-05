package yanyashyn.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanyashyn.demo.dto.request.ProductRequest;
import yanyashyn.demo.dto.response.ProductResponse;
import yanyashyn.demo.entities.Currency;
import yanyashyn.demo.entities.Product;
import yanyashyn.demo.exceptions.WrongInputDataException;
import yanyashyn.demo.repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
@Autowired
    private ProductRepository productRepository;
@Autowired
    private CurrencyService currencyService;

    public ProductResponse save(ProductRequest productRequest) throws WrongInputDataException {
        Product product = new Product();
        product.setDate(productRequest.getDate());
        product.setMoney(productRequest.getMoney());
        product.setName(productRequest.getName());
        product.setCurrency(currencyService.findOrSave(productRequest.getCurrencyName()));
        return new ProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAll (){
        List <ProductResponse> products = productRepository.findAll().stream().map(ProductResponse::new).collect(Collectors.toList());
        Collections.sort(products, new Comparator<ProductResponse>() {
            @Override
            public int compare(ProductResponse o1, ProductResponse o2) {
                if (o1.getDate() == null || o2.getDate() == null)
                    return 0;
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return products;
    }

    public void delete(String date) {
        System.out.println(date);
        productRepository.deleteProductsByDate(date);
    }

    public List<ProductResponse> makeReport(String year) {
        return productRepository.findProductsByDate(year);
    }

//    public List<ProductResponse> findByYear (LocalDate date){
//        return productRepository.findByDate(date)
    }

