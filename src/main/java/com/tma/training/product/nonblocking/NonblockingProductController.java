package com.tma.training.product.nonblocking;

import com.tma.training.common.Result;
import com.tma.training.product.ProductService;
import com.tma.training.product.rest.CreateProduct;
import com.tma.training.product.rest.UpdateProduct;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class handles non-blocking mode
 */
@Path("/api/v1/products/non-blocking")
public class NonblockingProductController {

    @Inject
    ProductService productService;

    /**
     * List all product existed in DB
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Blocking
    public Uni<Response> getAll() {
        return Uni.createFrom().voidItem().map(ignore -> {
            return Response.ok(new Result<>(productService.getAll())).build();
        });

    }

    /**
     * Get product by ID
     *
     * @param productId
     * @return
     */
    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getProduct(@PathParam("productId") String productId) {
        return Uni.createFrom().voidItem().map(ignore -> {
            return Response.ok(new Result<>(productService.getProduct(productId))).build();
        });
    }

    /**
     * Create product
     *
     * @param productVO
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createProduct(@RequestBody CreateProduct.RequestVO productVO) {
        return Uni.createFrom().voidItem().map(ignore -> {
            return Response.ok(new Result<>(productService.createProduct(productVO))).build();
        });
    }

    /**
     * Update product based on product ID
     *
     * @param productId
     * @param productVO
     * @return
     */
    @PATCH
    @Path("/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateProduct(@PathParam("productId") String
                                               productId, @RequestBody UpdateProduct.RequestVO productVO) {
        return Uni.createFrom().voidItem().map(ignore -> {
            return Response.ok(new Result<>(productService.updateProduct(productId, productVO))).build();
        });
    }

    /**
     * Delete product based on product ID
     *
     * @param productId
     * @return
     */
    @DELETE
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> deleteProduct(@PathParam("productId") String productId) {
        return Uni.createFrom().voidItem().map(ignore -> {
            return Response.ok(new Result<>(productService.deleteProduct(productId))).build();
        });
    }
}