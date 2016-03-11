package com.robinhood.spark.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.robinhood.spark.SparkAdapter;
import com.robinhood.spark.Sparkline;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RandomizedAdapter adapter;
    private TextView scrubInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sparkline sparkline = (Sparkline) findViewById(R.id.sparkline);

        adapter = new RandomizedAdapter();
        sparkline.setAdapter(adapter);
        sparkline.setScrubListener(new Sparkline.OnScrubListener() {
            @Override
            public void onScrubbed(Object value) {
                if (value == null) {
                    scrubInfoTextView.setText(R.string.scrub_empty);
                } else {
                    scrubInfoTextView.setText(getString(R.string.scrub_format, value));
                }
            }
        });

        View button = findViewById(R.id.random_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.randomize();
            }
        });

        scrubInfoTextView = (TextView) findViewById(R.id.scrub_info_textview);
    }

    public static class RandomizedAdapter extends SparkAdapter {
        private float[] yData;
        private Random random;

        public RandomizedAdapter() {
            random = new Random();
            yData = new float[50];
            randomize();
        }

        public void randomize() {
            for (int i = 0, count = yData.length; i < count; i++) {
                yData[i] = random.nextFloat();
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return yData.length;
        }

        @Override
        public Object getItem(int index) {
            return yData[index];
        }

        @Override
        public float getY(int index) {
            return yData[index];
        }
    }
}
