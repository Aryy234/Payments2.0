package ec.ary.uce.util;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total")
    private double total;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;

    public Purchase() {
    }

    public Purchase(int id, Products product, User user, int quantity, double total, String date, String status) {
        this.id = id;
        this.product = product;
        this.user = user;

        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public Purchase(Products product, User user, int quantity, double total, String date, String status) {
        this.product = product;
        this.user = user;

        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", quantity=" + quantity +
                ", total=" + total +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}