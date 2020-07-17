package com.shreyash25.carparking;

public class User {
    private String BookD;
    private String Emai;
    private int Id;
    private int TimeE;
    private int TimeS;

    public User() {
    }

    public User(int timeS, int timeE, String emai, String bookD,int id) {
        BookD = bookD;
        Emai = emai;
        Id=id;
        TimeE = timeE;
        TimeS = timeS;
    }

    public String getBookD() {
        return BookD;
    }

    public void setBookD(String bookD) {
        BookD = bookD;
    }

    public String getEmai() {
        return Emai;
    }

    public void setEmai(String emai) {
        Emai = emai;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTimeE() {
        return TimeE;
    }

    public void setTimeE(int timeE) {
        TimeE = timeE;
    }


    public int getTimeS() {

        return TimeS;
    }

    public void setTimeS(int timeS) {
        TimeS = timeS;
    }




}
