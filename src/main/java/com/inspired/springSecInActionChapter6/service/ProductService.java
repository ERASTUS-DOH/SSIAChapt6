package com.inspired.springSecInActionChapter6.service;

import com.inspired.springSecInActionChapter6.dto.Product;
import com.inspired.springSecInActionChapter6.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository  productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
