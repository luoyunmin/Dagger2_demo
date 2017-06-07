package fzone.shboka.com.dagger2_demo.presenter;

import fzone.shboka.com.dagger2_demo.view.UserLoginView;

/**
 * Created by 王天明 on 2015/12/23 0023.
 */
public interface MainPresenter extends Presenter {
    void setView(UserLoginView userLoginView);
    void login(String name, String pwd);
}
