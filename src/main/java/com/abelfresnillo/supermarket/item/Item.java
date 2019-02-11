package com.abelfresnillo.supermarket.item;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

@Document
@Data
public class Item {
    private String id;
    private String model;
    private String name;
    private double price;
    private Status status;
    private Date createdDate;
    private Date publishedDate;

    public Item(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = 0.00;
        this.status = Status.DRAFT;
        this.createdDate = new Date();
    }

    public void publish(String model, double price) {
        Assert.isTrue(price > 0.00, "The item price must be greater than 0.00");
        Assert.notNull(model, "A model must be provided");
        this.model = model;
        this.price = price;
        this.status = Status.PUBLISHED;
        this.publishedDate = new Date();
    }
}
