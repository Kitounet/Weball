package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.requestClass.UserInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

/**
 * Created by benoi on 14/07/2016.
 */
public class ReservateFragment extends Fragment {
    private onReservateListener myListener;
    private info    infos;
    private UserInfo    mUserInfo;
    Calendar    cal;
    private FieldProfile    five;
    private String          date;

    public static ReservateFragment newInstance(UserInfo mUserInfo, FieldProfile five, String date)
    {
        ReservateFragment instance = new ReservateFragment();
        instance.mUserInfo = mUserInfo;
        instance.five = five;
        instance.date = date;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.reservation_information,container, false);

        ImageView   name_edit = (ImageView) mView.findViewById(R.id.name_change_img);
        ImageView   duration_edit = (ImageView) mView.findViewById(R.id.duration_change_img);
        ImageView   time_edit = (ImageView) mView.findViewById(R.id.time_change_img);

        final TextView    name = (TextView) mView.findViewById(R.id.name);
        final TextView    duration = (TextView) mView.findViewById(R.id.duration);
        final TextView    time = (TextView) mView.findViewById(R.id.time);
        final Switch    private_match = (Switch) mView.findViewById(R.id.switch_private);

        name_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("NOM DU MATCH");
                builder.setMessage("Entrez le nom");
                builder.setView(input);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        name.setText(input.getText());
                    }

                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                    }
                });
                builder.show();
            }
        });

        duration_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] hours = { "1:00", "2:00", "3:00", "4:00", "5:00"};

                final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, hours);


                final Spinner  input = new Spinner(getActivity());
                input.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                input.setAdapter(adp);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("DUREE DU MATCH");
                builder.setMessage("Selectionnez le temps");
                builder.setView(input);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        duration.setText(input.getSelectedItem().toString());
                    }

                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                    }
                });
                builder.show();
            }
        });


        time_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> hours = new ArrayList<String>();
                int day_of_week = 0;
                Log.d("TEST", date);
                try {
                    DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                    Date dateConvert = format.parse(date);

                    cal = Calendar.getInstance();

                    cal.setTime(dateConvert);
                    day_of_week = cal.get(Calendar.DAY_OF_WEEK);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d("TEST", String.valueOf(five.getDays().size()) + " ==== " + String.valueOf(day_of_week));
                List<FieldProfile.Hour> l_hours = five.getDays().get(day_of_week - 1).getHours();

                for (int i = 0; i < l_hours.size(); i++)
                {
                    Double j = l_hours.get(i).getFrom() + 1;
                    int temp = l_hours.get(i).getFrom().intValue();
                    hours.add(String.valueOf(temp) + ":00");
                    while (j <= l_hours.get(i).getTo())
                    {
                        hours.add(String.valueOf(j.intValue()) + ":00");
                        j++;
                    }
                }

                final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, hours);


                final Spinner  input = new Spinner(getActivity());
                input.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                input.setAdapter(adp);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("HORAIRE DU MATCH");
                builder.setMessage("Selectionnez le début du match");
                builder.setView(input);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        time.setText(input.getSelectedItem().toString());
                    }

                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                    }
                });
                builder.show();
            }
        });

        TextView prev = (TextView) mView.findViewById(R.id.cancel);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                myListener.onPrevClicked(0);
            }
        });

        TextView validate = (TextView) mView.findViewById(R.id.validation);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().equals("") && !duration.getText().equals("") && !time.getText().equals(""))
                {
                    String hourbegin = time.getText().toString();
                    int pos = hourbegin.indexOf(":");
                    try {
                        DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                        Date dateConvert = format.parse(date);
                        cal = Calendar.getInstance();
                        cal.setTime(dateConvert);
                        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourbegin.substring(0, pos)));
                        cal.set(Calendar.MINUTE, Integer.parseInt(hourbegin.substring(pos + 1)));
                        final String startDate = cal.getTime().toString();
                        int pos2 = duration.getText().toString().indexOf(":");
                        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourbegin.substring(0, pos)) + Integer.parseInt(duration.getText().toString().substring(0, pos2)));
                        cal.set(Calendar.MINUTE, Integer.parseInt(hourbegin.substring(pos + 1)) + Integer.parseInt(duration.getText().toString().substring(pos2 + 1)));
                        final String endDate = cal.getTime().toString();


                        Log.d("TEST", startDate);
                        Log.d("TEST", endDate);
                        infos = new info(name.getText().toString(), startDate, endDate, false);

                        myListener.onValidateClicked(infos);
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }


                }
                else
                {
                    Toast.makeText(getActivity(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onReservateListener) {
            myListener = (onReservateListener) context;
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

    public interface onReservateListener{
        public void onPrevClicked(int nb);
        public void onValidateClicked(info infos);

    }

    public class info
    {
        String  matchname;
        String  startMatch;
        String  endMatch;
        boolean private_match;

        public info(String matchname, String startMatch, String endMatch, boolean private_match) {
            this.matchname = matchname;
            this.startMatch = startMatch;
            this.endMatch = endMatch;
            this.private_match = private_match;
        }

        public String getMatchname() {
            return matchname;
        }

        public void setMatchname(String matchname) {
            this.matchname = matchname;
        }

        public String getStartMatch() {
            return startMatch;
        }

        public void setStartMatch(String startMatch) {
            this.startMatch = startMatch;
        }

        public String getEndMatch() {
            return endMatch;
        }

        public void setEndMatch(String endMatch) {
            this.endMatch = endMatch;
        }

        public boolean isPrivate_match() {
            return private_match;
        }

        public void setPrivate_match(boolean private_match) {
            this.private_match = private_match;
        }
    }
}
