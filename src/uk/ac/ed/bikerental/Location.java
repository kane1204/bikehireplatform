package uk.ac.ed.bikerental;

/**
 * 
 * @author s1839592
 * @author s1841064
 *
 */

public class Location {
    /**
     * ...
     */
    private String postcode;
    private String address;
    
    /**
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
     * 
     * @param other
     * @return <code>true</code>
     */
    public boolean isNearTo(Location other) {
        // TODO: Implement Location.isNearTo  
    	// Compares the first two letters of the post code if they match it returns true
        assert false;
        return true;
    }

    /**
     * 
     * @return String <code>postcode</code>
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 
     * @return String <code>address</code>
     */
    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here

}
