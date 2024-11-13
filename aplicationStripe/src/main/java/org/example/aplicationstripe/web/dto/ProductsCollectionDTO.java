package org.example.aplicationstripe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ProductsCollectionDTO {
    private Integer current;
    private Integer pages;
    private List<ProductsDTO> productsDTOList;
}
