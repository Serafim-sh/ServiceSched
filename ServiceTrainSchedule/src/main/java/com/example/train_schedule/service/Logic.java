/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.train_schedule.service;

import com.example.train_schedule.dto.InputData;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author simik
 */
public interface Logic {

    ResponseEntity<Object> getTrainSchedule(String token, InputData inData);

    ResponseEntity<Object> setOneTisked(String token, InputData inData);

    ResponseEntity<Object> setGroupTisked(String token, List<InputData> ListInData);

    ResponseEntity<Object> setReturnedTickets(String token, InputData inData);

}
