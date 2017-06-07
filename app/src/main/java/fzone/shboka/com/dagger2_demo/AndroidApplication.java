package fzone.shboka.com.dagger2_demo;

import android.support.multidex.MultiDexApplication;

import fzone.shboka.com.dagger2_demo.DI.compent.ApplicationComponent;
import fzone.shboka.com.dagger2_demo.DI.compent.DaggerApplicationComponent;
import fzone.shboka.com.dagger2_demo.DI.module.ApplicationModule;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public class AndroidApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInject();
    }

    private void initInject(){
        this.applicationComponent =
                DaggerApplicationComponent.builder().
                        applicationModule(new ApplicationModule(this))
                        .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}