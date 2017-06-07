package fzone.shboka.com.dagger2_demo.view;

import java.util.Collection;

import fzone.shboka.com.dagger2_demo.domain.Shop;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
public interface UserDataListView extends LoadDataView {
    void renderUserList(Collection<Shop> userCollection);
}