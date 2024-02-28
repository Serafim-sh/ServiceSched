/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author simik
 */
public class OutputMessage {

    @JsonProperty("Errors")
    private List<Errors> errors;

    @JsonProperty("TrainSchedule")
    private List<TrainSchedule> trainSchedule;

    @JsonProperty("Tickets")  
    private List<Tickets> tickets; 
    

    public OutputMessage() {
    }

    public OutputMessage(List<Errors> errors, List<TrainSchedule> trainSchedule, List<Tickets> tickets) {
        this.errors = errors;
        this.trainSchedule = trainSchedule;
        this.tickets = tickets;
    }

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    public List<TrainSchedule> getTrainSchedule() {
        return trainSchedule;
    }

    public void setTrainSchedule(List<TrainSchedule> trainSchedule) {
        this.trainSchedule = trainSchedule;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "OutputMessage{" + "errors=" + errors + ", trainSchedule=" + trainSchedule + ", tickets=" + tickets + '}';
    }

}
