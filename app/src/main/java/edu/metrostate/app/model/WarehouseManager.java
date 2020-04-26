package edu.metrostate.app;

import java.io.*;
import java.util.*;
import edu.metrostate.jsonsimple.*;

/**
 * The WarehouseManager class is responsible for keeping track of a group of
 * warehouses. It reads and stores existing shipments from JSON files, and
 * writes warehouse contents to JSON.
 */

public class WarehouseManager {
    private ArrayList<Warehouse> warehouses = new ArrayList<>();

    public WarehouseManager() {
    }

    /**
     * The addWarehouse method creates a new Warehouse object and adds it to
     * the WarehouseManager array list. Clients will need to handle validation
     * to ensure the warehouses in their WarehouseManager are unique. This
     * method will add any warehouse, even if the warehouseId is not unique.
     * @param warehouse The new warehouse that will be added to the warehouse manager
     */
    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    /**
     * The addWarehouse method creates a new Warehouse object and adds it to
     * the WarehouseManager array list. Clients will need to handle validation
     * to ensure the warehouses in their WarehouseManager are unique. This
     * method will add any warehouse, even if the warehouseId is not unique.
     * @param warehouseID The ID that the new warehouse will be assigned
     */
    public void addWarehouse(int warehouseID, boolean air, boolean rail, boolean truck,
                             boolean ship, String name, boolean receiving) {
        Warehouse warehouse = new Warehouse(warehouseID, air, rail, truck, ship, name, receiving);
        warehouses.add(warehouse);
    }

    public Warehouse getWarehouseByName(String name) {
        Warehouse warehouse = null;
        for ( Warehouse wh : this.getWarehouses() ) {
            if (wh.getWarehouseName().equals(name)) {
                warehouse = wh;
                break;
            }
        }
        return warehouse;
    }

    public Warehouse getWarehouseByID(int id) {
        Warehouse warehouse = null;
        for ( Warehouse wh : this.getWarehouses() ) {
            if (wh.getWarehouseID() == id) {
                warehouse = wh;
                break;
            }
        }
        return warehouse;
    }

    public boolean validWarehouse(int id) {
        boolean isValid = false;
        for ( Warehouse wh : this.getWarehouses() ) {
            if (wh.getWarehouseID() == id) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    /**
     * The getWarehouses method returns the WarehouseManager's list of
     * warehouses.
     * @return The list of warehouses
     */

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }



    /**
     * The writeAllShipmentsToJSON method calls the writeShipmentsToJSON method
     * with the entire list of warehouses maintained by the WarehouseManager.
     */
    public void writeAllShipmentsToJSON() {
        JsonHandler.writeShipmentsToJSON(warehouses,"src/resources/shipments.json");
    }




    public void updateWarehouseDataStore(ArrayList<Warehouse> warehouseList) {
        JsonHandler.writeWarehousesToJSON(warehouseList, "src/resources/warehouses.json");
    }


}
