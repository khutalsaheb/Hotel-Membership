package com.ivitesse.epicure.model;

import androidx.annotation.NonNull;

public class EpiModel {
    private String name, description;
    private String dt;
    private String yeass;
    private String saving;
    private String original_value;
    private String discount_value;
    private String date_text;

    public EpiModel(String dt, String yeass, String title, String title_desc, String original_value, String discount_value, String saving) {
        this.dt = dt;
        this.yeass = yeass;
        this.title = title;
        this.title_desc = title_desc;
        this.original_value = original_value;
        this.discount_value = discount_value;
        this.saving = saving;


    }

    public String getSaving() {
        return saving;
    }

    public void setSaving(String saving) {
        this.saving = saving;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getYeass() {
        return yeass;
    }

    public void setYeass(String yeass) {
        this.yeass = yeass;
    }

    public String getOriginal_value() {
        return original_value;
    }

    public void setOriginal_value(String original_value) {
        this.original_value = original_value;
    }

    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(String discount_value) {
        this.discount_value = discount_value;
    }

    public String getDate_text() {
        return date_text;
    }

    public void setDate_text(String date_text) {
        this.date_text = date_text;
    }

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
