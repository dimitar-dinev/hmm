package com.example.myapplication.db;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Train> allTrains;

    public Database() {
        allTrains = new ArrayList<>();
    }

    public List<Train> getAllTrains() {
        return allTrains;
    }
}
