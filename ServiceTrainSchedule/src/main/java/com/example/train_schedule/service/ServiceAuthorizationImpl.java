/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.service;

import com.example.train_schedule.dao.DataDao;
import com.example.train_schedule.validation.Validate;
import org.springframework.stereotype.Service;
import static org.springframework.util.StringUtils.hasText;

/**
 *
 * @author simik
 */
@Service
public class ServiceAuthorizationImpl implements ServiceAuthorization {

    private final Validate valid;

    private final DataDao dao;

    public ServiceAuthorizationImpl(Validate valid, DataDao dao) {
        this.valid = valid;
        this.dao = dao;
    }

    @Override
    public int getAuthorization(String token) {

        String key = dao.getKey();
        int resultValidate = valid.validateToken(getTokenHeader(token), key);

        if (resultValidate == 0) {
            return 401;
        }
        
        int resultAuth = dao.getAuthorization(token);
        

        return resultAuth;
    }

    private String getTokenHeader(String token) {
        if (hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

}
