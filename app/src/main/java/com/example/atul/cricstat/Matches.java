package com.example.atul.cricstat;

/**
 * Created by Atul on 18-10-2017.
 */

public class Matches {
    private String date,venue,time,host,oppo,mtype,hostlogo,oppologo;
    private int id,seriesid;

    public Matches(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeriesid(int seriesid) {
        this.seriesid = seriesid;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setOppo(String oppo) {
        this.oppo = oppo;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setHostlogo(String hostlogo) {
        this.hostlogo = hostlogo;
    }

    public void setOppologo(String oppologo) {
        this.oppologo = oppologo;
    }

    public int getId() {
        return id;
    }

    public String getMtype() {
        return mtype;
    }

    public int getSeriesid() {
        return seriesid;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getHost() {
        return host;
    }

    public String getOppo() {
        return oppo;
    }

    public String getTime() {
        return time;
    }

    public String getHostlogo() {
        return hostlogo;
    }

    public String getOppologo() {
        return oppologo;
    }
}
