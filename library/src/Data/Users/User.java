package Data.Users;


import org.json.JSONObject;

public class User {
    private String name;
    private String password;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public JSONObject UserToJson(){
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("password",password);
        return json;
    }
    
    //User构造函数
    public User(JSONObject json){
        this.name = json.getString("name");
        this.password = json.getString("password");
    }

    public  User(){
        this.name = null;
        this.password = null;
    }

    public User(User u){
        this.name = u.name;
        this.password = u.password;
    }

    public  User(String user_name, String password){
        this.name = user_name;
        this.password = password;
    }


    
}
