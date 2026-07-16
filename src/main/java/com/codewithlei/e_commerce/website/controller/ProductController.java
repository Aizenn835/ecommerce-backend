package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.product.CreateProductDTO;
import com.codewithlei.e_commerce.website.dto.product.ProductDTO;
import com.codewithlei.e_commerce.website.model.enums.Category;
import com.codewithlei.e_commerce.website.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5500"})
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/search")
    public List<ProductDTO> search(@RequestParam String keyword){
        return productService.searchProducts(keyword);
    }
    @GetMapping("/get-all")
    public List<ProductDTO> getAll(){
        return productService.getAllProduct();
    }
    @PostMapping(value = "/create-product" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDTO> create(@RequestPart("dto") @Valid CreateProductDTO dto,
                                             @RequestPart("image") MultipartFile image)throws IOException{
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(dto, image));
    }
    @GetMapping("/get-category")
    public List<ProductDTO> getByCategory(@RequestParam Category category){
        return productService.getByCategory(category);
    }
    @PutMapping(value = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDTO update(@PathVariable("id")Long id, @RequestPart("dto") CreateProductDTO dto ,  @RequestPart("file") MultipartFile file)throws IOException {
        return productService.update(id , dto , file);
    }
    @GetMapping("/product-count")
    public Long getCount(){
        return productService.getCount();
    }
    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable("id")Long id){
        return productService.getById(id);
    }
}
