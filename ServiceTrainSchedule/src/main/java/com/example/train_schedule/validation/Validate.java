/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.train_schedule.validation;

import java.util.Date;

/**
 *
 * @author simik
 */
public interface Validate {
    
    int validateToken(String token, String jwtSecret);
    
    int validateDateTimeReturnedTickets(Date dateArrival);
    
}
