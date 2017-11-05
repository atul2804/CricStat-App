package com.example.atul.cricstat;

/**
 * Created by Atul on 23-09-2017.
 */

public class Players {
    private String name,birth,role,age,battype,bowltype;
    private int id;
    private String playerid;

    Players(){

    }

    Players(int id,String name,String birth,String role,String age,String battype,String bowltype,String playerid){
        this.id=id;
        this.age=age;
        this.name=name;
        this.battype=battype;
        this.bowltype=bowltype;
        this.birth=birth;
        this.playerid=playerid;
        this.role=role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBattype(String battype) {
        this.battype = battype;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setBowltype(String bowltype) {
        this.bowltype = bowltype;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlayerid() {
        return playerid;
    }

    public String getAge() {
        return age;
    }

    public String getBattype() {
        return battype;
    }

    public String getBirth() {
        return birth;
    }

    public String getBowltype() {
        return bowltype;
    }

    public String getRole() {
        return role;
    }
}
