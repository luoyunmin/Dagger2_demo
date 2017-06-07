package fzone.shboka.com.dagger2_demo.DI.compent;

import dagger.Component;
import fzone.shboka.com.dagger2_demo.DI.ActivityScope;
import fzone.shboka.com.dagger2_demo.DI.module.ActivityModule;
import fzone.shboka.com.dagger2_demo.DI.module.PresenterModule;
import fzone.shboka.com.dagger2_demo.DI.module.ServerModule;
import fzone.shboka.com.dagger2_demo.view.activity.DataListActivity;
import fzone.shboka.com.dagger2_demo.view.activity.MainActivity;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
@ActivityScope
@Component(modules = {ActivityModule.class,ServerModule.class,PresenterModule.class})
public interface PresenterComponent extends ActivityComponent {
    void inject(MainActivity activity);
    void inject(DataListActivity activity);
}