package com.example.infrastructure;

import java.util.Date;

/**
 * Value Object for date range.
 */
public class DateRange {
    private final Date from;
    private final Date to;

    public DateRange(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    /**
     * A date range is valid if 'from' is not after 'to'.
     */
    public boolean isValid() {
        if (from == null || to == null) {
            return false;
        }
        return !from.after(to);
    }

    /**
     * Checks whether this range is within given bounds.
     */
    public boolean isWithinBounds(Date minDate, Date maxDate) {
        if (!isValid() || minDate == null || maxDate == null) {
            return false;
        }
        return !from.before(minDate) && !to.after(maxDate);
    }
}