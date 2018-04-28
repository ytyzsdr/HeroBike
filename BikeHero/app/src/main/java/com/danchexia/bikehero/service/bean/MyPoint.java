package com.danchexia.bikehero.service.bean;

/**
 * Created by farley on 17/5/27.
 * description:
 */

public  class MyPoint{
    private Double x;
    private Double y;

    public MyPoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
