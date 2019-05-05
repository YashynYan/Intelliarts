package yanyashyn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yanyashyn.demo.dto.response.ProductResponse;
import yanyashyn.demo.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query ("Delete from Product p where p.date like :dateParam")
    void deleteProductsByDate (@Param("dateParam") String d);

    @Query ("Select p from Product p where p.date like CONCAT(:yearParam,'%')")
    ArrayList<ProductResponse> findProductsByDate  (@Param("yearParam") String year);
}
