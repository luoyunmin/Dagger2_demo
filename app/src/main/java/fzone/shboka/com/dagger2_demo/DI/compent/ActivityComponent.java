package fzone.shboka.com.dagger2_demo.DI.compent;

import dagger.Component;
import fzone.shboka.com.dagger2_demo.DI.ActivityScope;
import fzone.shboka.com.dagger2_demo.DI.module.ActivityModule;
import fzone.shboka.com.dagger2_demo.DI.module.ApplicationModule;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;
import fzone.shboka.com.dagger2_demo.delegate.ProgressDialogDelegate;
import fzone.shboka.com.dagger2_demo.delegate.ToastDelegate;
import fzone.shboka.com.dagger2_demo.presenter.Presenter;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
@ActivityScope
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    ToastDelegate getToastDelegate();
    ProgressDialogDelegate getProgressDialogDelegate();
    BaseActivity getActivity();
}