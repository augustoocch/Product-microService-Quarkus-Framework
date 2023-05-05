package org.augustoocc.data;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.augustoocc.domain.Product;
import org.augustoocc.exceptions.NotWritableEx;
import org.augustoocc.repository.ProductRepository;
import org.augustoocc.validations.Validations;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Slf4j
@ApplicationScoped
public class DataAccessObjects {


    @Inject
    NotWritableEx exception;


    @Inject
    ProductRepository productRepository;

    @Inject
    DateTimeFormatter logtimestamp;

    @Inject
    Validations validate;


    @ConsumeEvent("add-product")
    public Uni<Product> addProduct(Product p) {
        log.info("Product in creation phase at: {}", LocalDateTime.now(ZoneOffset.UTC).format(logtimestamp));
        if(validate.postValidation(p) == true) {
            throw exception.nullValues("Post method 'add-product', ");
        } else {
            return Panache.withTransaction(p::persist)
                    .replaceWith(p)
                    .onFailure().invoke(i -> exception.panacheFailure("Panache failure: " ));
        }
    }


    //@ConsumeEvent("delete-product")
    public Uni<Response> deleteProduct(Long id) {
        return Panache.withTransaction(() -> productRepository.deleteById(id))
                .map(deleted -> deleted
                ? Response.ok().status(200).build()
                : Response.ok().status(404).build());
    }

    @ConsumeEvent("get-by-id")
    public Uni<Product> getById(@PathParam("{id}") Long id) {
        log.info("Request received - getting customer");
        return Panache.withTransaction(() -> productRepository.findById(id))
                .onFailure().invoke(res -> log.error("Error recovering products ", res));
    }

    @ConsumeEvent("update-product")
    @Transactional
    public Uni<Product> updateCustomer(ProductMessage product) {
        if (validate.postValidation(product.getProduct())) {
            throw exception.nullValues("Put method 'add-customer', ");
        }
        log.info("Merging object with id: ", product.getId());
        return Panache.withTransaction(() -> productRepository.findById(product.getId())
                        .onItem().ifNotNull().invoke(entity -> {
                            entity.setName(product.getProduct().getName());
                            entity.setCode(product.getProduct().getCode());
                            entity.setDescription(product.getProduct().getDescription());
                        }))
                .replaceWith(product.getProduct())
                .onFailure().invoke(i -> exception.panacheFailure("Put method 'add-product'"));
    }





}
