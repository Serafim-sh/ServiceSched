/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.service;

import com.example.train_schedule.dao.DataDao;
import com.example.train_schedule.dto.Errors;
import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.dto.InvalidException;
import com.example.train_schedule.dto.ListIdTrain;
import com.example.train_schedule.dto.OutputMessage;
import com.example.train_schedule.dto.TrainSchedule;
import com.example.train_schedule.dto.TrainStations;
import com.example.train_schedule.dto.TrainWagons;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author simik
 */
@Service
public class ServiceScheduleImpl implements ServiceSchedule {

    private final DataDao dao;

    public ServiceScheduleImpl(DataDao dao) {
        this.dao = dao;
    }

    @Override
    public OutputMessage getSchedule(InputData inputData) {

        List<ListIdTrain> listIdTrain = dao.getIdTrainSchedule(inputData);

        if (listIdTrain == null || listIdTrain.isEmpty()) {
            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(409, "Нет расписания по заданным параметрам!");
            listErrors.add(errors);
            throw new InvalidException("Error scheduler", listErrors);
        }

        List<TrainSchedule> listTrainSchedule = new ArrayList<>();

        for (ListIdTrain idTrain : listIdTrain) {

            TrainSchedule trainSchedule = dao.getTrainSchedule(idTrain);
            List<TrainWagons> listTrainWagon = dao.getTrainWagon(idTrain);
            List<TrainStations> listTrainStation = dao.getTrainStation(idTrain);
            TrainSchedule tSchedule = new TrainSchedule();
            tSchedule.setNumberTrain(trainSchedule.getNumberTrain());
            tSchedule.setNameTrain(trainSchedule.getNameTrain());
            tSchedule.setDeparturePoint(trainSchedule.getDeparturePoint());
            tSchedule.setDateArrival(trainSchedule.getDateArrival());
            tSchedule.setDestinationPoint(trainSchedule.getDestinationPoint());
            tSchedule.setDateDeparture(trainSchedule.getDateDeparture());
            tSchedule.setListTrainStation(listTrainStation);
            tSchedule.setListTrainWagons(listTrainWagon);
            listTrainSchedule.add(tSchedule);

        }

        return new OutputMessage(
                null, listTrainSchedule, null);

    }

}
