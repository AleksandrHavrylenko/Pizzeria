package com.xzteam.pizzeria.domain;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "client_id")
    private Long id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "pass_hash", length = 50)
    private String passHash;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "phone", length = 11, unique = true)
    private String phone;

    @Column(name = "spent_money")
    private Float spentMoney;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bucket> buckets;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pizza> pizzas;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(Float spentMoney) {
        this.spentMoney = spentMoney;
    }

    public List<Bucket> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", passHash='" + passHash + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", spentMoney=" + spentMoney +
                '}';
    }
}