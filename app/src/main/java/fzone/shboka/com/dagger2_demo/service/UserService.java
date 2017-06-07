package fzone.shboka.com.dagger2_demo.service;


import fzone.shboka.com.dagger2_demo.domain.User;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public interface UserService {
    @GET("/userLogin?name=filters[0]&pwd=filters[1]")
    Observable<User> loginUser(@Query("filters[0]") String name,@Query("filters[1]") String email);
}