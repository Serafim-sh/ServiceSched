/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.train_schedule.service;

import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.dto.OutputMessage;

/**
 *
 * @author simik
 */
public interface ServiceSchedule {
    
    OutputMessage getSchedule(InputData inputData);
    
}
