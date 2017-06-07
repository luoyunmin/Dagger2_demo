package fzone.shboka.com.dagger2_demo.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import fzone.shboka.com.dagger2_demo.DI.compent.DaggerPresenterComponent;
import fzone.shboka.com.dagger2_demo.R;
import fzone.shboka.com.dagger2_demo.base.BaseActivity;
import fzone.shboka.com.dagger2_demo.domain.Shop;
import fzone.shboka.com.dagger2_demo.presenter.DataListPresenter;
import fzone.shboka.com.dagger2_demo.view.UserDataListView;
import fzone.shboka.com.dagger2_demo.view.adapter.DataListAdapter;

public class DataListActivity extends BaseActivity<DataListPresenter> implements UserDataListView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    DataListPresenter dataListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        initialize();
    }

    @Override
    protected DataListPresenter newPresenter() {
        DaggerPresenterComponent.builder().activityModule(getActivityModule()).build().inject(this);
        return dataListPresenter;
    }

    private void initialize() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        dataListPresenter.setView(this);
        dataListPresenter.loadData("1");
    }

    public void onItemClick(int position, Shop shop) {
        toastDelegate.showMsg(position + "==>" + shop.getName());
    }

    @Override
    public void renderUserList(final Collection<Shop> userCollection) {
        DataListAdapter adapter = new DataListAdapter(this, (ArrayList<Shop>) userCollection);
        adapter.setOnItemClickListener((parent, view, position, id) -> DataListActivity.this.onItemClick(position, ((ArrayList<Shop>) userCollection).get(position)));
        recyclerView.setAdapter(adapter);
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
    public void showRetry() {}

    @Override
    public void hideRetry() {}

    @Override
    public void showError(String message) {
        toastDelegate.showMsg(message);
    }

    @Override
    public Context getContext() {
        return this;
    }

}
