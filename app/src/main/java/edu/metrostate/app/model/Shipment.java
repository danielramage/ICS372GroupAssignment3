package edu.metrostate.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
//import javax.xml.bind.annotation.*;

/**
 * The Shipment class keeps track of the ID, weight, location, and other
 * metadata about a shipment.
 */
public class Shipment {
    private String shipmentID;
    private String shipmentMode;
    private float shipmentWeight;
    private int currentWarehouseID;
    private int destinationWarehouseID;
    private String currentWarehouseName;
    private long receivedAt;
    private long sentAt;
    private String readableReceivedAt;
    private String weightUnit;
    private WeightHelper weightHelper = new WeightHelper();

    public Shipment() {}
    /**
     * This Shipment constructor sets all the attributes of a shipment.
     * @param shipID The ID of the shipment
     * @param mode The shipping method of the shipment, such as 'air' or 'rail'
     * @param weight The weight of the shipment
     * @param whID The ID of the warehouse housing the shipment
     * @param time The time the shipment was received, in ms since Jan 1 1970
     */
    public Shipment(String shipID, String mode, float weight, int whID, long time) {
        setShipmentID(shipID);
        setShipmentMode(mode);
        setShipmentWeight(weight);
        setWarehouseID(whID);
        setReceiptTime(time);
        setReadableReceiptTime(time);
    }

    /**
     * This Shipment constructor sets the shipment ID, method, and weight, but
     * not the warehouse ID or receipt timestamp. It is intended to be used by
     * clients that are staging shipments prior to any attempt to send it to a
     * warehouse.
     * @param shipID The ID of the shipment
     * @param mode The shipping method of the shipment, such as 'air' or 'rail'
     * @param weight The weight of the shipment
     */
    public Shipment(String shipID, String mode, float weight) {
        setShipmentID(shipID);
        setShipmentMode(mode);
        setShipmentWeight(weight);
    }

    /**
     * The getShipmentID method returns the value of the property.
     * @return The ID of the shipment, as a String
     */

    public final String getShipmentID() {
        return shipmentID;
    }

    /**
     * The getShipmentMode method returns the value of the shipping mode.
     * @return The way the shipment is being sent, as a String
     */

    public final String getShipmentMode() {
        return shipmentMode;
    }

    /**
     * The getShipmentWeight method returns the weight of the shipment.
     * @return The shipment weight, as a float
     */
    public final float getShipmentWeight() {
        return weightHelper.getWeight();
    }

    /**
     * The getWeightUnit method returns the units of the shipment weight.
     * @return The shipment weight units, as a String
     */
    public final String getWeightUnit() {
        return weightHelper.getUnit();
    }
//    // T
//    public final void setWeightUnit(String unit) {
//        weightHelper.setUnit(unit);
//    }

    /**
     * The getWeightHelper method returns the shipment weight helper.
     * @return The weight helper for the shipment, which manages the weight unit variable
     */

    public WeightHelper getWeightHelper() {
        return weightHelper;
    }
    public void setWeightHelper(WeightHelper helper) {
        weightHelper = helper;
    }

    /**
     * The getWarehouseID method returns the ID of the warehouse housing the
     * shipment.
     * @return The warehouse ID where the shipment is located, as an int
     */
    public final int getWarehouseID() {
        return currentWarehouseID;
    }

    /**
     * The getDestinationWarehouseID method returns the ID of the warehouse shipment was sent to
     * Only used in shipment history
     * @return The warehouse ID where the shipment is located, as an int
     */
    public final int getDestinationWarehouseID() {
        return destinationWarehouseID;
    }

    /**
     * The getCurrentWarehouseName method returns the name of the warehouse the
     * shipment is currently located at.
     * @return The name of the shipment's warehouse, as a String
     */
    public final String getCurrentWarehouseName() {
        return currentWarehouseName;
    }

    /**
     * The getReadableReceivedAt method returns a human-readable date-time that the
     * shipment was received, in UTC.
     * @return The date and time the shipment was received, as a readable String
     */
    public final String getReadableReceivedAt() {
        return readableReceivedAt;
    }

    /**
     * The getReceivedAt method returns the timestamp the shipment was
     * received at the warehouse.
     * @return The shipment receipt timestamp, in ms since the UNIX Epoch
     */

    public final long getReceivedAt() {
        return receivedAt;
    }

    /**
     * The getSentAt method returns the timestamp the shipment
     * left the warehouse. Used in history
     * @return The shipment receipt timestamp, in ms since the UNIX Epoch
     */

    public final long getSentAt() {
        return sentAt;
    }

    /**
     * The setShipmentID method sets the ID of the shipment.
     * @param id The ID of the shipment
     */
    public final void setShipmentID(String id) {
        shipmentID = id;
    }

    /**
     * The setShipmentMode method sets the method by which the shipment will be
     * delivered from the warehouse.
     * @param mode The shipping method of the shipment
     */
    public final void setShipmentMode(String mode) {
        shipmentMode = mode;
    }

    /**
     * The setShipmentWeight method assigns the weight of a shipment.
     * @param weight The weight of the shipment
     */
    public final void setShipmentWeight(float weight) {
        weightHelper.setWeight(weight);
    }

    /**
     * The setWarehouseID method assigns warehouse ID to the shipment.
     * It also calls a method that fetches the name of the warehouse belonging
     * to that ID.
     * @param whID The ID of the warehouse accepting the shipment
     */
    public final void setWarehouseID(int whID) {
        currentWarehouseID = whID;
        //setWarehouseName(whID);
    }

    /**
     * The setWarehouseID method assigns warehouse ID to the shipment.
     * It also calls a method that fetches the name of the warehouse belonging
     * to that ID.
     * @param whID The ID of the warehouse accepting the shipment
     */
    public final void setDestinationWarehouseID(int whID) {
        destinationWarehouseID = whID;
    }

    /**
     * The setWarehouseName method is called by the setWarehouseID method to set
     * the warehouse name of the warehouse ID assigned to the shipment.
     * @param whID The ID of the warehouse accepting the shipment
     */
    /*
    private final void setWarehouseName(int whID) {
        for ( Warehouse wh: ShipTracker.warehouseMgr.getWarehouses()) {
            if (whID == wh.getWarehouseID()) {
                currentWarehouseName.set(wh.getWarehouseName());
            }
        }
    }

     */

    /**
     * The setReceiptTime method records the timestamp the shipment is accepted
     * at the warehouse.
     * It also calls a method that sets the human-readable date-time.
     */
    public final void setReceiptTime(long time) {
        receivedAt = time;
        setReadableReceiptTime(time);
    }

    /**
     * The setSentTime method records the timestamp the shipment left
     * the warehouse.
     * Only used for history
     */
    public final void setSentAt(long time) {
        sentAt = time;
    }

    /**
     * The setReadableReceiptTime method sets a human-readable date-time on the shipment
     * @param time The time in epoch milliseconds
     */
    private final void setReadableReceiptTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy hh:mm", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        readableReceivedAt = dateFormat.format(date);
    }

    /**
     * The WeightHelper class is a helper class used by Shipments in order to help process
     * shipment imports via a specific XML format provides the unit of measure for the
     * shipment weight as an XML attribute on an XML weight element:
     *      <Shipment id="foo" type="Air">
     *          <Weight unit="kg">70.2</Weight>
     *      </Shipment>
     */
    public static final class WeightHelper {
        private String weightUnit;
        private float weight;

        public WeightHelper() {}

        public String getUnit() { return weightUnit; }
        public void setUnit(String unit) { weightUnit = unit;}

        public float getWeight() { return weight; }
        public void setWeight(float wt) { weight = wt; }
    }
}