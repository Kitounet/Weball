package com.weball.benoit.weball;

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
 * Created by benoi on 25/02/2016.
 */
public class FieldProfileFragment extends Fragment {
//    private onTextViewListener myListener;
    private FiveInfo    mFive;

    public FieldProfileFragment() {
    }

    public static FieldProfileFragment newInstance(FiveInfo mFiveInfo)
    {
        FieldProfileFragment instance = new FieldProfileFragment();
        instance.mFive = mFiveInfo;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View mView = inflater.inflate(R.layout.field_profile,container, false);

        return mView;
    }
}
