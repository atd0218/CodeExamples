package com.tester.pkgtest.ooa;

import java.util.ArrayList;
import java.util.List;

public class DriverOOA {
    public static void performExercises(List<Exercise> l) {

        /**
         * i. using an enhanced-for loop, loop through each exercise
         * 1. if the exercise is a BallSport, output the (simple) class name i.e. refName.getClass().getSimpleName().
         * 2. if the exercise is an OlympicSport, output the (simple) class name
         */

        for(Exercise e: l) {
            if (e.getClass().getSimpleName().toString().equals("Soccer")) {
                System.out.println(e.getClass().getSimpleName());
                System.out.println(e.distanceCovered());
            }
            if (e.getClass().getSimpleName().toString().equals("RaceWalking")) {
                System.out.println(e.getClass().getSimpleName());
                System.out.println(e.distanceCovered());
            }
            e.move();
        }

    }

    public static void main(String[] args) {

        /**
         * i. create a List implemented by ArrayList; use type inference/diamond operator.
         * ii. create a Soccer object where the distanceKm is 12.5 (km); refer to the object using an Exercise reference; add to the list.
         * iii. create a Sprint object where the distanceKm is .1 (km); refer to the object using an Exercise reference; add to the list.
         * iv. create a Stroll object where the distanceKm is 10 (km); refer to the object using an Exercise reference; add to the list.
         * v. create a RaceWalking object where the distanceKm is 5 (km); refer
         */
        List<Exercise> list = new ArrayList<Exercise>();

        Exercise Soccer = new Soccer(12.5);
        list.add(Soccer);

        Exercise Sprint = new Sprint(.1);
        list.add(Sprint);

        Exercise Stroll = new Stroll(10);
        list.add(Stroll);

        Exercise RaceWalking = new RaceWalking(5);
        list.add(RaceWalking);

        performExercises(list);


    }
}
