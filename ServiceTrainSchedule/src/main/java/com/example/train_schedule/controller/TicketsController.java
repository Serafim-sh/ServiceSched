/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.example.train_schedule.controller;

import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.service.Logic;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author simik
 */
@RestController
@RequestMapping(value = "/api/")
public class TicketsController {

    private final Logic logic;

    public TicketsController(Logic logic) {
        this.logic = logic;
    }

    @RequestMapping(value = "train_shedule", method = RequestMethod.GET)
    public ResponseEntity<Object> getShedule(
            @RequestHeader("Authorization") String token,
            @RequestParam Optional<String> departurePoint,
            @RequestParam Optional<String> destinationPoint,
            @RequestParam Optional<Date> dateDeparture
    ) {

        InputData inData = new InputData(
                StringUtils.normalizeSpace(departurePoint.orElseGet(() -> null)),
                StringUtils.normalizeSpace(destinationPoint.orElseGet(() -> null)),
                dateDeparture.orElseGet(() -> null));

        return logic.getTrainSchedule(token, inData);
    }

    @RequestMapping(value = "new_ticket_one", method = RequestMethod.POST)
    public ResponseEntity<Object> setTicket(
            @RequestHeader("Authorization") String token,
            @RequestBody InputData inData) {
        return logic.setOneTisked(token, inData);
    }

    @RequestMapping(value = "new_ticket_group", method = RequestMethod.POST)
    public ResponseEntity<Object> setTickets(
            @RequestHeader("Authorization") String token,
            @RequestBody List<InputData> listInData) {
        return logic.setGroupTisked(token, listInData);
    }

    @RequestMapping(value = "ticket_refund", method = RequestMethod.POST)
    public ResponseEntity<Object> returnTickets(
            @RequestHeader("Authorization") String token,
            @RequestBody InputData inData) {

        return logic.setReturnedTickets(token, inData);

    }

}
