package com.ivitesse.epicure.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EpiModel {
    private String name, description;
    private String dt;
    private String yeass;
    private String saving;
    private String original_value;
    private String discount_value;
    private String date_text;
    private String rating;

    @NonNull
    public String getRating() {
        return rating;
    }

    public void setRating(@NonNull String rating) {
        this.rating = rating;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    private String profile_pic;
    private String review;
    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    private String address;

    public EpiModel(@Nullable String dt, @Nullable String yeass, @Nullable String title, @Nullable String title_desc, @Nullable String original_value, @Nullable String discount_value, @Nullable String saving) {
        this.dt = dt;
        this.yeass = yeass;
        this.title = title;
        this.title_desc = title_desc;
        this.original_value = original_value;
        this.discount_value = discount_value;
        this.saving = saving;


    }

    public EpiModel(@Nullable String dt, @Nullable String yeass, @Nullable String title, @Nullable String title_desc, @Nullable String original_value, @Nullable String discount_value, @Nullable String saving, @Nullable String address) {
        this.dt = dt;
        this.yeass = yeass;
        this.title = title;
        this.title_desc = title_desc;
        this.original_value = original_value;
        this.discount_value = discount_value;
        this.saving = saving;
        this.address = address;

    }

    @Nullable
    public String getSaving() {
        return saving;
    }

    public void setSaving(@Nullable String saving) {
        this.saving = saving;
    }

    @Nullable
    public String getDt() {
        return dt;
    }

    public void setDt(@Nullable String dt) {
        this.dt = dt;
    }

    @Nullable
    public String getYeass() {
        return yeass;
    }

    public void setYeass(@Nullable String yeass) {
        this.yeass = yeass;
    }

    @Nullable
    public String getOriginal_value() {
        return original_value;
    }

    public void setOriginal_value(@Nullable String original_value) {
        this.original_value = original_value;
    }

    @Nullable
    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(@Nullable String discount_value) {
        this.discount_value = discount_value;
    }

    @Nullable
    public String getDate_text() {
        return date_text;
    }

    public void setDate_text(@Nullable String date_text) {
        this.date_text = date_text;
    }

    public EpiModel(@Nullable String title, @Nullable String description, @Nullable String offer_from, @Nullable String offer_valid) {
        this.title = title;
        this.description = description;
        this.offer_from = offer_from;
        this.offer_valid = offer_valid;
    }

    public EpiModel() {

    }

    public EpiModel(@Nullable String title) {
        this.title = title;

    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getTitle_desc() {
        return title_desc;
    }

    public void setTitle_desc(@Nullable String title_desc) {
        this.title_desc = title_desc;
    }

    @Nullable
    public String getOffer_valid() {
        return offer_valid;
    }

    public void setOffer_valid(@Nullable String offer_valid) {
        this.offer_valid = offer_valid;
    }

    @Nullable
    public String getOffer_from() {
        return offer_from;
    }

    public void setOffer_from(@Nullable String offer_from) {
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
