package fzone.shboka.com.dagger2_demo.service;

import java.util.ArrayList;

import fzone.shboka.com.dagger2_demo.domain.Shop;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public interface ShopService {
    @GET("/shopList?userId=id")
    Observable<ArrayList<Shop>> getShopList(@Query("id") String id);
}
