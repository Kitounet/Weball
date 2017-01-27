package com.weball.benoit.weball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

//import com.viewpagerindicator.TitlePageIndicator;

import com.weball.benoit.weball.adapter.MyPagerAdapter;
import com.weball.benoit.weball.fragment.EmailFragment;
import com.weball.benoit.weball.fragment.InformationFragment;
import com.weball.benoit.weball.fragment.PasswordFragment;
import com.weball.benoit.weball.fragment.ProfilePictureFragment;
import com.weball.benoit.weball.fragment.SubscribeSuccessFragment;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.user;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 20/01/2016.
 */
public class SubscribeActivity3 extends FragmentActivity implements EmailFragment.onTextViewListener, InformationFragment.onTextViewListener2, PasswordFragment.onTextViewListener3,
        ProfilePictureFragment.onTextViewListener4, SubscribeSuccessFragment.onTextViewListener5 {
    private PagerAdapter    mPagerAdapter;
    protected ViewPager     pager;
    private String          email;
    private String          fullname;
    private String          birthday;
    private String          password;
    private String          photo;
    //private long char          photo;
    private int             globalnb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_subscribe3);

        List<Fragment> fragments = new Vector<Fragment>();

        fragments.add(Fragment.instantiate(this, EmailFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, InformationFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, ProfilePictureFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, PasswordFragment.class.getName()));

        fragments.add(Fragment.instantiate(this, SubscribeSuccessFragment.class.getName()));

        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        pager = (ViewPager) super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
//        TitlePageIndicator titleIndicator = (TitlePageIndicator)findViewById(R.id.circles);
//        titleIndicator.setViewPager(pager);
    }

    @Override
    public void TextViewClicked(int nb, List infos) {
        globalnb = nb;
        switch (nb){
            case 1:
                email = infos.get(0).toString();
                pager.setCurrentItem(nb);
                break;
            case 2:
                fullname = infos.get(0).toString();
                birthday = infos.get(1).toString();
                pager.setCurrentItem(nb);
                break;
            case 3:
                photo = infos.get(0).toString();
                pager.setCurrentItem(nb);
                break;
            case 4:
                password = infos.get(0).toString();
                String photoTest = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOgAAAE1CAIAAABiHDtqAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbW";
                Log.d("TEST", email);
                Log.d("TEST", email);
                Log.d("TEST", email);
                Log.d("TEST", email);
                Log.d("TEST", email);Log.d("TEST", email);
                Log.d("TEST", email);
                Log.d("TEST", email);
                Log.d("TEST", email);
                Log.d("TEST", email);

                user obj = new user(email, password, fullname, birthday);
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
                            }

                            @Override
                            public final void onNext(UserInfo response) {
                                if (response != null) {
                                    Log.d("TEST", response.getEmail());
                                    pager.setCurrentItem(globalnb);
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
        Intent intent = new Intent(SubscribeActivity3.this, loginPageActivity.class);
        startActivity(intent);
    }

    @Override
    public void showResult(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
