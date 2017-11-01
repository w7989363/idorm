package com.example.wentianlin.idorm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by wentianlin on 2017/11/1.
 */

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView stuNumText;
    private Spinner buildingSpin;
    private Spinner dormSpin;
    private String[] buildingData = new String[]{"5","9","13","14"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stuNumText = (TextView)findViewById(R.id.stuNumText);
        stuNumText.setText("123213");
        buildingSpin = (Spinner)findViewById(R.id.buildingNumSpin);
        buildingSpin.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,buildingData));
        buildingSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String buildingStr = buildingData[position];
                Log.d("debug",buildingStr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
