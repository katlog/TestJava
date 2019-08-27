package com.saasovation.agilepm.domain.model.product;

import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.identityaccess.domain.model.identity.Tenant;

import java.util.Collection;

public interface ProductRepository {

    public ProductId nextIdentity();

    public Collection<Product> allProductsOfTenant(Tenant aTenant);

    public Product productOfId(Tenant aTenant, ProductId aProductId);
    public Product productOfId(TenantId aTenant, ProductId aProductId);

    public void remove(Product aProduct);

    public void removeAll(Collection<Product> aProductCollection);

    public void save(Product aProduct);

    public void saveAll(Collection<Product> aProductCollection);

    void add(Product product);
}
