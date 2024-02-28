/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 *
 * @author simik
 */
public class Errors {

    @JsonProperty("IdError")
    private UUID id;
    @JsonProperty("Code")
    private Integer code;
    @JsonProperty("FieldName")
    private String fieldName;
    @JsonProperty("Message")
    private String message;

    public Errors() {
    }

    public Errors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Errors(UUID id, Integer code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    public Errors(Integer code, String fieldName, String message) {
        this.code = code;
        this.fieldName = fieldName;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Errors{" + "id=" + id + ", code=" + code + ", fieldName=" + fieldName + ", message=" + message + '}';
    }

}
