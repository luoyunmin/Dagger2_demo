package fzone.shboka.com.dagger2_demo.delegate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public class ToastDelegate {

    private Toast toast;
    @SuppressLint("ShowToast")
    public ToastDelegate(Activity context) {
        toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }

    public void showMsg(String msg) {
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showMsgLong(String msg) {
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}