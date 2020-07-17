package com.shreyash25.carparking;

public class Collection {
    private String BookDate;
    private int timestart;
    private int timeeend;
    private int id;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public Collection(String bookDate, int timeSta, int timeEn, int id) {
        BookDate = bookDate;
        timeeend = timeSta;
        timestart = timeEn;

        this.id=id;
    }
    public Collection(){

    }

    public String getBookDate() {
        return BookDate;
    }

    public void setBookDate(String book11) {
        BookDate = book11;
    }

    public int gettimestart() {
        return timestart;
    }

    public void settimestart(int timeS) {
        timestart = timeS;
    }

    public int gettimeend() {
        return timeeend;
    }

    public void settimeend(int timeE) {
        timeeend = timeE;
    }
}
