package Data.Users;

/**
 * Created by h3m on 2018/12/11.
 */

import Data.Tool.ReadData;
import Data.Tool.WritData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserAdd extends JInternalFrame {

    private JTextField textField_UserName;
    private JPasswordField textField_Password;
    private JButton button;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private static final User Type = null; //人员类型
    private static User user;             //用户名
    private JPasswordField password;
    private JTextField username;
    private JButton Add;
    private JButton reset;

    public UserAdd() {
        super();
        final BorderLayout borderLayout = new BorderLayout();    //创建布局管理器
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //设置关闭按钮处理事件
        borderLayout.setVgap(10);                                //设置组件之间垂直距离
        getContentPane().setLayout(borderLayout);                //使用布局管理器
        setTitle("用户添加");                            //设置窗体标题
        Toolkit tool = Toolkit.getDefaultToolkit();                //获得默认的工具箱
        Dimension screenSize = tool.getScreenSize();                //获得屏幕的大小
        setSize(285, 154);                                        //设置窗体大小
        setLocation((screenSize.width - getWidth()) / 4,
                (screenSize.height - getHeight()) / 4);            //设置窗体位置

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
                if (e.getKeyCode() == 10)                                //如果按了回车键
                    Add.doClick();                                        //添加
            }
        });
        centerPanel.add(password);                                    //添加到中心面板
        final JPanel southPanel = new JPanel();                        //新增一个底部面板
        mainPanel.add(southPanel, BorderLayout.SOUTH);                //添加到主面板中
        Add = new JButton();                                        //创建按钮组件
        Add.addActionListener(new UserAdd.UserAddAction());                //添加监听器
        Add.setText("添加");                                        //设置按钮文本
        southPanel.add(Add);                                        //把按钮添加到底部面板
        reset = new JButton();                                        //创建按钮组件
        reset.addActionListener(new UserAdd.BookResetAction());                //添加监听器
        reset.setText("取消");                                        //设置按钮文本
        southPanel.add(reset);                                        //把按钮添加到底部面板
        setVisible(true);                                            //设置创建可见
        setResizable(false);                                        //设置窗体不可改变大小
    }

    public static User getUser() {
        return user;
    }

    private class BookResetAction implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            UserAdd.this.dispose();
        }
    }

    private class UserAddAction implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            ReadData reader = new ReadData();
            User user = reader.getAUser(username.getText());
            if(user.getName() != null){
                JOptionPane.showMessageDialog(null, "用户名已存在！");
                username.setText("");//设置用户名输入框为空
                password.setText("");//设置密码输入框为空
            }
            else{
                user.setName(username.getText());
                user.setPassword(password.getText());
                WritData writer = new WritData();
                writer.WriteUser(user);
                JOptionPane.showMessageDialog(null, "添加成功！");
               // UserAdd.this.setVisible(false);
                UserAdd.this.dispose();
            }

        }
    }
}



