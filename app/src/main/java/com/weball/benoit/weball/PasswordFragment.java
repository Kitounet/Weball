package com.weball.benoit.weball;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

/**
 * Created by benoi on 20/01/2016.
 */
public class PasswordFragment extends Fragment{

    private onTextViewListener3 myListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.subscribe_password,container, false);

        final EditText p1 = (EditText) mView.findViewById(R.id.user_password);
        final EditText p2 = (EditText) mView.findViewById(R.id.user_password2);
        TextView next = (TextView) mView.findViewById(R.id.TextView2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = p1.getText().toString();
                String second = p2.getText().toString();
                if (!first.equals("") && !second.equals(""))
                {
                    if (first.equals(second)) {
                        List<String> infos = new Vector<String>();
                        infos.add(first);
                        infos.add(second);
                        myListener.TextViewClicked(3, infos);
                    }
                    else
                        myListener.showResult("Vos mots de passe ne sont pas identiques");
                }
                else
                    myListener.showResult("Veuillez remplir tous les champs");
            }
        });
        ImageView prev = (ImageView) mView.findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                myListener.ImageViewClicked(1);
            }
        });

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onTextViewListener3) {
            myListener = (onTextViewListener3) context;
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

    public interface onTextViewListener3{
        public void TextViewClicked(int nb, List infos);
        public void ImageViewClicked(int nb);
        public void showResult(String msg);
    }
}
