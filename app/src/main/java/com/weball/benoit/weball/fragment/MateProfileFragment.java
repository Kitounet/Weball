package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.Relationship;
import com.weball.benoit.weball.requestClass.SimpleAnswer;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.UserMe;
import com.weball.benoit.weball.row.MatchRowUserAdapter;
import com.weball.benoit.weball.row.Notif_Row;
import com.weball.benoit.weball.row.UserFriendAdapter;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 11/11/2016.
 */

public class MateProfileFragment extends Fragment {
    private UserInfo mUser;
    private List<Relationship> myFriend;
    private ImageView add_matches;
    private ImageView   deny;
    private TextView city;
    private ImageView   friendlist;
    private ImageView   user_picture;
    private TextView    full_name;
    private TextView    age;
    private TextView    matchs_nb;
    private ImageView   back;
    private TextView    teammates_nb;
    private UserMe mUserMe;
    private onProfileListener myListener;

    private String userId;
    private PieChartView chart;
    private PieChartData data;
    private PieChartView    chart2;
    private PieChartData    data2;
    private PieChartView    chart3;
    private PieChartData    data3;

    private TwoWayView mListView;

    public MateProfileFragment() {
    }

    public static MateProfileFragment newInstance(UserInfo mUserInfo, String userId)
    {
        MateProfileFragment instance = new MateProfileFragment();
        instance.mUser = mUserInfo;
        instance.userId = userId;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.mate_profile, container, false);
        add_matches = (ImageView) mView.findViewById(R.id.hidden_add_match);
        city = (TextView) mView.findViewById(R.id.city_text);
        user_picture = (ImageView) mView.findViewById(R.id.picture_profile);
        full_name = (TextView) mView.findViewById(R.id.fullname_text);
        age = (TextView) mView.findViewById(R.id.age_text);
        matchs_nb = (TextView) mView.findViewById(R.id.matchs_number) ;
        teammates_nb = (TextView) mView.findViewById(R.id.teammates_number);
        chart = (PieChartView) mView.findViewById(R.id.chart);
        chart2 = (PieChartView) mView.findViewById(R.id.chart2);
        chart3 = (PieChartView) mView.findViewById(R.id.chart3);
        friendlist = (ImageView) mView.findViewById(R.id.add_friend_picture);
        mListView = (TwoWayView) mView.findViewById(R.id.lvItems);
        deny = (ImageView) mView.findViewById(R.id.suppress_friend);
        back = (ImageView) mView.findViewById(R.id.prev);

