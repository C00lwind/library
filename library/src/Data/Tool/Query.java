package Data.Tool;
import Data.Readers.Reader;
import Data.Users.User;
import Data.Book.BookInformation;

import java.util.ArrayList;

public class Query {
    public User UserLoginCheck(String user_id, String password){
        ReadData reader = new ReadData();
        ArrayList<User> user_list = reader.readUser();
        int i = 0;
        while( i < user_list.size()){
            String n = user_list.get(i).getName();
            String p = user_list.get(i).getPassword();
            // == 判断的是是否指向内存，不是内容相同
            if(n.equals(user_id) && p.equals(password)){
                User user = new User(user_list.get(i));
                return user;
            }
            i++;
        }
        User user = new User();
        return user;

    }

    public Reader ReaderLoginCheck(String user_id, String password){
        ReadData reader = new ReadData();
        ArrayList<Reader> readerArrayList = reader.readReader();
        int i = 0;
        while( i < readerArrayList.size()){
            String n = readerArrayList.get(i).getReader_id();
            String p = readerArrayList.get(i).getPassword();
            // == 判断的是是否指向内存，不是内容相同
            if(n.equals(user_id) && p.equals(password)){
                Reader reader1 = new Reader(readerArrayList.get(i));
                return reader1;
            }
            i++;
        }
        Reader reader1 = new Reader();
        return reader1;

    }

    public int ReaderBookCheck(Reader reader, String bookId){
        ArrayList<BookInformation> booklist=new ArrayList<>();
        booklist=reader.getBook_list();

        if (booklist==null){
            return -1;
        }
        else{
            for(int i=0;i<booklist.size();i++){
                if (booklist.get(i).getBook_id().equals(bookId)){
                    return i;
                }
            }
        }

        return -1;
    }

}
