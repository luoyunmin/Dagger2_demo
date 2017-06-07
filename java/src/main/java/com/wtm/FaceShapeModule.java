package com.wtm;

import com.wtm.impl.SquareFace;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 王天明 on 2015/12/17 0017.
 */
@Module(includes = {FaceColorModule.class})
public class FaceShapeModule {
    @Provides FaceShape providesFaceShape(FaceColor color) {
        return new SquareFace(color);
    }
}
