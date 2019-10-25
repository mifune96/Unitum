package com.timbabe.unitum;

import androidx.annotation.NonNull;

public class CarSpec {
    private String carName;
    private int carLength;
    private int carWidth;
    private int carHeight;
    private int carEmptyWeight;
    private int carMaxWeight;

    public CarSpec(String carName, int carLength, int carWidth, int carHeight, int carEmptyWeight, int carMaxWeight) {
        this.carName = carName;
        this.carLength = carLength;
        this.carWidth = carWidth;
        this.carHeight = carHeight;
        this.carEmptyWeight = carEmptyWeight;
        this.carMaxWeight = carMaxWeight;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarLength() {
        return carLength;
    }

    public void setCarLength(int carLength) {
        this.carLength = carLength;
    }

    public int getCarWidth() {
        return carWidth;
    }

    public void setCarWidth(int carWidth) {
        this.carWidth = carWidth;
    }

    public int getCarHeight() {
        return carHeight;
    }

    public void setCarHeight(int carHeight) {
        this.carHeight = carHeight;
    }

    public int getCarEmptyWeight() {
        return carEmptyWeight;
    }

    public void setCarEmptyWeight(int carEmptyWeight) {
        this.carEmptyWeight = carEmptyWeight;
    }

    public int getCarMaxWeight() {
        return carMaxWeight;
    }

    public void setCarMaxWeight(int carMaxWeight) {
        this.carMaxWeight = carMaxWeight;
    }

    @NonNull
    @Override
    public String toString() {
        return carName;
    }
}
