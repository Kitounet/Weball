package com.weball.benoit.weball;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by benoi on 20/01/2016.
 */
public class SubscribeSuccessFragment extends Fragment{

    private onTextViewListener5 myListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.subscribe_success,container, false);
        TextView next = (TextView) mView.findViewById(R.id.TextView2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.ComeBackHome();
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
        if (context instanceof onTextViewListener5) {
            myListener = (onTextViewListener5) context;
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

    public interface onTextViewListener5{
        public void ComeBackHome();
    }
}
