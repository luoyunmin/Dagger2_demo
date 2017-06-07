package fzone.shboka.com.dagger2_demo.DI.module;

import dagger.Module;
import dagger.Provides;
import fzone.shboka.com.dagger2_demo.presenter.DataListPresenter;
import fzone.shboka.com.dagger2_demo.presenter.MainPresenter;
import fzone.shboka.com.dagger2_demo.presenter.impl.DataListPresenterImpl;
import fzone.shboka.com.dagger2_demo.presenter.impl.MainPresenterImpl;
import fzone.shboka.com.dagger2_demo.service.ShopService;
import fzone.shboka.com.dagger2_demo.service.UserService;

/**
 * Created by 王天明 on 2015/12/23 0023.
 */
@Module
public class PresenterModule {
    @Provides
    MainPresenter providesMainPresenter(UserService userService) {
        return new MainPresenterImpl(userService);
    }

    @Provides
    DataListPresenter providesDataListPresenter(ShopService service) {
        return new DataListPresenterImpl(service);
    }
}
