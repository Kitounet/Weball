package com.weball.benoit.weball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.notifications;
import com.weball.benoit.weball.requestClass.user;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by benoi on 07/12/2015.
 */
public class loginPageActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText login = (EditText) findViewById(R.id.user_login);
        final EditText pass = (EditText) findViewById(R.id.user_password);
        final Button loginButton = (Button) findViewById(R.id.connectionbutton);
        final TextView  create_account = (TextView) findViewById(R.id.create_account);
        final ImageView back_button = (ImageView) findViewById(R.id.prev);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(loginPageActivity.this, SubscribeActivity3.class);
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (login.getText().toString() != "" && pass.getText().toString() != "") {
                    Log.d("TEST", "I'm HEREEE ?");


                    notifications obj = new notifications("42", "gcm");
                    HashMap<String, notifications> tosend = new HashMap<String, notifications>();
                    tosend.put("notifications", obj);

                    WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                    service.getToken(login.getText().toString(), pass.getText().toString())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<UserInfo>()
                            {
                                @Override
                                public final void onCompleted() {
                                // do nothing
                                }

                                @Override
                                public final void onError(Throwable e) {
                                    Log.e("Weball", e.getMessage());
                                    Toast.makeText(getApplicationContext(), "Error Message : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public final void onNext(UserInfo response) {
                                    Intent intent = new Intent(loginPageActivity.this, NetworkActivity.class);
                                    Bundle mybundle = new Bundle();
                                    Log.d("TEST", "JE RENTRE ICIIIIII ?");
                                    if (response != null) {

                                        intent.putExtra("response", response);
                                        startActivity(intent);
                                    }
                                }
                            });

                }
                else
                    Toast.makeText(getApplicationContext(), "Vous devez remplir les champs pour vous connecter", Toast.LENGTH_SHORT).show();


            }
        });
    }


}
