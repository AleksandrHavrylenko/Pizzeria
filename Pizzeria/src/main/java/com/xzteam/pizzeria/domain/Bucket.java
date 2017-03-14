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

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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