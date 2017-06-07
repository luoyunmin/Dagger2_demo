package com.wtm;

import com.wtm.impl.WhiteFaceColor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 王天明 on 2015/12/17 0017.
 */
@Module
public class FaceColorModule {
    @Provides FaceColor providesFaceColor() {
        return new WhiteFaceColor();
    }
}
