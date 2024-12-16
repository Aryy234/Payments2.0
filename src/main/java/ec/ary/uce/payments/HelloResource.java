package ec.ary.uce.payments;

import ec.ary.uce.jpa.PurchaseService;
import ec.ary.uce.jpaUtil.EntityManagerUtil;
import ec.ary.uce.util.*;
import ec.ary.uce.util.Record;
import ec.ary.uce.annotations.QualifierNotification;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/facturacion")
public class HelloResource {

    @Inject
    Record recordSingleton;

    @Inject
    @QualifierNotification("creditcard")
    CreditCardPayment creditCardPayment;

    @Inject
    @QualifierNotification("paypal")
    PaypalPayment paypalPayment;

    @Inject
    @QualifierNotification("bank")
    BankTransaction bankTransaction;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String facturacion(@PathParam("id") int userId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            PurchaseService purchaseService = new PurchaseService(em);
            List<Purchase> purchases = purchaseService.getPurchasesByUserId(userId);
            if (purchases.isEmpty()) {
                return "No purchases found for user ID: " + userId;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("--------------FACTURA--------------\n\n");
                sb.append("User: ").append(purchases.get(0).getUser().getName()).append("\n\n");
                for (Purchase purchase : purchases) {
                    sb.append("Product: ").append(purchase.getProduct().getName()).append("\n")
                            .append("Price: ").append(purchase.getProduct().getPrice()).append("\n")
                            .append("Quantity: ").append(purchase.getQuantity()).append("\n")
                            .append("Total: ").append(purchase.getTotal()).append("\n")
                            .append("Date: ").append(purchase.getDate()).append("\n")
                            .append("Status: ").append(purchase.getStatus()).append("\n\n");
                }
                sb.append("Total a pagar: ").append(purchases.stream().mapToDouble(Purchase::getTotal).sum()).append("\n");
                return sb.toString();
            }
        } finally {
            em.close();
        }
    }

}