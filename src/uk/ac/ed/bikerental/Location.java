package uk.ac.ed.bikerental;

/**
 * Summary
 * 
 * @author s1839592
 * @author s1841064
 *
 */

public class Location {
    /**
     * Summary
     */
    private String postcode;
    private String address;
    
    /**
     * Summary
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
     * Summary
     * 
     * @param other
     * @return <code>true</code>
     */
    public boolean isNearTo(Location other) {
    	// Compares the first two letters of the post code if they match it returns true
        // is the location near to the this other location
        String otherPostcode = getPostcode();
        String other2Letters = otherPostcode.substring(0, 2);
        String LocPostcode = postcode.substring(0, 2);
        
        if (LocPostcode==other2Letters) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Summary
     * 
     * @return String <code>postcode</code>
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Summary
     * 
     * @return String <code>address</code>
     */
    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here

}
