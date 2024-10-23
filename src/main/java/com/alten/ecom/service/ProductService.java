package com.alten.ecom.service;

import com.alten.ecom.dto.ProductDTO;
import com.alten.ecom.exception.ProductNotFoundException;
import com.alten.ecom.mapper.ProductMapper;
import com.alten.ecom.model.Product;
import com.alten.ecom.repository.ProductRepository;
import com.alten.ecom.util.Patcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        productRepository.saveAndFlush(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productMapper.toDTO(productRepository.findAll());
    }

    public ProductDTO getProductById(Long id) {
        return productMapper.toDTO(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id)));
    }

    @Transactional
    public void patchProduct(Long id, ProductDTO productPatch) throws IllegalAccessException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        ProductDTO existingproductDTO = productMapper.toDTO(existingProduct);

        Patcher.productPatcher(existingproductDTO, productPatch);

        Product productToBeSaved = productMapper.toEntity(existingproductDTO);
        productToBeSaved.setId(existingProduct.getId());
        productToBeSaved.setCreatedAt(existingProduct.getCreatedAt());
        productToBeSaved.setUpdatedAt(existingProduct.getUpdatedAt());

        productRepository.saveAndFlush(productToBeSaved);

    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
