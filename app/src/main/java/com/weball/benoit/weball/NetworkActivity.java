package com.weball.benoit.weball;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.weball.benoit.weball.adapter.MyPagerAdapter;
import com.weball.benoit.weball.fragment.DayCalendarFragment;
import com.weball.benoit.weball.fragment.EmailFragment;
import com.weball.benoit.weball.fragment.FieldProfileFragment;
import com.weball.benoit.weball.fragment.GroupFragment;
import com.weball.benoit.weball.fragment.InformationFragment;
import com.weball.benoit.weball.fragment.ListFiveFragment;
import com.weball.benoit.weball.fragment.MatchFiveFragment;
import com.weball.benoit.weball.fragment.MatchProfileFragment;
import com.weball.benoit.weball.fragment.MateProfileFragment;
import com.weball.benoit.weball.fragment.MonthCalendarFragment;
import com.weball.benoit.weball.fragment.NotificationFragment;
import com.weball.benoit.weball.fragment.PasswordFragment;
import com.weball.benoit.weball.fragment.ProfilePictureFragment;
import com.weball.benoit.weball.fragment.SearchFiveFragment;
import com.weball.benoit.weball.fragment.SearchFriendFragment;
import com.weball.benoit.weball.fragment.SubscribeSuccessFragment;
import com.weball.benoit.weball.fragment.TeamMateFragment;
import com.weball.benoit.weball.fragment.UserProfileFragment;
import com.weball.benoit.weball.fragment.UserSettingsFragment;
import com.weball.benoit.weball.fragment.WeekCalendarFragment;
import com.weball.benoit.weball.gcm.RegistrationIntentService;
import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.requestClass.FiveInfo;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.UserMe;
import com.weball.benoit.weball.requestClass.user;
import com.weball.benoit.weball.service.NotificationService;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import android.support.v4.content.LocalBroadcastManager;


import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by benoi on 16/02/2016.
 */
