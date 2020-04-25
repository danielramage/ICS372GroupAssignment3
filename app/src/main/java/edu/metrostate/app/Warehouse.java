package edu.metrostate.app;

import java.util.ArrayList;

/**
 * The Warehouse class is responsible for keeping track of the shipments that
 * are located at the warehouse. Warehouses also keep track of whether they
 * are currently accepting shipments or not.
 */

public class Warehouse {
	private int warehouseID;
	private String warehouseName;
	private boolean airMode;
	private boolean railMode;
	private boolean truckMode;
	private boolean shipMode;
	private boolean receiving;
	private ArrayList<Shipment> shipments = new ArrayList<>();
	private ArrayList<Shipment> shipmentHistory = new ArrayList<>();

	public Warehouse() {}
	/**
	 * The Warehouse constructor creates a warehouse and assigns it the ID
	 * provided as an argument. Note that a client of this method would need
	 * to ensure the ID provided is unique.
	 * @param whID The ID assigned to the new warehouse
	 */
	public Warehouse(int whID) {
		setWarehouseID(whID);
	}

	/**
	 * The Warehouse constructor creates a warehouse and assigns it the ID
	 * provided as an argument. Note that a client of this method would need
	 * to ensure the ID provided is unique.
	 * @param whID The ID assigned to the new warehouse
	 * @param air Whether the warehouse handles air shipments
	 * @param rail Whether the warehouse handles rail shipments
	 * @param truck Whether the warehouse handles truck shipments
	 * @param ship Whether the warehouse handles ship shipments
	 * @param wName The name of the warehouse
	 * @param receive Whether the warehouse is receiving shipments
	 */
	public Warehouse(int whID, boolean air, boolean rail, boolean truck, boolean ship, String wName, boolean receive) {
		setWarehouseID(whID);
		setAirMode(air);
		setRailMode(rail);
		setTruckMode(truck);
		setShipMode(ship);
		setWarehouseName(wName);
		setReceiving(receive);
	}

	/**
	 * The getWarehouseID method returns the ID of the warehouse.
	 * @return The ID of the warehouse
	 */

	public int getWarehouseID() {

		return warehouseID;
	}

	/**
	 * The getWarehouseName method returns the name of the warehouse
	 * @return The name of the warehouse
	 */

	public String getWarehouseName() {
		return warehouseName;
	}

	// The get methods for the Warehouse fields
	public boolean getAirMode() { return airMode; }
	public boolean getRailMode() { return railMode; }
	public boolean getTruckMode() { return truckMode; }
	public boolean getShipMode() { return shipMode; }
	public boolean getReceiving() { return receiving; }

	// The set methods for the Warehouse fields
	public void setWarehouseName(String name) {
		warehouseName = name;
	}
	public void setWarehouseID(int id) { warehouseID = id; }
	public void setAirMode(boolean mode) { airMode = mode; }
	public void setRailMode(boolean mode) { railMode = mode; }
	public void setTruckMode(boolean mode) { truckMode = mode; }
	public void setShipMode(boolean mode) { shipMode = mode; }
	public void setReceiving(boolean receive) { receiving = receive; }

	/**
	 * The recordShipment method stores the shipment provided to the
	 * warehouse's list of shipments. Note that this method does not validate
	 * whether the warehouse is accepting shipments. It is intended to be used
	 * to record the location of shipments already accepted by the warehouse.
	 * @param shipment The existing shipment to be recorded at the warehouse
	 */
	public void recordShipment(Shipment shipment) {
		shipments.add(shipment);
	}

	/**
	 * The addIncomingShipment method adds a new shipment to the warehouse
	 * as long as the warehouse is currently accepting shipments.
	 * @param shipment The new shipment to be added to the warehouse
	 * @return True if the shipment was added; false if it was not
	 */
	public boolean addIncomingShipment(Shipment shipment) {
		boolean shipmentAdded = false;
		if (receiving) {
			recordShipment(shipment);
			shipmentAdded = true;
		}
		return shipmentAdded;
	}

	/**
	 * The recordShipmentHistory method stores the shipment that has left the
	 * warehouse in to the history.
	 * @param shipment The existing shipment to be recorded at the warehouse
	 */
	public void recordShipmentHistory(Shipment shipment) {

		shipmentHistory.add(shipment);
	}

	/**
	 * The removeShipment method removes the shipment provided from the
	 * warehouse's list of shipments.
	 * @param shipment The existing shipment to be recorded at the warehouse
	 * @return weather shipment was removed or not
	 */
	public boolean removeShipment(Shipment shipment) {

		return shipments.remove(shipment);

	}

	/**
	 * The isFreightEnabled method checks whether the warehouse is currently
	 * receiving freight or not.
	 * @return True if the warehouse is receiving freight and false if not
	 */
	public boolean isFreightEnabled() {
		return receiving;
	}

	/**
	 * The getShipments method returns the shipments currently located at the
	 * warehouse.
	 * @return The shipments located at the warehouse as an array list
	 */

	public ArrayList<Shipment> getShipments() {
		return shipments;
	}

	/**
	 * The getShipmentHistory method returns the shipment history of the
	 * warehouse.
	 * @return The shipments located at the warehouse as an array list
	 */

	public ArrayList<Shipment> getShipmentHistory() {
		return shipmentHistory;
	}
}