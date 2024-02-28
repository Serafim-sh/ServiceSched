/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author simik
 */
public class ConfigProperties {
    
    private static final Logger log = LoggerFactory.getLogger(ConfigProperties.class);

    public String getConfigValue(String configKey) {
        try (InputStream input = ConfigProperties.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                return "";
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            return prop.getProperty(configKey);

        } catch (IOException ex) {
            log.error("Read config parametr", ex);
            return "";
        }
    }
    
}
