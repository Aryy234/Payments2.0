package ec.ary.uce.resource;

import ec.ary.uce.jpa.PurchaseService;
import ec.ary.uce.jpaUtil.EntityManagerUtil;
import ec.ary.uce.util.Products;
import ec.ary.uce.util.Purchase;
import ec.ary.uce.util.User;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("/purchase")
public class PurchaseResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/all")
    public String getAllPurchases() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            PurchaseService purchaseService = new PurchaseService(em);
            List<Purchase> purchases = purchaseService.getAllPurchases();
            if (purchases.isEmpty()) {
                return "No purchases found";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-5s %-20s %-20s %-10s %-10s %-15s %-10s\n", "ID", "Product", "User", "Quantity", "Total", "Date", "Status"));
                for (Purchase purchase : purchases) {
                    sb.append(String.format("%-5d %-20s %-20s %-10d %-10.2f %-15s %-10s\n",
                            purchase.getId(),
                            purchase.getProduct().getName(),
                            purchase.getUser().getName(),
                            purchase.getQuantity(),
                            purchase.getTotal(),
                            purchase.getDate(),
                            purchase.getStatus()));
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
    public String createPurchase() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            PurchaseService purchaseService = new PurchaseService(em);
            Products product1 = em.find(Products.class, 1); // Assuming product with ID 1 exists
            Products product2 = em.find(Products.class, 2); // Assuming product with ID 2 exists
            User user = em.find(User.class, 1); // Assuming user with ID 1 exists

            purchaseService.createPurchase(new Purchase(product1, user, 10, product1.getPrice() * 10, "2023-10-01", "Pending"));
            purchaseService.createPurchase(new Purchase(product2, user, 20, product2.getPrice() * 20, "2023-10-01", "Pending"));
            return "Purchases created successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/update")
    public String updatePurchase() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            PurchaseService purchaseService = new PurchaseService(em);
            Purchase purchase = em.find(Purchase.class, 1); // Assuming purchase with ID 1 exists
            purchaseService.updatePurchase(purchase);
            return "Purchase updated successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/delete")
    public String deletePurchase() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            PurchaseService purchaseService = new PurchaseService(em);
            Purchase purchase = em.find(Purchase.class, 1); // Assuming purchase with ID 1 exists
            purchaseService.deletePurchase(purchase);
            return "Purchase deleted successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getPurchaseById(@PathParam("id") int id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            PurchaseService purchaseService = new PurchaseService(em);
            Purchase purchase = purchaseService.getPurchaseById(id);
            if (purchase != null) {
                return "Purchase ID: " + purchase.getId() + "\n" +
                        "Product: " + purchase.getProduct().getName() + "\n" +
                        "User: " + purchase.getUser().getName() + "\n" +
                        "Quantity: " + purchase.getQuantity() + "\n" +
                        "Total Price: " + purchase.getTotal() + "\n" +
                        "Date: " + purchase.getDate() + "\n" +
                        "Status: " + purchase.getStatus();
            } else {
                return "Purchase not found";
            }
        } finally {
            em.close();
        }
    }
}
