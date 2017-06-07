package fzone.shboka.com.dagger2_demo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import fzone.shboka.com.dagger2_demo.AndroidApplication;
import fzone.shboka.com.dagger2_demo.DI.compent.ActivityComponent;
import fzone.shboka.com.dagger2_demo.DI.compent.ApplicationComponent;
import fzone.shboka.com.dagger2_demo.DI.compent.DaggerActivityComponent;
import fzone.shboka.com.dagger2_demo.DI.compent.DaggerPresenterComponent;
import fzone.shboka.com.dagger2_demo.DI.module.ActivityModule;
import fzone.shboka.com.dagger2_demo.delegate.ProgressDialogDelegate;
import fzone.shboka.com.dagger2_demo.delegate.ToastDelegate;
import fzone.shboka.com.dagger2_demo.presenter.Presenter;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity {

    private ActivityComponent component;
    @Inject
    protected ToastDelegate toastDelegate;
    @Inject
    protected ProgressDialogDelegate proDelegate;

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = newPresenter();
    }

    protected abstract P newPresenter();

    public void showProDialog() {
        proDelegate.show();
    }

    public void hideProDialog() {
        proDelegate.hide();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityComponent injectActivityCompinent() {
        return DaggerActivityComponent.builder().activityModule(getActivityModule()).build();
    }

    public ActivityComponent getComponent() {
        if (component == null) {
            component = injectActivityCompinent();
        }
        return component;
    }

    public ToastDelegate getToastDelegate() {
        return toastDelegate;
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        component = null;
        if (presenter != null) {
            presenter.destroy();
        }
    }
}