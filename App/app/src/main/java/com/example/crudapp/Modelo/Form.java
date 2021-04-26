package com.example.crudapp.Modelo;

public class Form {
    private String formId;
    private String name;
    private String lastName;
    private String age;

    public Form(String pFormId, String pName, String pLastName, String pAge){
        setFormId(pFormId);
        setName(pName);
        setLastName(pLastName);
        setAge(pAge);
    }

    public void setFormId(String pFormId){
        formId = pFormId;
    }

    public String getFormId(){
        return formId;
    }

    public void setName(String pName){
        name = pName;
    }

    public String getName(){
        return name;
    }

    public void setLastName(String pLastName){
        lastName = pLastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setAge(String pAge){
        age = pAge;
    }

    public String getAge(){
        return age;
    }

    public CharSequence getChSeqName(){
        return name;
    }

    public CharSequence getChSeqLastName(){
        return lastName;
    }

    public  CharSequence getChSeqAge(){
        return age;
    }

    public String toString(){
        String res = "";
        res += name + " " + lastName + ", " + age;
        return res;
    }
}
