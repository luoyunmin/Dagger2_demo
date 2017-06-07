package fzone.shboka.com.dagger2_demo.presenter.impl;


import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;

import fzone.shboka.com.dagger2_demo.domain.User;
import fzone.shboka.com.dagger2_demo.presenter.MainPresenter;
import fzone.shboka.com.dagger2_demo.service.UserService;
import fzone.shboka.com.dagger2_demo.view.UserLoginView;
import fzone.shboka.com.dagger2_demo.view.activity.DataListActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public class MainPresenterImpl implements MainPresenter {

    private UserLoginView userLoginView;
    private UserService userService;
    private Observable<User> userObservable;

    @Inject
    public MainPresenterImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setView(UserLoginView userLoginView) {
        this.userLoginView = userLoginView;
    }
    @Override
    public void login(String name, String pwd) {
        userObservable = loginUser(name, pwd);
        userObservable.doOnSubscribe(userLoginView::showLoading).observeOn(AndroidSchedulers.mainThread()).subscribe(user -> {
            userLoginView.readUser(user);
            userLoginView.getContext().startActivity(new Intent(userLoginView.getContext(), DataListActivity.class));
            userLoginView.hideLoading();        }, throwable -> {

            userLoginView.readMsg(throwable.getMessage());
            throwable.printStackTrace();
        }, userLoginView::hideLoading);
    }

    private Observable<User> loginUser(String name, String pwd) {
        return userService.loginUser(name, pwd);
    }

    @Override
    public void resume() {
        Log.d("W", "resume");
    }

    @Override
    public void pause() {
        Log.d("W", "pause");
    }

    @Override
    public void destroy() {
        Log.d("W", "destroy");
        if (userObservable != null) {
            userObservable.unsubscribeOn(AndroidSchedulers.mainThread());
        }
    }
}
