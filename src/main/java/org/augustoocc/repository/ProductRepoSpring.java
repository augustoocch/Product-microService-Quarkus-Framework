package org.augustoocc.repository;

import org.augustoocc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository using quarkus with spring framework data jpa for optimization
public interface ProductRepoSpring extends JpaRepository<Product, Integer> {
}
