package com.example.myapplication.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainGenerator {

    public static List<Train> generateTrains(int n) {

        List<Train> trains = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            trains.add(generateTrain());
        }

        return trains;
    }


    public static Train generateTrain() {
        Random random = new Random();

        long number = 1000L + random.nextInt(10000);

        String departure = "Departure time#" + 100L + random.nextInt(100);

        int seats = 64 + random.nextInt(37);

        int reservedSeats =  seats - random.nextInt(30);

        return new Train(number, departure, seats, reservedSeats);
    }
}
