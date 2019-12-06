package com.hxg.module_login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hxg.hrouter_annotations.Route;
import com.hxg.lib_hrouter.HRouter;

@Route("login/login")
public class LoginActivity extends AppCompatActivity {
    private FragmentManagerHelper mFragmentManagerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView tvText = findViewById(R.id.tv_text);
        String login_text = getIntent().getStringExtra("login");
        int login_int = getIntent().getIntExtra("login1", 0);
        String object = getIntent().getStringExtra("object");
        if (login_text != null) {
            Log.i("huangxiaoguo", login_text);
            tvText.setText(login_text + "," + login_int + "," + object);
        }
        mFragmentManagerHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.fl_main);
        Fragment fragment = (Fragment) HRouter.getInstance().build("member/blankfragment").navigation();
        mFragmentManagerHelper.add(fragment);
    }

    public void onClickBack(View view) {
        setResult(999);
        finish();
    }

    public void onClickMember(View view) {
        HRouter.getInstance().build("member/member").navigation();
    }
}
