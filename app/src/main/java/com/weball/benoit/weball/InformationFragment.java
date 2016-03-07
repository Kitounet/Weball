package com.weball.benoit.weball;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by benoi on 20/01/2016.
 */
public class InformationFragment extends Fragment{

    private onTextViewListener2 myListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.subscribe_information,container, false);

        final EditText  firstname = (EditText) mView.findViewById(R.id.user_firstname);
        final EditText  lastname = (EditText) mView.findViewById(R.id.user_lastname);
        final EditText  birthday = (EditText) mView.findViewById(R.id.user_birthday);

        TextView next = (TextView) mView.findViewById(R.id.TextView2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = firstname.getText().toString();
                String second = lastname.getText().toString();
                String third = birthday.getText().toString();
                Log.d("TEST", third);
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,mm,dd");
                Date convertedDate = null;
                try {
                    convertedDate = inputFormat.parse(third);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                third = dateFormat.format(convertedDate);
                Log.d("TESSST", third);

                if (!first.equals("")  && !second.equals("") && !third.equals(""))
                {
                    List<String> infos = new Vector<String>();

                    infos.add(first);
                    infos.add(second);
                    infos.add(third);
                    myListener.TextViewClicked(2, infos);
                }
                else
                    myListener.showResult("Veuillez remplir tous les champs");
            }
        });

        ImageView prev = (ImageView) mView.findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                myListener.ImageViewClicked(0);
            }
        });


        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onTextViewListener2) {
            myListener = (onTextViewListener2) context;
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

    public interface onTextViewListener2{
        public void TextViewClicked(int nb, List infos);
        public void ImageViewClicked(int nb);
        public void showResult(String msg);
    }
}
