package org.augustoocc.controller;


import lombok.extern.slf4j.Slf4j;
import org.augustoocc.domain.Product;
import org.augustoocc.repository.ProductRepoJavax;
import org.augustoocc.repository.ProductRepoSpring;

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
    ProductRepoSpring repository;

    @GET
    public List<Product> list() {
       log.info("Request received - listing objects");
        return repository.findAll();

    }

    @POST
    public Response createObject(Product p) {
        log.info("Request received - creating objects");
        repository.save(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteObject(@PathParam("id") int id) {
        log.info("Request received - deleting object");
        repository.delete(repository.findById(id).get());
        return Response.ok().build();
    }

    @PUT
    public Response putObject (Product p) {
        log.info("Request received - putting objects");
        repository.save(p);
        return Response.ok().build();
    }

    @GET
    @Path("/get-user/{id}")
    public Product getProduct(@PathParam("id") int id) {
        log.info("Request received - getting objects");
        return repository.findById(id).get();

    }
}
