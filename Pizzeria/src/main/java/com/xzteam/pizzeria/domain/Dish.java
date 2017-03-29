package com.xzteam.pizzeria.domain;

import com.xzteam.pizzeria.domain.enums.DishType;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @Column(name = "dish_id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DishType type;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @ManyToMany(mappedBy = "dishes")
    private List<Bucket> buckets;

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

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
