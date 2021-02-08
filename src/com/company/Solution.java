package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {

    public LinkedHashMap<String, Integer> dateToDay(Map<String, Integer> dictionary) {
        // Creating Calendar class instance.
        Calendar calendar = Calendar.getInstance();
        //resDictionary to store final result
        LinkedHashMap<String, Integer> resDictionary = new LinkedHashMap<>();
        resDictionary.put("Mon", 0);
        resDictionary.put("Tue", 0);
        resDictionary.put("Wed", 0);
        resDictionary.put("Thu", 0);
        resDictionary.put("Fri", 0);
        resDictionary.put("Sat", 0);
        resDictionary.put("Sun", 0);
        //Keeping track of no of days filled
        Set<Integer> visited = new HashSet<>();
        // using keySet() for iteration over DATES

        for (String date : dictionary.keySet()) {
            Date mydate = getValidDate(date);
            if (mydate != null) {
                // Converting Date to Calendar.
                calendar.setTime(mydate);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                String dayName = getDayName(dayOfWeek);
                Integer val = resDictionary.get(dayName);
                val += dictionary.get(date);
                resDictionary.put(dayName, val);
                trackingVisited(visited, dayName);
            } else {
                System.out.println("Date is invalid : " + date);
                return null;
            }
        }
        // call fillUnavailableDays only if any day remains unfilled
        if (visited.size() < 7) {
            fillUnavailableDays(resDictionary, visited);
        }
        return resDictionary;
    }

    /**
     * This method takes Day name and stores its index
     * to be used to check if the days is present in the input dictionary
     * the main purpose is to handle if at any specific day
     * the value sums up to zero should not be treated as missing value
     *
     * @param visited its a list to store index of day in sequentially
     * @param dayName name of the day
     */
    private void trackingVisited(Set<Integer> visited, String dayName) {
        if (dayName.equals("Mon")) {
            visited.add(0);
        }
        if (dayName.equals("Tue")) {
            visited.add(1);
        }
        if (dayName.equals("Wed")) {
            visited.add(2);
        }
        if (dayName.equals("Thu")) {
            visited.add(3);
        }
        if (dayName.equals("Fri")) {
            visited.add(4);
        }
        if (dayName.equals("Sat")) {
            visited.add(5);
        }
        if (dayName.equals("Sun")) {
            visited.add(6);
        }
    }

    /**
     * This method returns Valid Date object for further Processing
     *
     * @param date Date String from Input Dictionary
     * @return date object
     */
    private Date getValidDate(String date) {

        Date mydate = null;
        if (isValidDateFormat(date)) {
            /*
             * y -> Year
             * M -> Month of year
             * d -> Day of month
             */
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            /*
             * By default setLenient() is true. We should make it false for
             * strict date validations.
             *
             * If setLenient() is true - It accepts all dates. If setLenient()
             * is false - It accepts only valid dates.
             */
            dateFormat.setLenient(false);
            try {
                mydate = dateFormat.parse(date);
            } catch (ParseException e) {
                mydate = null;
            }
        }
        return mydate;
    }

    /**
     * This method takes day no as Input and returns day name as return value
     *
     * @param dayOfWeek the index of the day Number in week
     * @return day name
     */
    private String getDayName(int dayOfWeek) {

        String dayName = null;
        switch (dayOfWeek) {
            case 1:
                dayName = "Sun";
                break;
            case 2:
                dayName = "Mon";
                break;
            case 3:
                dayName = "Tue";
                break;
            case 4:
                dayName = "Wed";
                break;
            case 5:
                dayName = "Thu";
                break;
            case 6:
                dayName = "Fri";
                break;
            case 7:
                dayName = "Sat";
                break;
        }
        return dayName;
    }


    /**
     * This method checks if the date is valid or not
     *
     * @param date string from input Dictionary
     * @return Valid date (TRUE/FALSE)
     */
    private boolean isValidDateFormat(String date) {
        // * Regular Expression that matches String with format dd/MM/yyyy.
        // * yyyy -> 4 digit number
        // * MM -> 01-12
        // * dd -> 01-31
        String pattern = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
        boolean result = false;
        if (date.matches(pattern)) {
            result = true;
        }
        return result;
    }

    // Filling the days whose dates are not available

    /**
     * This method fill the day which are missing in input Dictionary
     *
     * @param resDictionary Output Dictionary
     * @param visited       list consist of days filled from input Dictionary
     */
    private void fillUnavailableDays(LinkedHashMap<String, Integer> resDictionary, Set<Integer> visited) {
        List<Integer> justValues = new ArrayList<>();
        storingValueInList(resDictionary, justValues);
        //Locating the unavailable day from input Date
        // Finding ZEROS.. or missing days
        int curr = 0;
        int next;
        // curr+1 gives the starting Index of ZERO (Unfilled day/days)
        while ((curr + 1) < 6) {
            next = curr + 1;
            if (justValues.get(curr + 1) == 0 && !visited.contains(curr + 1)) {
                next = getNextDay(justValues, next, visited);
                int noOfContinuousVacantPosition = next - curr;
                int prevDayValue = justValues.get(curr);
                /*
                 *Using Arithmetic progression to fill missing values
                 * FirstNumber + (n-1)*commonDifference
                 */
                arithmeticProgToFillValue(justValues, curr, next, noOfContinuousVacantPosition, prevDayValue);
                curr = next + 1;
            }
            curr++;
            updateValueInDictionary(resDictionary, justValues);

        }
    }


    /**
     * This method returns the Next day after the missing values
     *
     * @param justValues contains only the values from the dictionary
     * @param next       starting index of missing values
     * @param Visited    days which are present in dictionary
     * @return the index of the Next Day
     */
    private int getNextDay(List<Integer> justValues, int next, Set<Integer> Visited) {
        int result = next;
        while ((result + 1) < justValues.size()) {
            if (justValues.get(result + 1) == 0 && !Visited.contains(result + 1)) {
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Stores just the value in List justValues
     *
     * @param resDictionary Output Dictionary
     * @param justValues    list consists of only the value part of Dictionary
     */
    private void storingValueInList(LinkedHashMap<String, Integer> resDictionary, List<Integer> justValues) {
        for (String date : resDictionary.keySet()) {
            justValues.add(resDictionary.get(date));
        }
    }

    /**
     * This method Uses Arithmetic Progression to fill the missing Values
     *
     * @param next                        index OF next Day where value is present or Already Filled
     * @param noOfContinuesVacantPosition no of continues Unfilled Values
     */
    private void arithmeticProgToFillValue(List<Integer> justValues, int curr, int next, int noOfContinuesVacantPosition, int prevValue) {
        int commonDiff = (justValues.get(next + 1) - prevValue) / (noOfContinuesVacantPosition + 1);
        //starts filling with second digit of the sequence X=2
        int x = 2;
        for (int i = curr + 1; i < next + 1; i++) {
            justValues.set(i, prevValue + ((x - 1) * commonDiff));
            x++;
        }
    }

    /**
     * This method updates the missing Values to the output Dictionary
     *
     * @param resDictionary output Dictionary
     * @param justValues    Values after processing (filling the missing dates)
     */
    private void updateValueInDictionary(LinkedHashMap<String, Integer> resDictionary, List<Integer> justValues) {
        int index = 0;
        for (String date : resDictionary.keySet()) {
            resDictionary.put(date, justValues.get(index));
            index++;
        }
    }
}
