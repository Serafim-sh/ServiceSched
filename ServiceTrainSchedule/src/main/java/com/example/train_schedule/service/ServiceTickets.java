/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.train_schedule.service;

import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.dto.OutputMessage;
import java.util.List;

/**
 *
 * @author simik
 */
public interface ServiceTickets {

    OutputMessage setOneTisked(InputData inData);

    OutputMessage setGroupTisked(List<InputData> ListInData);

    OutputMessage setReturnedTickets(InputData inData);

}
