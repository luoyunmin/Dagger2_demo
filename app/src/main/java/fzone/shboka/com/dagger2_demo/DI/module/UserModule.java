package fzone.shboka.com.dagger2_demo.DI.module;

import dagger.Module;
import dagger.Provides;
import fzone.shboka.com.dagger2_demo.factory.ServiceFactory;
import fzone.shboka.com.dagger2_demo.service.UserService;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
@Module
public class UserModule {
    @Provides
    UserService providesUserService() {
        return ServiceFactory.create(UserService.class);
    }
}
