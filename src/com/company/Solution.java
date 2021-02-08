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
        Set<String> visited = new HashSet<>();
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
                visited.add(dayName);
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

    // takes string date and converts to date object
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


    // checks if the date is valid

    /**
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
     * @param resDictionary Output Dictionary
     * @param visited       list consist of days filled from input Dictionary
     */
    private void fillUnavailableDays(LinkedHashMap<String, Integer> resDictionary, Set<String> visited) {
        List<Integer> justValues = new ArrayList<>();
        storingValueInList(resDictionary, visited, justValues);
        //Locating the unavailable day from input Date
        // Finding ZEROS.. or missing days
        int curr = 0;
        int next;
        // curr+1 gives the starting Index of ZERO (Unfilled day/days)
        while ((curr + 1) < 6) {
            next = curr + 1;
            if (justValues.get(curr + 1) == 0) {
                next = getNextDay(justValues, next);
                int noOfContinuousVacantPosition = next - curr;
                // Check if the Previous value for date is zero or sums to ZERO
                processPreviousValue(justValues, curr);
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
     * @param justValues list consists of only the value part of Dictionary
     * @param curr       current Index
     *                   if the input data in the dictionary sums to zero for a specific day and the next day value is not present
     */
    private void processPreviousValue(List<Integer> justValues, int curr) {
        if (justValues.get(curr) == Integer.MAX_VALUE) {
            justValues.add(curr, 0);
            justValues.remove(curr + 1);
            //prevValue = 0;
        }
    }

    private int getNextDay(List<Integer> justValues, int next) {
        int result = next;
        while (result + 1 < justValues.size()) {
            if (justValues.get(result + 1) == 0) {
                //noOfContinuesVacantPosition++;
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
     * @param visited       list consist of days filled from input Dictionary
     * @param justValues    list consists of only the value part of Dictionary
     */
    private void storingValueInList(LinkedHashMap<String, Integer> resDictionary, Set<String> visited, List<Integer> justValues) {
        for (String date : resDictionary.keySet()) {
            if (visited.contains(date) && resDictionary.get(date) == 0) {
                justValues.add(Integer.MAX_VALUE);
            } else {
                justValues.add(resDictionary.get(date));
            }
        }
    }

    /**
     * @param next                        index OF next Day where value is present or Already Filled
     * @param noOfContinuesVacantPosition no of continues Unfilled Values
     */
    private void arithmeticProgToFillValue(List<Integer> justValues, int curr, int next, int noOfContinuesVacantPosition, int prevValue) {
        int commonDiff = (justValues.get(next + 1) - prevValue) / (noOfContinuesVacantPosition + 1);
        //System.out.println(commonDiff);
        //starts filling with second digit of the sequence X=2
        int x = 2;
        for (int i = curr + 1; i < next + 1; i++) {
            justValues.add(i, prevValue + ((x - 1) * commonDiff));
            justValues.remove(i + 1);
            x++;
        }
    }

    /**
     * @param resDictionary output Dictionary
     * @param justValues    Values after processing (filling the missing dates)
     */
    private void updateValueInDictionary(LinkedHashMap<String, Integer> resDictionary, List<Integer> justValues) {
        int index = 0;
        for (String date : resDictionary.keySet()) {
            if (justValues.get(index) == null) {
                resDictionary.put(date, 0);
            } else {
                resDictionary.put(date, justValues.get(index));
            }
            index++;
        }
    }
}
