
package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="cinema")
public class Cinema implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 400)
    private String name;
    @Column(length = 20)
    private String owner;
    @Column(nullable = false )
    private Integer capacity;
    @Column(length = 400)    
    private String description;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonIgnoreProperties("cinemas")
    private Category category;
    
    @OneToMany(mappedBy = "cinema", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties({"cinema","client"})
    private List<Message> messages;
 
    @OneToMany(mappedBy = "cinema", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties({"cinema","client"})
    private List<Message> reservations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getReservations() {
        return reservations;
    }

    public void setReservations(List<Message> reservations) {
        this.reservations = reservations;
    }
    
}
