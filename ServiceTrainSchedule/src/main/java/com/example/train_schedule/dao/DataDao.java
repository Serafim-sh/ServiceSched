/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.train_schedule.dao;

import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.dto.ListIdTrain;
import com.example.train_schedule.dto.Tickets;
import com.example.train_schedule.dto.TrainSchedule;
import com.example.train_schedule.dto.TrainStations;
import com.example.train_schedule.dto.TrainWagons;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author simik
 */
public interface DataDao {

    List<ListIdTrain> getIdTrainSchedule(InputData inData);

    TrainSchedule getTrainSchedule(ListIdTrain idTrain);

    List<TrainWagons> getTrainWagon(ListIdTrain idTrain);

    List<TrainStations> getTrainStation(ListIdTrain idTrain);

    int setTiskets(InputData inData, int pidTrain, int pidWagons, int pidPassenger);

    Tickets getTiskets(int id);

    int getTicketsReturned(String numberTicked);

    void setTicketsReturned(int id);

    int getAuthorization(String token);

    String getKey();

    int validSeat(int pidTrain, int pidWagons, int seat);

    int getPidTrain(String numberTrain);

    int getPidWagons(String numberWagons, int pidTrain);

    int searchPassengers(InputData inData);

    int validCountSeat(int pidTrain, int pidWagons, int countSeat);

    void deleteTickets(int id);

    int validNumberPlase(int pidTrain, int pidWagons, int seat);

    void setLogError(UUID id, String messageError);

    void setLogger(String code, String coment, String action, String id_zapbd);
}
