package com.codyb.videogamesalesapp.videogame;

import java.time.LocalDate;

public class VideoGameDTO {
    Integer id;
    String title;
    String console;
    String genre;
    String publisher;
    String developer;
    Double critic_score;
    Double total_sales;
    Double na_sales;
    Double jp_sales;
    Double pal_sales;
    Double other_sales;
    LocalDate release_date;
    LocalDate last_update;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setCritic_score(Double critic_score) {
        this.critic_score = critic_score;
    }

    public void setTotal_sales(Double total_sales) {
        this.total_sales = total_sales;
    }

    public void setNa_sales(Double na_sales) {
        this.na_sales = na_sales;
    }

    public void setJp_sales(Double jp_sales) {
        this.jp_sales = jp_sales;
    }

    public void setPal_sales(Double pal_sales) {
        this.pal_sales = pal_sales;
    }

    public void setOther_sales(Double other_sales) {
        this.other_sales = other_sales;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public void setLast_update(LocalDate last_update) {
        this.last_update = last_update;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getConsole() {
        return console;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public Double getCritic_score() {
        return critic_score;
    }

    public Double getTotal_sales() {
        return total_sales;
    }

    public Double getNa_sales() {
        return na_sales;
    }

    public Double getJp_sales() {
        return jp_sales;
    }

    public Double getPal_sales() {
        return pal_sales;
    }

    public Double getOther_sales() {
        return other_sales;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public LocalDate getLast_update() {
        return last_update;
    }

}
