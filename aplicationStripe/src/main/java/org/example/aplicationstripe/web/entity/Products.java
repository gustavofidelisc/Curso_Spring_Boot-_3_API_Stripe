package org.example.aplicationstripe.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.aplicationstripe.web.dto.ProductsDTO;

@Entity
//@Table(name = "produtos") pegando o nome que está no banco
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private String description;
    private Float price;
    @Column(name = "stripe_id")
    private String stripeID;

    public Products(ProductsDTO dto){
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
    }
}
