
/**
 * There are two types of parking slots: slots only for visitors and slots only for staff members. A
parking slot must have an identifier, which starts with a capital letter followed by a three-digit
number e.g. “S001”, “V127”. A parking slot also should know if a car and what car is parked
in the slot. You must be able to add a car to the slot and remove a car from the slot.               
 *
 * @author Quinn Chan 103053395
 * @version JDK 14.0.2 - 13/09/2020
 */
public class ParkingSlot
{
    
    private String identifier               , type, availability;//slot ID, for staff/visitor, occupied/not occupied
    private Car parkedCar;//car object parked in the slot
    
    /**
     * Constructor for objects of class ParkingSlot
     */
    public ParkingSlot(String newIdentifier, String newType)
    {
        // initialise instance variables
        identifier = newIdentifier;
        type = newType;
        parkedCar = null;
    }

    /**
     * getter method of slot identifier
     *
     * @param  nil
     * @return    identifier-slot ID(string)
     */
    public String getSlotID()
    {
        return this.identifier               ;
    }
    
    /**
     * getter method of slot availability
     *
     * @param  nil
     * @return    availability-slot availability(string)
     */
    public String getSlotAvail()
    {
        
        if (this.parkedCar == null)
            this.availability ="not occupied";
        else
            this.availability ="occupied";
        return this.availability               ;
    }
    
    /**
     * getter method of slot type
     *
     * @param  nil
     * @return    type -slot type staff or visitor(string)
     */
    public String getSlotType()
    {
        return this.type               ;
    }
    
    /**
     * getter method of slot car
     *
     * @param  nil
     * @return    parkedCar-the car object parked in the slot
     */
    public Car getSlotCar()
    {
        return this.parkedCar               ;
    }
    
    /**
     * setter method of the car parked in the slot
     *
     * @param  newParkedCar car object
     * @return    nil
     */
    public void setParkedCar(Car newParkedCar)
    {
        this.parkedCar = newParkedCar;
    }
    
    /**
     * remove the car parked in the slot
     *
     * @param  nil
     * @return   nil 
     */
    public void delParkedCar()
    {
        this.parkedCar = null;
    }
}
