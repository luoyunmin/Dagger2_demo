package fzone.shboka.com.dagger2_demo.presenter;

import fzone.shboka.com.dagger2_demo.view.UserDataListView;

/**
 * Created by 王天明 on 2015/12/23 0023.
 */
public interface DataListPresenter extends Presenter {
    void setView(UserDataListView dataListView);
    void loadData(String id);
}
