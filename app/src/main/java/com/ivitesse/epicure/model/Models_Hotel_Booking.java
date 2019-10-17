package com.ivitesse.epicure.model;

import androidx.annotation.Nullable;

public class Models_Hotel_Booking {
    private String placename;
    private String placelocation;
    private String days;
    private String star;
    private String placeimage;
    private String offer;
    private String review;
    private String likes;
    private int dimage;
    private String placeprices;
    private String daysleft;
    private String check_in, check_out, room_of_rooms, no_of_guest;

    public Models_Hotel_Booking(@Nullable String placename, @Nullable String check_in, @Nullable String check_out, @Nullable String no_of_guest,
                                @Nullable String room_of_rooms, @Nullable String placeprices, int dimage) {
        this.placename = placename;
        this.check_in = check_in;
        this.check_out = check_out;
        this.no_of_guest = no_of_guest;
        this.room_of_rooms = room_of_rooms;
        this.placeprices = placeprices;
        this.dimage = dimage;


    }

    public Models_Hotel_Booking(@Nullable String review, @Nullable String placename, @Nullable String star, int dimage, @Nullable String placeprices, @Nullable String offer) {
        this.review = review;
        this.placename = placename;
        this.offer = offer;
        this.dimage = dimage;
        this.star = star;
        this.placeprices = placeprices;
    }

    public Models_Hotel_Booking(@Nullable String placename, @Nullable String placelocation, @Nullable String star, @Nullable String likes, @Nullable String placeprices, int dimage) {
        this.placename = placename;
        this.placelocation = placelocation;
        this.likes = likes;
        this.dimage = dimage;
        this.star = star;
        this.placeprices = placeprices;
    }

    public Models_Hotel_Booking(@Nullable String placename, int dimage) {
        this.placename = placename;
        this.dimage = dimage;

    }

    public Models_Hotel_Booking(@Nullable String daysleft, @Nullable String placename, @Nullable String placelocation, @Nullable String days, int dimage, @Nullable String placeprices) {
        this.daysleft = daysleft;
        this.placename = placename;
        this.placelocation = placelocation;
        this.days = days;
        this.dimage = dimage;
        this.placeprices = placeprices;
    }

    public Models_Hotel_Booking(@Nullable String placename, @Nullable String placelocation, int dimage) {
        this.placename = placename;
        this.placelocation = placelocation;
        this.dimage = dimage;
    }

    public Models_Hotel_Booking(@Nullable String placename, @Nullable String placelocation, @Nullable String star, @Nullable String days, int dimage) {
        this.placename = placename;
        this.placelocation = placelocation;
        this.star = star;
        this.days = days;
        this.dimage = dimage;
    }

    @Nullable
    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(@Nullable String check_in) {
        this.check_in = check_in;
    }

    @Nullable
    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(@Nullable String check_out) {
        this.check_out = check_out;
    }

    @Nullable
    public String getRoom_of_rooms() {
        return room_of_rooms;
    }

    public void setRoom_of_rooms(@Nullable String room_of_rooms) {
        this.room_of_rooms = room_of_rooms;
    }

    @Nullable
    public String getNo_of_guest() {
        return no_of_guest;
    }

    public void setNo_of_guest(@Nullable String no_of_guest) {
        this.no_of_guest = no_of_guest;
    }

    @Nullable
    public String getOffer() {
        return offer;
    }

    public void setOffer(@Nullable String offer) {
        this.offer = offer;
    }

    @Nullable
    public String getReview() {
        return review;
    }

    public void setReview(@Nullable String review) {
        this.review = review;
    }

    @Nullable
    public String getLikes() {
        return likes;
    }

    public void setLikes(@Nullable String likes) {
        this.likes = likes;
    }

    public int getDimage() {
        return dimage;
    }

    public void setDimage(int dimage) {
        this.dimage = dimage;
    }

    @Nullable
    public String getPlaceprices() {
        return placeprices;
    }

    public void setPlaceprices(@Nullable String placeprices) {
        this.placeprices = placeprices;
    }

    @Nullable
    public String getDaysleft() {
        return daysleft;
    }

    public void setDaysleft(@Nullable String daysleft) {
        this.daysleft = daysleft;
    }

    @Nullable
    public String getPlacename() {
        return placename;
    }

    public void setPlacename(@Nullable String placename) {
        this.placename = placename;
    }

    @Nullable
    public String getPlacelocation() {
        return placelocation;
    }

    public void setPlacelocation(@Nullable String placelocation) {
        this.placelocation = placelocation;
    }

    @Nullable
    public String getDays() {
        return days;
    }

    public void setDays(@Nullable String days) {
        this.days = days;
    }

    @Nullable
    public String getStar() {
        return star;
    }

    public void setStar(@Nullable String star) {
        this.star = star;
    }

    @Nullable
    public String getPlaceimage() {
        return placeimage;
    }

    public void setPlaceimage(@Nullable String placeimage) {
        this.placeimage = placeimage;
    }
}
