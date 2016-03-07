package com.weball.benoit.weball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        final Button loginButton = (Button) findViewById(R.id.connect);
        Log.d("TAMER", "TA MERE LA GROSSE CHIENNE");
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (login.getText().toString() != null && pass.getText().toString() != null) {
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
                                }

                                @Override
                                public final void onNext(UserInfo response) {
                                    Log.d("TAMER", "TAGROSSE MERE");
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Log.d("TAMER", response.getToken());
                                    Intent intent = new Intent(loginPageActivity.this, NetworkActivity.class);
                                    Bundle mybundle = new Bundle();
                                    if (response != null) {
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());
                                        Log.d("TAMER", response.getToken());

                                        intent.putExtra("response", response);
                                        startActivity(intent);
                                    }
                                    Log.d("TEST", "WHAT DA FUCKKKKKKKK MENNN");
                                }
                            });

                }


            }
        });
    }

    private void showResult(UserInfo users)
    {
        Toast.makeText(getApplicationContext(), users.getToken(), Toast.LENGTH_SHORT).show();
    }

}
