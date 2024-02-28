/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.service;

import com.example.train_schedule.dao.DataDao;
import com.example.train_schedule.dto.Errors;
import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.dto.InvalidException;
import com.example.train_schedule.dto.OutputMessage;
import com.example.train_schedule.dto.Tickets;
import com.example.train_schedule.validation.Validate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author simik
 */
@Service
public class ServiceTicketsImpl implements ServiceTickets {

    private final DataDao dao;
    private final Validate valid;

    public ServiceTicketsImpl(DataDao dao, Validate valid) {
        this.dao = dao;
        this.valid = valid;
    }

    @Override
    public OutputMessage setOneTisked(InputData inData) {

        int pidTrain = dao.getPidTrain(inData.getNumberTrain());

        int pidWagons = dao.getPidWagons(inData.getNumberWagons(), pidTrain);

        int existsSpase = dao.validNumberPlase(pidTrain, pidWagons, inData.getSeat());

        if (existsSpase == 0) {
            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(409, "Выбранного места не существует!");
            listErrors.add(errors);
            throw new InvalidException("Error seat wagon", listErrors);
        }

        int freeSpace = dao.validSeat(pidTrain, pidWagons, inData.getSeat());

        int idTickets;

        Tickets tickets = null;

        if (freeSpace == 0) {

            int pidPassenger = dao.searchPassengers(inData);

            idTickets = dao.setTiskets(inData, pidTrain, pidWagons, pidPassenger);

            tickets = dao.getTiskets(idTickets);

        } else {
            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(409, "Выбранное место недоступно!");
            listErrors.add(errors);
            throw new InvalidException("Error seat wagon", listErrors);
        }

        List<Tickets> listTickets = new ArrayList<>();
        listTickets.add(tickets);
        return new OutputMessage(null, null, listTickets);
    }

    @Override
    public OutputMessage setGroupTisked(List<InputData> listInData) {

        int countSeat = listInData.size();

        int pidTrain = dao.getPidTrain(listInData.get(0).getNumberTrain());

        int pidWagons = dao.getPidWagons(listInData.get(0).getNumberWagons(), pidTrain);

        int validCountSeat = dao.validCountSeat(pidTrain, pidWagons, countSeat);

        if (validCountSeat != 0) {

            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(409, "Не доступно выбранное количество мест в вагоне!");
            listErrors.add(errors);
            throw new InvalidException("Error group seat wagon", listErrors);
        }

        List<Integer> listIdTickets = new ArrayList<>();

        for (InputData inData : listInData) {

            int freeSpace = dao.validSeat(pidTrain, pidWagons, inData.getSeat());

            int idTickets;

            if (freeSpace == 0) {

                int pidPassenger = dao.searchPassengers(inData);

                idTickets = dao.setTiskets(inData, pidTrain, pidWagons, pidPassenger);
                listIdTickets.add(idTickets);

            }

        }

        List<Tickets> listTickets = new ArrayList<>();
        if (countSeat == listIdTickets.size()) {

            for (Integer idTickets : listIdTickets) {

                Tickets tickets = null;
                tickets = dao.getTiskets(idTickets);
                listTickets.add(tickets);

            }

        } else {

            for (Integer idTickets : listIdTickets) {

                dao.deleteTickets(idTickets);
            }
            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(409, "Не доступно одно из выбранных мест в вагоне!");
            listErrors.add(errors);
            throw new InvalidException("Error group seat wagon", listErrors);
        }

        return new OutputMessage(null, null, listTickets);

    }

    @Override
    public OutputMessage setReturnedTickets(InputData inData) {

        int pidTicket = dao.getTicketsReturned(inData.getNumberTicket());

        Tickets ticket = dao.getTiskets(pidTicket);

        if (ticket == null) {

            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(409, "Билет не найден!");
            listErrors.add(errors);
            throw new InvalidException("Error returned tickets find", listErrors);

        }

        int time = valid.validateDateTimeReturnedTickets(ticket.getRegistrationDate());

        if (time == 1) {

            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(409, "Нельзя вернуть билет позднее 2 часов от времени отправления поезда");
            listErrors.add(errors);
            throw new InvalidException("Error returned tickets", listErrors);

        }

        dao.setTicketsReturned(ticket.getIdTickets());

        Tickets tickets = new Tickets(null, inData.getNumberTicket(), "Возврат билета осуществлен");
        List<Tickets> listTickets = new ArrayList<>();
        listTickets.add(tickets);
        return new OutputMessage(null, null, listTickets);

    }

}
