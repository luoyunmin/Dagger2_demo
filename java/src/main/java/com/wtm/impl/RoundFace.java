package com.wtm.impl;

import com.wtm.FaceColor;
import com.wtm.FaceShape;

/**
 * Created by 王天明 on 2015/12/17 0017.
 * 圆脸
 */
public class RoundFace implements FaceShape {

    private FaceColor faceColor;

    public RoundFace(FaceColor faceColor) {
        this.faceColor = faceColor;
    }

    @Override
    public void shape() {
        System.out.println("好大一张"+faceColor.getColor()+"圆脸呦");
    }
}
