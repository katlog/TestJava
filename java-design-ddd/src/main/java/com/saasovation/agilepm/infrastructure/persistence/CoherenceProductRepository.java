package com.saasovation.agilepm.infrastructure.persistence;

import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.ProductRepository;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import java.util.*;

/**
 * Created by fw on 2019/3/20
 */
public class CoherenceProductRepository implements ProductRepository {

    private final HashMap<Tenant, NamedCache> caches;

    public CoherenceProductRepository() {
        super();
        this.caches = new HashMap<Tenant,NamedCache>();
    }
    private synchronized NamedCache cache(Tenant tenant) {
        NamedCache cache = this.caches.get(tenant);
        if (cache == null) {
            cache = CacheFactory.getCache(
                    "agilepm.Product." + tenant.id(),
                    Product.class.getClassLoader());
            this.caches.put(tenant, cache);
        }
        return cache;
    }

    @Override
    public ProductId nextIdentity() {
        return new ProductId(
                java.util.UUID.randomUUID()
                        .toString()
                        .toUpperCase());
    }


    @Override
    public Product productOfId(TenantId aTenant, ProductId aProductId) {
        return null;
    }


    @Override
    public void remove(Product aProduct) {
        this.cache(aProduct.tenant()).remove(this.idOf(aProduct));
    }

    @Override
    public void removeAll(Collection<Product> aProductCollection) {
        for (Product product : aProductCollection) {
            this.remove(product);
        }
    }

    @Override
    public void save(Product aProduct) {
        this.cache(aProduct.tenant())
                .put(this.idOf(aProduct), aProduct);

    }

    @Override
    public void saveAll(Collection<Product> aProductCollection) {
        if (!aProductCollection.isEmpty()) {
            TenantId tenantId = null;
            Map<String, Product> productsMap = new HashMap<String, Product>(aProductCollection.size());
            for (Product product : aProductCollection) {
                if (tenantId == null) {
                    tenantId = product.tenantId();
                }
                productsMap.put(this.idOf(product), product);
            }

        }
    }

    @Override
    public void add(Product product) {

    }

    private String idOf(Product aProduct) {
        return this.idOf(aProduct.productId());
    }

    private String idOf(ProductId aProductId) {
        return aProductId.id();
    }


    @SuppressWarnings("unchecked")
    @Override
    public Collection<Product> allProductsOfTenant(Tenant aTenant) {
        Set<Map.Entry<String, Product>> entries = this.cache(aTenant).entrySet();
        Collection<Product> products = new HashSet<Product>(entries.size());
        for (Map.Entry<String, Product> entry : entries) {
            products.add(entry.getValue());
        }
        return products;
    }
    @Override
    public Product productOfId(Tenant aTenant, ProductId aProductId) {
        return (Product) this.cache(aTenant).get(this.idOf(aProductId));
    }
}
