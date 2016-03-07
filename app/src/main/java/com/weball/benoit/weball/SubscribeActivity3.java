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

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
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
        super.setContentView(R.layout.activity_subscribe3);

        List<Fragment> fragments = new Vector<Fragment>();

        fragments.add(Fragment.instantiate(this, EmailFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, InformationFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, PasswordFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, ProfilePictureFragment.class.getName()));
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
        Intent intent = new Intent(SubscribeActivity3.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showResult(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
