package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.weball.benoit.weball.adapter.ExpandableListAdapter;
import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.requestClass.FiveInfo;
import com.weball.benoit.weball.requestClass.Matchs;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.createMatchRequest;
import com.weball.benoit.weball.row.FiveRowAdapter;
import com.weball.benoit.weball.row.Five_Row;
import com.weball.benoit.weball.row.MatchRowAdapter;
import com.weball.benoit.weball.row.Match_Row;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.service.WeballService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 29/03/2016.
 */
public class DayCalendarFragment extends Fragment {

    private UserInfo mUserInfo;
    private Calendar    myC;
    private FieldProfile myFive;
    List<Matchs> myMatches;
    private String      selectedDate;
    ListView mListView;
    private onDayListener myListener;

    public DayCalendarFragment()
    {

    }

    public static DayCalendarFragment newInstance(UserInfo user, FieldProfile myFive, String date)
    {
        DayCalendarFragment instance = new DayCalendarFragment();
        instance.mUserInfo =  user;
        instance.myFive = myFive;
        instance.selectedDate = date;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View mView = inflater.inflate(R.layout.day_five_calendar, container, false);
        TextView    date =  (TextView)mView.findViewById(R.id.day);

        ImageView   prev = (ImageView) mView.findViewById(R.id.prev);
        ImageView   create_match = (ImageView) mView.findViewById(R.id.add_match);



        try {
            myC = Calendar.getInstance();
            myC.setTime(new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(selectedDate));
            myC.set(Calendar.HOUR_OF_DAY, 0);
            myC.set(Calendar.MINUTE, 0);
            myC.set(Calendar.SECOND, 0);
            String startDate = myC.getTime().toString();
            myC.set(Calendar.HOUR_OF_DAY, 23);
            myC.set(Calendar.MINUTE, 59);
            String endDate = myC.getTime().toString();

            WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
            service.getMatches(myFive.get_id(), mUserInfo.getToken(), "startDate", startDate, endDate)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Matchs>>() {
                        @Override
                        public final void onCompleted() {
                            // do nothing
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("Weball", e.getMessage());
                        }

                        @Override
                        public final void onNext(List<Matchs> response) {
                            if (!response.isEmpty())
                            {
                                myMatches = response;
                                List<Match_Row> rows = generateRows(response);
                                MatchRowAdapter adapter = new MatchRowAdapter(mView.getContext(),rows);
                                mListView.setAdapter(adapter);
                            }
                            else
                            {
                                Log.d("TEST", "RESPONSE IS EMPTY");
                            }
                        }
                    });


        } catch (ParseException e) {
            e.printStackTrace();
        }

        date.setText(selectedDate);

        mListView = (ListView) mView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MatchRowAdapter myadapter = (MatchRowAdapter) parent.getAdapter();
                if (myadapter != null)
                    myListener.onListClicked(myadapter.getMatchId(position), "listMatch");
            }
        });



        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onPrevClicked();
            }
        });

        create_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onCreateMatchPressed(selectedDate, myFive);
            }
        });


        return mView;
    }


    private List<Match_Row> generateRows(List<Matchs> matches)
    {
        List<Match_Row> rows = new ArrayList<Match_Row>();

        for (int i = 0; i < matches.size(); i++)
        {
            String status = "";
            if (checkDate(matches.get(i).getEndDate()))
            {
                status = "TERMINÉ";
            }
            else
            {
                if (matches.get(i).getMaxPlayers() > matches.get(i).getCurrentPlayers())
                    status = "EN ATTENTE";
                else
                    status = "PRÊT";
            }
            String s_hour = matches.get(i).getStartDate().substring(11, 13);
            String s_minute = matches.get(i).getStartDate().substring(14, 16);
            String e_hour = matches.get(i).getEndDate().substring(11, 13);
            String e_minute = matches.get(i).getEndDate().substring(14, 16);
            String time = s_hour + "h";
            if (!s_minute.equals("00"))
                time += s_minute + " - ";
            else
                time += " - " + e_hour + "h";
            if (!e_minute.equals("00"))
                time += e_minute;

            rows.add(new Match_Row(matches.get(i).getId(), status, time, matches.get(i).getName(),
                    matches.get(i).getCreatedBy().getFullName(), matches.get(i).getCreatedBy().getId(),
                    "", matches.get(i).getTeams().get(0).getCurrentPlayers().toString(), matches.get(i).getTeams().get(0).getName(),
                    matches.get(i).getTeams().get(1).getCurrentPlayers().toString(), matches.get(i).getTeams().get(1).getName(), "",
                    matches.get(i).getCreatedBy().getPhoto(), matches.get(i).getField()));
        }
        return rows;
    }

    private boolean    checkDate(String date)
    {
        String datetoconvert = date.substring(0, 16);
        String month = datetoconvert.substring(5, 7);
        String year = datetoconvert.substring(0, 4);
        String day = datetoconvert.substring(8, 10);
        String hour = datetoconvert.substring(11, 13);
        String minutes = datetoconvert.substring(14, 16);
        int month_nb = Integer.parseInt(month);
        int year_nb = Integer.parseInt(year);
        int day_nb = Integer.parseInt(day);
        int hours_nb = Integer.parseInt(hour);
        int minutes_nb = Integer.parseInt(minutes);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month_nb - 1);
        c.set(Calendar.YEAR, year_nb);
        c.set(Calendar.DAY_OF_MONTH, day_nb);
        c.set(Calendar.HOUR_OF_DAY, hours_nb);
        c.set(Calendar.MINUTE, minutes_nb);
        long timeEvent = c.getTimeInMillis();

        Calendar currentDate =  Calendar.getInstance();
        long timeNow = currentDate.getTimeInMillis();
        if (timeEvent < timeNow)
            return false;

        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onDayListener) {
            myListener = (onDayListener) context;
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

    public interface onDayListener{
        public void onPrevClicked();
        public void onCreateMatchPressed(String selectDate, FieldProfile five);
        public void onListClicked(String idmatch, String msg);

    }
}
