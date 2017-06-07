package com.wtm.impl;


import com.wtm.FaceColor;

/**
 * Created by 王天明 on 2015/12/17 0017.
 * 红脸
 */

public class RedFaceColor implements FaceColor {

    @Override
    public void isWhatColr() {
        System.out.println("一张红色的脸,就像猴子屁股一样啦");
    }

    @Override
    public String getColor() {
        return "红色";
    }
}
