package com.example.thammy.me;

/**
 * Created by THAMMY on 8/26/2016.
 */
public class Country {
   private String Name;
   private String Code;
   private int Id;

    public  Country(){}

    public  Country(int id , String name,String code){
        this.setId(id);
        this.setName(name);
        this.setCode(code);
    }

    public  Country( String name,String code){
        this.setName(name);
        this.setCode(code);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
