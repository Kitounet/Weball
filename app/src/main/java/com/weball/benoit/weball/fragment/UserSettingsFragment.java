package com.weball.benoit.weball.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.UpdateAnswer;
import com.weball.benoit.weball.requestClass.UserInfo;
import com.weball.benoit.weball.requestClass.UserMe;
import com.weball.benoit.weball.requestClass.update_body;
import com.weball.benoit.weball.requestClass.update_password;
import com.weball.benoit.weball.requestClass.user;
import com.weball.benoit.weball.service.ServiceFactory;
import com.weball.benoit.weball.service.WeballService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 14/05/2016.
 */
public class UserSettingsFragment extends Fragment {
    private UserMe mUser;
    private String token;
    private String register_date;
    private onSettingsListener  myListener;

    public UserSettingsFragment() {
    }

    public static UserSettingsFragment newInstance(UserMe mUserMe, String token)
    {
        UserSettingsFragment instance = new UserSettingsFragment();
        instance.mUser = mUserMe;
        instance.token = token;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String[] months = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"};
        final View mView = inflater.inflate(R.layout.settings_fragment, container, false);
        ImageView   name_edit = (ImageView) mView.findViewById(R.id.name_change_img);
        ImageView   email_edit = (ImageView) mView.findViewById(R.id.email_change_img);
        ImageView   birthday_edit = (ImageView) mView.findViewById(R.id.birthday_change_img);
        ImageView   city_edit = (ImageView) mView.findViewById(R.id.city_change_img);
        ImageView   password_edit = (ImageView) mView.findViewById(R.id.password_change_img);
        ImageView   country_edit = (ImageView) mView.findViewById(R.id.country_change_img);
        Button      apply   = (Button) mView.findViewById(R.id.button_apply);
        ImageView   back_picture = (ImageView) mView.findViewById(R.id.prev);
        final TextView    name = (TextView) mView.findViewById(R.id.name);
        final TextView    email = (TextView) mView.findViewById(R.id.email);
        final TextView    birthday = (TextView) mView.findViewById(R.id.birthday);
        final TextView    city = (TextView) mView.findViewById(R.id.city);
        final TextView     password = (TextView) mView.findViewById(R.id.password);
        final TextView  country = (TextView) mView.findViewById(R.id.country);

        name_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("NOM COMPLET");
                builder.setMessage("Entrez votre nom complet");
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

        birthday_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String month_s;
                                String day_s;
                                if (monthOfYear < 10) {
                                    month_s = "0" + String.valueOf(monthOfYear + 1);
                                }
                                else {
                                    month_s = String.valueOf(monthOfYear + 1);
                                }
                                if (dayOfMonth < 10)
                                {
                                    day_s = "0" + String.valueOf(dayOfMonth);
                                }
                                else
                                {
                                    day_s = String.valueOf(dayOfMonth);
                                }
                                register_date = String.valueOf(year) + "," + month_s + "," + day_s;
                                birthday.setText(String.valueOf(dayOfMonth) + " " + months[monthOfYear] + " " + String.valueOf(year));

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        email_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("EMAIL");
                builder.setMessage("Entrez votre email");
                builder.setView(input);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        email.setText(input.getText());
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

        city_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("VILLE");
                builder.setMessage("Entrez votre ville");
                builder.setView(input);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        city.setText(input.getText());
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

        country_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("PAYS");
                builder.setMessage("Entrez votre pays");
                builder.setView(input);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        country.setText(input.getText());
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

        password_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText input2 = new EditText(getActivity());
                input2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                layout.addView(input);
                layout.addView(input2);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("MOT DE PASSE");
                builder.setMessage("entrez deux fois votre nouveau mot de passe");
                builder.setView(layout);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id)
                    {
                        String p1 = input.getText().toString();
                        String p2 = input.getText().toString();
                        if (!p1.equals("") && !p2.equals("") && p1.equals(p2))
                            password.setText(input.getText());
                        else
                            Toast.makeText(getActivity(), "Les mots de passes ne sont pas identiques", Toast.LENGTH_SHORT).show();
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

        back_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.onPrevPressed();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         //String email, String fullName, String birthday, String city, String country
                                         update_body obj = new update_body(email.getText().toString(), name.getText().toString(), register_date, city.getText().toString(), country.getText().toString());
                                         HashMap<String, update_body> tosend = new HashMap<String, update_body>();
                                         tosend.put("user", obj);

                                         WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                                         service.updateUserInfo(token, tosend)
                                                 .subscribeOn(Schedulers.newThread())
                                                 .observeOn(AndroidSchedulers.mainThread())
                                                 .subscribe(new Subscriber<UpdateAnswer>() {
                                                     @Override
                                                     public final void onCompleted() {
                                                         // do nothing
                                                     }

                                                     @Override
                                                     public final void onError(Throwable e) {
                                                         Log.e("Weball", e.getMessage());
                                                     }

                                                     @Override
                                                     public final void onNext(UpdateAnswer response) {
                                                         if (response != null) {
                                                             Log.d("TEST", response.getEmail());
                                                         }
                                                     }
                                                 });
                                         if (!password.getText().toString().equals("")) {
                                             Log.d("TEST", "START PASSWORD CHANGE!");
                                             update_password obj2 = new update_password(password.getText().toString());
                                             HashMap<String, update_password> tosend2 = new HashMap<String, update_password>();
                                             tosend2.put("user", obj2);

                                             service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                                             service.updateUserPassword(token, tosend2)
                                                     .subscribeOn(Schedulers.newThread())
                                                     .observeOn(AndroidSchedulers.mainThread())
                                                     .subscribe(new Subscriber<UpdateAnswer>() {
                                                         @Override
                                                         public final void onCompleted() {
                                                             // do nothing
                                                         }

                                                         @Override
                                                         public final void onError(Throwable e) {
                                                             Log.e("Weball", e.getMessage());
                                                         }

                                                         @Override
                                                         public final void onNext(UpdateAnswer response) {
                                                             if (response != null) {
                                                                 Log.d("TEST", response.getEmail());
                                                             }
                                                         }
                                                     });

                                         }

                                         service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                                         service.getUserInfo(token)
                                                 .subscribeOn(Schedulers.newThread())
                                                 .observeOn(AndroidSchedulers.mainThread())
                                                 .subscribe(new Subscriber<UserMe>() {
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
                                                                    if (response != null) {
                                                                        myListener.onApplyChange(response);
                                                                    }

                                                                }
                                                            }
                                                 );
                                     }
                                 });

        name.setText(mUser.getFullName());
        country.setText(mUser.getCountry());
        String date = mUser.getBirthday();
        date = date.substring(0, 10);
        register_date = date;
        String month = date.substring(5, 7);
        int month_nb = Integer.parseInt(month);
        date = date.substring(8) + " " + months[month_nb - 1] + " " + date.substring(0, 4);
        birthday.setText(date);
        city.setText(mUser.getCity());
        return mView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSettingsListener) {
            myListener = (onSettingsListener) context;
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

    public interface onSettingsListener{
        public void onApplyChange(UserMe response);
        public void onPrevPressed();
    }
}
