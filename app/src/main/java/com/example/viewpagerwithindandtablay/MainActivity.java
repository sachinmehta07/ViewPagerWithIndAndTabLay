package com.example.viewpagerwithindandtablay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout indicatorContainer;

    private int[] images = new int[] {
            R.drawable.dice_1,
            R.drawable.dice_1,
            R.drawable.dice_1,
            R.drawable.dice_1,
            R.drawable.dice_1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        indicatorContainer = findViewById(R.id.indicator);

        ImagePagerAdapter adapter = new ImagePagerAdapter(Arrays.asList(images));
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicator(position);
            }
        });

        for (int i = 0; i < adapter.getItemCount(); i++) {
            View indicator = new View(this);
            int size = getResources().getDimensionPixelSize(R.dimen.indicator_size);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
            layoutParams.setMargins(16, 0, 16, 0);
            indicator.setLayoutParams(layoutParams);
            indicator.setBackground(getDrawable(R.drawable.indicator_inactive));
            indicatorContainer.addView(indicator);
        }

        updateIndicator(0);
    }

    private void updateIndicator(int position) {
        for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
            View indicator = indicatorContainer.getChildAt(i);
            if (i == position) {
                indicator.setBackground(getDrawable(R.drawable.indicator_active));
            } else {
                indicator.setBackground(getDrawable(R.drawable.indicator_inactive));
            }
        }
    }

}
