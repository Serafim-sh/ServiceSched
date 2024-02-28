/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.train_schedule.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author simik
 */
public class InputData {

    private String departurePoint;
    private String destinationPoint;
    private Date dateDeparture;

    //  поезд вагон место дата отрпавления  добавить
    private String numberTrain;
    private String numberWagons;
    private Integer seat;
    private String familyName;
    private String FirstName;
    private String patronymic;
    private Integer gender;
    private LocalDate birthDay;
    private int docType;
    private String docSer;
    private String docNum;
    private String ctznOksm;

    private String numberTicket;

    public InputData() {
    }

    public InputData(String departurePoint, String destinationPoint, Date dateDeparture) {
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.dateDeparture = dateDeparture;
    }

    public InputData(String numberTrain, String numberWagons, Integer seat, String familyName, String FirstName, String patronymic, Integer gender, LocalDate birthDay, int docType, String docSer, String docNum, String ctznOksm, String numberTicket) {
        this.numberTrain = numberTrain;
        this.numberWagons = numberWagons;
        this.seat = seat;
        this.familyName = familyName;
        this.FirstName = FirstName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthDay = birthDay;
        this.docType = docType;
        this.docSer = docSer;
        this.docNum = docNum;
        this.ctznOksm = ctznOksm;
        this.numberTicket = numberTicket;
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

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getNumberTrain() {
        return numberTrain;
    }

    public void setNumberTrain(String numberTrain) {
        this.numberTrain = numberTrain;
    }

    public String getNumberWagons() {
        return numberWagons;
    }

    public void setNumberWagons(String numberWagons) {
        this.numberWagons = numberWagons;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public String getDocSer() {
        return docSer;
    }

    public void setDocSer(String docSer) {
        this.docSer = docSer;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(String numberTicket) {
        this.numberTicket = numberTicket;
    }

    public String getCtznOksm() {
        return ctznOksm;
    }

    public void setCtznOksm(String ctznOksm) {
        this.ctznOksm = ctznOksm;
    }

}
