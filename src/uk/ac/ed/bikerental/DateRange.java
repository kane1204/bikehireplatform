package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * This class provides means of finding a period of time between two dates.
 * 
 * @author s1839592
 * @author s1841064
 *
 */

public class DateRange {
    /**
     * First day and last day of a period of time.
     */
    private LocalDate start, end;
    
    /**
     * Define constructor for DateRange
     * 
     * @param start
     * @param end
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    /**
     * Getter method for the date <code>start</code>.
     * 
     * @return <code>start</code> LocalDate
     */
    public LocalDate getStart() {
        return this.start;
    }
    
    /**
     * Getter method for the date <code>end</code>.
     * 
     * @return <code>end</code> LocalDate
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * Using the getter methods for <code>start</code> and <code>end</code>, find Number of years
     * in this date range.
     * 
     * @return the number of years in this date range.
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    /**
     * Using the getter methods for <code>start</code> and <code>end</code>, find Number of days
     * in this date range.
     * 
     * @return the number of days in this date range.
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * Check if the 
     * 
     * @param other
     * @return <code>null</code>
     */
    public Boolean overlaps(DateRange other) {
        // TODO: implement date range intersection checking
    	// if the date range of date1's(getters) end date and date2's(other) start date is <= 0
        assert false;
        return null;
    }

    /**
     * Create a hash code for <code>start</code> and <code>end</code>
     * 
     * @return this hash code.
     */
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    /**
     * Find the truth value of the equality of <code>start</code> and <code>other.start</code>;
     * and <code>end</code> and <code>other.end</code>
     * 
     * @return this truth value.
     */
    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
    // You can add your own methods here
    
}
