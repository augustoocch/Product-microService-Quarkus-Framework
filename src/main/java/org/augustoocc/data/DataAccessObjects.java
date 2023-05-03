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











}
