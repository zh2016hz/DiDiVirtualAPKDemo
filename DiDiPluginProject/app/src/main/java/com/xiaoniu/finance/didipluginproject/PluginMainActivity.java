package com.xiaoniu.finance.didipluginproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PluginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
       TextView  tv = (TextView) findViewById(R.id.plugin);
        if(tv ==null){
            Log.e("22222,","为什么是空");
        }else {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("22222,","哈哈哈哈哈");
                }
            });
        }


    }
}
