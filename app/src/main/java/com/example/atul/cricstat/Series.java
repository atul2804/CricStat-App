package com.example.atul.cricstat;

/**
 * Created by Atul on 17-10-2017.
 */

public class Series {

    private String name,host,opponent,start_date,end_date;
    private int id,num_test,num_odi;
    private String sid;

    public Series(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setNum_odi(int num_odi) {
        this.num_odi = num_odi;
    }

    public void setNum_test(int num_test) {
        this.num_test = num_test;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public int getId() {
        return id;
    }

    public int getNum_odi() {
        return num_odi;
    }

    public int getNum_test() {
        return num_test;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getStart_date() {
        return start_date;
    }
}
