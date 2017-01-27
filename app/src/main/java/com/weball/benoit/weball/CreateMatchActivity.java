package com.weball.benoit.weball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.weball.benoit.weball.adapter.MyPagerAdapter;
import com.weball.benoit.weball.fragment.ReservateFragment;
import com.weball.benoit.weball.fragment.ReservationFriendFragment;
import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.requestClass.MatchAnswer;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.createMatchRequest;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 29/03/2016.
 */
public class CreateMatchActivity extends FragmentActivity implements ReservateFragment.onReservateListener, ReservationFriendFragment.onReservateFriendListener{
    private UserInfo user;
    private FieldProfile five;
    private String    date;
    private ArrayList<String>       mfriendList;
    private ReservateFragment.info infos;
    private PagerAdapter mPagerAdapter;
    protected ViewPager     pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_reservation);

        user = (UserInfo) getIntent().getSerializableExtra("user");
        five = (FieldProfile) getIntent().getSerializableExtra("five");
        date = (String) getIntent().getSerializableExtra("date");


        List<Fragment> fragments = new Vector<Fragment>();

        fragments.add(ReservateFragment.newInstance(user, five, date));
        fragments.add(ReservationFriendFragment.newInstance(user));

        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        pager = (ViewPager) super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);

    }


    @Override
    public void onPrevClicked(int nb){
        if (nb == 0)
        {
            onBackPressed();
        }
        else if (nb == 1)
        {
            pager.setCurrentItem(0);
        }


    }
    @Override
    public void onValidateClicked(ReservateFragment.info infos){
        this.infos = infos;
        pager.setCurrentItem(1);
    }

    @Override
    public void onValidateFriendClicked(ArrayList<String> friendlist)
    {
        Log.d("TEST", "onVALIDATEFRIENDCLICKED");
        mfriendList = friendlist;


        //(String name, String startDate, String endDate, int maxPlayers, String field, ArrayList<String> guests)
        createMatchRequest match = new createMatchRequest(infos.getMatchname(), infos.getStartMatch(), infos.getEndMatch(), 10, five.getFields().get(0).get_id(), friendlist);
        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.createMatch(user.getToken(), "application/json" , match)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MatchAnswer>()
                {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Weball", e.getMessage() + "BLBLBLBLBLBLB");
                        Toast.makeText(getApplicationContext(), "Error Message : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public final void onNext(MatchAnswer response) {
                        if (response != null)
                        {
                            Log.d("TEST", "Match created");
                            Toast.makeText(getApplicationContext(), "Match Create", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateMatchActivity.this, NetworkActivity.class);
                            Bundle mybundle = new Bundle();
                            Log.d("TEST", "JE RENTRE ICIIIIII ?");
                            intent.putExtra("response", user);
                            startActivity(intent);
                        }
                    }
                });
    }

}

