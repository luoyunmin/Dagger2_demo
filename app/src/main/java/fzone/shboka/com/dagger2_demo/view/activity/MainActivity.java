package fzone.shboka.com.dagger2_demo.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fzone.shboka.com.dagger2_demo.DI.compent.DaggerPresenterComponent;
import fzone.shboka.com.dagger2_demo.R;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;
import fzone.shboka.com.dagger2_demo.domain.User;
import fzone.shboka.com.dagger2_demo.presenter.MainPresenter;
import fzone.shboka.com.dagger2_demo.view.UserLoginView;

public class MainActivity extends BaseActivity<MainPresenter> implements UserLoginView {

    @Bind(R.id.textView)
    TextView textView;
    /**
     * 懒的搞输入
     * 默认账号密码1
     */
    private String name = "1";
    private String pwd = "1";
    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();
    }

    @Override
    protected MainPresenter newPresenter() {
        DaggerPresenterComponent.builder().activityModule(getActivityModule()).build().inject(this);
        return mainPresenter;
    }

    private void initialize() {
        mainPresenter.setView(this);
    }

    @OnClick(R.id.loginBtn)
    public void loginClick(View v) {
        mainPresenter.login(name, pwd);
    }

    @Override
    public void readUser(User user) {
        textView.setText(user.toString());
    }

    @Override
    public void readMsg(String msg) {
        textView.setText(msg);
    }

    @Override
    public void showLoading() {
        showProDialog();
    }

    @Override
    public void hideLoading() {
        hideProDialog();
    }

    @Override
    public void showRetry() {
    }

    @Override
    public void hideRetry() {
    }

    @Override
    public void showError(String message) {
        toastDelegate.showMsg(message);
    }

    @Override
    public Context getContext() {
        return this;
    }
}