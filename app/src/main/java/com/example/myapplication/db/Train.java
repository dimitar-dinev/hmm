package com.example.myapplication.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Train implements Parcelable {

    private long number;
    private String departureTime;
    private int seats;
    private int reservedSeats;

    private boolean seatReserved;

    public static Creator<Train> CREATOR = new Creator<Train>() {
        @Override
        public Train createFromParcel(Parcel source) {
            return new Train(source);
        }

        @Override
        public Train[] newArray(int size) {
            return new Train[size];
        }
    };

    public Train (Parcel in) {
        this.number = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.number);
    }

    public Train(long number, String departureTime, int seats, int reservedSeats) {
        this.number = number;
        this.departureTime = departureTime;
        this.seats = seats;
        this.reservedSeats = reservedSeats;

        this.seatReserved = false;
    }

    public void reserveAllSeats() {
        this.reservedSeats = this.seats;
    }

    public void reserveSeat() {
        this.reservedSeats++;
        this.seatReserved = true;
    }


    public void withdrawSeat() {
        this.reservedSeats--;
        this.seatReserved = false;
    }

    public long getNumber() {
        return number;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getSeats() {
        return seats;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public boolean isSeatReserved() {
        return seatReserved;
    }

    public void setSeatReserved(boolean seatReserved) {
        this.seatReserved = seatReserved;
    }
}
