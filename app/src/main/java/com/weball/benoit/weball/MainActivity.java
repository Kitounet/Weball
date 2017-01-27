package com.weball.benoit.weball;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;
/**
 * Created by benoi on 08/12/2015.
 */
public class MainActivity extends AppCompatActivity{

    private SliderLayout mSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button start_text = (Button) findViewById(R.id.start_text);
        start_text.setOnClickListener(new View.OnClickListener()
        {
            @Override
        public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, loginPageActivity.class);
                startActivity(intent);
            }
        });

    }


}
