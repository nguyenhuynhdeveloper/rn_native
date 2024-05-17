package com.rn_native.open_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.facebook.react.bridge.ReactApplicationContext;
import com.rn_native.R;
import com.rn_native.module_doc.CalendarModule;

//import com.rn_native.R;

//import com.rn_native.R;

// Đây là 1 file vẽ ra cái activity 
public class OpenActivity extends AppCompatActivity {

//    CalendarModule calendarModule = new CalendarModule( );

    Button btn_trigger_event ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        btn_trigger_event = findViewById(R.id.btn_trigger_event);
        btn_trigger_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarModule.sendEvent("EventReminder", "eventData");
            }
        });
    }
}