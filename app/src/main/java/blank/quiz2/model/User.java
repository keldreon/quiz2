package blank.quiz2.model;

import com.google.gson.annotations.SerializedName;

public class User {

    //private int id;
    @SerializedName("id")
    private String iD;
    @SerializedName("passwd")
    private String pass;
    @SerializedName("jumlah")
    private String saldo;


    public User(){}


    public User(String iD, String pass){
        this.iD = iD;
        this.pass=pass;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
