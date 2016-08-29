package com.example.thammy.me;


/**
 * Created by THAMMY on 8/26/2016.
 */

public class feeditem {
    private int id;
    private String name, status;

    public feeditem() {
    }

    public feeditem(int id, String name, String status) {
        super();
        this.id = id;
        this.name = name;

        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
