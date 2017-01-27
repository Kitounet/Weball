package com.weball.benoit.weball.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.adapter.ExpandableListAdapter;
import com.weball.benoit.weball.requestClass.FiveInfo;
import com.weball.benoit.weball.requestClass.MatchAnswer;
import com.weball.benoit.weball.requestClass.MatchInfo;
import com.weball.benoit.weball.requestClass.NotificationAnswer;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.createMatchRequest;
import com.weball.benoit.weball.row.FiveRowAdapter;
import com.weball.benoit.weball.row.Five_Row;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 15/07/2016.
 */
public class MatchProfileFragment extends Fragment{
    private String  match_id;
    private boolean join = false;
    private boolean leave = false;
    private int already_here = 0;
    private UserInfo    user;
    private MatchInfo   mMatch;
    private List<MatchInfo.Team>    teams;
    private HashMap<MatchInfo.Team, List<User>> Luser;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;

    public static MatchProfileFragment newInstance(String match_id, UserInfo user)
    {
        MatchProfileFragment instance = new MatchProfileFragment();
        instance.user = user;
        instance.match_id = match_id;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View mView = inflater.inflate(R.layout.match_profile_fragment,container, false);

        expListView = (ExpandableListView) mView.findViewById(R.id.expandableListView);

        Button  confirm = (Button) mView.findViewById(R.id.reservate);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //join or leave match*
                if (leave == true)
                {

                }
                else if (join == true)
                {

                }

            }
        });


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getContext(),
                        teams.get(groupPosition)
                                + " : "
                                + Luser.get(
                                teams.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                if (user.get_id().equals(Luser.get(teams.get(groupPosition)).get(childPosition).get_id()))
                {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    leave = true;
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Voulez vous quitter le match?").setPositiveButton("Oui", dialogClickListener)
                            .setNegativeButton("Non", dialogClickListener).show();
                }
                else if (Luser.get(teams.get(groupPosition)).get(childPosition).get_id().equals("addrow"))
                {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    join = true;
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Voulez vous rejoindre le match?").setPositiveButton("Oui", dialogClickListener)
                            .setNegativeButton("Non", dialogClickListener).show();
                }
                return false;
            }
        });

        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getMatchInfo(match_id, user.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MatchInfo>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Weball", e.getMessage());
                    }

                    @Override
                    public final void onNext(MatchInfo response) {
                        String[] days_name = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
                        String[] months_name = {"Jan", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil", "Aou", "Sep", "Oct", "Nov", "Déc"};
                        TextView  fiveName = (TextView) mView.findViewById(R.id.five_name);
                        TextView  status = (TextView) mView.findViewById(R.id.status_match);
                        TextView  date = (TextView) mView.findViewById(R.id.date_match);

                        if (response != null) {
                            mMatch = response;

                            try {
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                                Date dateConvert = format.parse(response.getStartDate());

                                Calendar cal = Calendar.getInstance();

                                cal.setTime(dateConvert);
                                String dateToShow = days_name[cal.get(Calendar.DAY_OF_WEEK)] + " " + cal.get(Calendar.DAY_OF_MONTH) + " " + months_name[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" +
                                        cal.get(Calendar.MINUTE) + " - ";

                                dateConvert = format.parse(response.getEndDate());
                                cal.setTime(dateConvert);
                                dateToShow += cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
                                date.setText(dateToShow);
                            }
                            catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if (response.getCurrentPlayers() < response.getMaxPlayers())
                            {
                                status.setText("EN ATTENTE");
                                status.setTextColor(getResources().getColor(R.color.orange));
                            }
                            else
                            {
                                status.setText("PRÊT");
                                status.setTextColor(getResources().getColor(R.color.green));
                            }
                            fiveName.setText(response.getFive().getName());
                            if (!response.getFive().getPhoto().isEmpty())
                                Picasso.with(getContext()).load(response.getFive().getPhoto()).placeholder(R.drawable.fields_header).fit().centerCrop().into((ImageView) mView.findViewById(R.id.backgroundImage));
                            prepareListData(mMatch);


                            listAdapter = new ExpandableListAdapter(getContext(), teams, Luser, user);

                            // setting list adapter
                            expListView.setAdapter(listAdapter);
                            

                        }
                    }
                });

        return mView;
    }

    private void prepareListData(MatchInfo matchInfo) {
        teams = new ArrayList<MatchInfo.Team>();
        Luser = new HashMap<MatchInfo.Team, List<User>>();

        // Adding child data
        for (int i = 0; i < matchInfo.getTeams().size(); i++)
        {
            teams.add(matchInfo.getTeams().get(i));
        }


        // Adding child data
        List<User> team1 = new ArrayList<User>();
        for (int i = 0; i < matchInfo.getTeams().get(0).getUsers().size(); i++)
        {

            team1.add(new User(matchInfo.getTeams().get(0).getUsers().get(i).get_id(), matchInfo.getTeams().get(0).getUsers().get(i).getFullName(),
                    matchInfo.getTeams().get(0).getUsers().get(i).getPhoto()));

            if (matchInfo.getTeams().get(0).getUsers().get(i).get_id().equals(user.get_id()))
                already_here = 1;
        }


        List<User> team2 = new ArrayList<User>();
        for (int i = 0; i < matchInfo.getTeams().get(1).getUsers().size(); i++)
        {
            team2.add(new User(matchInfo.getTeams().get(1).getUsers().get(i).get_id(), matchInfo.getTeams().get(1).getUsers().get(i).getFullName(),
                    matchInfo.getTeams().get(1).getUsers().get(i).getPhoto()));
            if (matchInfo.getTeams().get(1).getUsers().get(i).get_id().equals(user.get_id()))
                already_here = 1;
        }

        if (matchInfo.getTeams().get(0).getCurrentPlayers() < matchInfo.getMaxPlayers() / 2 && already_here == 0)
        {
            team1.add(new User("addrow", "", ""));
        }
        if (matchInfo.getTeams().get(1).getCurrentPlayers() < matchInfo.getMaxPlayers() / 2 && already_here == 0)
        {
            team2.add(new User("addrow", "", ""));
        }

        Luser.put(teams.get(0), team1); // Header, Child data
        Luser.put(teams.get(1), team2);
    }
    
    public class User
    {
        private String _id;
        private String  fullName;
        private String  photo;

        public User(String _id, String fullName, String photo) {
            this._id = _id;
            this.fullName = fullName;
            this.photo = photo;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
    

}