        deny.setEnabled(false);
        deny.setClickable(false);
        deny.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onPrevPressed();
            }
        });


        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                service.deleteFriend(userId, mUser.getToken())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SimpleAnswer>() {
                            @Override
                            public final void onCompleted() {
                                // do nothing
                            }

                            @Override
                            public final void onError(Throwable e) {
                                Log.e("Weball", e.getMessage());
                            }

                            @Override
                            public final void onNext(SimpleAnswer response) {
                                if (response != null) {
                                    Log.d("TEST", "onDeleteFriendRequest");
                                    if (response.getOk() == 1)
                                    {
                                            Toast.makeText(getActivity(), "Relation supprimé", Toast.LENGTH_SHORT).show();
                                    }
//                                    myListener.onFriendSuppressPressed();
                                }

                            }
                        });
        }
        });

        friendlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                service.sendRequest(userId, mUser.getToken())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SimpleAnswer>() {
                            @Override
                            public final void onCompleted() {
                                // do nothing
                            }

                            @Override
                            public final void onError(Throwable e) {
                                Log.e("Weball", e.getMessage());
                            }

                            @Override
                            public final void onNext(SimpleAnswer response) {
                                if (response != null) {
                                    Log.d("TEST", "onDeleteFriendRequest");
                                    if (response.getOk() == 1)
                                    {
                                        Toast.makeText(getActivity(), "Relation ajouté", Toast.LENGTH_SHORT).show();
                                    }
//                                    myListener.onAddFriendClicked();
                                }
                            }
                        });

            }
        });

        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getRelashionship(mUser.get_id(), mUser.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Relationship>>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Weball", e.getMessage());
                    }

                    @Override
                    public final void onNext(List<Relationship> response) {
                        if (response != null) {
                            Log.d("TEST", "OnNextGroupFragment");
                            myFriend = response;
                            for (int i = 0; i < myFriend.size(); i++)
                            {
                                Log.d("TEST", "AVANT LA BOUCLE POUR MATCH");
                                Log.d("TEST", userId + " == " + myFriend.get(i).getUser().getId());
                                if (userId.equals(myFriend.get(i).getUser().getId()))
                                {
                                    Log.d("TEST", "Y A MATCH !!");
                                    deny.setEnabled(true);
                                    deny.setClickable(true);
                                    deny.setVisibility(View.VISIBLE);
                                    friendlist.setEnabled(false);
                                    friendlist.setClickable(false);
                                    friendlist.setVisibility(View.INVISIBLE);
                                }
                            }

                        }
                    }
                });


        WeballService service2 = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service2.getMateInfo(userId,mUser.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserMe>()
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
                    public final void onNext(UserMe response) {
                        if (response != null)
                        {
                            int     win;
                            int     total;
                            int     nul;
                            int     loose;
                            mUserMe = response;
                            full_name.setText(response.getFullName());
                            city.setText(response.getCity());
                            //calcul age
                            String birthday = response.getBirthday();
                            birthday = birthday.substring(0, 10);
                            String month = birthday.substring(5, 7);
                            String year = birthday.substring(0, 4);
                            String day = birthday.substring(8);
                            int month_nb = Integer.parseInt(month);
                            int year_nb = Integer.parseInt(year);
                            int day_nb = Integer.parseInt(day);
                            final Calendar c = Calendar.getInstance();
                            int mYear = c.get(Calendar.YEAR);
                            int mMonth = c.get(Calendar.MONTH);
                            int mDay = c.get(Calendar.DAY_OF_MONTH);
                            int age_calc = mYear - year_nb;
                            if (mMonth <= month_nb)
                                if (mMonth == month_nb && day_nb <= mDay)
                                    age_calc = age_calc;
                                else
                                    age_calc = age_calc - 1;
                            else
                                age_calc = age_calc - 1;
                            String old_year = String.valueOf(age_calc) + " ans";
                            age.setText(old_year);
                            if (!response.getPhoto().isEmpty())
                                Picasso.with(getContext()).load(response.getPhoto()).placeholder(R.drawable.add_photo_picture).fit().centerCrop().into((ImageView) mView.findViewById(R.id.picture_profile));
                            if (response.getNMatchs() != null)
                            {
                                if (response.getNMatchs().getTotal() != 0)
                                    matchs_nb.setText(response.getNMatchs().getTotal());
                                else
                                    matchs_nb.setText("0");
                            }
                            else
                                matchs_nb.setText("0");
                            if (response.getRelationShip() != null)
                            {
                                if (response.getRelationShip().getNRelations() != 0)
                                    teammates_nb.setText(Integer.toString(response.getRelationShip().getNRelations()));
                                else
                                    teammates_nb.setText("0");
                            }
                            else
                                teammates_nb.setText("0");

                            if (response.getNMatchs() != null)
                            {
                                win = response.getNMatchs().getWin();
                                loose = response.getNMatchs().getLoose();
                                total = response.getNMatchs().getTotal();
                                nul = response.getNMatchs().getNul();
                            }
                            else
                            {
                                win = 0;
                                loose = 0;
                                total = 0;
                                nul = 0;
                            }

                            Log.d("TEST", "PSYKOKWAK");
                            if (response.getNextMatches() == null)
                            {
                                Log.d("TEST", "JE MANGE UNE POMME");
                                add_matches.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                            else
                            {
                                MatchRowUserAdapter adapter = new MatchRowUserAdapter(mView.getContext(),response.getNextMatches());
                                Log.d("TEST", "BeforeSetAdapter");
                                mListView.setAdapter(adapter);
                                Log.d("TEST", "AfterSetAdapter");
                                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        MatchRowUserAdapter myadapter = (MatchRowUserAdapter) parent.getAdapter();
                                        if (myadapter != null)
                                            Log.d("TEST", myadapter.getMatchId(position));
                                    }
                                });
                            }


                            List<SliceValue> values = new ArrayList<SliceValue>();
                            SliceValue  sliceValue = new SliceValue(nul, ChartUtils.COLOR_ORANGE);
                            values.add(sliceValue);
                            sliceValue = new SliceValue(total - nul, ChartUtils.DEFAULT_COLOR);
                            values.add(sliceValue);
                            data = new PieChartData(values);
                            data.setHasCenterCircle(true);
                            data.setSlicesSpacing(1);
                            data.setCenterCircleScale(0.9F);
                            data.setCenterText1(String.valueOf(nul));
                            data.setCenterText1Color(ChartUtils.DEFAULT_COLOR);
                            data.setCenterText1FontSize(25);
                            if (total != 0)
                                data.setCenterText2(String.valueOf(nul * 100 / total) + "%");
                            else
                                data.setCenterText2(String.valueOf("0%"));
                            data.setCenterText2Color(ChartUtils.DEFAULT_COLOR);
                            data.setCenterText2FontSize(15);
                            chart.setPieChartData(data);

                            values = new ArrayList<SliceValue>();
                            sliceValue = new SliceValue(win, ChartUtils.COLOR_GREEN);
                            values.add(sliceValue);
                            sliceValue = new SliceValue(total - win, ChartUtils.DEFAULT_COLOR);
                            values.add(sliceValue);
                            data2 = new PieChartData(values);
                            data2.setHasCenterCircle(true);
                            data2.setSlicesSpacing(1);
                            data2.setCenterCircleScale(0.9F);
                            data2.setCenterText1(String.valueOf(win));
                            data2.setCenterText1Color(ChartUtils.DEFAULT_COLOR);
                            data2.setCenterText1FontSize(25);
                            if (total != 0)
                                data2.setCenterText2(String.valueOf(win * 100 / total) + "%");
                            else
                                data2.setCenterText2("0%");
                            data2.setCenterText2Color(ChartUtils.DEFAULT_COLOR);
                            data2.setCenterText2FontSize(15);
                            chart2.setPieChartData(data2);

                            values = new ArrayList<SliceValue>();
                            sliceValue = new SliceValue(loose, ChartUtils.COLOR_RED);
                            values.add(sliceValue);
                            sliceValue = new SliceValue(total - loose, ChartUtils.DEFAULT_COLOR);
                            values.add(sliceValue);
                            data3 = new PieChartData(values);
                            data3.setHasCenterCircle(true);
                            data3.setSlicesSpacing(1);
                            data3.setCenterCircleScale(0.9F);
                            data3.setCenterText1(String.valueOf(loose));
                            data3.setCenterText1Color(ChartUtils.DEFAULT_COLOR);
                            data3.setCenterText1FontSize(25);
                            if (total != 0)
                                data3.setCenterText2(String.valueOf(loose * 100 / total) + "%");
                            else
                                data3.setCenterText2("0%");
                            data3.setCenterText2Color(ChartUtils.DEFAULT_COLOR);
                            data3.setCenterText2FontSize(15);
                            chart3.setPieChartData(data3);

                        }

                    }
                });



        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onProfileListener) {
            myListener = (onProfileListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectFragmentListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }

    public interface onProfileListener{
        public void onFriendSuppressPressed();
        public void onAddFriendClicked();
        public void onPrevPressed();
    }


}
