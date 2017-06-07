package fzone.shboka.com.dagger2_demo.view;

import fzone.shboka.com.dagger2_demo.domain.User;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
public interface UserLoginView extends LoadDataView{
    void readUser(User user);
    void readMsg(String msg);
}