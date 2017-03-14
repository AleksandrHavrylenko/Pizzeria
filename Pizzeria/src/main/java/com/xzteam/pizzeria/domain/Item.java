package com.xzteam.pizzeria.domain;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    private Long id;

    @Column(name = "price")
    private Float price;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @OneToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", price=" + price +
                ", bucket=" + bucket +
                '}';
    }
}