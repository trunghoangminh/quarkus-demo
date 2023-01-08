package com.tma.training.product;

import com.tma.training.common.Result;
import com.tma.training.product.rest.CreateProduct;
import com.tma.training.product.rest.GetProduct;
import com.tma.training.product.rest.ListProduct;
import com.tma.training.product.rest.UpdateProduct;
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
import java.util.List;

/**
 * This class handles
 */
@Path("/api/v1/products/blocking")
public class BlockingProductController {

    @Inject
    ProductService productService;

    /**
     * List all product existed in DB
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Result<List<ListProduct.ResponseVO>> getAll() {
        return new Result<>(productService.getAll());
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
    public Result<GetProduct.ResponseVO> getProduct(@PathParam("productId") String productId) {
        return new Result<>(productService.getProduct(productId));
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
    public Result<CreateProduct.ResponseVO> createProduct(@RequestBody CreateProduct.RequestVO productVO) {
        return new Result<>(productService.createProduct(productVO));
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
    public Result<UpdateProduct.ResponseVO> updateProduct(@PathParam("productId") String productId, @RequestBody UpdateProduct.RequestVO productVO) {
        return new Result<>(productService.updateProduct(productId, productVO));
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
    public Result<String> deleteProduct(@PathParam("productId") String productId) {
        return new Result<>(productService.deleteProduct(productId));
    }
}