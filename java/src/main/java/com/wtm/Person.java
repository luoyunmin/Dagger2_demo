package com.wtm;

import dagger.Component;

@Component(modules = {FaceShapeModule.class})
public interface Person {
    PersonCreate getCreate();
}