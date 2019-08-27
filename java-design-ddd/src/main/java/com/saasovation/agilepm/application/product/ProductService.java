package com.saasovation.agilepm.application.product;

import com.saasovation.agilepm.domain.model.product.*;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fw on 2019/8/26
 */
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(rollbackFor = Exception.class)
    public String newProduct(
            String aTenantId,String aProductName,String aProductDescription) {
        Product product =
                new Product(
                        new TenantId(aTenantId),
                        this.productRepository.nextIdentity(),
                        "My Product",
                        "This is the description of my product.",
                        new ProductDiscussion(
                                new DiscussionDescriptor(
                                        DiscussionDescriptor.UNDEFINED_ID),
                                DiscussionAvailability.NOT_REQUESTED));

        this.productRepository.add(product);
        return product.productId().id();
    }
}
