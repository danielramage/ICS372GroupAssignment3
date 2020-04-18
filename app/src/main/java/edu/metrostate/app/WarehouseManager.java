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
            }
        }
        return warehouse;
    }

    public Warehouse getWarehouseByID(int id) {
        Warehouse warehouse = null;
        for ( Warehouse wh : this.getWarehouses() ) {
            if (wh.getWarehouseID() == id) {
                warehouse = wh;
            }
        }
        return warehouse;
    }

    public boolean validWarehouse(int id) {
        boolean isValid = false;
        for ( Warehouse wh : this.getWarehouses() ) {
            if (wh.getWarehouseID() == id) {
                isValid = true;
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
     * The createExistingShipmentsnFromJSON method parses a json file and creates records
     * for every shipment in the file, reading and storing the metadata for each shipment,
     * including where is is located and when it was received at that warehouse.
     * It then adds a corresponding record of the shipment for its warehouse.
     * @param inputFile The file containing the JSON shipment array
     * @throws FileNotFoundException If the file is not found
     * @throws IOException If the input or output encounters a problem
     * @throws ParseException If the file cannot be parsed
     */
    public ArrayList<String> createExistingShipmentsFromJSON(String inputFile) throws IOException, ParseException {
        FileReader file = new FileReader(inputFile);
        JSONParser parser = new JSONParser();
        JSONObject shipmentsObject = (JSONObject) parser.parse(file);
        JSONArray shipmentsArray = (JSONArray) shipmentsObject.get("warehouse_contents");
        ArrayList<String> invalidShipments = new ArrayList<String>();

        for (Object object : shipmentsArray) {
            JSONObject jsonShipment = (JSONObject) object;

            int wID = Integer.parseInt(jsonShipment.get("warehouse_id").toString());
            String sMode = (String) jsonShipment.get("shipment_method");
            String sID = (String) jsonShipment.get("shipment_id");
            float sWeight = Float.parseFloat(jsonShipment.get("weight").toString());
            long rDate = (long) jsonShipment.get("receipt_date");

            if (validWarehouse(wID)) {
                Shipment shipment = new Shipment(sID, sMode, sWeight, wID, rDate);
                getWarehouseByID(wID).recordShipment(shipment);
                addShipmentToDataStore(shipment);
            } else {
                invalidShipments.add(sID);
            }
        }
        return invalidShipments;
    }

    /**
     * The writeAllShipmentsToJSON method calls the writeShipmentsToJSON method
     * with the entire list of warehouses maintained by the WarehouseManager.
     */
    public void writeAllShipmentsToJSON() {
        writeShipmentsToJSON(warehouses);
    }

    /**
     * The writeShipmentsToJSON method accepts an array list of warehouses and
     * writes the shipments of each warehouse to a JSON file located in the
     * target directory of the project.
     * @param whList An array list of warehouses
     */
    public void writeShipmentsToJSON(ArrayList<Warehouse> whList) {
        JSONObject warehouseContents = new JSONObject();
        JSONArray shipmentsArray = new JSONArray();
        for ( Warehouse wh : whList ) {
            for ( Shipment sh : wh.getShipments() ) {
                JSONObject shipment = new JSONObject();
                shipment.put("warehouse_id", sh.getWarehouseID());
                shipment.put("shipment_method", sh.getShipmentMode());
                shipment.put("shipment_id", sh.getShipmentID());
                shipment.put("weight", sh.getShipmentWeight());
                shipment.put("receipt_date", sh.getReceivedAt());
                shipmentsArray.add(shipment);
            }
        }
        warehouseContents.put("shipments", shipmentsArray);

        //Write JSON file
        try (FileWriter file =
            new FileWriter("src/resources/shipments.json")) {
            file.write(warehouseContents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeWarehousesToJSON(ArrayList<Warehouse> whList) {
        JSONObject warehouseContents = new JSONObject();
        JSONArray warehouseArray = new JSONArray();
        for ( Warehouse wh : whList ) {
            JSONObject warehouse = new JSONObject();
            warehouse.put("warehouse_id", wh.getWarehouseID());
            warehouse.put("name", wh.getWarehouseName());
            warehouse.put("air", wh.getAirMode());
            warehouse.put("rail", wh.getRailMode());
            warehouse.put("truck", wh.getTruckMode());
            warehouse.put("ship", wh.getShipMode());
            warehouse.put("receiving", wh.getReceiving());
            warehouseArray.add(warehouse);
        }
        warehouseContents.put("warehouses", warehouseArray);

        //Write JSON file
        try (FileWriter file = new FileWriter("src/resources/warehouses.json")) {
            file.write(warehouseContents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateWarehouseDataStore(ArrayList<Warehouse> warehouseList) {
        writeWarehousesToJSON(warehouseList);
    }

    public void addShipmentToDataStore(Shipment shipment) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader("src/resources/shipments.json");
        JSONObject shipmentsObject = (JSONObject) parser.parse(file);
        JSONArray shipmentsArray = (JSONArray) shipmentsObject.get("shipments");
        JSONObject shipmentObject = new JSONObject();
        shipmentObject.put("shipment_id", shipment.getShipmentID());
        shipmentObject.put("weight", shipment.getShipmentWeight());
        shipmentObject.put("warehouse_id", shipment.getWarehouseID());
        shipmentObject.put("shipment_method", shipment.getShipmentMode());
        shipmentObject.put("receipt_date", shipment.getReceivedAt());

        shipmentsArray.add(shipmentObject);
        shipmentsObject.put("shipments", shipmentsArray);

        //Write JSON file
        try (FileWriter updatedFile = new FileWriter("src/resources/shipments.json")) {
            updatedFile.write(shipmentsObject.toJSONString());
            updatedFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
