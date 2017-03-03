package com.suood.guava.utilities;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.Collections;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Created by FENGCUIJIE on 2017/2/27.
 */
public class OrderingDemo {
    public void order_list_of_strings_alphabetically_case_insensitive () {

        List<String> TOP_RATED_CENTERS = Lists.newArrayList(
                "Dawson", "Gatski", "Langer", "Hein",
                "Frankie Baggadonuts", "Turner", "Trafton",
                "Stephenson", "Ringo", "Otto", "Webster");


        String topNameAlphabetically = Ordering
                .from(String.CASE_INSENSITIVE_ORDER)
                .min(TOP_RATED_CENTERS);

//        assertEquals("Dawson", topNameAlphabetically);
    }
    public void find_max_value_from_list_of_integers_guava () {

        List<Integer> top10CentersNumbers = Lists.newArrayList(
                63, 52, 62, 0, 66, 0, 57, 51, 60
        );

        Integer maxJerseyNumber = Ordering
                .natural()
                .max(top10CentersNumbers);

//        assertEquals(new Integer (66), maxJerseyNumber);
    }
    public void order_elements_based_on_length () {

        Ordering<String> byLength = new Ordering<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Ints.compare(s1.length(), s2.length());
            }
        };

        List<String> famousBridges = Lists.newArrayList(
                "Great Belt Bridge",
                "Chapel Bridge",
                "Chengyang Bridge",
                "null",
                "Brooklyn Bridge",
                "Ponte Vecchio"
        );

        Collections.sort(famousBridges,
                byLength.nullsFirst());

//        logger.info(famousBridges);

//        assertEquals("Great Belt Bridge", famousBridges.get(5));
    }
    public void reverse_elements_in_list () {

        Ordering<String> byLength = new Ordering<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Ints.compare(s1.length(), s2.length());
            }
        };

        List<String> famousBridges = Lists.newArrayList(
                "Great Belt Bridge",
                "Chapel Bridge",
                "Chengyang Bridge",
                "null",
                "Brooklyn Bridge",
                "Ponte Vecchio"
        );

        Collections.sort(famousBridges,
                byLength.nullsFirst().reverse());



        assertEquals("Great Belt Bridge", famousBridges.get(0));
    }
    public void order_list_of_integer_least_to_greatest () {

        List<Integer> startingLineUp = Lists.newArrayList(
                73, 58, 66, 57, 32, 88, 90, 12, 15, 99, 11
        );

        List<Integer> startingLineUpInOrder = Ordering
                .natural()
                .leastOf(startingLineUp, startingLineUp.size());

//        logger.info(startingLineUpInOrder);

//        assertEquals(new Integer(11), startingLineUpInOrder.get(0));
    }
    public void order_list_of_integer_greatest_to_least () {

        List<Integer> startingLineUp = Lists.newArrayList(
                73, 58, 66, 57, 32, 88, 90, 12, 15, 99, 11
        );

        List<Integer> startingLineUpGreatestToLeast = Ordering
                .natural()
                .greatestOf(startingLineUp, startingLineUp.size());

//        logger.info(startingLineUpGreatestToLeast);

//        assertEquals(new Integer(99), startingLineUpGreatestToLeast.get(0));
    }
    public void is_list_of_numbers_sorted_in_java_with_guava () {

        List<Integer> uwConferenceTitles = Lists.newArrayList(
                1896, 1897, 1901, 1906, 1912,
                1952, 1959, 1962, 1993, 1998,
                1999, 2010, 2011, 2012);

        boolean isSorted = Ordering
                .natural()
                .isOrdered(uwConferenceTitles);


//        assertTrue(isSorted);
    }
    public void is_list_of_strings_sorted_in_java_with_guava () {

        List<String> secConferenceEast = Lists.newArrayList(
                "Florida",
                "Georgia",
                "Missouri",
                "South Carolina",
                "Tennessee",
                "Vanderbilt");

        boolean isSorted = Ordering
                .natural()
                .isOrdered(secConferenceEast);

//        assertTrue(isSorted);
    }
    public void is_list_of_strings_sorted_case_insensitive_in_java_with_guava () {

        List<String> secConferenceEast = Lists.newArrayList(
                "alabama",
                "Alabama",
                "ALABAMA");

        boolean isSorted = Ordering
                .from(String.CASE_INSENSITIVE_ORDER)
                .isOrdered(secConferenceEast);

//        assertTrue(isSorted);
    }
    public void order_by_object_field () {

        List<GlassWare> beerGlasses = Lists.newArrayList(
                new GlassWare("Flute Glass", "Enhances and showcases..."),
                new GlassWare("Pilsner Glass (or Pokal)", "showcases color, ..."),
                new GlassWare("Pint Glass", "cheap to make..."),
                new GlassWare("Goblet (or Chalice)", "Eye candy..."),
                new GlassWare("Mug (or Seidel, Stein)", "Easy to drink..."),
                new GlassWare(null, null)
        );

        Ordering<GlassWare> byGlassWareName = Ordering.natural().nullsFirst()
                .onResultOf(new Function<GlassWare, String>() {
                    public String apply(GlassWare glassWare) {
                        return glassWare.getName();
                    }
                });

        GlassWare firstBeerGlass = byGlassWareName.min(beerGlasses);

        // first element will be null
//        assertNull(firstBeerGlass.getName());

        GlassWare lastBeerGlass = byGlassWareName.max(beerGlasses);
        assertEquals("Pint Glass", lastBeerGlass.getName());
    }
    public static void main(String[] args) {
    }


}
class GlassWare {

    private String name;
    private String description;

    public GlassWare(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}