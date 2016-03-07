package com.weball.benoit.weball;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 16/02/2016.
 */
public class NetworkActivity extends FragmentActivity implements EmailFragment.onTextViewListener, InformationFragment.onTextViewListener2, PasswordFragment.onTextViewListener3,
        ProfilePictureFragment.onTextViewListener4, SubscribeSuccessFragment.onTextViewListener5, MatchFiveFragment.onMapListener{
    private PagerAdapter mPagerAdapter;
    protected ViewPager pager;
    private String          login;
    private String          email;
    private String          firstname;
    private String          lastname;
    private String          birthday;
    private String          password;
    //private long char          photo;
    private int             globalnb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_network);

        UserInfo user = (UserInfo) getIntent().getSerializableExtra("response");
        List<Fragment> fragments = new Vector<Fragment>();

        Log.d("TEST", user.getToken());

        Fragment fragment = FiveFragment.newInstance(user);
        fragments.add(Fragment.instantiate(this, EmailFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, InformationFragment.class.getName()));
        fragments.add(FiveFragment.newInstance(user));
        fragments.add(Fragment.instantiate(this, ProfilePictureFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, SubscribeSuccessFragment.class.getName()));

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new MyPagerAdapter(super.getSupportFragmentManager(), fragments));
        pager.setCurrentItem(2);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setTextColor(getResources().getColor(R.color.white));
        tabs.setIndicatorColor(getResources().getColor(R.color.green));
        tabs.setUnderlineColor(getResources().getColor(R.color.green));
        tabs.setViewPager(pager);
    }

    @Override
    public void TextViewClicked(int nb, List infos) {
        globalnb = nb;
        switch (nb){
            case 1:
                login = infos.get(0).toString();
                Log.d("TESSST", login);
                email = infos.get(1).toString();
                pager.setCurrentItem(nb);
                break;
            case 2:
                firstname = infos.get(0).toString();
                lastname = infos.get(1).toString();
                birthday = infos.get(2).toString();
                pager.setCurrentItem(nb);
                break;
            case 3:
                password = infos.get(0).toString();
                pager.setCurrentItem(nb);
                break;
            case 4:
//                photo = infos.get(0).toString();
                String photo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOgAAAE1CAIAAABiHDtqAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbW";
                user obj = new user(login, email, password, firstname, lastname, birthday, photo);
                HashMap<String, user> tosend = new HashMap<String, user>();
                tosend.put("user", obj);
                pager.setCurrentItem(globalnb);


                WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                service.toSubscribe(tosend)
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
                                showResult("Votre compte n'a pas été crée");
                            }

                            @Override
                            public final void onNext(UserInfo response) {
                                if (!response.getFirstName().equals("")) {
                                    Log.d("TEST", response.getFirstName());
                                    pager.setCurrentItem(globalnb);
                                }
                                else {
                                    Log.d("TEST", response.getMessage());
                                    showResult("Votre compte n'a pas été crée" + response.getMessage());
                                }
                            }
                        });

                break;
            default:
                break;
        }

    }

    @Override
    public void ImageViewClicked(int nb) { pager.setCurrentItem(nb);}

    @Override
    public void ComeBackHome() {
        Intent intent = new Intent(NetworkActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showResult(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapClicked(FiveInfo infos)
    {
        Log.d("TEST", infos.getCountry());
        Log.d("TEST", infos.getCountry());
        Log.d("TEST", infos.getCountry());
        Log.d("TEST", infos.getCountry());
        Log.d("TEST", infos.getCountry());
//        Fragment newFragment = FieldProfileFragment.newInstance(infos);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        transaction.replace(pager.getCurrentItem(), newFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }
}
