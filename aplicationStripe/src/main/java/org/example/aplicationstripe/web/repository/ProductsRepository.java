package org.example.aplicationstripe.web.repository;

import org.example.aplicationstripe.web.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //isso cria o basico
public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Query(value ="SELECT * FROM products WHERE price = 21", nativeQuery = true)
    List<Products> method1(Float price);

    @Query(value = "SELECT p FROM Products p WHERE p.price < :price" )
    List<Products> method2(Float price);

    //List<Products> FindAllByPriceLessThan(Float price);

    //List<Products> FindAllByNameContainingIgnoreCase(String name);
}
