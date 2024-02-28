/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.exception;

import com.example.train_schedule.dao.DataDao;
import com.example.train_schedule.dto.Errors;
import com.example.train_schedule.dto.InvalidException;
import com.example.train_schedule.dto.OutputMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author simik
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final DataDao dataDao;

    public RestExceptionHandler(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
        UUID id = UUID.randomUUID();
        dataDao.setLogError(id, ex.toString());
        log.error("Exception " + id.toString(), ex);
        Errors err = new Errors(id, 500, "Произошла неизвестная ошибка");
        List<Errors> listErrors = new ArrayList<>();
        listErrors.add(err);
        return new ResponseEntity<>(new OutputMessage(listErrors, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    protected ResponseEntity<Object> runtimeExceptions(Exception ex) {
        UUID id = UUID.randomUUID();
        dataDao.setLogError(id, ex.toString());
        log.error("RuntimeException " + id.toString(), ex);
        Errors err = new Errors(id, 500, "Произошла неизвестная ошибка");
        List<Errors> listErrors = new ArrayList<>();
        listErrors.add(err);
        return new ResponseEntity<>(new OutputMessage(listErrors, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidException.class)
    @ResponseBody
    public ResponseEntity<Object> onInvalidInsuranceException(
            InvalidException e) {
        UUID id = UUID.randomUUID();
        dataDao.setLogError(id, e.toString());
        log.error("InvalidException " + id.toString(), e);

        final List<Errors> flk = e.getGetListError().stream()
                .map(err -> new Errors(
                err.getCode(),
                err.getFieldName(),
                err.getMessage()
        )
                ).collect(Collectors.toList());
        return new ResponseEntity<>(new OutputMessage(flk, null, null), HttpStatus.resolve(flk.get(0).getCode()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<Object> methodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        UUID id = UUID.randomUUID();
        dataDao.setLogError(id, e.toString());
        log.error("HttpRequestMethodNotSupportedException " + id.toString(), e);
        Errors err = new Errors(id, 405, e.getLocalizedMessage());
        List<Errors> listErrors = new ArrayList<>();
        listErrors.add(err);
        return new ResponseEntity<>(new OutputMessage(listErrors, null, null), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<Object> onNotValidException(
            HttpMessageNotReadableException e) {
        UUID id = UUID.randomUUID();
        dataDao.setLogError(id, e.toString());
        log.error("HttpMessageNotReadableException " + id.toString(), e);
        Errors err = new Errors(id, 400, e.getLocalizedMessage());
        List<Errors> listErrors = new ArrayList<>();
        listErrors.add(err);
        return new ResponseEntity<>(new OutputMessage(listErrors, null, null), HttpStatus.BAD_REQUEST);
    }

}
