package com.test.banner.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.test.banner.App;
import com.test.banner.R;
import com.test.banner.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.config.Constants;
import com.youth.banner.config.IndicatorConfig;

public class IndicatorPositionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Banner banner;
    Spinner spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_position);
        banner = (Banner) findViewById(R.id.banner);
        spinnerPosition = (Spinner) findViewById(R.id.spinnerPosition);
        spinnerPosition.setOnItemSelectedListener(this);

        banner.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        IndicatorConfig.Builder builder = new IndicatorConfig.Builder();
        switch (position) {
            case 0:
                builder.gravity(Constants.LEFT);
                break;
            case 1:
                builder.gravity(Constants.CENTER);
                break;
            case 2:
                builder.gravity(Constants.RIGHT);
                break;
        }
        banner.setIndicatorConfig(builder.build());
        banner.start();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
