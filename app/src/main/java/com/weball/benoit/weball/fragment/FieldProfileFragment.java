package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.squareup.picasso.Picasso;
import com.weball.benoit.weball.adapter.ExpandableListAdapter;
import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.requestClass.Matchs;
import com.weball.benoit.weball.row.Message_Row;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.service.WeballService;

import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import hirondelle.date4j.DateTime;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;





/**
 * Created by benoi on 25/02/2016.
 */
public class FieldProfileFragment extends Fragment {
    private onFieldProfileListener myListener;
    private Calendar    myC;
    private String      five_id;
    private FieldProfile myFive;
    private UserInfo mUser;
    private Button      mReservate;
    private CaldroidFragment dialogCaldroidFragment;

    public FieldProfileFragment() {
    }

    public static FieldProfileFragment newInstance(String five_id, UserInfo mUserInfo)
    {
        FieldProfileFragment instance = new FieldProfileFragment();
        instance.five_id = five_id;
        instance.mUser = mUserInfo;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View mView = inflater.inflate(R.layout.field_profile, container, false);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                String selectdate = formatter.format(date);

                myListener.onReservateClicked(myFive, mUser, selectdate);
                dialogCaldroidFragment.onDestroyView();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                super.onChangeMonth(month, year);

            }
        };

        mReservate = (Button) mView.findViewById(R.id.reservate);

        WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
        service.getFiveInfo(five_id, mUser.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FieldProfile>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {

                        Log.e("Weball", e.getMessage());
                        myListener.onImageClicked();
                    }

                    @Override
                    public final void onNext(FieldProfile response) {

                        TextView fivename = (TextView) mView.findViewById(R.id.five_name);
                        TextView total_match = (TextView) mView.findViewById(R.id.number_matchs);
                        TextView hours = (TextView) mView.findViewById(R.id.realHoursTextView);
                        TextView address = (TextView) mView.findViewById(R.id.realAddressTextView);
                        TextView phone = (TextView) mView.findViewById(R.id.realPhonesTextView);
                        TextView price = (TextView) mView.findViewById(R.id.realPricesTextView);

                        myFive = response;
                        if (!response.getName().isEmpty()) {
                            fivename.setText(response.getName());
                            if (response.getNTotalMatchs().toString().length() != 0)
                                total_match.setText(response.getNTotalMatchs().toString());
                            else
                                total_match.setText("0");
                            if (!response.getPhoto().isEmpty())
                                Picasso.with(getContext()).load(response.getPhoto()).placeholder(R.drawable.fields_header).fit().centerCrop().into((ImageView) mView.findViewById(R.id.backgroundImage));
                            address.setText(response.getAddress());
                            phone.setText(response.getPhone());
                            hours.setText(getHours(response.getDays()));
                            price.setText(getPrices(response.getDays()));

                        }
                        else {
                            myListener.onImageClicked();
                        }
                    }
                });

        ImageView prev = (ImageView) mView.findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onImageClicked();
            }
        });
        final Bundle state = savedInstanceState;

        myC = Calendar.getInstance();
        myC.set(Calendar.MONTH, 0);
        myC.set(Calendar.HOUR_OF_DAY, 0);
        myC.set(Calendar.MINUTE, 0);
        myC.set(Calendar.SECOND, 0);
        myC.set(Calendar.DAY_OF_MONTH, 0);
        final String startDate = myC.getTime().toString();
        myC.set(Calendar.YEAR, myC.get(Calendar.YEAR) + 1);
        myC.set(Calendar.HOUR_OF_DAY, 23);
        final String endDate = myC.getTime().toString();


        mReservate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "IN RESERVATE = " + myFive.get_id() + myFive.getName());

                WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                service.getMatches(myFive.get_id(), mUser.getToken(), "startDate", startDate, endDate)
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
                                           HashMap<Date, Drawable>  dateEventColorChange = new HashMap<Date, Drawable>();
                                           if (!response.isEmpty()) {
                                               ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                                               ColorDrawable red = new ColorDrawable(Color.RED);
                                               ColorDrawable orange = new ColorDrawable(getResources().getColor(R.color.orange));


                                               for (int i = 0; i < response.size(); i++) {
                                                   if (checkDate(response.get(i).getStartDate())) {
                                                       if (response.get(i).getMaxPlayers() - response.get(i).getCurrentPlayers() > 0) {
                                                           try {
                                                               if (!checkInsideDate(response.get(i).getStartDate(), dateEventColorChange)) {
                                                                   int j = 1;
                                                                   int free = 1;
                                                                   int full = 0;
                                                                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                                                                   Date date = format.parse(response.get(i).getStartDate());
                                                                   if (response.size() > i + j)
                                                                   {
                                                                       int check = 0;
                                                                       Date date2 = format.parse(response.get(i + j).getStartDate());
                                                                       Calendar cal = Calendar.getInstance();
                                                                       cal.setTime(date);
                                                                       Calendar cal2 = Calendar.getInstance();
                                                                       cal2.setTime(date2);
                                                                       while (cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) && check == 0) {
                                                                           if (response.get(i + j).getMaxPlayers() > response.get(i + j).getCurrentPlayers()) {
                                                                               free++;
                                                                               j++;
                                                                               if (response.size() > i + j){
                                                                                   date2 = format.parse(response.get(i + j).getStartDate());
                                                                                   cal2.setTime(date2);
                                                                               }
                                                                               else
                                                                                   check = 1;
                                                                           } else {
                                                                               full++;
                                                                               j++;
                                                                               if (response.size() > i + j) {
                                                                                   date2 = format.parse(response.get(i + j).getStartDate());
                                                                                   cal2.setTime(date2);
                                                                               }
                                                                               else
                                                                                   check = 1;
                                                                           }
                                                                       }
                                                                   }

                                                                   if (full > free)
                                                                       dateEventColorChange.put(date, green);
                                                                   else
                                                                       dateEventColorChange.put(date, orange);

                                                               }
                                                               else{
                                                                   int j = 1;
                                                                   int free = 0;
                                                                   int full = 1;
                                                                   int check = 0;
                                                                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                                                                   Date date = format.parse(response.get(i).getStartDate());
                                                                   if (response.size() > i + j)
                                                                   {
                                                                       Date date2 = format.parse(response.get(i + j).getStartDate());
                                                                       Calendar cal = Calendar.getInstance();
                                                                       cal.setTime(date);
                                                                       Calendar cal2 = Calendar.getInstance();
                                                                       cal2.setTime(date2);
                                                                       while (cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) && check == 0) {
                                                                           if (response.get(i + j).getMaxPlayers() > response.get(i + j).getCurrentPlayers()) {
                                                                               free++;
                                                                               j++;
                                                                               if (response.size() > i + j){
                                                                                   date2 = format.parse(response.get(i + j).getStartDate());
                                                                                   cal2.setTime(date2);
                                                                               }
                                                                               else
                                                                                   check = 1;

                                                                           } else {
                                                                               full++;
                                                                               j++;
                                                                               if (response.size() > i + j){
                                                                                   date2 = format.parse(response.get(i + j).getStartDate());
                                                                                   cal2.setTime(date2);
                                                                               }
                                                                               else
                                                                                   check = 1;
                                                                           }
                                                                       }
                                                                   }

                                                                   if (full > free)
                                                                       dateEventColorChange.put(date, green);
                                                                   else
                                                                       dateEventColorChange.put(date, orange);
                                                               }
                                                           } catch (ParseException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                   } else {
                                                       try {
                                                           int j = 1;
                                                           int full = 0;
                                                           int free = 0;
                                                           DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                                                           Date date = format.parse(response.get(i).getStartDate());
                                                           Calendar cal = Calendar.getInstance();
                                                           cal.setTime(date);
                                                           Calendar cal2 = Calendar.getInstance();
                                                           if ((cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) && (cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))){
                                                               if (response.size() > i + j){
                                                                   int check = 0;
                                                                   Date date2 = format.parse(response.get(i + j).getStartDate());
                                                                   cal2.setTime(date2);
                                                                   while ((cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) && (cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) && check == 0) {
                                                                       if (checkDate(response.get(i + j).getStartDate())){
                                                                           if ((response.get(i + j).getMaxPlayers() - response.get(i + j).getCurrentPlayers()) > 0){
                                                                               j++;
                                                                               free++;
                                                                               if (response.size() > i + j) {
                                                                                   date2 = format.parse(response.get(i + j).getStartDate());
                                                                                   cal2.setTime(date2);
                                                                               }
                                                                               else
                                                                                   check = 1;
                                                                           }
                                                                           else{
                                                                               j++;
                                                                               full++;
                                                                               if (response.size() > i + j){
                                                                                   date2 = format.parse(response.get(i + j).getStartDate());
                                                                                   cal2.setTime(date2);
                                                                               }
                                                                               else
                                                                                   check = 1;
                                                                           }
                                                                       }
                                                                   }
                                                               }

                                                               if (full == 0 && free == 0)
                                                                   dateEventColorChange.put(date, red);
                                                               else if (full > free)
                                                                   dateEventColorChange.put(date, green);
                                                               else if (free > full)
                                                                   dateEventColorChange.put(date, orange);
                                                           }
                                                           else {
                                                               dateEventColorChange.put(date, red);
                                                           }

                                                       } catch (ParseException e) {
                                                           e.printStackTrace();
                                                       }
                                                   }
                                               }
                                           } else {
                                           }


                                           // Setup caldroid to use as dialog
                                           dialogCaldroidFragment = new CaldroidFragment();
                                           dialogCaldroidFragment.setCaldroidListener(listener);
                                           if (dateEventColorChange.size() != 0)
                                            dialogCaldroidFragment.setBackgroundDrawableForDates(dateEventColorChange);

                                           // If activity is recovered from rotation
                                           final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
                                           if (state != null) {
                                               dialogCaldroidFragment.restoreDialogStatesFromKey(
                                                       getFragmentManager(), state,
                                                       "DIALOG_CALDROID_SAVED_STATE", dialogTag);
                                               Bundle args = dialogCaldroidFragment.getArguments();
                                               if (args == null) {
                                                   args = new Bundle();
                                                   dialogCaldroidFragment.setArguments(args);
                                               }
                                           } else {
                                               // Setup arguments
                                               Bundle bundle = new Bundle();
                                               bundle.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
                                               // Setup dialogTitle
                                               dialogCaldroidFragment.setArguments(bundle);
                                           }

                                           dialogCaldroidFragment.show(getFragmentManager(),
                                                   dialogTag);


                                       }
                                   });


