package org.example.aplicationstripe.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.aplicationstripe.web.entity.Products;

@AllArgsConstructor @NoArgsConstructor
@Getter @ToString
public class ProductsDTO {
    private Long id;
    @NotNull(message = "O nome do produto não pode ser nulo") @NotEmpty(message = "O nome do produto não pode ser nulo")
    @Size(min =2, max =100, message = "O nome do produto deve ter entre 2 a 100 caracteres")
    private String name;
    @Size(max =255, message = "A descricao do produto deve ser menor que 100 caracteres")
    private String description;
    @Min(value =0, message = "O valor do produto deve ser maior que 0")
    private float price;
    private String stripeID;
    public ProductsDTO (Products entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.stripeID = entity.getStripeID();
    }
}
