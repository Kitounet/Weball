package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.Matchs;
import com.weball.benoit.weball.row.MatchRowAdapter;
import com.weball.benoit.weball.row.MatchRowUserAdapter;
import com.weball.benoit.weball.row.Match_Row;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.UserMe;

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
 * Created by benoi on 13/04/2016.
 */
public class UserProfileFragment extends Fragment {
    private UserInfo mUser;
    private ImageView   add_matches;
    private ImageView   conf_img;
    private ImageView   user_picture;
    private TextView    city;
    private ImageView   friendlist;
    private TextView    full_name;
    private TextView    age;
    private TextView    matchs_nb;
    private TextView    teammates_nb;
    private UserMe      mUserMe;
    private onProfileListener myListener;
    private ImageView       searchFriend;

    private PieChartView    chart;
    private PieChartData    data;
    private PieChartView    chart2;
    private PieChartData    data2;
    private PieChartView    chart3;
    private PieChartData    data3;

    private TwoWayView      mListView;

    public UserProfileFragment() {
    }

    public static UserProfileFragment newInstance(UserInfo mUserInfo)
    {
        UserProfileFragment instance = new UserProfileFragment();
        instance.mUser = mUserInfo;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.user_profile, container, false);
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
        conf_img = (ImageView) mView.findViewById(R.id.conf_img);
        friendlist = (ImageView) mView.findViewById(R.id.add_friend_picture);
        mListView = (TwoWayView) mView.findViewById(R.id.lvItems);
        searchFriend = (ImageView) mView.findViewById(R.id.search_image);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MatchRowUserAdapter myadapter = (MatchRowUserAdapter)parent.getAdapter();
                Log.d("TEST", "MANGE UNE POMME!");
                if (myadapter != null)
                    myListener.onMatchListClicked(myadapter.getMatchId(position));
            }
        });


        searchFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onSearchMyFriendClicked();
            }
        });

        conf_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myListener.onConfClicked(mUserMe);
            }
        });

        friendlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onFriendClicked(mUser);
            }
        });


        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getUserInfo(mUser.getToken())
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
                            Log.d("TEST", "1234654984984849849498");
                            mUserMe = response;
                            full_name.setText(response.getFullName());
                            city.setText(response.getCity());
                            //calcul age
                            Log.d("TEST", "1234654984984849849498");
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
                            Log.d("TEST", "1234654984984849849498");

                            int age_calc = mYear - year_nb;
                            if (mMonth <= month_nb)
                                if (mMonth == month_nb && day_nb <= mDay)
                                    age_calc = age_calc;
                                else
                                    age_calc = age_calc - 1;
                            else
                                age_calc = age_calc - 1;
                            Log.d("TEST", "1234654984984849849498");
                            String old_year = String.valueOf(age_calc) + " ans";
                            age.setText(old_year);
                            if (!response.getPhoto().isEmpty())
                                Picasso.with(getContext()).load(response.getPhoto()).placeholder(R.drawable.add_photo_picture).fit().centerCrop().into((ImageView) mView.findViewById(R.id.picture_profile));
                            Log.d("TEST", "1234654984984849849498");
                            if (response.getNMatchs() != null)
                            {
                                if (response.getNMatchs().getTotal() != 0)
                                    matchs_nb.setText(response.getNMatchs().getTotal());
                                else
                                    matchs_nb.setText("0");
                            }
                            else
                                matchs_nb.setText("0");
                            Log.d("TEST", "1234654984984849849498DIXIIX");
                            if (response.getRelationShip() != null)
                            {
                                if (response.getRelationShip().getNRelations() != 0)
                                    teammates_nb.setText(Integer.toString(response.getRelationShip().getNRelations()));
                                else
                                    teammates_nb.setText("0");
                            }
                            else
                                teammates_nb.setText("0");
                            Log.d("TEST", "1234654984984849849498DIXIIX2");


                            if (response.getNMatchs() != null)
                            {
                                Log.d("TEST", "1234654984984849849498DIXIIXgetNMATCHS");
                                win = response.getNMatchs().getWin();
                                loose = response.getNMatchs().getLoose();
                                total = response.getNMatchs().getTotal();
                                nul = response.getNMatchs().getNul();
                            }
                            else
                            {
                                Log.d("TEST", "1234654984984849849498DIXIIXgetNMATCHS2");
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
        public void onSearchMyFriendClicked();
        public void onConfClicked(UserMe response);
        public void onFriendClicked(UserInfo mUser);
        public void onMatchListClicked(String id);
    }


}
