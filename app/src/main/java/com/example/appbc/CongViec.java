package com.example.appbc;

public class CongViec {
    private int img;
    private String name, ngay;

    public CongViec(int img, String name, String ngay) {
        this.img = img;
        this.name = name;
        this.ngay = ngay;
    }

    public CongViec() {

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
