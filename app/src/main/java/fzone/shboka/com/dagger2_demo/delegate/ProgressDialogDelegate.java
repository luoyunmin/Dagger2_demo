package fzone.shboka.com.dagger2_demo.delegate;

import android.app.ProgressDialog;
import android.content.Context;

import javax.inject.Inject;

/**
 * Created by 王天明 on 2015/12/21 0021.
 */
public class ProgressDialogDelegate {

    private ProgressDialog progressDialog;
    private String msg;
    private String title;
    private Context context;

    @Inject
    public ProgressDialogDelegate(Context context, String title, String msg) {
        this.msg = msg;
        this.title = title;
        this.context = context;
    }

    public void show() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(context, title, msg);
        } else {
            progressDialog.show();
        }
    }

    public void hide() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}