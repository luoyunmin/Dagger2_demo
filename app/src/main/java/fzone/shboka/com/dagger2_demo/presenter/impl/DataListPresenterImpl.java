package fzone.shboka.com.dagger2_demo.presenter.impl;


import com.bumptech.glide.Glide;

import javax.inject.Inject;

import fzone.shboka.com.dagger2_demo.presenter.DataListPresenter;
import fzone.shboka.com.dagger2_demo.presenter.Presenter;
import fzone.shboka.com.dagger2_demo.service.ShopService;
import fzone.shboka.com.dagger2_demo.view.UserDataListView;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public class DataListPresenterImpl implements DataListPresenter {

    private ShopService shopService;
    private UserDataListView dataListView;

    @Inject
    public DataListPresenterImpl(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public void setView(UserDataListView dataListView) {
        this.dataListView = dataListView;
    }
    @Override
    public void loadData(String id) {
        shopService
                .getShopList(id)
                .doOnSubscribe(dataListView::showLoading)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(dataListView::renderUserList, throwable -> {
            dataListView.hideLoading();
            dataListView.showError("加载数据错误:" + throwable.getMessage());
        }, dataListView::hideLoading);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        Glide.get(dataListView.getContext()).clearMemory();
    }
}