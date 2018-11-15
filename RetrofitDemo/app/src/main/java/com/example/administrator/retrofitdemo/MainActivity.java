package com.example.administrator.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText num;
    private Button search;
    private LinearLayout scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.num);
        search = findViewById(R.id.search);
        scroll = findViewById(R.id.scroll);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPhone(num.getText().toString());
            }
        });
    }

    private void getPhone(final String num) {
        if(TextUtils.isEmpty(num)){
            Toast.makeText(MainActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        Call<ResponseBody> data = RetrofitBean.getApi().getPhone(num,Config.APP_KEY);
        data.enqueue(new retrofit2.Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                Gson gson = new Gson();
                try {
                    result = response.body().string();
                    Log.e("result",result+"");
                    PhoneInfo info = gson.fromJson(result,PhoneInfo.class);
                    TextView tv = new TextView(MainActivity.this);
                    tv.setText("手机号:"+num+"\t"+info.getResult().getProvince()+info.getResult().getCity()+"\t"+info.getResult().getCompany());
                    scroll.addView(tv);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("error",t.getMessage().toString());
            }
        });
    }
}
