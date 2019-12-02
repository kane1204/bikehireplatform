package uk.ac.ed.bikerental;

/**
 * The location class provides methods that deal with the location of bike stores and the location.
 * the customer queries.
 * 
 * @author s1839592
 * @author s1841064
 *
 */

public class Location {
    /**
     * Postcode and address of the store.
     * <p>
     * <code>postcode</code> should never be less than 6 characters.
     */
    private String postcode;
    private String address;
    
    /**
     * Define the constructor for Location.
     * This also prevents the postcode string being less than 6 characters long. 
     * 
     * @param postcode
     * @param address
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    
    /**
     * Compare the first two letters of the postcode of the store and and the customer's queried
     * location.
     * 
     * @param other
     * @return <code>true</code> if the first two letters of each postcode match; or
     * <p>
     * <code>false</code> if the first to letters of the post code don't match.
     */
    public boolean isNearTo(Location other) {
        String otherPostcode = other.getPostcode();
        String other2Letters = otherPostcode.substring(0, 2);
        String LocPostcode = postcode.substring(0, 2);
        
        if (LocPostcode.contentEquals(other2Letters)) {
            return true;
        } else {
            return false;
        }
        //made line 43 other.getPostcose() (i assume you just for got to add 'other' before the
        //method.
    }

    /**
     * Getter method for the postcode string.
     * 
     * @return String <code>postcode</code>
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Getter method for the string <code>address</code>.
     * 
     * @return String <code>address</code>
     */
    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here

}
