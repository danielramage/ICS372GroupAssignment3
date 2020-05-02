package edu.metrostate.app.model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class WarehouseApplicationTest {

    static WarehouseApplication app;

    @Before
    public void setUpBeforeClass() throws Exception {

        app = new WarehouseApplication();
        app.addShipment("12345j", "air", 1000, 12345, 12345);


    }

    @Test
    public void getAllWarehouses() {
        assert(app.getAllWarehouses().size() >= 1);
    }

    @Test
    public void getWarehouse() {
        assertEquals(12345, app.getWarehouse(12345).getWarehouseID());
    }

    @Test
    public void getShipmentsFromWarehouse() {
        assert(app.getShipmentsFromWarehouse(12345).size() >= 1);
    }

    @Test
    public void getAllShipments() {
        assert(app.getAllShipments().size() >= 1);
    }

    @Test
    public void getShipment() {
        assertEquals("12345j", app.getShipment("12345j").getShipmentID());
    }


    @Test
    public void editShipment() {
        app.editShipment("12345j", "rail", 500);
        assertEquals("rail", app.getShipment("12345j").getShipmentMode());
    }

    @Test
    public void editWarehouse() {
        app.editWarehouse(12345,true, true,true, true, "Changed", true);
        assertEquals("Changed", app.getWarehouse(12345).getWarehouseName());
    }

    @Test
    public void importShipmentsByJson() {
        app.importShipmentsByJson("testShip.json");
        assert(app.getShipment("54321j") != null);
    }

    @Test
    public void saveData() {
        app.saveData();
        assert(new File("data/shipments.json").exists());
    }
}