package name.katlog.dddimpl.chapter04;

import javax.annotation.Resource;


/**
 * Created by fw on 2019/2/15
 */
public class Sample2 {

    @Path("/tenants/{tenantId}/products")
    public class ProductResource extends Resource {

        @javax.annotation.Resource
        private ProductService productService;
        //...
        @GET
        @Path("{productId}")
        @Produces({ "application/vnd.saasovation.projectovation+xml" })
        public Product getProduct(
                @PathParam("tenantId") String aTenantId,
                @PathParam("productId") String aProductId,
                @Context Request aRequest) {
            Product product = productService.product(aTenantId, aProductId);
            if (product == null) {
                // throw new WebApplicationException(
                //         Response.Status.NOT_FOUND);
            }
            return product; // serialized to XML using MessageBodyWriter
        }
        //...
    }

    @interface  Path{
        String value();
    }
    @interface GET{}
    @interface Produces{
        String[] value();
    }
    @interface PathParam{
        String value();
    }
    @interface Context{}

    class Resource{

    }
    class Product{}
    interface ProductService{
        Product product(String aTenantId, String aProductId);
    }

    class Request{}

    class WebApplicationException extends Exception{}
}
