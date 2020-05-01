package edu.metrostate.app;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.metrostate.app.model.Warehouse;
import edu.metrostate.app.model.WarehouseApplication;

public class AddWarehouseActivity extends AppCompatActivity {

    private Button mHome;
    protected Button mAddWarehouseBtn;

    Warehouse mNewWarehouse;

    boolean mAir;
    boolean mTruck;
    boolean mShip;
    boolean mRail;
    boolean mReceiving;

    EditText mWarehouseName;
    EditText mWarehouseID;
    String warehouseName;
    int warehouseID;

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warehouse);

        final WarehouseApplication application = (WarehouseApplication)getApplication();

        //returns to home activity when clicked
       mHome = (Button) findViewById(R.id.home);
       mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity();
            }
        });
        //grab Warehouse ID
        mWarehouseID = (EditText)findViewById(R.id.addWarehouseID);
        try{
            warehouseID = Integer.parseInt(mWarehouseID.getText().toString());
        }catch(NumberFormatException nfe){}
        //grab Warehouse Name
        mWarehouseName = (EditText)findViewById(R.id.addWarehouseName);
        warehouseName = mWarehouseName.getText().toString();

        //Add new warehouse to the list of warehouses
        mAddWarehouseBtn = (Button) findViewById(R.id.addWarehouse);
        mAddWarehouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mNewWarehouse = new Warehouse(warehouseID, mAir, mRail, mTruck, mShip, warehouseName, mReceiving);
                    mNewWarehouse.setWarehouseName(mWarehouseName.getText().toString());
                    mNewWarehouse.setWarehouseID(Integer.parseInt(mWarehouseID.getText().toString()));
                    //Only valid warehouses are added to the list
                    if((mWarehouseName.getText().toString()!=null)&&((Integer.parseInt(mWarehouseID.getText().toString())!= 0))){
                        application.warehouseManager.addWarehouse(mNewWarehouse);
                        Toast success = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG);
                        success.setGravity(Gravity.TOP, 0, 400 );
                        success.show();
                    }
                }
                catch(NumberFormatException nfe){
                    Toast error = Toast.makeText(getApplicationContext(), "Enter a valid warehouse ID and name", Toast.LENGTH_LONG );
                    error.setGravity(Gravity.TOP, 0, 400 );
                    error.show();

                }

                // Tests whether warehouses have been successfully added
               //test = findViewById(R.id.test);
               //test.setText(application.warehouseManager.getWarehouses().toString());

            }
        });

    }

    /**
     * Method to return the user to the home screen
     */

    public void homeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Method is associated with the checkboxes. Enables a shipping method if box is checked and
     * disables if box is unchecked
     * @param view Grabs a checkbox based off the ID
     */
    public void onCheckBoxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){
            case R.id.airShipping:
                if(checked)
                    mAir = true;
                else
                    mAir = false;
                break;
            case R.id.truckShipping:
                if(checked)
                    mTruck = true;
                else
                    mTruck = false;
                break;
            case R.id.shipShipping:
                if(checked)
                    mShip = true;
                else
                    mShip = false;
                break;
            case R.id.railShipping:
                if(checked)
                    mRail = true;
                else
                    mRail = false;
                break;
            case R.id.receivingShipments:
                if(checked)
                    mReceiving = true;
                else
                    mReceiving = false;
                break;
        }

    }
}
