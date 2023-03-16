package PrivateAccountSystem;

public class User  {
    private String userName;
    private String pwd;
    private String id;
    private String phone;
    public User(){

    }
    public User(String userName,String pwd,String id,String phone){
        this.userName = userName;
        this.pwd = pwd;
        this.id = id;
        this.phone = phone;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setPwd(String pwd){
        this.pwd = pwd;
    }
    public String getPwd(){
        return pwd;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setPhoneNum(String phone){
        this.phone = phone;
    }
    public String getPhoneNum(){
        return phone;
    }
}
