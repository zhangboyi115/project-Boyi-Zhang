package cs389.team6;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onehome.UserInfoHolder;
import com.example.onehome.bean.User;
import com.example.onehome.biz.UserBiz;
import com.example.onehome.net.CommonCallback;
import com.example.onehome.utils.T;

import okhttp3.Call;

public class LoginActivity extends BaseActivity {

    private UserBiz mUserBiz = new UserBiz();

    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtLogin;
    private TextView mTvRegister;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initView();
        
        initEvent();
    }

    private void initEvent() {
        
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    T.showToast("username or password can't be empty");
                    return;
                }

                startLoadingProgress();

                mUserBiz.login(username, password, new CommonCallback<User>() {
                    @Override
                    protected void onSuccess(User response) {
                        stopLoadingProgress();
                        T.showToast("login successful");
                        //keep user information
                        UserInfoHolder.getInstance().setUser(response);
                        //check login successful
                        toOrderActivity();
                    }

                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }


                });

            }
        });
        
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegisterActivity();
            }
        });
    }



    private void toRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void toOrderActivity() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        mEtUsername =(EditText) findViewById(R.id.id_et_username);
        mEtPassword =(EditText) findViewById(R.id.id_et_password);
        mBtLogin =(Button) findViewById(R.id.id_btn_login);
        mTvRegister =(TextView) findViewById(R.id.id_tv_register);
    }
}