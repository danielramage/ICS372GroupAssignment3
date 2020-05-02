package edu.metrostate.app.model;

import org.junit.*;

import java.io.File;

import static org.junit.Assert.*;

class JsonHandlerTest {

    static WarehouseManager warehouseManager;
    static String shipFile = "shipmentstest.json";
    static String warehouseFile = "warehousestest.json";

    @Before
    static void setUpBeforeClass() throws Exception {


        Warehouse warehouse = new Warehouse(12345, true, true, true, true, "Warehouse", true);
        Shipment shipment = new Shipment("12345j", "air", 1000, 12345, 12345);
        warehouse.addIncomingShipment(shipment);
        warehouseManager.addWarehouse(warehouse);

    }

    @Test
    void testWriteShipmentsToJSON() {


        JsonHandler.writeShipmentsToJSON(warehouseManager.getWarehouses(), shipFile);

        assertEquals(true, new File(shipFile).exists());

    }

    @Test
    void testWriteWarehousesToJSON() {


        JsonHandler.writeWarehousesToJSON(warehouseManager.getWarehouses(), warehouseFile);

        assertEquals(true, new File(warehouseFile).exists());

    }

}
