/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.dao;

import com.example.train_schedule.dto.Errors;
import com.example.train_schedule.dto.InputData;
import com.example.train_schedule.dto.InvalidException;
import com.example.train_schedule.dto.ListIdTrain;
import com.example.train_schedule.dto.Tickets;
import com.example.train_schedule.dto.TrainSchedule;
import com.example.train_schedule.dto.TrainStations;
import com.example.train_schedule.dto.TrainWagons;
import com.example.train_schedule.sql.Request;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author simik
 */
@Repository
public class DataDaoImpl implements DataDao {

    private static final Logger log = LoggerFactory.getLogger(DataDaoImpl.class);

    private final JdbcTemplate jtPSql;
    Random random = new Random();

    Date dateIn;

    public DataDaoImpl(JdbcTemplate jtPSql) {
        this.jtPSql = jtPSql;
    }

    @Override
    public List<ListIdTrain> getIdTrainSchedule(InputData inData) {

        Request sql = new Request();

        log.info(inData.getDeparturePoint() + "   " + inData.getDateDeparture() + "   " + inData.getDestinationPoint());

        List<ListIdTrain> listId = jtPSql.query(
                sql.getIDSHEDULE(),
                new Object[]{inData.getDeparturePoint(), inData.getDateDeparture(), inData.getDestinationPoint()},
                new int[]{Types.VARCHAR, Types.DATE, Types.VARCHAR},
                new BeanPropertyRowMapper<>(ListIdTrain.class));

        return listId;

    }

    @Override
    public TrainSchedule getTrainSchedule(ListIdTrain idTrain) {

        Request sql = new Request();

        TrainSchedule trainSched = jtPSql.query(
                sql.getSCHEDULETRAIN(),
                new Object[]{idTrain.getId(), idTrain.getPidstatbeg(), idTrain.getPidstatend()},
                new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                new BeanPropertyRowMapper<>(TrainSchedule.class)
        ).stream().findAny().orElse(null);
        return trainSched;
    }

    @Override
    public List<TrainWagons> getTrainWagon(ListIdTrain idTrain) {
        Request sql = new Request();

        List<TrainWagons> listTrainWagon = jtPSql.query(
                sql.getSCHEDULERWAGON(),
                new Object[]{idTrain.getPidstatbeg(), idTrain.getId()},
                new int[]{Types.INTEGER, Types.INTEGER},
                new BeanPropertyRowMapper<>(TrainWagons.class)
        );
        return listTrainWagon;
    }

    @Override
    public List<TrainStations> getTrainStation(ListIdTrain idTrain) {
        Request sql = new Request();

        List<TrainStations> listTrainStations = jtPSql.query(
                sql.getSCHEDULESTATION(),
                new Object[]{idTrain.getId()},
                new int[]{Types.INTEGER},
                new BeanPropertyRowMapper<>(TrainStations.class)
        );
        return listTrainStations;
    }

