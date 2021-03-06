
/**
 * A car will be identified by its registration number. A registration number always starts with a
capital letter, followed by a five-digit number e.g. “T12345”. A car should have an owner and
knows if the owner is a staff member.               
 *
 * @author Quinn Chan 103053395
 * @version JDK 14.0.2 - 13/09/2020
 */
public class Car
{
    
    private String regNum, owner;//car registration number, owner's name
    private char isStaff;//Y for staff, N for visitor

    /**
     * Constructor for objects of class Car
     */
    public Car(String newRegNum, String newOwner, char newIsStaff)
    {
        // initialise instance variables
        regNum = newRegNum;
        owner = newOwner;
        isStaff = newIsStaff;
    }

    /**
     * getter method of car registration number
     *
     * @param  nil
     * @return    regNum-registration number(string)
     */
    public String getRegNum()
    {
        return this.regNum;
    }
    
    /**
     * getter method of car owner
     *
     * @param  nil
     * @return    owner-owner's name(string)
     */
    public String getOwner()
    {
        return this.owner;
    }
    
    /**
     * getter method of car isStaff
     *
     * @param  nil
     * @return    isStaff-Y means staff's car, N means visitor's car(char)
     */
    public char getIsStaff()
    {
        return this.isStaff;
    }
}
