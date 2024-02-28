/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.sql;

/**
 *
 * @author simik
 */
public class Request {

    private final String LogError = "insert into tickets.logerror (servicename, iderror, messageerror, dateofcreate) values ('TicketService',?,?,?)";

    private final String LOG = "insert into tickets.loger (date_z, company, user_ir, login_ir, code, comment, module, action_ir, id_sluch) values(?,?,?,?,?,?,?,?,?)";

    private final String DELETETICKET = "delete from tickets.tickets_rgd where id = ?";

    private final String PIDWAGON = "select id from tickets.wagons where number_wagon = ? and pid_train = ? and datedelete is null";

    private final String PIDTRAIN = "select id from tickets.train where lower(number_train) = lower(?) and datedelete is null";

    private final String PLASEEXISTS = "select 1 as status from tickets.wagons where pid_train = ? and id = ? and plase >= ?";

    private final String PLASEVALID = "select 1 as status from tickets.tickets_rgd a where pid_train = ? and pid_wagon = ? and seat = ?";

    private final String AUTHOR = "select 1 as status from tickets.access where token = ? and deletedate is null";

    private final String IMDRDOC = "select id from tickets.passengers where lower(firstname) = lower(?) and birthday = ? and type_doc = ? and num_doc = ? and datedelete is null";

    private final String DRDOC = "select id from tickets.passengers where birthday = ? and type_doc=? and ser_doc = ? and num_doc = ? and datedelete is null";

    private final String GROUPPLASE = "select \n"
            + "(select plase from tickets.wagons where pid_train = ? and id = ? and datedelete is null)\n"
            + "-\n"
            + "(select count(*) from tickets.tickets_rgd a where pid_wagon = ? and pid_train = ? and cancellation_date is null) \n"
            + "plase";

    private final String TICKET = "select number_Ticket as numberTicket, a.id as idTickets, date_departure  as registrationDate from tickets.tickets_rgd a\n"
            + "join\n"
            + "tickets.seat_station b on a.pid_train = b.pid_train and a.pid_wagon = b.pid_wagon and a.seat = b.seat\n"
            + "join\n"
            + "tickets.stations c on b.pid_station = c.id\n"
            + "where a.id = ? and cancellation_date is null;";

    private final String IDTICKET = "select id from tickets.tickets_rgd where number_ticket = ? and cancellation_date is null";

    private final String RETURNTICK = "update tickets.tickets_rgd set cancellation_date = now() where id = ?";

    private final String INPASSENGERS = "insert into tickets.passengers(\n"
            + "	lastname, firstname, patronymic, birthday, gender, type_doc, ser_doc, num_doc, ctzn_oksm, datecreate)\n"
            + "	values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id;";

    private final String INTICKED = "insert into tickets.tickets_rgd(pid_wagon, pid_train, pid_station, type_ticket, number_ticket, seat, type_seat, pid_passenger, price, datecreate) \n"
            + "(select pid_wagon, pid_train,pid_station, ?, ?, ?, type_seat, ?, price, ? from tickets.seat_station \n"
            + "where pid_train = ? and pid_wagon = ? and seat = ?) returning id;";

    private final String IDSHEDULE = "select distinct a.id, b.id as pidstatbeg, c.id as pidstatend  from tickets.train a\n"
            + "join\n"
            + "(select pid_train, id from tickets.stations \n"
            + "where lower(city) = lower(?) and datedelete is null \n"
            + "and coalesce(date_arrival::date,date_departure::date) = ?  \n"
            + ") b on a.id = b.pid_train\n"
            + "join\n"
            + "(select pid_train, id from tickets.stations \n"
            + "where lower(city) = lower(?) and datedelete is null \n"
            + ") c on a.id=c.pid_train where datedelete is null";

    private final String SCHEDULETRAIN = "select number_train as numberTrain, name_train as nameTrain,\n"
            + "b.name_station || ' ' || b.city as departurePoint, coalesce(b.date_arrival, b.date_departure) as dateArrival,\n"
            + "c.name_station || ' ' || c.city as destinationPoint, coalesce(c.date_arrival, c.date_departure) as dateDeparture\n"
            + "from tickets.train a\n"
            + "join\n"
            + "(select id, pid_train, name_station, city, date_arrival, date_departure from tickets.stations) b on a.id = b.pid_train\n"
            + "join\n"
            + "(select id, pid_train, name_station, city, date_arrival, date_departure from tickets.stations) c on a.id=c.pid_train\n"
            + "where a.id = ? and b.id = ?  and c.id = ? ";

    private final String SCHEDULESTATION = "select name_station as nameStation, date_arrival dateArrival, date_departure dateDeparture, parking\n"
            + "from tickets.stations where datedelete is null and pid_train = ?";

    private final String SCHEDULERWAGON = "select a.pid_train, sum(plase) - count(c.id) plase, type_wagon, min(price_ticket_beg) price_ticket_beg , max(price_ticket_end) price_ticket_end  \n"
            + "from tickets.wagons a\n"
            + "join \n"
            + "(select distinct pid_train, pid_wagon, pid_station \n"
            + " from tickets.seat_station where pid_station = ? and datedelete is null\n"
            + ") b on a.pid_train = b.pid_train and b.pid_wagon = a.id \n"
            + "left outer join\n"
            + "tickets.tickets_rgd c on a.pid_train = c.pid_train and c.pid_wagon = a.id and b.pid_station = c.pid_station \n"
            + "where a.pid_train = ? \n"
            + "group by a.pid_train, type_wagon";

    public String getSCHEDULERWAGON() {
        return SCHEDULERWAGON;
    }

    public String getSCHEDULETRAIN() {
        return SCHEDULETRAIN;
    }

    public String getSCHEDULESTATION() {
        return SCHEDULESTATION;
    }

    public String getIDSHEDULE() {
        return IDSHEDULE;
    }

    public String getINTICKED() {
        return INTICKED;
    }

    public String getIDTICKET() {
        return IDTICKET;
    }

    public String getINPASSENGERS() {
        return INPASSENGERS;
    }

    public String getRETURNTICK() {
        return RETURNTICK;
    }

    public String getTICKET() {
        return TICKET;
    }

    public String getGROUPPLASE() {
        return GROUPPLASE;
    }

    public String getDRDOC() {
        return DRDOC;
    }

    public String getPLASEEXISTS() {
        return PLASEEXISTS;
    }

    public String getIMDRDOC() {
        return IMDRDOC;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public String getDELETETICKET() {
        return DELETETICKET;
    }

    public String getPIDWAGON() {
        return PIDWAGON;
    }

    public String getPIDTRAIN() {
        return PIDTRAIN;
    }

    public String getPLASEVALID() {
        return PLASEVALID;
    }

    public String getLogError() {
        return LogError;
    }

    public String getLOG() {
        return LOG;
    }

}
