package org.augustoocc.controller;


import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.augustoocc.data.DataAccessObjects;
import org.augustoocc.data.ProductMessage;
import org.augustoocc.domain.Product;
import org.augustoocc.repository.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ws.rs.Path;

@Path("/api/v1/product")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductAPI {

    @Inject
    ProductRepository repository;

    @Inject
    EventBus bus;

    @Inject
    DataAccessObjects dataAccessObjects;

    @Inject
    DateTimeFormatter logtimestamp;

    @GET
    public Uni<List<Product>> list() {
        log.info("Request received - listing objects");
        return repository.listAll();
    }

    @POST
    public Uni<Response> createObject(Product p) {
        log.info("Request received - creating objects");
        return bus.<Product>request("add-product", p)
                    .invoke(i -> {log.info(LocalDateTime.now(ZoneOffset.UTC).format(logtimestamp));})
                    .map(i -> Response.ok(i.body()).build());
    }

    @PUT
    @Path("update/{id}")
    public Uni<Response> putObject (@PathParam("{id}") Long id, Product p) {
        log.info("Request received - putting objects");
        ProductMessage productMessage = new ProductMessage(id, p);
        return bus.<Product>request("update-product", productMessage)
                .invoke(i -> {log.info(LocalDateTime.now(ZoneOffset.UTC).format(logtimestamp));})
                .map(i -> Response.ok(i.body()).build());
    }

    @DELETE
    @Path("delete/{id}")
    public Uni<Response> deleteObject(@PathParam("id") Long id) {
        log.info("Request received - deleting objects");
        return dataAccessObjects.deleteProduct(id);
    }

    @GET
    @Path("/get-product/{id}")
    public Uni<Response> getProduct(@PathParam("id") Long id) {
        return bus.<Product>request("get-by-id", id)
                .invoke(i -> {log.info(LocalDateTime.now(ZoneOffset.UTC).format(logtimestamp));})
                .map(i -> Response.ok(i.body()).build());
    }
}
