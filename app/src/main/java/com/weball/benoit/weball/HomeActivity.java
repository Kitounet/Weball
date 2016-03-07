package com.weball.benoit.weball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by benoi on 11/12/2015.
 */
public class HomeActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button loginButton = (Button) findViewById(R.id.connecter);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, loginPageActivity.class);
                 startActivity(intent);
            }
        });
        Button subscribeButton = (Button) findViewById(R.id.inscription);
        subscribeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SubscribeActivity3.class);
                startActivity(intent);
            }
        });
    }
}
