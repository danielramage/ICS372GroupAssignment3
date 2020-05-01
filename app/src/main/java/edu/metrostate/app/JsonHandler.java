package edu.metrostate.app.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.metrostate.jsonsimple.JSONArray;
import edu.metrostate.jsonsimple.JSONObject;
import edu.metrostate.jsonsimple.JSONParser;
import edu.metrostate.jsonsimple.ParseException;

public class JsonHandler {

    /**
     * The writeShipmentsToJSON method accepts an array list of warehouses and
     * writes the shipments of each warehouse to a JSON file located in the
     * target directory of the project.
     * @param whList An array list of warehouses
     */

    public static void writeShipmentsToJSON(ArrayList<Warehouse> whList, String fileName) {
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
        //"src/resources/shipments.json"
        try (FileWriter file =
                     new FileWriter(fileName)) {
            file.write(warehouseContents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeWarehousesToJSON(ArrayList<Warehouse> whList, String fileName) {
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
        //"src/resources/warehouses.json"
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(warehouseContents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void loadShipments(String fileName, WarehouseManager warehouseMgr) throws
            IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader(fileName);
        JSONObject shipmentsObject = (JSONObject) parser.parse(file);
        JSONArray shipmentsArray = (JSONArray) shipmentsObject.get("shipments");

        for ( Object shipment : shipmentsArray ) {
            JSONObject shipmentObject = (JSONObject) shipment;

            int wID = Integer.parseInt(shipmentObject.get("warehouse_id").toString());
            String sMode = (String) shipmentObject.get("shipment_method");
            String sID = (String) shipmentObject.get("shipment_id");
            float sWeight = Float.parseFloat(shipmentObject.get("weight").toString());
            long rDate = (long) shipmentObject.get("receipt_date");

            // Create the shipment
            Shipment newShipment = new Shipment(sID, sMode, sWeight, wID, rDate);

            // Add the shipment to the Warehouse's records
            for (Warehouse wh : warehouseMgr.getWarehouses()) {
                if (wh.getWarehouseID() == wID) {
                    wh.recordShipment(newShipment);
                }
            }
        }
    }


    static void loadWarehouses(String fileName, WarehouseManager warehouseMgr) throws
            IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader(fileName);
        JSONObject warehousesObject = (JSONObject) parser.parse(file);
        JSONArray warehousesArray = (JSONArray) warehousesObject.get("warehouses");

        for ( Object warehouse : warehousesArray ) {
            JSONObject warehouseObject = (JSONObject) warehouse;

            int wId = Integer.parseInt(warehouseObject.get("warehouse_id").toString());
            boolean air = (boolean) warehouseObject.get("air");
            boolean rail = (boolean) warehouseObject.get("rail");
            boolean truck = (boolean) warehouseObject.get("truck");
            boolean ship = (boolean) warehouseObject.get("ship");
            String wName = warehouseObject.get("name").toString();
            boolean wReceiving = (boolean) warehouseObject.get("receiving");
            warehouseMgr.addWarehouse(wId, air, rail, truck, ship, wName, wReceiving);
        }
    }

    public static void addShipmentToDataStore(Shipment shipment) throws IOException, ParseException {
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
