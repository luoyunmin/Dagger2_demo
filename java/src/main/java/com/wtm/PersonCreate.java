package com.wtm;

import javax.inject.Inject;

public class PersonCreate {

    private final FaceShape faceShape;

    @Inject public PersonCreate(FaceShape faceShape) {
        this.faceShape = faceShape;
    }

    public void say(){
        faceShape.shape();
    }
}
