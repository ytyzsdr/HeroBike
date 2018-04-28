package com.danchexia.bikehero.myviews;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by seal on 16/4/6.
 */
public class AddressEntity implements Parcelable {

    private String address;

    private String area;
    private String district;
    private String name;

    public float latitude;
    public float longitude;

    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<AddressEntity> getCREATOR() {
        return CREATOR;
    }

    public AddressEntity() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected AddressEntity(Parcel in) {
        name = in.readString();
        address= in.readString();
        area = in.readString();
        longitude = in.readFloat();
        latitude = in.readFloat();
    }

    public static final Creator<AddressEntity> CREATOR = new Creator<AddressEntity>() {
        @Override
        public AddressEntity createFromParcel(Parcel in) {
            return new AddressEntity(in);
        }

        @Override
        public AddressEntity[] newArray(int size) {
            return new AddressEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(area);
        dest.writeFloat(longitude);
        dest.writeFloat(latitude);
    }

}
