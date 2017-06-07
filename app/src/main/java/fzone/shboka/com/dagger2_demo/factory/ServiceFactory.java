package fzone.shboka.com.dagger2_demo.factory;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import fzone.shboka.com.dagger2_demo.config.ServereConfig;
import fzone.shboka.com.dagger2_demo.dataConverter.FastJsonConverter;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public class ServiceFactory {

    private static final String endpoint = ServereConfig.HOST;
    private static RestAdapter restAdapter;

    static {
        OkHttpClient okHttpClient = new OkHttpClient();
        //设置5秒超时时间,默认15秒
        okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);

        restAdapter = new RestAdapter.Builder()
                //.setConverter(new FastJsonConverter())
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(endpoint)
                .build();
    }

    public static <T> T create(Class<T> service) {
        return restAdapter.create(service);
    }
}