package com.example.atul.cricstat;

/**
 * Created by Atul on 22-09-2017.
 */

public class Team {

    private String name,board,logo,tid;
    private String odirank,testrank,captain,vicecaptain;
    private int id;

    public Team(){

    }

    public Team(int id,String name,String board,String captain,String vicecaptain,String odirank,String testrank,String logo,String tid){
        this.id = id;
        this.name=name;
        this.board=board;
        this.captain=captain;
        this.vicecaptain=vicecaptain;this.odirank=odirank;this.testrank=testrank;this.logo=logo;this.tid = tid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setVicecaptain(String vicecaptain) {
        this.vicecaptain = vicecaptain;
    }

    public void setOdirank(String odirank) {
        this.odirank = odirank;
    }

    public void setTestrank(String testrank) {
        this.testrank = testrank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    //getter

    public int getId() {
        return id;
    }

    public String getBoard() {
        return board;
    }

    public String getCaptain() {
        return captain;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getOdirank() {
        return odirank;
    }

    public String getTestrank() {
        return testrank;
    }

    public String getVicecaptain() {
        return vicecaptain;
    }

    public String getTid() {
        return tid;
    }
}
