package Data.Tool;
import java.io.*;
import java.util.*;

import Data.Readers.Reader;
import Data.Book.BookInformation;
import Data.Users.User;
import Form.UserLogin;
import org.json.JSONArray;
import org.json.JSONObject;

//Writer 对外接口都是对象，不是json

public class WritData {

    //初始化数据
    public void WriteBookInformation(ArrayList<BookInformation> list){
        try {
            File file = new File("./src/json/BookInformation.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            int i = 0;
            while(i <list.size()) {
                bw.write(list.get(i).BookInformationToJson().toString());
                bw.newLine();
                i++;
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加数据
    public void WriteBookInformation(BookInformation book){
        JSONObject json = book.BookInformationToJson();
        try {
            File file = new File("./src/json/BookInformation.json");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json.toString());
            bw.newLine();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //添加user
    public void WriteUser(User user){
        JSONObject json =  user.UserToJson();
        try {
            JSONArray jsonArray;
            File file = new File("./src/json/User.json");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json.toString());
            bw.newLine();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //修改密码，策略是全部读出，修改后覆盖写回
    public void ChangePassword() {
        User user = UserLogin.getUser();
        ReadData reder = new ReadData();
        ArrayList<User> userArrayList = reder.readUser();
        for(int i = 0; i<userArrayList.size(); i++){
            if(userArrayList.get(i).getName().equals(user.getName())){
                userArrayList.get(i).setPassword(user.getPassword());
                break;
            }
        }
       try {
            File file = new File("./src/json/User.json");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
            BufferedWriter bw = new BufferedWriter(fw);
           for(int i = 0; i<userArrayList.size(); i++) {
               bw.write(userArrayList.get(i).UserToJson().toString());
               bw.newLine();
           }
               bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    //添加读者
    public void WriteReader(Reader reader)  {
        JSONObject json =reader.ReaderToJson();
        try {
            File file = new File("src/json/Reader.json");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json.toString());
            bw.newLine();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void WriteReader(ArrayList<Reader> reader){
        try {
            File file = new File("./src/json/Reader.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
           for(int i=0;i<reader.size();i++) {
               bw.write(reader.get(i).ReaderToJson().toString());
               bw.newLine();
           }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }  }

}
