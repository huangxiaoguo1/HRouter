package com.hxg.hrouter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hxg.hrouter_annotations.Route;
import com.hxg.lib_hrouter.HRouter;
import com.hxg.module_login.FragmentManagerHelper;

@Route("main/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin(View view) {
        HRouter.getInstance()
                .build("login/login")
                .withString("login", "我是测试带过来的数据")
                .withInt("login1", 321)
                .navigation(this, 666);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 666:
                Log.e("huangxiaoguo", "返回数据");
                break;
        }
    }
}
