package Data.Book;

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookInformation {
    String book_id;     //图书编号
    int type_id;        //类型编号
    String book_name;   //书名
    String writer;      //作者
    String publisher;   //出版社
    String price;        //价格

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    String state;       //借阅状态

    public BookInformation() {

    }


    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getBook_id() {
        return book_id;
    }

    public int getType_id() {
        return type_id;
    }


    public String getPublisher() {
        return publisher;
    }


    public String getPrice() {
        return price;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public JSONObject BookInformationToJson(){
        JSONObject obj = new JSONObject();
        obj.put("book_id",book_id);
        obj.put("type_id",type_id);
        obj.put("book_name",book_name);
        obj.put("writer",writer);
        obj.put("publisher",publisher);
        obj.put("price",price);
        obj.put("state",state);
        return obj;
    }

    public BookInformation(JSONObject json){
        this.writer = json.getString("writer");
        this.book_id = json.getString("book_id");
        this.type_id = json.getInt("type_id");
        this.publisher = json.getString("publisher");
        this.price = json.getString("price");
        this.book_name = json.getString("book_name");
        this.state = json.getString("state");

    }

}
