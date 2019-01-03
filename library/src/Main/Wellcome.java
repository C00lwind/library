package Main;

import Data.Readers.Reader;
import Form.UserLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Form.ReaderLogin;

/**
 * Created by h3m on 2018/12/26.
 */
public class Wellcome extends JFrame{
    private static final JDesktopPane
            DESKTOP_PANE = new JDesktopPane();//桌面窗体
    private JButton UserLogin;
    private JButton ReaderLogin;

    public void Wellcome() {
        final BorderLayout borderLayout = new BorderLayout();	//创建布局管理器
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//设置关闭按钮处理事件
        borderLayout.setVgap(10);								//设置组件之间垂直距离
        getContentPane().setLayout(borderLayout);				//使用布局管理器
        setTitle("图书馆管理系统登录");							//设置窗体标题
        Toolkit tool = Toolkit.getDefaultToolkit();				//获得默认的工具箱
        Dimension screenSize = tool.getScreenSize();			    //获得屏幕的大小
        setSize(285, 154);										//设置窗体大小
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);			//设置窗体位置

        UserLogin = new JButton();                                        //创建按钮组件
        UserLogin.addActionListener(new Wellcome.UserLoginAction());                //添加监听器
        UserLogin.setText("管理员登录");                                        //设置按钮文本
        ReaderLogin = new JButton();                                        //创建按钮组件
        ReaderLogin.addActionListener(new Wellcome.BookResetAction());                //添加监听器
        ReaderLogin.setText("读者登录");                                        //设置按钮文本
        setVisible(true);											//设置创建可见
        setResizable(false);										//设置窗体不可改变大小
        final JPanel mainPanel = new JPanel();					//创建主面板
        mainPanel.setLayout(new BorderLayout());				    //设置边框布局
        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));		//设置边框为0
        getContentPane().add(mainPanel);						//在窗体中加入主面板
        final JPanel centerPanel = new JPanel();
        final GridLayout gridLayout = new GridLayout(2, 2);		//创建网格布局管理器
        gridLayout.setHgap(5);									//设置组件之间平行距离
        gridLayout.setVgap(20);									//设置组件之间垂直距离
        centerPanel.setLayout(gridLayout);						//使用布局管理器
        mainPanel.add(centerPanel);								//添加到主面板

        centerPanel.add(UserLogin);
        centerPanel.add(ReaderLogin);

    }

    private class BookResetAction implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            ReaderLogin ReaderRunnable = new ReaderLogin();
            Thread ReaderThread = new Thread(ReaderRunnable);
            ReaderThread.start();

        }
    }
    private class UserLoginAction implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            UserLogin userRunnable = new UserLogin();
            Thread userThread = new Thread(userRunnable);
            userThread.start();
            // new ReaderLogin();
        }
    }

}
