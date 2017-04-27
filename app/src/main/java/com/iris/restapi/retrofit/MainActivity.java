package com.iris.restapi.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iris.restapi.retrofit.listener.OnNetworkResponseListener;
import com.iris.restapi.retrofit.repo.AuthRepo;
import com.iris.restapi.retrofit.repo.MainRepo;
import com.iris.restapi.retrofit.vo.AuthVO;
import com.iris.restapi.retrofit.vo.ErrorVO;
import com.iris.restapi.retrofit.vo.MainResultVO;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Client.setDebugMode(true);
        new AuthRepo<AuthVO>(this)
                .setOnNetworkResponseListener(new OnNetworkResponseListener<AuthVO>() {
                    @Override
                    public void success(AuthVO authVO) {
                        TokenManager.setToken(getApplicationContext(), authVO.getAuth().getToken());
                    }

                    @Override
                    public void error(ErrorVO error) {
                    }
                })
                .requestLogin()
                .build();
//
        Button button = (Button) findViewById(R.id.testbtn);
        button.setOnClickListener(v -> {
            test();
        });
    }

    private void test() {
        new MainRepo<MainResultVO>(this)
                .setOnNetworkResponseListener(new OnNetworkResponseListener<MainResultVO>() {
                    @Override
                    public void success(MainResultVO mainResultVO) {
                        Log.d("mad-noah", "success: sssss");
                    }

                    @Override
                    public void error(ErrorVO error) {

                    }
                })
                .requestMainList()
                .build();
    }
}
