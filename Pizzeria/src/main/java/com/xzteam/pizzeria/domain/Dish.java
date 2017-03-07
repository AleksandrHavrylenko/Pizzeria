package com.xzteam.pizzeria.domain;

import com.xzteam.pizzeria.domain.enums.DishType;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @Column(name = "dish_id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DishType type;

    @Column(name = "price")
    private Float price;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "image_url", length = 100)
    private String imageUrl;

    @OneToOne(mappedBy = "dish")
    private Item item;

    public Dish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", weight=" + weight +
                ", imageUrl='" + imageUrl + '\'' +
                ", item=" + item +
                '}';
    }
}
