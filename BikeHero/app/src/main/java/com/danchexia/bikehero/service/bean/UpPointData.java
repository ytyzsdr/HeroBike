package com.danchexia.bikehero.service.bean;

import java.util.Date;

/**
 * Created by farley on 17/5/27.
 * description:
 */

public class UpPointData {
    public UpPointData(Date dateTime, MyPoint mPoint) {
        this.dateTime = dateTime;
        this.mPoint = mPoint;
    }

    private Date dateTime;
    private MyPoint mPoint;


    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public MyPoint getmPoint() {
        return mPoint;
    }

    public void setmPoint(MyPoint mPoint) {
        this.mPoint = mPoint;
    }
}
