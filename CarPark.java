import java.util.*;
import javafx.util.Pair;


/**
 * CarPark is responsible for maintaining a list of available parking slots. You should be able to
find a slot, add a slot, delete a slot, and provide a list of all slots included in the car park.               
 *
 * @author Quinn Chan 103053395
 * @version JDK 14.0.2 - 22/10/2020
 */
public class CarPark
{
    
    private ArrayList<ParkingSlot> allSlots; // to contain all car slots in an arraylist
    private int staffSlotSize = 0; // keep track of number of staff slots
    private int visitorSlotSize = 0; // keep track of number of staff slots
    /**
     * Constructor for objects of class CarPark
     * 
     * @param  int staffNum, int visitorNum  
     */
    public CarPark(int staffNum, int visitorNum)
    {
        // initialise instance variables
        String identifier;        
        allSlots = new ArrayList<ParkingSlot>();
        
        for (int i=1; i<=staffNum; i++){
            identifier = "S"+ i/100 + (i/10)%10 + i%10;
            ParkingSlot newStaffSlot = new ParkingSlot(identifier,"staff");
            allSlots.add(newStaffSlot);
            staffSlotSize++;
        }
        
        for (int i=1; i<=visitorNum; i++){
            identifier = "V"+ i/100 + (i/10)%10 + i%10;
            ParkingSlot newVisitorSlot = new ParkingSlot(identifier,"visitor");
            allSlots.add(newVisitorSlot);
            visitorSlotSize++;
        }
              
    }

       
    /**
     * return the carslots in 2D array of strings
     *
     * @return    message to indicate result(string)
     */
    public Object[][] toArray()
    {
        Object[][] slotsArray = new Object [allSlots.size()][4];
        for (int i=0; i<allSlots.size(); i++){
            Object[] row = toRow(i);   
            slotsArray[i][0] = row[0];
            slotsArray[i][1] = row[1];
            slotsArray[i][2] = row[2];
            slotsArray[i][3] = row[3];            
        }
        
        return slotsArray;        
    } 
    
    /**
     * return a car in carslots[i] in an array
     *
     * @param  int index
     * @return  object[]
     */
    public Object[] toRow(int index)
    {
        ParkingSlot slot = allSlots.get(index);
        String slotID = slot.getSlotID();
        String type = slot.getSlotType();
        String avail = slot.getSlotAvail();            
        String carowner = "N/A";
        if (avail.equals("occupied"))
           carowner = slot.getSlotCar().getRegNum()+"/"+ slot.getSlotCar().getOwner(); 
        Object[] row = {slotID, type, avail, carowner};
        return row;
    }
    
    /**
     * get all slots
     *
     * @param  nil
     * @return    allSlots-the list of all slots (ArrayList)
     */
    public ArrayList getAllSlots()
    {
        return allSlots;
    }
    
    /**
     * get visitor slots size
     *
     * @param  nil
     * @return    visitorSlotSize-the size of visitor slots (int)
     */
    public int getVisitorSlotsSize()
    {
        return this.visitorSlotSize;
    }
    
    /**
     * get staff slots size
     *
     * @param  nil
     * @return    staffSlotSize-the size of staff slots(int)
     */
    public int getStaffSlotsSize()
    {
        return this.staffSlotSize;
    }
    
    /**
     * Method to park a car into selected slot
     *
     * @param  vehicle-a car object and slotID -a slot ID(string)
     * @return    message to indicate result(string)
     */
    public Pair<Integer, String> parkCar (Car vehicle, String slotID)
    {
        String carExist = findCar(vehicle.getRegNum());
        if (carExist.equals("Car not found")){
            for (ParkingSlot slot: allSlots){
                if (slot.getSlotID().equals(slotID)){
                    if (slot.getSlotCar()!=null)
                        return new Pair<Integer, String>(-1,"The slot is occupied");
                    else if ((vehicle.getIsStaff()=='N' && slot.getSlotType().equals("staff")) || (vehicle.getIsStaff()=='Y' && slot.getSlotType().equals("visitor")))
                        return new Pair<Integer, String>(-1,"Owner type does not match slot type");
                    else                    
                        {slot.setParkedCar(vehicle);
                        return new Pair<Integer, String>(allSlots.indexOf(slot), vehicle.getRegNum() +" is parked at "+ slot.getSlotID());}
                }           
                
            }
            return new Pair<Integer, String>(-1,"Slot not found");
        }
        else
            return new Pair<Integer, String>(-1,"Parking failed. "+carExist);
    }
    
    /**
     * Method to remove a car slot
     *
     * @param   slotID -a slot ID(string)
     * @return    message to indicate result(string)
     */
    public Pair<Integer, String> delSlot(String slotID)
    {
        for (ParkingSlot slot: allSlots){
            if (slot.getSlotID().equals(slotID)){
                if (slot.getSlotCar()!=null)
                    return new Pair<Integer, String>(-1,"The slot is occupied and cannot be deleted");                
                else                    
                    {
                    if (slot.getSlotType().equals("staff"))
                        staffSlotSize--;
                    else
                        visitorSlotSize--;
                    int index = allSlots.indexOf(slot);
                    allSlots.remove(slot);
                    return new Pair<Integer, String>(index,"The slot is removed successfully.");
                }
            }           
            
        }
        return new Pair<Integer, String>(-1,"Slot not found");        
        
    }
    
    
    /**
     * Method to add a slot
     *
     * @param  slotID -a slot ID(string), isStaff (char)- Y for staff/N for visitor
     * @return    message to indicate result(string)
     */
    public Pair<Integer, String> addSlot(String slotID, char isStaff)
    {
        for (ParkingSlot slot: allSlots){
            if (slot.getSlotID().equals(slotID)){
                return new Pair<Integer, String>(-1,"Slot ID already existed");
            }
        }
        ParkingSlot newSlot;
        if (isStaff=='Y'){
            newSlot = new ParkingSlot(slotID,"staff");
            staffSlotSize++;
        }
        else{
            newSlot = new ParkingSlot(slotID,"visitor");
            visitorSlotSize++;
        }
        allSlots.add(newSlot);         
        return new Pair<Integer, String>(allSlots.size()-1,"Slot successfully added");
    }
    
        
    /**
     * method to find a car in the carpark by regNum
     *
     * @param  regNum -a car registration number(string)
     * @return    message to indicate result(string)
     */
    public String findCar(String regNum)
    {
        for (ParkingSlot slot: allSlots){
            if (slot.getSlotCar()!=null) {
                if (slot.getSlotCar().getRegNum().equals(regNum)){
                    return ("The car is found at "+slot.getSlotID()+ ". The owner is "+ slot.getSlotCar().getOwner());
                }
            }
        }
        
        return ("Car not found");        
    }
    
    /**
     * method to remove a car in the carpark
     *
     * @param  regNum -a car registration number(string)
     * @return    message to indicate result(string)
     */
    public Pair<Integer, String> delCar(String regNum)
    {
        for (ParkingSlot slot: allSlots){
            if (slot.getSlotCar()!=null) {
                if (slot.getSlotCar().getRegNum().equals(regNum)){
                    slot.delParkedCar();
                    return new Pair<Integer, String>(allSlots.indexOf(slot),"The car is found at "+slot.getSlotID()+ " and is now removed");
                }
            }
        }
        
        return new Pair<Integer, String>(-1,"Car not found");        
    } 
}
