package com.weball.benoit.weball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 11/12/2015.
 */
public class ProfileActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        UserInfo user = (UserInfo) getIntent().getSerializableExtra("response");
        Log.d("Debug", user.getToken());

        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getUserInfo(user.getToken())
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
                        TextView fullname = (TextView)findViewById(R.id.fullName);
                        fullname.setText(response.getUsername());
                        TextView firstname = (TextView)findViewById(R.id.firstname);
                        firstname.setText(response.getFirstName());
                        TextView birthday = (TextView)findViewById(R.id.birthday);
                        birthday.setText(response.getBirthday());
                        TextView date = (TextView)findViewById(R.id.registerDate);
                        date.setText(response.getDate());
                    }
                });

    }
}
