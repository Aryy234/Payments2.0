package ec.ary.uce.resource;

import ec.ary.uce.jpa.ProductsService;
import ec.ary.uce.jpaUtil.EntityManagerUtil;
import ec.ary.uce.util.Products;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductsResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/all")
    public String getAllProducts() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            ProductsService productsService = new ProductsService(em);
            List<Products> products = productsService.getAllProducts();
            if (products.isEmpty()) {
                return "No products found";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-20s %-10s %-10s\n", "Name", "Price", "Stock"));
                for (Products product : products) {
                    sb.append(String.format("%-20s %-10.2f %-10d\n", product.getName(), product.getPrice(), product.getStock()));
                }
                return sb.toString();
            }
        } finally {
            em.close();
        }
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create")
    public String createProduct() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            ProductsService productsService = new ProductsService(em);
            productsService.createProduct(new Products("product1", 10.0, 100));
            productsService.createProduct(new Products("product2", 20.0, 200));
            return "Products created successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/update")
    public String updateProduct() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            ProductsService productsService = new ProductsService(em);
            productsService.updateProduct(new Products(1, "product0", 5.0, 50));
            return "Product updated successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/delete")
    public String deleteProduct() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            ProductsService productsService = new ProductsService(em);
            productsService.deleteProduct(1);
            return "Product deleted successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getProductById(@PathParam("id") int id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            ProductsService productsService = new ProductsService(em);
            Products product = productsService.getProductById(id);
            if (product != null) {
                return String.format("ID: %d\nName: %s\nPrice: %.2f\nStock: %d",
                        product.getId(), product.getName(), product.getPrice(), product.getStock());
            } else {
                return "Product not found";
            }
        } finally {
            em.close();
        }
    }

}
