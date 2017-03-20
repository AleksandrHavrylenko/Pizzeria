package com.xzteam.pizzeria.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "pizza")
public class Pizza implements Serializable {

    @Id
    @Column(name = "pizza_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(mappedBy = "pizzas")
    private List<Bucket> buckets;

    @ManyToMany
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Pizza() {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}