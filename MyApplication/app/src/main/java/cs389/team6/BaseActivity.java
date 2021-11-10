package cs389.team6;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(0xff000000);
        }
        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setMessage("Loading...");
    }

    protected void stopLoadingProgress() {

        if(mLoadingDialog !=null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    protected void startLoadingProgress() {
        mLoadingDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLoadingProgress();
        mLoadingDialog = null;
    }
}
