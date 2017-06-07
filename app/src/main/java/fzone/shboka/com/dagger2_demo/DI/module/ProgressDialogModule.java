package fzone.shboka.com.dagger2_demo.DI.module;

import dagger.Module;
import dagger.Provides;
import fzone.shboka.com.dagger2_demo.DI.ActivityScope;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;
import fzone.shboka.com.dagger2_demo.delegate.ProgressDialogDelegate;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
@Module
public class ProgressDialogModule {
    @ActivityScope
    @Provides
    ProgressDialogDelegate providesDelegate(BaseActivity activity) {
        return new ProgressDialogDelegate(activity, "提示", "正在加载,请稍候...");
    }
}
