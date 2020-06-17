package com.example.myapplication.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;


public class TrainFragment extends DialogFragment {

    public interface OnSeatsReservedListener {
        void onReserveSeat(long trainNumber);
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private long number;
    private int seats;
    private int reservedSeats;
    private String departure;

    private OnSeatsReservedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (OnSeatsReservedListener) context;
    }

    public TrainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TrainFragment newInstance(long number, String depature, int seats, int reserved) {
        TrainFragment fragment = new TrainFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, number);
        args.putString(ARG_PARAM2, depature);
        args.putInt(ARG_PARAM3, seats);
        args.putInt(ARG_PARAM4, reserved);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = getArguments().getLong(ARG_PARAM1);
            departure = getArguments().getString(ARG_PARAM2);
            seats = getArguments().getInt(ARG_PARAM3);
            reservedSeats = getArguments().getInt(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_train, container, false);
    }


    private TextView numberTextView;
    private TextView departureTextView;
    private TextView seatsTextView;
    private TextView reservedSeatsTextView;

    private Button backButton;
    private Button saveButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numberTextView = view.findViewById(R.id.numberTextView);
        departureTextView = view.findViewById(R.id.departureTextView);
        seatsTextView = view.findViewById(R.id.seatsTextView);
        reservedSeatsTextView = view.findViewById(R.id.reservedSeatsTextView);

        // Train info
        numberTextView.setText(String.valueOf(number));
        departureTextView.setText(departure);
        seatsTextView.setText(String.valueOf(seats));
        reservedSeatsTextView.setText(String.valueOf(reservedSeats));

        // Actions
        backButton = view.findViewById(R.id.backButton);
        saveButton = view.findViewById(R.id.saveButton);


        backButton.setOnClickListener(l -> dismiss());
        saveButton.setOnClickListener(l -> {

            if (seats > reservedSeats) {
                   listener.onReserveSeat(number);
                   dismiss();
            } else {
                Toast.makeText(getActivity(), "No more free seats, sorry!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
