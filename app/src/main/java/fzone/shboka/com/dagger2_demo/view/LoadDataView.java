package fzone.shboka.com.dagger2_demo.view;

import android.content.Context;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
public interface LoadDataView {
    /**
     * 显示隐藏读取数据的view
     */
    void showLoading();
    void hideLoading();

    /**
     * 显示隐藏重新加载view
     */
    void showRetry();
    void hideRetry();

    /**
     * 显示错误
     * @param message
     */
    void showError(String message);

    Context getContext();
}
