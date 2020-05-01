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

    private WarehouseApplication application;

    private ListView shipmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipment_report);


        application = (WarehouseApplication)getApplication();
        shipmentList = findViewById(R.id.shipmentList);

        //todo: only one item passed??
        ShipmentAdapter adapter = new ShipmentAdapter(this, application.getAllShipments());
        shipmentList.setAdapter(adapter);

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
