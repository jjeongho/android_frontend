package com.deu.cse.volt.Login.SignUp;

public class IdTemp {
    private static IdTemp idtemp = new IdTemp();
    private String Id;
    private IdTemp(){

    }

    public static IdTemp getInstance(){
        return idtemp;
    }

    public void setIdTemp(String Id){
        this.Id = Id;
    }

    public String getIdTemp(){
        return Id;
    }

}
