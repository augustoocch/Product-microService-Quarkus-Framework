package org.augustoocc.controller;


import lombok.extern.slf4j.Slf4j;
import org.augustoocc.domain.Product;
import org.augustoocc.repository.ProductRepo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.Path;

@Path("/api/v1/product")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductAPI {

    @Inject
    ProductRepo repository;

    @GET
    public List<Product> list() {
       log.info("Request received - listing objects");
        return repository.listProduct();

    }

    @POST
    public Response createObject(Product p) {
        log.info("Request received - creating objects");
        repository.createProduct(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteObject(@PathParam("id") int id) {
        log.info("Request received - deleting object");
        repository.deleteProduct(id);
        return Response.ok().build();
    }

    @PUT
    public Response putObject (Product p) {
        log.info("Request received - putting objects");
        repository.putObject(p);
        return Response.ok().build();
    }

    @GET
    @Path("/get-user/{id}")
    public Response getProduct(@PathParam("id") int id) {
        log.info("Request received - getting objects");
        repository.getObject(id);
        return Response.ok().build();
    }
}
