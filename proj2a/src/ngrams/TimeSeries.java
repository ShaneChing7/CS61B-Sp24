package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /**
     * If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here.
     */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int i = startYear; i <= endYear; i++) {
            this.put(i, ts.get(i));
        }

    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        Set<Integer> set = this.keySet();
        return new ArrayList<>(set);
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> list = new ArrayList<>();
        for(int year : years()){
            list.add(this.get(year));
        }
        return list;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     * <p>
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {

        TimeSeries newSeries = new TimeSeries();

        if(this.isEmpty() && ts.isEmpty()) {
            return newSeries;
        }

        for(int y : this.years()){
            newSeries.put(y,this.get(y));
        }

        for(int y : ts.years()){
            if(newSeries.containsKey(y)){
                newSeries.replace(y,ts.get(y) + newSeries.get(y));
            }else{
                newSeries.put(y,ts.get(y));
            }
        }

        return newSeries;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     * <p>
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {

        TimeSeries newSeries = new TimeSeries();

        for(int year : this.years()){
            if(ts.containsKey(year)){
                newSeries.put(year,this.get(year) / ts.get(year));
            }else {
                // 如果 ts 中缺少该年份，则抛出异常
                throw new IllegalArgumentException("Missing year " + year + " in the second TimeSeries");
            }
        }
        return newSeries;
    }

}
