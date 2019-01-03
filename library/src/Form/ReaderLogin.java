package Form;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Data.Tool.Query;
import Data.Readers.Reader;

import Main.*;

/**
 * Created by h3m on 2018/12/26.
 */
public class ReaderLogin extends JFrame implements Runnable {
    private static final Reader Type = null; //人员类型
    private static Reader reader;             //用户名
    private JPasswordField password;
    private JTextField username;
    private JButton login;
    private JButton reset;

    public ReaderLogin() {
        super();
    }

    public void run() {
        final BorderLayout borderLayout = new BorderLayout();    //创建布局管理器
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);            //设置关闭按钮处理事件
        borderLayout.setVgap(10);                                //设置组件之间垂直距离
        getContentPane().setLayout(borderLayout);                //使用布局管理器
        setTitle("图书馆读者系统登录");                            //设置窗体标题
        Toolkit tool = Toolkit.getDefaultToolkit();                //获得默认的工具箱
        Dimension screenSize = tool.getScreenSize();                //获得屏幕的大小
        setSize(285, 154);                                        //设置窗体大小
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);            //设置窗体位置

        final JPanel mainPanel = new JPanel();                    //创建主面板
        mainPanel.setLayout(new BorderLayout());                    //设置边框布局
        mainPanel.setBorder(new EmptyBorder(10, 0, 5, 30));        //设置边框为0
        getContentPane().add(mainPanel);                        //在窗体中加入主面板

        final JPanel centerPanel = new JPanel();                //添加一个中心面板
        final GridLayout gridLayout = new GridLayout(2, 2);        //创建网格布局管理器
        gridLayout.setHgap(5);                                    //设置组件之间平行距离
        gridLayout.setVgap(20);                                    //设置组件之间垂直距离
        centerPanel.setLayout(gridLayout);                        //使用布局管理器
        mainPanel.add(centerPanel);                                //添加到主面板

        final JLabel userNamelabel = new JLabel();                //创建一个标签
        userNamelabel.setHorizontalAlignment(SwingConstants.CENTER);//设置对齐方式
        userNamelabel.setPreferredSize(new Dimension(0, 0));    //设置组件大小
        userNamelabel.setMinimumSize(new Dimension(0, 0));        //设置组件最小的大小
        centerPanel.add(userNamelabel);                            //添加到中心面板
        userNamelabel.setText("用 户 名：");                        //设置标签文本
        username = new JTextField(20);                            //创建文本框
        username.setPreferredSize(new Dimension(0, 0));            //设置组件大小
        centerPanel.add(username);                                //添加到中心面板

        final JLabel passwordLabel = new JLabel();                    //创建一个标签
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置对齐方式
        centerPanel.add(passwordLabel);                                //添加到中心面板
        passwordLabel.setText("密    码：");                            //设置标签文本
        password = new JPasswordField(20);                            //创建密码框
        password.setEchoChar('*');                                    //设置密码框的回显字符
        password.addKeyListener(new KeyAdapter() {                    //监听密码框
            public void keyPressed(final KeyEvent e) {                //监听键盘单击事件
                if (e.getKeyCode() == 10)                            //如果按了回车键
                    login.doClick();                                //进行登录
            }
        });
        centerPanel.add(password);                                    //添加到中心面板
        final JPanel southPanel = new JPanel();                        //新增一个底部面板
        mainPanel.add(southPanel, BorderLayout.SOUTH);                //添加到主面板中
        login = new JButton();                                        //创建按钮组件
        login.addActionListener(new ReaderLogin.ReaderLoginAction());                //添加监听器
        login.setText("登录");                                        //设置按钮文本
        southPanel.add(login);                                        //把按钮添加到底部面板
        reset = new JButton();                                        //创建按钮组件
        reset.addActionListener(new ReaderLogin.ResetAction());                //添加监听器
        reset.setText("重置");                                        //设置按钮文本
        southPanel.add(reset);                                        //把按钮添加到底部面板
        setVisible(true);                                            //设置创建可见
        setResizable(false);                                        //设置窗体不可改变大小

    }

    public static Reader getReader() {
        return reader;
    }

    public static void setReader(Reader reader) {
        ReaderLogin.reader = reader;
    }

    private class ReaderLoginAction implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            Query query = new Query();
            reader = query.ReaderLoginCheck(username.getText(), String.valueOf(password.getPassword()));
            if (reader.getReader_id() != null) {//判断用户名是否为null
                try {
                    ReaderMode ReaderRunnable = new ReaderMode();
                    Thread ReaderThread = new Thread(ReaderRunnable);
                    //ReaderMode frame = new ReaderMode();//创建一个主窗体
                    ReaderRunnable.setVisible(true);//设置其可见
                    ReaderLogin.this.setVisible(false);//设置登录窗体为不显示
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "请输入正确的用户名和密码！");//弹出提示框
                username.setText("");//设置用户名输入框为空
                password.setText("");//设置密码输入框为空
            }
      }
    }


    private class ResetAction implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            username.setText("");//设置用户名输入框为空
            password.setText("");//设置密码输入框为空
        }
    }
}

