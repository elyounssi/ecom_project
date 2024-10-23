package com.alten.ecom.mapper;

import com.alten.ecom.dto.ProductDTO;
import com.alten.ecom.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);

    List<ProductDTO> toDTO(List<Product> product);
    List<Product> toEntity(List<ProductDTO> productDTO);

}
