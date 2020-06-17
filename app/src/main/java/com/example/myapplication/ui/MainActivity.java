package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.Database;
import com.example.myapplication.db.Train;
import com.example.myapplication.db.TrainGenerator;
import com.example.myapplication.repository.DBTrainRepository;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements TrainAdapter.OnTrainClickListener, TrainFragment.OnSeatsReservedListener {

    private final int INITIAL_TRAINS = 5;


    private RecyclerView recyclerView;
    private TrainAdapter trainAdapter;
    private DBTrainRepository trainRepository;


    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Database database = new Database();
        trainRepository = new DBTrainRepository(database);
        trainRepository.insertTrains(TrainGenerator.generateTrains(INITIAL_TRAINS));


        trainAdapter = new TrainAdapter(trainRepository.getALlTrains(), this);
        recyclerView.setAdapter(trainAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // TODO: change it to 500ms!!!!
        handler.post(new SimulateVisitors());
    }

    @Override
    public void onTrainClick(Train train) {
//        Toast.makeText(this, train.getNumber()  + "", Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getSupportFragmentManager();
        TrainFragment trainFragment = TrainFragment.newInstance(train.getNumber(), train.getDepartureTime(), train.getSeats(), train.getReservedSeats());
        trainFragment.show(fragmentManager, "fragment");
    }

    @Override
    public void onWithdrawReserve(long trainNumber) {
        Toast.makeText(this, "Withdrawing reserve..", Toast.LENGTH_SHORT).show();
        trainRepository.withdrawReserve(trainNumber);
        trainAdapter.notifyDataSetChanged();
    }

    @Override
    public void onReserveSeat(long trainNumber) {
        Toast.makeText(this, "Reserving..", Toast.LENGTH_SHORT).show();
        trainRepository.reserveSeat(trainNumber);
        trainAdapter.notifyDataSetChanged();
    }


    private class SimulateVisitors implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            int randomInt = random.nextInt(3);
            switch (randomInt) {
                case 0: {
                    //  Reserve a train in full
                    Toast.makeText(MainActivity.this, "reserve a train in full", Toast.LENGTH_SHORT).show();
                    List<Train> trainList = trainRepository.getALlTrains();
                    if (trainList.size() != 0) {
                        int randomTrain = random.nextInt(trainList.size());
                        trainRepository.reserveWholeTrain(trainList.get(randomTrain).getNumber());
                    }
                    break;
                }
                case 1: {
                    // Add a train
                    Toast.makeText(MainActivity.this, "add a train ", Toast.LENGTH_SHORT).show();
                    trainRepository.insertTrain(TrainGenerator.generateTrain());
                    break;
                }
                case 2: {
                    // Delete the first train
                    Toast.makeText(MainActivity.this, "delete train ", Toast.LENGTH_SHORT).show();
                    List<Train> trainList = trainRepository.getALlTrains();
                    if (trainList.size() != 0) {
                        trainRepository.deleteTrain(trainList.get(0).getNumber());

                    }
                    break;
                }
            }

            trainAdapter.notifyDataSetChanged();

            //TODO: change to 500ms
            handler.postDelayed(this, 1000);
        }
    }
}