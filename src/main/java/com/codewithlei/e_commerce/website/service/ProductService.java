package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.product.CreateProductDTO;
import com.codewithlei.e_commerce.website.dto.product.ProductDTO;
import com.codewithlei.e_commerce.website.model.enums.Category;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Component
public interface ProductService {
    List<ProductDTO> getAllProduct();
    List<ProductDTO> getByCategory(Category category);
    List<ProductDTO> searchProducts(String keyword);
    ProductDTO create(CreateProductDTO dto , MultipartFile file)throws IOException;
    ProductDTO update(Long id , CreateProductDTO dto , MultipartFile file)throws IOException;
    Long getCount();
    ProductDTO getById(Long id);
}
