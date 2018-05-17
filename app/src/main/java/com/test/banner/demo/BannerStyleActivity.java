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

public class BannerStyleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Banner banner;
    Spinner spinnerStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_style);
        banner = (Banner) findViewById(R.id.banner);
        spinnerStyle = (Spinner) findViewById(R.id.spinnerStyle);
        spinnerStyle.setOnItemSelectedListener(this);

        //默认是CIRCLE_INDICATOR
        banner.setImagesWithTitles(App.images, App.titles)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        IndicatorConfig.Builder builder = new IndicatorConfig.Builder();
        switch (position) {
            case 0:
                builder.style(Constants.NOT_INDICATOR);
                break;
            case 1:
                builder.style(Constants.CIRCLE_INDICATOR);
                break;
            case 2:
                builder.style(Constants.NUM_INDICATOR);
                break;
            case 3:
                builder.style(Constants.NUM_INDICATOR_TITLE);
                break;
            case 4:
                builder.style(Constants.CIRCLE_INDICATOR_TITLE);
                break;
            case 5:
                builder.style(Constants.CIRCLE_INDICATOR_TITLE_INSIDE);
                break;
        }
        banner.setIndicatorConfig(builder.build());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
