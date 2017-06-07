package com.wtm.impl;

import com.wtm.FaceColor;

/**
 * Created by 王天明 on 2015/12/17 0017.
 */
public class WhiteFaceColor implements FaceColor {
    @Override
    public void isWhatColr() {
        System.out.println("这是一个小白脸");
    }

    @Override
    public String getColor() {
        return "白里透红";
    }
}
