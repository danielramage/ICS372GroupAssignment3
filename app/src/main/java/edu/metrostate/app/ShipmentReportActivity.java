package edu.metrostate.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import edu.metrostate.app.model.WarehouseApplication;

public class ShipmentReportActivity extends AppCompatActivity {

    private Button home;

    private Button getShipments;

    private WarehouseApplication application;

    private ListView shipmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipment_report);


        application = (WarehouseApplication)getApplication();
        application.onCreate();

        shipmentList = findViewById(R.id.shipmentList);

        //todo: only one item passed??
        ShipmentAdapter adapter = new ShipmentAdapter(this, application.getAllShipments());
        shipmentList.setAdapter(adapter);


        getShipments = (Button) findViewById(R.id.getShipments);
        getShipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListActivity();
            }
        });


        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity();
            }
        });
    }

    //todo: returns back to home screen
    public void showListActivity() {
//        Intent intent = new Intent(this, this);   //no need since not navigating to diff. activity
//        startActivity(intent);

      //  System.out.println("Printing data..");
    }

    //todo: returns back to home screen
    public void homeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
