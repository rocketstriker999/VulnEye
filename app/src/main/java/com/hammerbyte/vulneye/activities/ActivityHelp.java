package com.hammerbyte.vulneye.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hammerbyte.vulneye.R;


public class ActivityHelp extends AppCompatActivity implements View.OnClickListener {


    ImageView Q1_Expander, Q2_Expander, Q3_Expander;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Q1_Expander = findViewById(R.id.Q1_Expand_Button);
        Q2_Expander = findViewById(R.id.Q2_Expand_Button);
        Q3_Expander = findViewById(R.id.Q3_Expand_Button);

        Q1_Expander.setOnClickListener(this);
        Q2_Expander.setOnClickListener(this);
        Q3_Expander.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Q1_Expand_Button:
                if (findViewById(R.id.Q1_Answer).getVisibility() == View.GONE) {
                    findViewById(R.id.Q1_Answer).setVisibility(View.VISIBLE);
                    Q1_Expander.setImageResource(R.drawable.vector_up);
                } else {
                    findViewById(R.id.Q1_Answer).setVisibility(View.GONE);
                    Q1_Expander.setImageResource(R.drawable.vector_down);
                }

                break;

            case R.id.Q2_Expand_Button:
                if (findViewById(R.id.Q2_Answer).getVisibility() == View.GONE) {
                    findViewById(R.id.Q2_Answer).setVisibility(View.VISIBLE);
                    Q2_Expander.setImageResource(R.drawable.vector_up);
                } else {
                    findViewById(R.id.Q2_Answer).setVisibility(View.GONE);
                    Q2_Expander.setImageResource(R.drawable.vector_down);
                }
                break;

            case R.id.Q3_Expand_Button:
                if (findViewById(R.id.Q3_Answer).getVisibility() == View.GONE) {
                    findViewById(R.id.Q3_Answer).setVisibility(View.VISIBLE);
                    Q3_Expander.setImageResource(R.drawable.vector_up);
                } else {
                    findViewById(R.id.Q3_Answer).setVisibility(View.GONE);
                    Q3_Expander.setImageResource(R.drawable.vector_down);
                }
                break;

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
