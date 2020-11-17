package com.company.recency;

import java.util.*;

public class Recency {

    private Set<Event> eventList;

    private void setup(){

        eventList = new HashSet<>();
        eventList.add(new Event("A", 2));
        eventList.add(new Event("A", 4));
        eventList.add(new Event("A", 8));
        eventList.add(new Event("A", 10));
        eventList.add(new Event("A", 12));

        eventList.add(new Event("B", 1));
        eventList.add(new Event("B", 3));
        eventList.add(new Event("B", 6));
        eventList.add(new Event("B", 13));

        eventList.add(new Event("C", 2));
        eventList.add(new Event("C", 5));
        eventList.add(new Event("C", 7));
        eventList.add(new Event("C", 9));
        eventList.add(new Event("C", 15));



    }

    public void runTest(){
        setup();
        List<Integer> result;
        result = calculateFrequency(eventList, Arrays.asList(1, 5, 8), 10);
        System.out.println(result);

    }


    public List<Integer> calculateFrequency(Set<Event> eventList, List<Integer> groups, int asOfTime){
        //assume given ranges and point in time are valid
        //assume all events have Category and Timestamp

        //points and ranges are all inclusive

        int numgroups = groups.size();

        //initialize results array with zeros
        List<Integer> results = new ArrayList<>(Collections.nCopies(numgroups, 0));

        for (Event e : eventList) {
            for (int i=0; i < numgroups; i++){

                int range = groups.get(i);
                int count = results.get(i);

                int startTime = asOfTime - range;
                if (isInRange(e, startTime, asOfTime)) {
                    count++;
                }

                results.set(i, count);


            }


        }


        return results;
    }


    //this answer has too many loops
    public List<Integer> calculateFrequencyNaive(Set<Event> eventList, List<Integer> groups, int asOfTime){
        //assume given ranges and point in time are valid
        //assume all events have Category and Timestamp

        //points and ranges are all inclusive

        List<Integer> result = new ArrayList<>();


        for (Integer group : groups) {
            Integer count = 0;

            Integer startTime = asOfTime - group;
            for (Event e : eventList) {
                if (isInRange(e, startTime, asOfTime)) {
                    count++;
                }
            }

            result.add(count);
        }


        return result;
    }




    private boolean isInRange(Event e, Integer start, Integer end){
        //returns true if this event falls within [start,end] range, inclusive

        Integer time = e.getTimestamp();
        if (time <= end && time >= start) {
            //System.out.println("Event " + e.getCategory() + "." + e.getTimestamp() + " is within " + start + " and " + end);
            return true;
        } else {
            return false;
        }
    }
}
