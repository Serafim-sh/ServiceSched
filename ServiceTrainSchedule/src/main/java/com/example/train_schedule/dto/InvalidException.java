/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.dto;

import java.util.List;

/**
 *
 * @author simik
 */
public class InvalidException extends RuntimeException {

    private List<Errors> getListError;

    public InvalidException(String message, List<Errors> getListError) {
        super(message);
        this.getListError = getListError;
    }

    public List<Errors> getGetListError() {
        return getListError;
    }  

}
