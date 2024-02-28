/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.service;

import com.example.train_schedule.dto.Errors;
import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.dto.InvalidException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author simik
 */
@Service
public class LogicImpl implements Logic {

    private final ServiceAuthorization servAuth;
    private final ServiceSchedule serviceSchedule;
    private final ServiceTickets serviceTickets;

    public LogicImpl(ServiceAuthorization servAuth, ServiceSchedule serviceSchedule, ServiceTickets serviceTickets) {
        this.servAuth = servAuth;
        this.serviceSchedule = serviceSchedule;
        this.serviceTickets = serviceTickets;
    }

    @Override
    public ResponseEntity<Object> getTrainSchedule(String token, InputData inData) {

        final int authorization = servAuth.getAuthorization(token);

        if (authorization == 401 || authorization == 403 || authorization == 500) {

            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(authorization, HttpStatus.resolve(authorization).toString());
            listErrors.add(errors);
            throw new InvalidException("Error Authorization Service Train chedule", listErrors);
        }

        return ResponseEntity.ok(serviceSchedule.getSchedule(inData));

    }

    @Override
    public ResponseEntity<Object> setOneTisked(String token, InputData inData) {
        final int authorization = servAuth.getAuthorization(token);

        if (authorization == 401 || authorization == 403 || authorization == 500) {

            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(authorization, HttpStatus.resolve(authorization).toString());
            listErrors.add(errors);
            throw new InvalidException("Error Authorization Service Train chedule", listErrors);
        }

        return ResponseEntity.ok(serviceTickets.setOneTisked(inData));

    }

    @Override
    public ResponseEntity<Object> setGroupTisked(String token, List<InputData> ListInData) {
        final int authorization = servAuth.getAuthorization(token);

        if (authorization == 401 || authorization == 403 || authorization == 500) {

            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(authorization, HttpStatus.resolve(authorization).toString());
            listErrors.add(errors);
            throw new InvalidException("Error Authorization Service Train chedule", listErrors);

        }

        return ResponseEntity.ok(serviceTickets.setGroupTisked(ListInData));

    }

    @Override
    public ResponseEntity<Object> setReturnedTickets(String token, InputData inData) {

        final int authorization = servAuth.getAuthorization(token);

        if (authorization == 401 || authorization == 403 || authorization == 500) {

            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(authorization, HttpStatus.resolve(authorization).toString());
            listErrors.add(errors);
            throw new InvalidException("Error Authorization Service Train chedule", listErrors);

        }

        return ResponseEntity.ok(serviceTickets.setReturnedTickets(inData));
    }

}
