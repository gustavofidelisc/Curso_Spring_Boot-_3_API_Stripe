package org.example.aplicationstripe.web.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.param.ProductCreateParams;
import org.example.aplicationstripe.web.dto.ProductsDTO;
import org.example.aplicationstripe.web.entity.Products;
import org.example.aplicationstripe.web.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products createProduct(ProductsDTO productsDTO) throws StripeException {
        Products products = new Products(productsDTO);

        Product stripeProduct = Product.create(ProductCreateParams.builder()
                        .setName(productsDTO.getName())
                        .setDescription(productsDTO.getDescription())
                        .setDefaultPriceData(ProductCreateParams.DefaultPriceData.builder()
                                .setCurrency("brl")
                                .setUnitAmount((long) (products.getPrice() * 100))
                                .build())
                .build());
        products.setId(null);
        products.setStripeID(stripeProduct.getId());
        return productsRepository.save(products);
    }

    public Page<Products> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return productsRepository.findAll(pageable);
    }

    public Optional<Products> getById(long id){
        return productsRepository.findById(id);
    }
}
