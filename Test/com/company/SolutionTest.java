package com.company;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {


    // Input Dictionary has all the day
    @Test
    void BasicTestCase_ContainsAllDays() {
        Solution solObj = new Solution();

        // Input Dictionary has all the day
        Map<String, Integer> input0 = Stream.of(new Object[][]{
                {"2020-01-01", 4}, {"2020-01-02", 4}, {"2020-01-03", 6}, {"2020-01-04", 8}, {"2020-01-05", 2}, {"2020-01-06", -6}, {"2020-01-07", 2}, {"2020-01-08", -2}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        //output
        Map<String, Integer> expected0 = Stream.of(new Object[][]{
                {"Mon", -6}, {"Tue", 2}, {"Wed", 2}, {"Thu", 4}, {"Fri", 6}, {"Sat", 8}, {"Sun", 2}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));


        LinkedHashMap<String, Integer> result = solObj.dateToDay(input0);
        assertEquals(expected0, result);

    }

    @Test
    void BasicTestCase_MissingTwoDays() {
        Solution solObj = new Solution();


        // Input Dictionary has missing Thursday and Friday
        Map<String, Integer> input1 = Stream.of(new Object[][]{
                {"2020-01-01", 6}, {"2020-01-04", 12}, {"2020-01-05", 14}, {"2020-01-06", 2}, {"2020-01-07", 4}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        //output
        Map<String, Integer> expected1 = Stream.of(new Object[][]{
                {"Mon", 2}, {"Tue", 4}, {"Wed", 6}, {"Thu", 8}, {"Fri", 10}, {"Sat", 12}, {"Sun", 14}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));


        LinkedHashMap<String, Integer> result = solObj.dateToDay(input1);
        assertEquals(expected1, result);

    }

    @Test
    void BasicTestCase_MondaySundayIsGiven() {
        Solution solObj = new Solution();


        // Input Dictionary only consists of Monday and Sunday
        Map<String, Integer> input2 = Stream.of(new Object[][]{
                {"2020-01-05", 14}, {"2020-01-06", 2}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        //output
        Map<String, Integer> expected2 = Stream.of(new Object[][]{
                {"Mon", 2}, {"Tue", 4}, {"Wed", 6}, {"Thu", 8}, {"Fri", 10}, {"Sat", 12}, {"Sun", 14}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        LinkedHashMap<String, Integer> result = solObj.dateToDay(input2);
        assertEquals(expected2, result);
    }


    // Input Dictionary has invalid day
    @Test
    void BasicTestCase_inValidDay() {
        Solution reso = new Solution();

        // Input Dictionary has invalid day
        Map<String, Integer> input0 = Stream.of(new Object[][]{
                {"2020-01-71", 4}, {"2020-01-02", 4}, {"2020-01-03", 6}, {"2020-01-04", 8}, {"2020-01-05", 2}, {"2020-01-06", -6}, {"2020-01-07", 2}, {"2020-01-08", -2}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));


        LinkedHashMap<String, Integer> result = reso.dateToDay(input0);
        assertNull(result);

    }


    @Test
    void BasicTestCase_inValidMonth() {
        Solution reso = new Solution();
        // Input Dictionary has invalid month
        Map<String, Integer> input1 = Stream.of(new Object[][]{
                {"2020-71-01", 6}, {"2020-01-04", 12}, {"2020-01-05", 14}, {"2020-01-06", 2}, {"2020-01-07", 4}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
        LinkedHashMap<String, Integer> result = reso.dateToDay(input1);
        assertNull(result);
    }


    // Input Dictionary year as 1970
    @Test
    void BasicTestCase_NegativeExtremeYear() {
        Solution solObj = new Solution();


        // Input Dictionary year as 1970
        Map<String, Integer> input0 = Stream.of(new Object[][]{
                {"1970-01-01", 1}, {"1970-01-02", 2}, {"1970-01-03", 3}, {"1970-01-04", 4}, {"1970-01-05", 5}, {"1970-01-06", 6}, {"1970-01-07", 7}, {"1970-01-08", 8}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        //output
        Map<String, Integer> expected0 = Stream.of(new Object[][]{
                {"Mon", 5}, {"Tue", 6}, {"Wed", 7}, {"Thu", 9}, {"Fri", 2}, {"Sat", 3}, {"Sun", 4}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        LinkedHashMap<String, Integer> result = solObj.dateToDay(input0);
        assertEquals(expected0, result);

    }


    @Test
    void BasicTestCase_PositiveExtremeYear() {
        Solution solObj = new Solution();

        //Input Dictionary has year as 2100
        Map<String, Integer> input1 = Stream.of(new Object[][]{
                {"2100-01-14", 7}, {"2100-01-15", 8}, {"2100-01-16", 9}, {"2100-01-17", 10}, {"2100-01-18", 11}, {"2100-01-19", 12}, {"2100-01-20", 13}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
        //output
        Map<String, Integer> expected1 = Stream.of(new Object[][]{
                {"Mon", 11}, {"Tue", 12}, {"Wed", 13}, {"Thu", 7}, {"Fri", 8}, {"Sat", 9}, {"Sun", 10}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));


        LinkedHashMap<String, Integer> result = solObj.dateToDay(input1);
        assertEquals(expected1, result);

    }

    @Test
    void BasicTestCase_MidRangeYear() {
        Solution solObj = new Solution();
        // Input Dictionary only consists of Monday and Sunday in year 2035
        Map<String, Integer> input2 = Stream.of(new Object[][]{
                {"2035-12-30", 30}, {"2035-12-24", 24}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        //output
        Map<String, Integer> expected2 = Stream.of(new Object[][]{
                {"Mon", 24}, {"Tue", 25}, {"Wed", 26}, {"Thu", 27}, {"Fri", 28}, {"Sat", 29}, {"Sun", 30}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        LinkedHashMap<String, Integer> result = solObj.dateToDay(input2);
        assertEquals(expected2, result);
    }


    @Test
    void BasicTestCase_largeNoOfEntries() {
        Solution solObj = new Solution();


        //Input Dictionary has year as 2100 and more entries
        Map<String, Integer> input1 = Stream.of(new Object[][]{
                {"2100-02-01", 1}, {"2100-02-02", 2}, {"2100-02-03", 3}, {"2100-02-04", 10}, {"2100-02-05", 11}, {"2100-02-06", 12}, {"2100-02-07", 13}, {"2100-02-08", 7}, {"2100-02-09", 8}, {"2100-02-10", 9}, {"2100-02-11", 10}, {"2100-02-12", 11}, {"2100-02-13", 12}, {"2100-02-14", 13},
                {"2100-02-15", 7}, {"2100-02-16", 8}, {"2100-02-17", 9}, {"2100-02-18", 10}, {"2100-02-19", 11}, {"2100-02-20", 12}, {"2100-02-21", 13}, {"2100-02-22", 7}, {"2100-02-23", 8}, {"2100-02-24", 9}, {"2100-02-25", 10}, {"2100-02-26", 11}, {"2100-02-27", 12}, {"2100-02-28", 13}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
        //output
        Map<String, Integer> expected1 = Stream.of(new Object[][]{
                {"Mon", 22}, {"Tue", 26}, {"Wed", 30}, {"Thu", 40}, {"Fri", 44}, {"Sat", 48}, {"Sun", 52}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));


        LinkedHashMap<String, Integer> result = solObj.dateToDay(input1);
        assertEquals(expected1, result);

    }

    @Test
    void BasicTestCase_extremeValue() {
        Solution solObj = new Solution();

        // Input Dictionary only consists of Monday and Sunday in year 2035 extreme/edge values
        Map<String, Integer> input2 = Stream.of(new Object[][]{
                {"2035-12-30", 1000000}, {"2035-12-24", -1000000}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        //output
        Map<String, Integer> expected2 = Stream.of(new Object[][]{
                {"Mon", -1000000}, {"Tue", -666667}, {"Wed", -333334}, {"Thu", -1}, {"Fri", 333332}, {"Sat", 666665}, {"Sun", 1000000}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));


        LinkedHashMap<String, Integer> result = solObj.dateToDay(input2);
        assertEquals(expected2, result);

    }

}