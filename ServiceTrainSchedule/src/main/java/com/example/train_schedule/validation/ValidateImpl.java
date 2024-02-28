/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.validation;

import com.example.train_schedule.configuration.ConfigProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author simik
 */
@Service
public class ValidateImpl implements Validate {

    ConfigProperties configProp = new ConfigProperties();

    private static final Logger log = LoggerFactory.getLogger(ValidateImpl.class);

    @Override
    public int validateToken(String token, String jwtSecret) {
        if (token != null) {
            if (jwtSecret != null) {
                try {
                    Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                    return 1;
                } catch (ExpiredJwtException expEx) {
                    log.error("Token expired", expEx);
                    return 0;
                } catch (UnsupportedJwtException unsEx) {
                    log.error("Unsupported jwt", unsEx);
                    return 0;
                } catch (MalformedJwtException mjEx) {
                    log.error("Malformed jwt", mjEx);
                    return 0;
                } catch (SignatureException sEx) {
                    log.error("Invalid signature", sEx);
                    return 0;
                } catch (Exception e) {
                    log.error("invalid token", e);
                    return 0;
                }
            }
            return 0;
        }
        return 0;
    }

    @Override
    public int validateDateTimeReturnedTickets(Date dateArrival) {

        int constHours = Integer.valueOf(configProp.getConfigValue("hours"));

        Date dt = new Date();

        long milliseconds = dateArrival.getTime() - dt.getTime();
        int hours = (int) (milliseconds / (60 * 60 * 1000));

        if (hours < 2) {
            return 1;
        }
        return 0;

    }

}
