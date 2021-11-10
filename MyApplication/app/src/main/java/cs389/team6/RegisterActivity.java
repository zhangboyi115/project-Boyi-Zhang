package cs389.team6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class RegisterActivity extends AppCompatActivity {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtRePassword;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpToolbar();
        initView();
        initEvent();
        setTitle("Register");

    }
    public void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void initView() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtUsername = (EditText) findViewById(R.id.id_et_password);
        mEtUsername = (EditText) findViewById(R.id.id_et_repassword);
        mBtnRegister = (Button) findViewById(R.id.id_btn_register);

    }

    private void initEvent() {
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}