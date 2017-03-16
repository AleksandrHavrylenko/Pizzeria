package com.xzteam.pizzeria.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "bucket")
public class Bucket {

    @Id
    @Column(name = "bucket_id")
    private Long id;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "price")
    private Float price;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(name = "bucket_dish",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishes;

    @ManyToMany
    @JoinTable(name = "bucket_pizza",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> pizzas;

    public Bucket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}