package com.example.myapplication.repository;

import com.example.myapplication.db.Database;
import com.example.myapplication.db.Train;

import java.util.List;

public class DBTrainRepository implements TrainRepository {

    private Database database;

    public DBTrainRepository(Database database) {
        this.database = database;
    }


    @Override
    public List<Train> getALlTrains() {
        return this.database.getAllTrains();
    }

    @Override
    public void insertTrain(Train train) {
        this.database.getAllTrains().add(train);
    }

    @Override
    public void insertTrains(List<Train> trains) {
        this.database.getAllTrains().addAll(trains);
    }

    @Override
    public void reserveSeat(long trainNumber) {
        List<Train> allTrains = this.database.getAllTrains();
        for(int i = 0; i < allTrains.size(); i++) {
            Train currentTrain = allTrains.get(i);
            if (currentTrain.getNumber() == trainNumber) {
                currentTrain.reserveSeat();
                return;
            }
        }
    }

    @Override
    public void withdrawReserve(long trainNumber) {
        List<Train> allTrains = this.database.getAllTrains();
        for(int i = 0; i < allTrains.size(); i++) {
            Train currentTrain = allTrains.get(i);
            if (currentTrain.getNumber() == trainNumber) {
                currentTrain.withdrawSeat();
                return;
            }
        }
    }

    @Override
    public void reserveWholeTrain(long trainNumber) {
        List<Train> allTrains = this.database.getAllTrains();
        for(int i = 0; i < allTrains.size(); i++) {
            Train currentTrain = allTrains.get(i);
            if (currentTrain.getNumber() == trainNumber) {
                currentTrain.reserveAllSeats();
                return;
            }
        }
    }

    @Override
    public void deleteTrain(long trainNumber) {
        List<Train> allTrains = this.database.getAllTrains();
        for(int i = 0; i < allTrains.size(); i++) {
            Train currentTrain = allTrains.get(i);
            if (currentTrain.getNumber() == trainNumber) {
                allTrains.remove(i);
                return;
            }
        }
    }
}
