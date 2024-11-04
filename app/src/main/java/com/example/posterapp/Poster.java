package com.example.posterapp;

/**
 * This is the Poster model for poster objects
 */
public class Poster {
    String name, createdBy, description;
    int image;
    Boolean isSelected = false;
    float rating;

    /**
     * This is the Poster constructor
     * @param name The name of the poster
     * @param createdBy The directors of the poster
     * @param description The description of the poster
     * @param image The image of the poster
     * @param rating The rating of the poster
     */
    public Poster(String name, String createdBy, String description, int image, float rating) {
        this.name = name;
        this.createdBy = createdBy;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }
}
