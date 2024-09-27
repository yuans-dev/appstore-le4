package com.eggplanters.lib;

public class AppEntry {
    private String title;
    private String publisher;
    private String genre;
    private String description;
    private double star_rating;
    private int downloads;
    private String imageUrl;

    public AppEntry(String title, String publisher, String genre, String description, double star_rating,
            int downloads, String imageUrl) {
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.description = description;
        this.star_rating = star_rating;
        this.downloads = downloads;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStar_rating() {
        return star_rating;
    }

    public void setStar_rating(double star_rating) {
        this.star_rating = star_rating;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
