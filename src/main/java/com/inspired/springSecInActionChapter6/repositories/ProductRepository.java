package com.inspired.springSecInActionChapter6.repositories;

import com.inspired.springSecInActionChapter6.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
