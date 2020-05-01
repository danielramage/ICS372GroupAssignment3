package edu.metrostate.app.model;

import android.app.Application;

import java.io.IOException;
import java.util.ArrayList;

import edu.metrostate.jsonsimple.ParseException;

public class WarehouseApplication extends Application {

    public WarehouseManager warehouseManager = new WarehouseManager();


    @Override
    public void onCreate() {
        super.onCreate();


        try {
            JsonHandler.loadWarehouses("data/warehouses.json", warehouseManager);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        try {
            JsonHandler.loadShipments("data/shipments.json", warehouseManager);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * getWarehouse
     * @return a list of all warehouses
     */
    public ArrayList<Warehouse> getAllWarehouses(){
        return warehouseManager.getWarehouses();
    }


    /**
     *
     * @param warehouseId
     * @return
     */
    public Warehouse getWarehouse(int warehouseId){
        return warehouseManager.getWarehouseByID(warehouseId);
    }


    /**
     *
     * @param warehouseId
     * @return
     */
    public ArrayList<Shipment> getShipmentsFromWarehouse(int warehouseId){
        Warehouse warehouse = getWarehouse(warehouseId);
        return warehouse.getShipments();
    }

    /**
     *
     * @return
     */
    public ArrayList<Shipment> getAllShipments(){
        ArrayList<Shipment> allShipments = new ArrayList<>();
        ArrayList<Warehouse> ListOfWarehouses = getAllWarehouses();
        for(Warehouse warehouse: ListOfWarehouses){
            allShipments.addAll(warehouse.getShipments());
        }
        return allShipments;
    }

    public Shipment getShipment(String shipmentId){
        ArrayList<Warehouse> ListOfWarehouses = getAllWarehouses();
        for(Warehouse warehouse: ListOfWarehouses){
            for(Shipment shipment: warehouse.getShipments()){
                if(shipment.getShipmentID() == shipmentId){
                    return shipment;
                }
            }
        }
        return null;
    }

    public boolean addShipment(String shipID, String mode, float weight, int whID, long time){
        boolean shipmentAdded;
        Shipment shipment = new Shipment(shipID, mode, weight, whID, time);
        Warehouse warehouse = warehouseManager.getWarehouseByID(whID);
        shipmentAdded = warehouse.addIncomingShipment(shipment);
        if(shipmentAdded){
            saveData();
        }
        return shipmentAdded;
    }

    public boolean editShipment(String shipID, String mode, float weight){
        Shipment shipment = getShipment(shipID);
        shipment.setShipmentMode(mode);
        shipment.setShipmentWeight(weight);
        return true;
    }

    public boolean addWarehouse(int whID, boolean air, boolean rail, boolean truck, boolean ship, String wName, boolean receive){
        warehouseManager.addWarehouse(new Warehouse(whID, air, rail, truck, ship, wName, receive));
        saveData();
        return true;
    }

    public boolean editWarehouse(int whID, boolean air, boolean rail, boolean truck, boolean ship, String wName, boolean receive){
        Warehouse warehouse = warehouseManager.getWarehouseByID(whID);
        warehouse.setAirMode(air);
        warehouse.setRailMode(rail);
        warehouse.setTruckMode(truck);
        warehouse.setShipMode(ship);
        warehouse.setWarehouseName(wName);
        warehouse.setReceiving(receive);
        return true;
    }

    public boolean importShipmentsByJson(String fileName){
        try {
            JsonHandler.loadShipments(fileName, warehouseManager);
        } catch (IOException | ParseException e) {
            return false;
        }
        return true;
    }

    public boolean importShipmentsByXML(String fileName){
        return false;
    }

    public void saveData(){
        JsonHandler.writeWarehousesToJSON(getAllWarehouses(), "data/warehouses.json");
        JsonHandler.writeShipmentsToJSON(getAllWarehouses(), "data/shipments.json");
    }
}
