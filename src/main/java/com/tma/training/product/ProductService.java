package com.tma.training.product;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.tma.training.common.enumeration.ProductCode;
import com.tma.training.exception.ProductException;
import com.tma.training.product.entity.Product;
import com.tma.training.product.entity.Product.ProductField;
import com.tma.training.product.rest.CreateProduct;
import com.tma.training.product.rest.GetProduct;
import com.tma.training.product.rest.ListProduct;
import com.tma.training.product.rest.UpdateProduct;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * Product service implementation
 */
@ApplicationScoped
public class ProductService {

    public List<ListProduct.ResponseVO> getAll() {
        return ListProduct.toVOs(Product.listAll());
    }

    public GetProduct.ResponseVO getProduct(String productId) {
        Product product = Product.findById(new ObjectId(productId));
        if (product == null) {
            throw new ProductException(ProductCode.PRODUCT_NOT_FOUND, productId);
        }
        return GetProduct.toVO(product);
    }

    public CreateProduct.ResponseVO createProduct(CreateProduct.RequestVO productVO) {
        Product product = CreateProduct.toEntity(productVO);
        product.persist();
        return CreateProduct.toVO(product);
    }

    public UpdateProduct.ResponseVO updateProduct(String productId, UpdateProduct.RequestVO productVO) {
        Product product = Product.findById(new ObjectId(productId));
        if (product == null) {
            throw new ProductException(ProductCode.PRODUCT_NOT_FOUND, productId);
        }

        Document id = new Document("_id", new ObjectId(productId));

        Document updater = new Document();
        if (productVO.getName() != null && !productVO.getName().equals(product.getName())) {
            updater.append(ProductField.name, productVO.getName());
        }
        if (productVO.getDescription() != null && !productVO.getDescription().equals(product.getDescription())) {
            updater.append(ProductField.description, productVO.getDescription());
        }
        if (productVO.getPrice() != null && !productVO.getPrice().equals(product.getPrice())) {
            updater.append(ProductField.price, productVO.getPrice());
        }
        if (productVO.getManufacturer() != null && !productVO.getManufacturer().equals(product.getPrice())) {
            updater.append(ProductField.manufacturer, productVO.getManufacturer());
        }

        FindOneAndUpdateOptions queryOpts = new FindOneAndUpdateOptions();
        queryOpts.upsert(true);
        queryOpts.returnDocument(ReturnDocument.AFTER);
        Product updatedProduct = Product.<Product>mongoCollection().findOneAndUpdate(id, new Document("$set", updater), queryOpts);
        return UpdateProduct.toVO(updatedProduct);
    }

    public String deleteProduct(String productId) {
        boolean isSucceed = Product.deleteById(new ObjectId(productId));
        if (isSucceed) {
            return "Success";
        }
        return null;
    }
}