public class NetworkActivity extends FragmentActivity implements EmailFragment.onTextViewListener, InformationFragment.onTextViewListener2, PasswordFragment.onTextViewListener3,
        ProfilePictureFragment.onTextViewListener4, SubscribeSuccessFragment.onTextViewListener5, MatchFiveFragment.onMapListener, ListFiveFragment.onListListener, FieldProfileFragment.onFieldProfileListener,
        DayCalendarFragment.onDayListener, MonthCalendarFragment.onMonthListener, WeekCalendarFragment.onWeekListener, SearchFiveFragment.onListFiveClicked,
        UserProfileFragment.onProfileListener, UserSettingsFragment.onSettingsListener, TeamMateFragment.onTeamMateListener, SearchFriendFragment.onListFriendClicked, GroupFragment.onGroupListener, MateProfileFragment.onProfileListener
{
    protected ViewPager pager;
    private String      token;
    private String          email;
    private String          fullname;
    private String          birthday;
    private String          password;
    private UserInfo user;
    private String          photo;
    private int             globalnb;
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_network);


        //Initializing our broadcast receiver
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {

            //When the broadcast received
            //We are sending the broadcast from GCMRegistrationIntentService

            @Override
            public void onReceive(Context context, Intent intent) {
                //If the broadcast has received with success
                //that means device is registered successfully
                if(intent.getAction().equals(RegistrationIntentService.REGISTRATION_SUCCESS)){
                    //Getting the registration token from the intent
                    String token = intent.getStringExtra("token");
                    //Displaying the token as toast
                    Toast.makeText(getApplicationContext(), "Registration token:" + token, Toast.LENGTH_LONG).show();

                    //if the intent is not with success then displaying error messages
                } else if(intent.getAction().equals(RegistrationIntentService.REGISTRATION_ERROR)){
                    Toast.makeText(getApplicationContext(), "GCM registration error!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_LONG).show();
                }
            }
        };

        //Checking play service is available or not
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        //if play service is not available
        if(ConnectionResult.SUCCESS != resultCode) {
            //If play service is supported but not installed
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                //Displaying message that play service is not installed
                Toast.makeText(getApplicationContext(), "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());

                //If play service is not supported
                //Displaying an error message
            } else {
                Toast.makeText(getApplicationContext(), "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }

            //If play service is available
        } else {
            //Starting intent to register device
            Intent itent = new Intent(this, RegistrationIntentService.class);
            startService(itent);
        }


        user = (UserInfo) getIntent().getSerializableExtra("response");
        List<Fragment> fragments = new Vector<Fragment>();

        if (user == null) {
            token = getIntent().getStringExtra("token");
            Log.d("TEST", "TOKENNNNNNNNNNNNNNNNNNNNNNNN");
            user = new UserInfo();
            user.setToken(token);
        }

        Log.d("TEST", user.getToken());
        fragments.add(NotificationFragment.newInstance(user));
        fragments.add(GroupFragment.newInstance(user));
        fragments.add(MatchFiveFragment.newInstance(user));
        fragments.add(ListFiveFragment.newInstance(user));
        fragments.add(UserProfileFragment.newInstance(user));

        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new MyPagerAdapter(super.getSupportFragmentManager(), fragments));
        pager.setCurrentItem(2);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setIndicatorColor(getResources().getColor(R.color.green));
        tabs.setShouldExpand(true);
        tabs.setViewPager(pager);

        Intent serviceIntent = new Intent(this,NotificationService.class);
        serviceIntent.putExtra("UserInfo", user.getToken());
        this.startService(serviceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_ERROR));
    }


    //Unregistering receiver on activity paused
    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
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
                com.weball.benoit.weball.requestClass.user obj = new user(email, password, fullname, birthday);
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
                                if (!response.getToken().equals("")) {
                                    Log.d("TEST", response.getToken());
                                    pager.setCurrentItem(globalnb);
                                }
                            }
                        });

                break;
            default:
                break;
        }

    }

    public void getUser()
    {


    }

    @Override
    public void ImageViewClicked(int nb) { pager.setCurrentItem(nb);}

    @Override
    public void ComeBackHome() {
        Intent intent = new Intent(NetworkActivity.this, loginPageActivity.class);
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
        Log.d("TEST", "onMapClicked Network activity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        transaction.replace(R.id.map_five, FieldProfileFragment.newInstance(infos.get_id(), user));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onListClicked(String id, String name)
    {
        Log.d("TEST", "OnListClicked Network Activity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (name == "listFive")
        {
            transaction.replace(R.id.list_five, FieldProfileFragment.newInstance(id, user));
        }
        else if (name == "searchFive") {
            transaction.replace(R.id.search_five_fragment, FieldProfileFragment.newInstance(id, user));
        }
        else if (name == "searchFriend"){
            transaction.replace(R.id.search_friend_fragment, MateProfileFragment.newInstance(user, id));
        }
        else if (name == "listMatch")
        {
            transaction.replace(R.id.day_five_calendar, MatchProfileFragment.newInstance(id, user));
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onReservateClicked(FieldProfile myFive, UserInfo user, String date)
    {
        Log.d("TEST", "onReservateCliked Network Activity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        Log.d("TEST", myFive.get_id());
        Log.d("TEST", myFive.getName());
        transaction.replace(R.id.FieldProfileFragment, DayCalendarFragment.newInstance(user, myFive, date));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onImageClicked()
    {
        Log.d("TEST", "onImageClicked Network Activity");
        onBackPressed();
    }

//    @Override
//    public void onItemClicked(Calendar horaire, FieldProfile five, UserInfo user)
//    {
//        Intent intent = new Intent(NetworkActivity.this, CreateMatchActivity.class);
//        Bundle mybundle = new Bundle();
//        intent.putExtra("user", user);
//        intent.putExtra("five", five);
//        intent.putExtra("horaire", horaire);
//        startActivity(intent);
//    }

    @Override
    public void onSearchClicked(List<FiveInfo> fives, String name)
    {
        Log.d("TEST", "onSearchClicked Network Activity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (name == "matchFive")
            transaction.replace(R.id.map_five, SearchFiveFragment.newInstance(user, fives));
        else
            transaction.replace(R.id.list_five, SearchFiveFragment.newInstance(user, fives));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCancelClicked()
    {
        onBackPressed();
    }

    @Override
    public void onConfClicked(UserMe myUser)
    {
        Log.d("TEST", "onConfClicked Network Activity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.user_profile_fragment, UserSettingsFragment.newInstance(myUser, user.getToken().toString()));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onApplyChange(UserMe response)
    {
        Log.d("TEST", "on Apply Change !");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.settings_fragment, UserProfileFragment.newInstance(user));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onPrevPressed()
    {
        Log.d("TEST", "JE SUIS DANS PREVPRESSED");
        onBackPressed();
    }

    @Override
    public void onFriendClicked(UserInfo response)
    {
        Log.d("TEST", "onFriendClicked Network Activity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.user_profile_fragment, TeamMateFragment.newInstance(user));
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onPrevClicked()
    {
        Log.d("TEST", "onPrevClicked Network Activity");
        onBackPressed();
    }

    @Override
    public void onSearchFriendClicked()
    {
        Log.d("TEST", "onSearchFriendClicked() Network activity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.teammate_fragment, SearchFriendFragment.newInstance(user));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCreateMatchPressed(String selectDate, FieldProfile five)
    {
        Log.d("TEST", "onCreateMatchPressed() Network Activity" );
        Intent intent = new Intent(NetworkActivity.this, CreateMatchActivity.class);
        Bundle mybundle = new Bundle();
        intent.putExtra("user", user);
        intent.putExtra("five", five);
        intent.putExtra("date", selectDate);
        startActivity(intent);
    }

    @Override
    public void onGroupListClicked(String id)
    {
        Log.d("TEST","onGroupListClicked");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.friend_list_page, MateProfileFragment.newInstance(user, id));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFriendSuppressPressed()
    {

    }


    @Override
    public void onAddFriendClicked()
    {

    }

    @Override
    public void onSearchMyFriendClicked()
    {
        Log.d("TEST", "onSearchMyFriendClicked");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.user_profile_fragment, GroupFragment.newInstance(user));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onMatchListClicked(String id)
    {
        Log.d("TEST", "onMatchListClicked");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.user_profile_fragment, MatchProfileFragment.newInstance(id, user));
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
