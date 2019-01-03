package Main;
import javax.swing.*;
import java.awt.*;

/**
 * Created by h3m on 2018/12/26.
 */

public class ReaderMode extends JFrame implements Runnable{
        private static final JDesktopPane
                DESKTOP_PANE = new JDesktopPane();//桌面窗体
        public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
            DESKTOP_PANE.add(iframe);	//新增子窗体
        }

    @Override
    public void run() {

    }

    public ReaderMode() {
            super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);    //设置关闭按钮处理事件
        Toolkit tool = Toolkit.getDefaultToolkit();                   //获得默认的工具箱
        Dimension screenSize = tool.getScreenSize();                   //获得屏幕的大小
        setSize(800, 600);                              //设置窗体大小
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);            //设置窗体位置
        setTitle("图书馆读者系统");                                //设置窗体标题
        JMenuBar menuBar = createMenu(); 	                    //调用创建菜单栏的方法
        setJMenuBar(menuBar);				                    //设置菜单栏
        getContentPane().add(DESKTOP_PANE);                     //将桌面窗体添加到主窗体中
        }

        //public void run(){}

    /**
         * 创建菜单栏
         */
        private JMenuBar createMenu() { // 创建菜单栏的方法

            JMenuBar menuBar = new JMenuBar();   //创建工具栏
            JMenu baseMenu = new JMenu("基础数据管理");// 初始化基础数据维护菜单
            //JMenu readerManagerMItem = new JMenu("读者信息管理");//新增读者信息管理子菜单
            //readerManagerMItem.add(ReaderMenuActions.READER_ADD);//添加读者信息添加菜单项
            //readerManagerMItem.add(ReaderMenuActions.READER_MODIFY);//添加读者信息添加菜单项
            JMenu readerModify = new JMenu("读者信息修改");
            readerModify.add(ReaderMenuActions.READER_MODIFY);
            //JMenu bookTypeManageMItem = new JMenu("图书类别管理");//新增图书类别管理子菜单
            //bookTypeManageMItem.add(ReaderMenuActions.BOOKTYPE_ADD);//添加图书类型添加菜单项
            //bookTypeManageMItem.add(ReaderMenuActions.BOOKTYPE_MODIFY);//添加图书类型修改菜单项
            JMenu menu = new JMenu("图书信息管理");//新增图书信息管理子菜单
            //menu.add(ReaderMenuActions.BOOK_ADD);//添加图书信息添加菜单项
            //menu.add(ReaderMenuActions.BOOK_MODIFY);//添加图书信息修改菜单项
            //baseMenu.add(readerManagerMItem);//添加读者信息管理子菜单
            baseMenu.add(ReaderMenuActions.READER_MODIFY);
            //baseMenu.add(bookTypeManageMItem);//添加图书类别管理子菜单
            baseMenu.add(menu);				//添加图书信息管理子菜单


            JMenu borrowManageMenu = new JMenu("图书借阅"); // 初始化新书订购管理菜单
            borrowManageMenu.add(ReaderMenuActions.BORROW); //添加借阅菜单项
            borrowManageMenu.add(ReaderMenuActions.GIVE_BACK); //添加归还菜单项
            borrowManageMenu.add(ReaderMenuActions.BOOK_SEARCH); //添加搜索菜单项
            //borrowManageMenu.add(ReaderMenuActions.expired); //添加搜索菜单项


            menuBar.add(borrowManageMenu); // 添加借阅管理菜单到菜单栏
            menuBar.add(readerModify);
            //menuBar.add(readerManagerMItem);
            return menuBar;

        }
}
