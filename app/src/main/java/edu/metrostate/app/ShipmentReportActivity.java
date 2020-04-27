package edu.metrostate.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShipmentReportActivity extends AppCompatActivity {

    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_report);

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity();
            }
        });
    }

    //todo: returns back to home screen
    public void homeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