//                myListener.onReservateClicked(myFive, mUser);

            }
        });

        return mView;
    }


    private boolean    checkInsideDate(String date, HashMap<Date, Drawable> listDate) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            Date dateConvert = format.parse(date);

            Calendar cal = Calendar.getInstance();

            cal.setTime(dateConvert);

            for (Date key : listDate.keySet())
            {
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(key);
                if ((cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) && (cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))){
                    return true;
                }
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean    checkDate(String date)
    {
        Log.d("TEST", "checkDate = " + date);
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

    private String  getHours(List<FieldProfile.Day> days)
    {
        String[]    tab_d = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
        String message_final = "";
        double case_of = 0;
        int delay = 0;

        for (int i = 0; i < days.size(); i++)
        {
            List<FieldProfile.Hour> day_hours = days.get(i).getHours();
            if (i == 0) {
                message_final += tab_d[i] + ": ";
                case_of = days.get(i).getDay();
                for (int j = 0; j < day_hours.size(); j++)
                {
                    if (j == 0)
                        message_final += day_hours.get(j).getFrom().toString() + " - " + day_hours.get(j).getTo().toString();
                    else
                        message_final += ", " + day_hours.get(j).getFrom().toString() + " - " + day_hours.get(j).getTo().toString();
                }
            }
            else
                if (case_of != days.get(i).getDay()) {
                    message_final += "\n" + tab_d[i + delay] + ": ";
                    case_of = days.get(i).getDay();
                    for (int j = 0; j < day_hours.size(); j++)
                    {
                        if (j == 0)
                            message_final += day_hours.get(j).getFrom().toString() + " - " + day_hours.get(j).getTo().toString();
                        else
                            message_final += ", " + day_hours.get(j).getFrom().toString() + " - " + day_hours.get(j).getTo().toString();
                    }
                }
                else {
                    case_of = days.get(i).getDay();
                    delay -= 1;
                }
        }

        return message_final;
    }

    private String  getPrices(List<FieldProfile.Day> days)
    {
        String[]    tab_d = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
        int case_of = 0;
        int delay = 0;
        String message_final = "";
        if (days.size() == 0)
        for (int i = 0; i < days.size(); i++)
        {
            List<FieldProfile.Hour> day_hours = days.get(i).getHours();
            if (i == 0) {
                message_final += tab_d[i] + ": ";
                case_of = days.get(i).getDay();
                for (int j = 0; j < day_hours.size(); j++)
                {
                    message_final += "\n\t" + day_hours.get(j).getFrom().toString() + " - " + day_hours.get(j).getTo().toString() + " : " + day_hours.get(j).getPrice().toString() + "€";
                }
            }
            else
                if (case_of != days.get(i).getDay()) {
                    message_final += "\n" + tab_d[i + delay] + ": ";
                    case_of = days.get(i).getDay();
                    for (int j = 0; j < day_hours.size(); j++)
                    {
                        message_final += "\n\t" + day_hours.get(j).getFrom().toString() + " - " + day_hours.get(j).getTo().toString() + " : " + day_hours.get(j).getPrice().toString() + "€";
                    }
                }
                else {
                    case_of = days.get(i).getDay();
                    delay -= 1;
                }
        }

        return message_final;
    }

    private List<Message_Row> generateRows(List<Message_Row> message_rows)
    {
        List<Message_Row> rows = new ArrayList<Message_Row>();



        for (int i = 0; i < message_rows.size(); i++)
        {
            rows.add(new Message_Row(message_rows.get(i).getUser_id(), message_rows.get(i).getUsername(), message_rows.get(i).getMessage(), message_rows.get(i).getDate()));
        }
        return rows;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onFieldProfileListener) {
            myListener = (onFieldProfileListener) context;
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



    public interface onFieldProfileListener{
        public void onImageClicked();
        public void onReservateClicked(FieldProfile myFive, UserInfo user, String date);
    }

}
