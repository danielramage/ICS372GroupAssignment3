package edu.metrostate.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.metrostate.app.model.WarehouseApplication;

public class MainActivity extends AppCompatActivity {

    private static final int WRITE_STORAGE_PERMISSION_REQUEST = 5;

    private WarehouseApplication application;

    //todo: reference to Button objects
    private Button shipmentReport; //refactor later to put in local scope (inside the onCreate method)
    private Button addShipment;
    private Button manageShipment;
    private Button importShipment;

    private Button warehouseReport;
    private Button addWarehouse;
    private Button manageWarehouse;

    /**
     * Creates the view for the application
     * @param savedInstanceState saved state information for the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //  Check Storage Permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_STORAGE_PERMISSION_REQUEST);
        }


        application = (WarehouseApplication)getApplication();

        //todo: Instantiating buttons, connecting them to respective views via id, registering each button
        shipmentReport = (Button) findViewById(R.id.shipmentReport);
        shipmentReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipmentReportActivity();
            }
        });

        addShipment = (Button) findViewById(R.id.addShipment);
        addShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShipmentActivity();
            }
        });

        manageShipment = (Button) findViewById(R.id.manageShipment);
        manageShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageShipmentActivity();
            }
        });

        importShipment = (Button) findViewById(R.id.importShipment);
        importShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importShipmentActivity();
            }
        });

        warehouseReport = (Button) findViewById(R.id.warehouseReport);
        warehouseReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warehouseReportActivity();
            }
        });

        addWarehouse = (Button) findViewById(R.id.addWarehouse);
        addWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWarehouseActivity();
            }
        });

        manageWarehouse = (Button) findViewById(R.id.manageWarehouse);
        manageWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageWarehouseActivity();
            }
        });




    }


    //todo: methods called when button is clicked
    public void shipmentReportActivity() {
        Intent intent = new Intent(this, ShipmentReportActivity.class);
        startActivity(intent);
    }

    public void warehouseReportActivity() {
        Intent intent = new Intent(this, WarehouseReportActivity.class);
        startActivity(intent);
    }

    public void addShipmentActivity() {
        Intent intent = new Intent(this, AddShipmentActivity.class);
        startActivity(intent);
    }


    public void manageShipmentActivity() {
        Intent intent = new Intent(this, ManageShipmentActivity.class);
        startActivity(intent);
    }


    public void importShipmentActivity() {
        Intent intent = new Intent(this, ImportShipmentActivity.class);
        startActivity(intent);
    }


    public void addWarehouseActivity() {
        Intent intent = new Intent(this, AddWarehouseActivity.class);
        startActivity(intent);
    }

    public void manageWarehouseActivity() {
        Intent intent = new Intent(this, ManageWarehouseActivity.class);
        startActivity(intent);
    }



    /**
     * Indicates when the user has responded to a permission request
     * @param requestCode The request code
     * @param permissions The permissions requested
     * @param grantResults The result
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WRITE_STORAGE_PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission required, closing application", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }
        }
    }

}
