package com.rest.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID if needed
    private int id;
    
    private String text;
    private String details;

    // Default Constructor
    public Message() {
    }

    // Parameterized Constructor
    public Message(int id, String text, String details) {
        this.id = id;
        this.text = text;
        this.details = details;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
