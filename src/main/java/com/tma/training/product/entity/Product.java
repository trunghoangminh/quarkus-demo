package com.tma.training.product.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Data
@RegisterForReflection
@MongoEntity(collection = "products")
@FieldNameConstants(innerTypeName = "ProductField")
public class Product extends PanacheMongoEntityBase {
    private ObjectId id;
    private String name;
    private String description;
    private BigDecimal price;
    private String manufacturer;
}
