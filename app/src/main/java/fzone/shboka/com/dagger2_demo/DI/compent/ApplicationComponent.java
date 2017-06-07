package fzone.shboka.com.dagger2_demo.DI.compent;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import fzone.shboka.com.dagger2_demo.DI.ActivityScope;
import fzone.shboka.com.dagger2_demo.DI.module.ApplicationModule;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
@Component(modules = {ApplicationModule.class})
@Singleton
public interface ApplicationComponent {
    Context context();
}