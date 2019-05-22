package com.saasovation.agilepm.infrastructure.persistence;

import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fw on 2019/3/20
 */
// public class MongoProductRepository extends MongoRepository<Product>
//         implements ProductRepository {
//
//     public MongoProductRepository() {
//         super();
//         this.serializer(new BSONSerializer<Product>(Product.class));
//     }
//
//     public MongoProductRepository() {
//         super();
//         this.serializer(new BSONSerializer<Product>(Product.class));
//         Map<String, String> overrides = new HashMap<String, String>();
//         overrides.put("description", "summary");
//         this.serializer().registerOverrideMappings(overrides);
//     }
//
//     public ProductId nextIdentity() {
//         return new ProductId(new ObjectId().toString());
//     }
// }
