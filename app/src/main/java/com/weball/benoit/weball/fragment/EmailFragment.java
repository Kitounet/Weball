package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weball.benoit.weball.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by benoi on 20/01/2016.
 */
public class EmailFragment extends Fragment{

    private onTextViewListener myListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View mView = inflater.inflate(R.layout.subscribe_mail,container, false);

        final EditText email = (EditText) mView.findViewById(R.id.user_email);

        TextView next = (TextView) mView.findViewById(R.id.TextView2);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                if (!mail.equals(""))
                {
                    List<String> infos = new Vector<String>();

                    infos.add(mail);
                    myListener.TextViewClicked(1, infos);
                }
                else
                    myListener.showResult("Veuillez remplir tous les champs");
            }
        });

        ImageView prev = (ImageView) mView.findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                myListener.ComeBackHome();
            }
        });

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onTextViewListener) {
            myListener = (onTextViewListener) context;
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

    public interface onTextViewListener{
        public void TextViewClicked(int nb, List infos);
        public void ComeBackHome();
        public void showResult(String msg);
    }
}
