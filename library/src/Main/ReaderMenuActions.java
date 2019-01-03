package Main;


import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import Data.Book.BookAdd;
import Data.Readers.ReaderAdd;
import Data.Readers.ReadersModify;
import Data.Users.Password;
import Data.Users.UserAdd;

import Data.BorrowAndBack.*;
import Form.ReaderLogin;

/**
 * 菜单中各按钮的Action执行方法对应关系
 *
 */
public class ReaderMenuActions {
    private static Map<String,JInternalFrame> frames;  // 子窗体集

    public static BookSearchAction BOOK_SEARCH;        // 图书搜索窗口
    public static GiveBackAction GIVE_BACK;            // 图书归还窗口
    public static BorrowAction BORROW;                 // 图书借阅窗口
   // public static ExpiredAction expired;               // 图书借阅超期窗口
    //读者信息菜单
   // public static ReaderAddAction READER_ADD;          // 读者信息添加窗口
    //书信息菜单
 //   public static BookTypeModAction BOOKTYPE_MODIFY;   // 图书类型修改窗口
//    public static BookTypeAddAction BOOKTYPE_ADD;      // 图书类型添加窗口
    public static ReaderModAction READER_MODIFY;       // 读者信息修改窗口
 //   public static BookModAction BOOK_MODIFY;           // 图书信息修改窗口
//    public static BookAddAction BOOK_ADD;              // 图书信息添加窗口

    static {
        frames = new HashMap<String, JInternalFrame>();
//        MODIFY_PASSWORD = new PasswordModAction();
//        USER_ADD = new UserAddAction();
        BOOK_SEARCH = new BookSearchAction();
        GIVE_BACK = new GiveBackAction();
        BORROW = new BorrowAction();
//        expired = new ExpiredAction();
//        NEWBOOK_CHECK = new CheckBookAction();
//        NEWBOOK_ORDER = new BoodOrderAction();
//        BOOKTYPE_MODIFY = new BookTypeModAction();
//        BOOKTYPE_ADD = new BookTypeAddAction();
        READER_MODIFY = new ReaderModAction();
//        READER_ADD = new ReaderAddAction();
//        BOOK_MODIFY = new BookModAction();
//        BOOK_ADD = new BookAddAction();
//        EXIT = new ExitAction();
    }

//    private static class PasswordModAction extends AbstractAction {
//        PasswordModAction() {
//            putValue(Action.NAME,"密码修改");
//            putValue(Action.LONG_DESCRIPTION, "修改当前用户密码");
//            putValue(Action.SHORT_DESCRIPTION, "更换口令");
//        }
//        public void actionPerformed(ActionEvent e) {
//            if (!frames.containsKey("更改密码")||frames.get("更改密码").isClosed()) {
//                Password iframe=new Password();
//                frames.put("更改密码", iframe);
//                ReaderMode.addIFame(frames.get("更改密码"));
//            }
//        }
//    }

//    private static class ExitAction extends AbstractAction { // 退出系统动作
//        public ExitAction() {
//            super("退出系统", null);
//            putValue(Action.LONG_DESCRIPTION, "退出图书馆管理系统");
//            putValue(Action.SHORT_DESCRIPTION, "退出系统");
//        }
//        public void actionPerformed(final ActionEvent e) {
//            System.exit(0);
//        }
//    }
//


//    private static class UserAddAction extends AbstractAction {
//        UserAddAction() {
//            super("用户添加", null);
//            putValue(Action.LONG_DESCRIPTION, "添加新的用户");
//            putValue(Action.SHORT_DESCRIPTION, "用户添加");
//        }
//        public void actionPerformed(ActionEvent e) {
//            if (!frames.containsKey("用户信息添加")||frames.get("用户信息添加").isClosed()) {
//                UserAdd iframe = new UserAdd();
//                frames.put("用户信息添加", iframe);
//                ReaderMode.addIFame(frames.get("用户信息添加"));
//            }
//        }
//    }
    private static class BookSearchAction extends AbstractAction {
        BookSearchAction() {
            super("图书搜索", null);
            putValue(Action.LONG_DESCRIPTION, "搜索入库的图书信息");
            putValue(Action.SHORT_DESCRIPTION, "图书搜索");
        }
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书查询")||frames.get("图书查询").isClosed()) {
                BookSearch iframe=new BookSearch();
                frames.put("图书查询", iframe);
                ReaderMode.addIFame(frames.get("图书查询"));
            }
        }
    }

    private static class GiveBackAction extends AbstractAction {
        GiveBackAction() {
            super("图书归还", null);
            putValue(Action.LONG_DESCRIPTION, "归还借阅的图书");
            putValue(Action.SHORT_DESCRIPTION, "图书归还");
        }
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书归还管理")||frames.get("图书归还管理").isClosed()) {
                ReaderBookBack iframe=new ReaderBookBack(ReaderLogin.getReader().getReader_id());
                frames.put("图书归还管理", iframe);
                ReaderMode.addIFame(frames.get("图书归还管理"));
            }
        }
    }
    private static class BorrowAction extends AbstractAction {
        BorrowAction() {
            super("图书借阅", null);
            putValue(Action.LONG_DESCRIPTION, "从图书馆借阅图书");
            putValue(Action.SHORT_DESCRIPTION, "图书借阅");
        }
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书借阅管理")||frames.get("图书借阅管理").isClosed()) {
                ReaderBookBorrow iframe=new ReaderBookBorrow(ReaderLogin.getReader().getReader_id());
                frames.put("图书借阅管理", iframe);
                ReaderMode.addIFame(frames.get("图书借阅管理"));
            }
        }
    }

    private static class ReaderModAction extends AbstractAction {
        ReaderModAction() {
            super("读者信息修改", null);
            putValue(Action.LONG_DESCRIPTION, "修改和删除读者的基本信息");
            putValue(Action.SHORT_DESCRIPTION, "读者信息修改");
        }
        public void actionPerformed(ActionEvent e) {

            if (!frames.containsKey("读者信息修改")||frames.get("读者信息修改").isClosed()) {
                ReadersModify iframe=new ReadersModify(ReaderLogin.getReader().getReader_id());
               frames.put("读者信息修改", iframe);
                ReaderMode.addIFame(frames.get("读者信息修改"));
            }
        }
    }


    private ReaderMenuActions() {
        super();
    }

}