    @Override
    public int setTiskets(InputData inData, int pidTrain, int pidWagons, int pidPassenger) {

        Request sql = new Request();

        dateIn = new Date();

        int outPassenger = 0;

        if (pidPassenger == 0) {
            try {
                outPassenger = jtPSql.queryForObject(
                        sql.getINPASSENGERS(),
                        new Object[]{
                            inData.getFamilyName(),
                            inData.getFirstName(),
                            inData.getPatronymic(),
                            inData.getBirthDay(),
                            inData.getGender(),
                            inData.getDocType(),
                            inData.getDocSer(),
                            inData.getDocNum(),
                            inData.getCtznOksm(),
                            new java.sql.Timestamp(dateIn.getTime())

                        },
                        new int[]{
                            Types.VARCHAR,
                            Types.VARCHAR,
                            Types.VARCHAR,
                            Types.DATE,
                            Types.INTEGER,
                            Types.INTEGER,
                            Types.VARCHAR,
                            Types.VARCHAR,
                            Types.VARCHAR,
                            Types.TIMESTAMP
                        },
                        Integer.class
                );
            } catch (EmptyResultDataAccessException e) {
                outPassenger = 0;
            }

        } else {
            outPassenger = pidPassenger;
        }

        int outTicket = 0;
        try {
            outTicket = jtPSql.queryForObject(
                    sql.getINTICKED(),
                    new Object[]{"E",
                        Integer.toString(random.nextInt(1000000)),
                        inData.getSeat(),
                        outPassenger,
                        new java.sql.Timestamp(dateIn.getTime()),
                        pidTrain,
                        pidWagons,
                        inData.getSeat()
                    },
                    new int[]{Types.VARCHAR,
                        Types.VARCHAR,
                        Types.INTEGER,
                        Types.INTEGER,
                        Types.TIMESTAMP,
                        Types.INTEGER,
                        Types.INTEGER,
                        Types.INTEGER
                    },
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            outTicket = 0;
        }

        return outTicket;
    }

    @Override
    public Tickets getTiskets(int id) {

        Request sql = new Request();

        Tickets tickets = jtPSql.query(
                sql.getTICKET(),
                new Object[]{id},
                new int[]{Types.INTEGER},
                new BeanPropertyRowMapper<>(Tickets.class
                )
        ).stream().findAny().orElse(null);

        return tickets;

    }

    @Override
    public int getTicketsReturned(String numberTicked) {

        Request sql = new Request();
        log.info(numberTicked);

        int out;

        try {
            out = jtPSql.queryForObject(
                    sql.getIDTICKET(),
                    new Object[]{numberTicked},
                    new int[]{Types.VARCHAR},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            log.error("Get idTicket ", e);
            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(500, "Ошибка пойска билета!");
            listErrors.add(errors);
            throw new InvalidException("Error get ticket", listErrors);
        }

        return out;
    }

    @Override
    public void setTicketsReturned(int id) {
        Request sql = new Request();

        jtPSql.update(sql.getRETURNTICK(), new Object[]{id}, new int[]{Types.INTEGER});

    }

    @Override
    public int getAuthorization(String token) {

        Request sql = new Request();
        int out = 0;

        try {
            out = jtPSql.queryForObject(
                    sql.getAUTHOR(),
                    new Object[]{
                        token
                    },
                    new int[]{Types.VARCHAR},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            out = 0;
        }

        return out;
    }

    @Override
    public int searchPassengers(InputData inData) {

        Request sql = new Request();
        int out = 0;

        try {
            out = jtPSql.queryForObject(
                    sql.getIMDRDOC(),
                    new Object[]{
                        inData.getFirstName(),
                        inData.getBirthDay(),
                        inData.getDocType(),
                        inData.getDocNum()
                    },
                    new int[]{Types.VARCHAR, Types.DATE, Types.INTEGER, Types.VARCHAR},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            out = 0;
        }

        if (out != 0) {
            return out;

        }

        try {
            out = jtPSql.queryForObject(
                    sql.getDRDOC(),
                    new Object[]{
                        inData.getBirthDay(),
                        inData.getDocType(),
                        inData.getDocSer(),
                        inData.getDocNum()

                    },
                    new int[]{Types.DATE, Types.INTEGER, Types.VARCHAR, Types.VARCHAR},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            out = 0;
        }

        return out;
    }

    @Override
    public int validCountSeat(int pidTrain, int pidWagons, int countSeat) {

        Request sql = new Request();
        int out = 0;

        try {
            out = jtPSql.queryForObject(
                    sql.getGROUPPLASE(),
                    new Object[]{
                        pidTrain,
                        pidWagons,
                        pidWagons,
                        pidTrain
                    },
                    new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            out = 0;
        }

        if (out >= countSeat) {
            out = 0;
        }

        return out;

    }

    @Override
    public void deleteTickets(int id) {
        Request sql = new Request();

        jtPSql.update(sql.getDELETETICKET(), new Object[]{id}, new int[]{Types.INTEGER});

    }

    @Override
    public String getKey() {

        return "9eb3b5bf2fb847909184fa7013bafc5d";
    }

    @Override
    public int validSeat(int pidTrain, int pidWagons, int seat) {

        Request sql = new Request();

        int out = 0;

        try {
            out = jtPSql.queryForObject(
                    sql.getPLASEVALID(),
                    new Object[]{pidTrain, pidWagons, seat},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            out = 0;
        }

        return out;

    }

    @Override
    public int getPidTrain(String numberTrain) {

        Request sql = new Request();

        int out = 0;

        log.info(numberTrain);

        try {
            out = jtPSql.queryForObject(
                    sql.getPIDTRAIN(),
                    new Object[]{numberTrain},
                    new int[]{Types.VARCHAR},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            log.error("Get pidTrain ", e);
            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(500, "Ошибка поиска поезда!");
            listErrors.add(errors);
            throw new InvalidException("Error get train", listErrors);
        }

        return out;

    }

    @Override
    public int getPidWagons(String numberWagons, int pidTrain) {
        Request sql = new Request();

        log.info(numberWagons);
        log.info(Integer.toString(pidTrain));

        int out = 0;

        try {
            out = jtPSql.queryForObject(
                    sql.getPIDWAGON(),
                    new Object[]{numberWagons, pidTrain},
                    new int[]{Types.VARCHAR, Types.INTEGER},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            log.error("Get pidWagons ", e);
            List<Errors> listErrors = new ArrayList<>();
            Errors errors;
            errors = new Errors(500, "Ошибка поиска вагона!");
            listErrors.add(errors);
            throw new InvalidException("Error get wagon", listErrors);
        }

        return out;
    }

    @Override
    public int validNumberPlase(int pidTrain, int pidWagons, int seat) {

        Request sql = new Request();

        int out = 0;

        try {
            out = jtPSql.queryForObject(
                    sql.getPLASEEXISTS(),
                    new Object[]{pidTrain, pidWagons, seat},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            out = 0;
        }

        return out;

    }

    @Override
    public void setLogError(UUID id, String messageError) {

        Request sql = new Request();

        dateIn = new Date();

        jtPSql.update(sql.getLogError(), id, messageError, new java.sql.Timestamp(dateIn.getTime()));

    }

    @Override
    public void setLogger(String code, String coment, String action, String id_zapbd) {

        dateIn = new Date();
        Request sql = new Request();
        jtPSql.update(sql.getLOG(),
                new java.sql.Timestamp(dateIn.getTime()),
                "TestGD",
                "Test User",
                "User",
                code,
                coment,
                "Сервис по расписанию и продажи жд билетов",
                action,
                id_zapbd);

    }

}
