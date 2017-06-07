package fzone.shboka.com.dagger2_demo.DI.module;


import dagger.Module;
import dagger.Provides;
import fzone.shboka.com.dagger2_demo.DI.ActivityScope;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;
import fzone.shboka.com.dagger2_demo.delegate.ToastDelegate;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */

@Module
public class ToastModule {
    @ActivityScope
    @Provides
    ToastDelegate providesDelegate(BaseActivity activity) {
        return new ToastDelegate(activity);
    }
}
