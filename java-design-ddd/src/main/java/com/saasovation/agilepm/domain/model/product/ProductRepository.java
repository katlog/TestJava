package com.saasovation.agilepm.domain.model.product;

import name.katlog.dddimpl.chapter07_domainservice.Tenant;

import java.util.Collection;

public interface ProductRepository {

    public ProductId nextIdentity();

    public Collection<Product> allProductsOfTenant(Tenant aTenant);

    public Product productOfId(Tenant aTenant, ProductId aProductId);

    public void remove(Product aProduct);

    public void removeAll(Collection<Product> aProductCollection);

    public void save(Product aProduct);

    public void saveAll(Collection<Product> aProductCollection);
}
