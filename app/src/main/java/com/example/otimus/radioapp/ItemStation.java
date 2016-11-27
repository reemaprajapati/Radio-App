package com.example.otimus.radioapp;

import java.io.Serializable;

/**
 * Created by Otimus on 10/25/2016.
 */
public class ItemStation implements Serializable{
    int id;
    int image;
    String name;
    String frequency;

    public ItemStation(int id,int image, String name, String frequency) {
        this.id = id;
        this.image=image;
        this.name = name;
        this.frequency = frequency;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
