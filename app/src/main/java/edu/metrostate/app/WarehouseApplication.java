package edu.metrostate.app;

import android.app.Application;

import java.io.IOException;
import java.util.ArrayList;

import edu.metrostate.jsonsimple.ParseException;

public class WarehouseApplication extends Application {
    WarehouseManager warehouseManager = new WarehouseManager();
    private ArrayList<Shipment> shipments = new ArrayList<>();//todo

    @Override
    public void onCreate() {
        super.onCreate();

        try {
           shipments = JsonHandler.loadWarehouses("src/resources/warehouses.json", warehouseManager);//todo
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        try {
            JsonHandler.loadShipments("src/resources/shipments.json", warehouseManager);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


}
