package org.augustoocc.repository;

import org.augustoocc.domain.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

//Repository using quarkus with javax
@ApplicationScoped
public class ProductRepoJavax {

    @Inject
    EntityManager em;

    @Transactional
    public void createProduct(Product p) {
        em.persist(p);
    }

    @Transactional
    public void deleteProduct(int id) {
        Product p = new Product();
        p = em.find(Product.class, id);
        em.remove(p);
    }

    @Transactional
    public void putObject(Product p) {
        em.merge(p);
    }

    @Transactional
    public Product getObject(int id) {
        return em.find(Product.class, id);
    }


    @Transactional
    public List<Product> listProduct() {
        List<Product> productList =  em.createQuery("select p from Product p").getResultList();
        return productList;
    }

}
