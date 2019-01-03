package Data.Tool;

import java.io.*;
import java.util.*;

import Data.Readers.Reader;
import Data.Book.BookInformation;
import org.json.JSONObject;
import Data.Users.User;

public class ReadData {
    //读取所有书的信息
    public ArrayList<BookInformation> readBookInformation(){
        ArrayList<BookInformation> list = new ArrayList<BookInformation>();
        try{
            File file = new File("./src/json/BookInformation.json");
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(fr);
            String s = null;
            while((s = reader.readLine())!= null){
                JSONObject json = new JSONObject(s);
                BookInformation book = new BookInformation(json);
                list.add(book);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public int  readBookInformation(String Book_id){
        ArrayList<BookInformation> list=new ArrayList<>();
        ReadData read=new ReadData();
        int num=-1;
        list=read.readBookInformation();
        if (list==null){
            return num;//找不到书
        }else{
            for(int i=0;i<list.size();i++){
                if (list.get(i).getBook_id().equals(Book_id)){
                    num=i;
                    return num;//找到书，返回序号
                }
            }
        }
        return num;//返回书在链表中的序号
    }


    //读取所有user信息
    public ArrayList<User> readUser () {
        ArrayList<User> list = new ArrayList<User>();
        try {
            File file = new File("./src/json/User.json");
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(fr);
            String s = null;
            while ((s = reader.readLine()) != null) {
                JSONObject json = new JSONObject(s);
                User user = new User(json);
                list.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getAUser(String user_name){
        ArrayList<User> list = new ArrayList<User>();
        User user = new User();
        try {
            File file = new File("./src/json/User.json");
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(fr);
            String s = null;
            while ((s = reader.readLine()) != null) {
                JSONObject json = new JSONObject(s);
                user = new User(json);
                if(user.getName().equals(user_name)) return user;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setName(null);
        return user;
    }
    //从文件中读入读者信息
    public ArrayList<Reader> readReader(){
        ArrayList<Reader> list=new ArrayList<Reader>();

        try {
            File file =new File("./src/json/Reader.json");
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader reader =new BufferedReader(fr);
            String s=null;
            while ((s=reader.readLine())!=null){
                JSONObject json =new JSONObject(s);
                Reader read  = new Reader(json);
                list.add(read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getnumReader(String reader_id){
        ArrayList<Reader> list=new ArrayList<>();
        list=readReader();
        int num=-1;
        if(list==null){//没有读者
            return num;
        }
        for (int i=0;i<list.size();i++){
            if(list.get(i).getReader_id().equals(reader_id)){//表中寻找读者序号
                num=i;
                return num;
            }
        }
        return num;//表中没有
    }

    public Reader getReader(String reader_id ){
        ArrayList<Reader> list=new ArrayList<Reader>();
        list=readReader();
        Reader r=new Reader();
        if (list==null){
            r.setReader_id(null);
            return r;
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getReader_id().equals(reader_id)){
                r=list.get(i);
                return r;
            }
        }
        r.setReader_id(null);
        return r;
    }
}
