package com.example.myapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.Train;

import java.util.List;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.TrainViewHolder> {

    private List<Train> trainList;
    private OnTrainClickListener listener;

    public TrainAdapter(List<Train> trainList, OnTrainClickListener listener) {
        this.trainList = trainList;
        this.listener = listener;
    }

    public interface OnTrainClickListener {
        void onTrainClick(Train train);
        void onWithdrawReserve(long trainNumber);
    }


    static class TrainViewHolder extends RecyclerView.ViewHolder {

        TextView numberTextView;
        TextView departureTextView;
        TextView seatsTextView;
        TextView reservedSeatsTextView;

        Button actionButton;

        public TrainViewHolder(View v) {
            super(v);

            numberTextView = v.findViewById(R.id.numberTextView);
            departureTextView = v.findViewById(R.id.departureTextView);
            seatsTextView = v.findViewById(R.id.seatsTextView);
            reservedSeatsTextView = v.findViewById(R.id.reservedSeatsTextView);

            actionButton = v.findViewById(R.id.button);

        }
    }

    @NonNull
    @Override
    public TrainAdapter.TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item, parent, false);
        return new TrainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainAdapter.TrainViewHolder holder, int position) {

        Train currentTrain = trainList.get(position);

        holder.numberTextView.setText(String.valueOf(currentTrain.getNumber()));
        holder.departureTextView.setText(currentTrain.getDepartureTime());
        holder.seatsTextView.setText(String.valueOf(currentTrain.getSeats()));
        holder.reservedSeatsTextView.setText(String.valueOf(currentTrain.getReservedSeats()));

        if (!currentTrain.isSeatReserved()) {
            holder.actionButton.setVisibility(View.INVISIBLE);
        } else {
            holder.actionButton.setVisibility(View.VISIBLE);
            holder.actionButton.setOnClickListener(l -> {
                listener.onWithdrawReserve(currentTrain.getNumber());
            });
        }

        holder.itemView.setOnClickListener(l -> {
            listener.onTrainClick(currentTrain);
        });
    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }
}
