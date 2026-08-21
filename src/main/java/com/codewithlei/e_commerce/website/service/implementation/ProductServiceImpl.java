package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.product.CreateProductDTO;
import com.codewithlei.e_commerce.website.dto.product.ProductDTO;
import com.codewithlei.e_commerce.website.exception.productException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.mapper.ProductMapper;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.enums.Category;
import com.codewithlei.e_commerce.website.repository.ProductRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.ProductService;
import com.codewithlei.e_commerce.website.validation.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImageServiceImpl imageService;
    private final ProductValidation productValidation;
    private final UserRepository userRepository;

    @Override
    public List<ProductDTO> getAllProduct(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToDTO)
                .toList();
    }
    @Override
    public List<ProductDTO> getByCategory(Category category){
        return productRepository.findByCategory(category)
                .stream()
                .map(productMapper::mapToDTO)
                .toList();
    }
    @Override
    public List<ProductDTO> searchProducts(String keyword){
        List<ProductEntity> product = productRepository.findByProductNameContainingIgnoreCase(keyword);

        return product.stream()
                .map(productMapper::mapToDTO)
                .toList();
    }
    @Override
    public ProductDTO create(CreateProductDTO dto , MultipartFile file)throws IOException {
        productValidation.validateImg(file);
        String image = imageService.imageUpload(file , "product-photos");
        ProductEntity product = productMapper.mapToEntity(dto, image);
        ProductEntity saved = productRepository.save(product);
        return productMapper.mapToDTO(saved);
    }
    @Override
    public ProductDTO update(Long id , CreateProductDTO dto , MultipartFile file)throws IOException{
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        String image = imageService.imageUpload(file , "product-photos");
        ProductEntity updated = productMapper.updateToMapEntity(product , dto , image);
        return productMapper.mapToDTO(productRepository.save(updated));
    }
    @Override
    public Long getCount(){
        return productRepository.count(); // delete this later.
    }
    @Override
    public ProductDTO getById(Long id){
        return productRepository.findById(id)
                .map(productMapper::mapToDTO)
                .orElseThrow(ProductNotFoundException::new);
    }
}
