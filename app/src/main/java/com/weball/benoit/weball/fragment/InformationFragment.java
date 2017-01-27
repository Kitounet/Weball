package com.weball.benoit.weball.fragment;

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

import com.weball.benoit.weball.R;

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

        final EditText  fullname = (EditText) mView.findViewById(R.id.user_lastname);
        final EditText  birthday = (EditText) mView.findViewById(R.id.user_birthday);

        TextView next = (TextView) mView.findViewById(R.id.TextView2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = fullname.getText().toString();
                 String second = birthday.getText().toString();
                Log.d("TEST", second);
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,mm,dd");
                Date convertedDate = null;
                try {
                    convertedDate = inputFormat.parse(second);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                second = dateFormat.format(convertedDate);

                if (!first.equals("")  && !second.equals(""))
                {
                    List<String> infos = new Vector<String>();

                    infos.add(first);
                    infos.add(second);
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
