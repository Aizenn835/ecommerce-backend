package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.product.CreateProductDTO;
import com.codewithlei.e_commerce.website.dto.product.ProductDTO;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public ProductDTO mapToDTO(ProductEntity product){
        return ProductDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .productDescription(product.getProductDescription())
                .category(product.getCategory())
                .color(product.getColor())
                .numberOfSold(product.getNumberOfSold())
                .stock(product.getStock())
                .productSize(product.getProductSize())
                .imgUrl(product.getImgUrl())
                .build();
    }
    public ProductEntity mapToEntity(CreateProductDTO dto , String image){
        return ProductEntity.builder()
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .productDescription(dto.getProductDescription())
                .category(dto.getCategory())
                .color(dto.getColor())
                .numberOfSold(dto.getNumberOfSold())
                .stock(dto.getStock())
                .imgUrl(image)
                .productSize(dto.getProductSize())
                .build();
    }
    public ProductEntity updateToMapEntity(ProductEntity product , CreateProductDTO dto ,
                                           String image){
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setProductDescription(dto.getProductDescription());
        product.setCategory(dto.getCategory());
        product.setColor(dto.getColor());
        product.setNumberOfSold(dto.getNumberOfSold());
        product.setStock(dto.getStock());
        product.setProductSize(dto.getProductSize());
        product.setImgUrl(image);

        return product;
    }
}
