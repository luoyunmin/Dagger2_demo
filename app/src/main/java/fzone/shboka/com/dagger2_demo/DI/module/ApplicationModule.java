package fzone.shboka.com.dagger2_demo.DI.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fzone.shboka.com.dagger2_demo.AndroidApplication;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
}
