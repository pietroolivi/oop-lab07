package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {

        JANUARY(31, "january"), 
        FEBRUARY(28, "february"), 
        MARCH(31, "march"), 
        APRIL(30, "april"), 
        MAY(31, "may"), 
        JUNE(30, "june"), 
        JULY(31, "july"), 
        AUGUST(31, "august"), 
        SEPTEMBER(30, "september"), 
        OCTOBER(31, "october"), 
        NOVEMBER(30, "november"), 
        DECEMBER(31, "december");

        private final int mothLength;
        private final String actualName;
        
        private Month(final int monthLength, final String actualName) {
            this.mothLength = monthLength;
            this.actualName = actualName;
        }
        
        public static Month fromString(String name) {
            int matches = 0;
            Month matchingMonth = null;
            for (Month month : Month.values()) {
                String monthName = month.actualName;
                if (monthName.length() >= name.length()) {
                    monthName = monthName.substring(0, name.length());
                    if (monthName.equalsIgnoreCase(name)) {
                        matchingMonth = month;
                        ++matches;
                    }
                }
            }
            if (matches == 1) {
                return matchingMonth;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(String arg0, String arg1) {
                final int length1 = Month.fromString(arg0).mothLength;
                final int length2 = Month.fromString(arg1).mothLength;
                return length1 >= length2 ? (length1 == length2 ? 0 : 1) : -1;
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(String arg0, String arg1) {
                final Month month1 = Month.fromString(arg0);
                final Month month2 = Month.fromString(arg1);
                return month1.compareTo(month2);
            }
        };
    }
}