package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * 
 * @author s1839592
 * @author s1841064
 *
 */

public class DateRange {
    /**
     * ...
     */
    private LocalDate start, end;
    
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    /**
     * 
     * @return <code>start</code> LocalDate
     */
    public LocalDate getStart() {
        return this.start;
    }
    
    /**
     * 
     * @return <code>end</code> LocalDate
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * 
     * @return Number of years between <code>start</code> and <code>end</code>
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    /**
     * 
     * @return Number of days between <code>start</code> and <code>end</code>
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * 
     * @param other
     * @return <code>null</code>
     */
    public Boolean overlaps(DateRange other) {
        // TODO: implement date range intersection checking
        assert false;
        return null;
    }

    /**
     * 
     * @return A hash code for <code>start</code> and <code>end</code>
     */
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    /**
     * 
     * @return Truth value of the equality of <code>start</code> and <code>other.start</code>;
     * and <code>end</code> and <code>other.end</code>
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
