package edu.metrostate.app;

import android.app.Application;

import java.io.IOException;

import edu.metrostate.jsonsimple.ParseException;

public class WarehouseApplication extends Application {
    WarehouseManager warehouseManager = new WarehouseManager();

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            JsonHandler.loadWarehouses("src/resources/warehouses.json", warehouseManager);
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
