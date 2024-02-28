/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author simik
 */
public class TrainSchedule {

    private String numberTrain;
    private String nameTrain;
    private String departurePoint;
    private String destinationPoint;
    private Date dateArrival;
    private Date dateDeparture;
    private String comment;
    private List<TrainWagons> listTrainWagons;
    private List<TrainStations> listTrainStation;

    public TrainSchedule() {
    }

    public String getNumberTrain() {
        return numberTrain;
    }

    public void setNumberTrain(String numberTrain) {
        this.numberTrain = numberTrain;
    }

    public String getNameTrain() {
        return nameTrain;
    }

    public void setNameTrain(String nameTrain) {
        this.nameTrain = nameTrain;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TrainWagons> getListTrainWagons() {
        return listTrainWagons;
    }

    public void setListTrainWagons(List<TrainWagons> listTrainWagons) {
        this.listTrainWagons = listTrainWagons;
    }

    public List<TrainStations> getListTrainStation() {
        return listTrainStation;
    }

    public void setListTrainStation(List<TrainStations> listTrainStation) {
        this.listTrainStation = listTrainStation;
    }

}
