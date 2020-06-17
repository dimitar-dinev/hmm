package com.example.myapplication.repository;

import com.example.myapplication.db.Train;

import java.util.List;

public interface TrainRepository {

    List<Train> getALlTrains();

    void insertTrain(Train train);

    void insertTrains(List<Train> trains);

    void reserveSeat(long trainNumber);

    void withdrawReserve(long trainNumber);

    void reserveWholeTrain(long trainNumber);

    void deleteTrain(long trainNumber);

}
