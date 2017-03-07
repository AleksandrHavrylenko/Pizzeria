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
    private Long pizzaId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "pizza")
    private Item item;

    @ManyToMany
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Pizza() {
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaId=" + pizzaId +
                ", name='" + name + '\'' +
                '}';
    }
}