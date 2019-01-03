package Data.Readers;

import Data.Book.BookInformation;
import org.json.JSONObject;

import java.util.ArrayList;

public class Reader {
    private String reader_id;//读者编号
    private String reader_name;//读者名字
    private String sex;   //性别
    private int  age ;//年龄
    private int   maxnum ;//最大借书数
    private int  keepmoney;//押金
    private String password;//密码
    private ArrayList<BookInformation> book_list=new ArrayList<>() ;
    private int  BookBorrow;//已借书数
    //  private int  money_overdue;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReader_id() {
        return reader_id;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(int maxnum) {
        this.maxnum = maxnum;
    }

    public int getKeepmoney() {
        return keepmoney;
    }

    public void setKeepmoney(int keepmoney) {
        this.keepmoney = keepmoney;
    }

    public ArrayList<BookInformation> getBook_list() {
        return book_list;
    }

    public void setBook_list(ArrayList<BookInformation> book_list) {
        this.book_list = book_list;
    }

     public int getBookBorrow() {
         return BookBorrow;
     }

     public void setBookBorrow(int bookBorrow) {
         BookBorrow = bookBorrow;
     }
    public Reader(){
        this.reader_id=null;
        this.reader_name=null;
        this.age=0;
        this.keepmoney=0;
        this.maxnum=0;
        this.sex=null;
        this.book_list=null;
        this.BookBorrow=0;
        this.password=null;
    }
    public Reader(Reader reader){
        this.reader_id=reader.reader_id;
        this.reader_name=reader.reader_name;
        this.age=reader.age;
        this.keepmoney=reader.keepmoney;
        this.maxnum=reader.maxnum;
        this.sex=reader.sex;
        this.book_list= reader.book_list;
        this.BookBorrow=reader.BookBorrow;
        this.password=reader.password;
    }
    public Reader(JSONObject json){
        this.reader_id=json.getString( "reader_id");
        this.reader_name=json.getString("reader_name");
        this.age=json.getInt("age");
        this.keepmoney=json.getInt("keepmoney");
        this.maxnum=json.getInt("maxnum");
        this.sex=json.getString("sex");
        this.BookBorrow=json.getInt("BookBorrow");
        this.password=json.getString("password");
        System.out.println(this.BookBorrow);
        for(int i=0;i<this.BookBorrow;i++){
            String temp = "book" + String.valueOf(i);
            JSONObject json1=new JSONObject();
            json1=json.getJSONObject(temp);
            BookInformation book=new BookInformation(json1);
            this.book_list.add(book);
        }
    }
    public JSONObject ReaderToJson(){
        JSONObject json =new JSONObject();
        json.put("reader_id",reader_id);
        json.put("reader_name",reader_name);
        json.put("age",age);
        json.put("keepmoney",keepmoney);
        json.put("maxnum",maxnum);
        json.put("sex",sex);
        json.put("BookBorrow",BookBorrow);
        json.put("password",password);
        //int m=10;
       // m=book_list.size();
        if(book_list==null) return json;
        for(int i=0;i<book_list.size();i++){
            String tem = "book" + String.valueOf(i);
            BookInformation qwe=new BookInformation();
            qwe=book_list.get(i);
            if (qwe!=null){
                JSONObject j=new JSONObject();
                j=qwe.BookInformationToJson();
                json.put(tem,j);
            }

        }
        return json;
    }
}
