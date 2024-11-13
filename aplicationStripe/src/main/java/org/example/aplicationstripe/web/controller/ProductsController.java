package org.example.aplicationstripe.web.controller;

import jakarta.validation.*;
import org.example.aplicationstripe.exception.InvalidDTOException;
import org.example.aplicationstripe.web.dto.ProductsCollectionDTO;
import org.example.aplicationstripe.web.dto.ProductsDTO;
import org.example.aplicationstripe.web.entity.Products;
import org.example.aplicationstripe.web.repository.ProductsRepository;
import org.example.aplicationstripe.web.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsRepository productsRepository, ProductsService productsService) {
        this.productsService = productsService;
    }
    @PostMapping ("/products")
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody  ProductsDTO productsDTO) throws InvalidDTOException {

        try{
            List<String> messages = verifyDTO(productsDTO);

        if(!messages.isEmpty()){
            throw new InvalidDTOException(messages);
        }
        Products products = productsService.createProduct(productsDTO);
        ProductsDTO response = new ProductsDTO(products);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<ProductsCollectionDTO> getAllProducts(@RequestParam Integer page) {
        Page<Products> products = productsService.getAll(page);
        List<ProductsDTO> productsDTOList =products.get().map(ProductsDTO::new).toList();

        ProductsCollectionDTO response = new ProductsCollectionDTO();
        response.setCurrent(page);
        response.setPages(products.getTotalPages());
        response.setProductsDTOList(productsDTOList);

        return ResponseEntity.ok(response);
    }

    @GetMapping ("/products/{id}")
    public ResponseEntity<ProductsDTO> getById(@PathVariable("id") Long id ) {
        Optional<Products> optionalProducts = productsService.getById(id);
        if(optionalProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Products products = optionalProducts.get();
        ProductsDTO response = new ProductsDTO(products);
        return ResponseEntity.ok(response);
    }

    private List<String> verifyDTO(ProductsDTO dto){
        Validator validator;
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
            Set<ConstraintViolation<ProductsDTO>> violationSet = validator.validate(dto);
            return violationSet.stream().map(ConstraintViolation::getMessage).toList();
        }
    }
}
