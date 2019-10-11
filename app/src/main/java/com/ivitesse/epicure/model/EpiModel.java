package com.ivitesse.epicure.model;

import androidx.annotation.NonNull;

public class EpiModel {
    private String name, description;

    public EpiModel(String title, String description, String offer_from, String offer_valid) {
        this.title = title;
        this.description = description;
        this.offer_from = offer_from;
        this.offer_valid = offer_valid;
    }

    public EpiModel() {

    }

    public EpiModel(String title) {
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_desc() {
        return title_desc;
    }

    public void setTitle_desc(String title_desc) {
        this.title_desc = title_desc;
    }

    public String getOffer_valid() {
        return offer_valid;
    }

    public void setOffer_valid(String offer_valid) {
        this.offer_valid = offer_valid;
    }

    public String getOffer_from() {
        return offer_from;
    }

    public void setOffer_from(String offer_from) {
        this.offer_from = offer_from;
    }

    private String title, title_desc, offer_valid, offer_from;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
