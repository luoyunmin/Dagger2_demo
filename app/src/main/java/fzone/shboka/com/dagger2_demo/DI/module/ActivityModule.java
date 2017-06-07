package fzone.shboka.com.dagger2_demo.DI.module;


import dagger.Module;
import dagger.Provides;
import fzone.shboka.com.dagger2_demo.DI.ActivityScope;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;


/**
 * Created by 王天明 on 2015/12/18 0018.
 */
@Module(includes = {ToastModule.class,ProgressDialogModule.class})
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }
    @ActivityScope
    @Provides
    BaseActivity activity() {
        return this.activity;
    }
}
