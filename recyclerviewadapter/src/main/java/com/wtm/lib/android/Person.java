package com.wtm.lib.android;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
public class Person {

    private String name;
    private String imgUrl;

    public Person() {
    }

    public Person(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
