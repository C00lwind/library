package Data.Readers;


import Data.Tool.ReadData;
import Data.Tool.WritData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class ReaderAdd extends JInternalFrame {
    private static Reader reader;
    private JTextField  reader_name;      //用户名
    private JTextField   sex;             //性别
    private JTextField   age;            //年龄
    private JTextField   keepmoney;      //押金
    private JTextField   maxnum;        //最大借书数
  //  private JPasswordField password;   //密码
    private JTextField   reader_id;  //用户名id
    private JPasswordField password;
    private JButton save;
    private JButton cancle;

    public ReaderAdd (){
        super();
        final BorderLayout borderLayout =new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
        setIconifiable(true);                     //设置窗体可最小化
        setClosable(true);                       //设置窗体可关闭
        setTitle("读者信息添加");						        // 设置窗体标题
        setBounds(100, 100, 396, 260);	// 设置窗体位置和大小

        final JPanel mainPanel =new JPanel();        //创建中心面板
        mainPanel.setBorder(new EmptyBorder(5,10,5,10));//设置边框
        final GridLayout gridLayout =new GridLayout(0,4);//创建表格布局管理器
        gridLayout.setVgap(5);					//设置组件之间垂直距离
        gridLayout.setHgap(5);					//设置组件之间平行距离
        mainPanel.setLayout(gridLayout);		//设置布局
        getContentPane().add(mainPanel);		//将中心面板加入到窗体

        final JLabel reader_idLable=new JLabel();//创建读者编号标签
        reader_idLable.setText("读者编号： ");//设置标签文本
        mainPanel.add(reader_idLable);          //添加到中心面板

        reader_id=new JTextField("请输入读者编号",13);//创建书号文本框
        reader_id.setColumns(13);//设置文本边框
        reader_id.addKeyListener(new reader_idListener());//注册监听器
        reader_id.addFocusListener(new reader_idFocusListener());//注册监听器
        mainPanel.add(reader_id);


        final JLabel reader_nameLabel = new JLabel();//创建作者标签
        reader_nameLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_nameLabel.setText("读者姓名：");//设置标签文本
        mainPanel.add(reader_nameLabel);//添加到中心面板

        reader_name= new JTextField();//创建读者姓名文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(reader_name);//添加到中心面板

        final JLabel reader_sexLabel = new JLabel();//创建性别标签
       // reader_sexLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_sexLabel.setText("性别：");//设置标签文本
        mainPanel.add(reader_sexLabel);//添加到中心面板

        sex = new JTextField();//创建性别文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(sex);//添加到中心面板

        final JLabel ageLabel = new JLabel();//创建年龄标签
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        ageLabel.setText("年龄：");//设置标签文本
        mainPanel.add(ageLabel);//添加到中心面板

        age = new JTextField();//创建年龄文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(age);//添加到中心面板

        final JLabel keepmoneyLabel = new JLabel();//创建押金标签
       // writerLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        keepmoneyLabel.setText("押金：");//设置标签文本
        mainPanel.add(keepmoneyLabel);//添加到中心面板

        keepmoney = new JTextField();//创建押金文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(keepmoney);//添加到中心面板

        final JLabel borrowmaxLabel = new JLabel();//创建作者标签
        borrowmaxLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        borrowmaxLabel.setText("最大借书数：");//设置标签文本
        mainPanel.add(borrowmaxLabel);//添加到中心面板

        maxnum = new JTextField();//创建最大借书数文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(maxnum);//添加到中心面板

        final JLabel passwordLabel = new JLabel();//创建作者标签
        //passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        passwordLabel.setText("密码：");//设置标签文本
        mainPanel.add(passwordLabel);//添加到中心面板

        password = new JPasswordField(20);//创建最大借书数文本框
        password.setEchoChar('*');
        mainPanel.add(password);//添加到中心面板

        final JPanel bottomPanel = new JPanel();//创建底部面板
        bottomPanel.setBorder(new LineBorder(SystemColor.
                activeCaptionBorder, 1, false));//设置边框
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体中
        final FlowLayout flowLayout = new FlowLayout();//流布局管理器
        flowLayout.setVgap(2);	//设置组件之间垂直距离
        flowLayout.setHgap(30);//设置组件之间平行距离
        flowLayout.setAlignment(FlowLayout.RIGHT);//设置对齐方式
        bottomPanel.setLayout(flowLayout);//设置底部面板布局
       // String s=reader_id.getText();
        save=new JButton();//创建添加按钮
        save.addActionListener(new AddReaderActionListener());//注册监听器
        save.setText("添加");//设置按钮文本
        bottomPanel.add(save);//添加到底部面板

        cancle=new JButton();//创建取消按钮
        cancle.addActionListener(new CancleActionListener());//注册监听器
        cancle.setText("取消");//设置按钮文本
        bottomPanel.add(cancle);

        final JLabel imageLabel = new JLabel();//图片标签
        imageLabel.setBorder(new LineBorder(SystemColor.
                activeCaptionBorder, 1, false));//设置边框
        getContentPane().add(imageLabel, BorderLayout.NORTH);//添加到窗体中
        imageLabel.setText("读者信息添加(LOGO图片)");//设置标签文本

        setVisible(true);
       }// 显示窗体可见
    class AddReaderActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ReadData read=new ReadData();
            Reader read_tem;
            read_tem=read.getReader(reader_id.getText());
            if (read_tem.getReader_id()!=null){
                JOptionPane.showMessageDialog(null,"该用户Id已存在！");
                reader_id.setText("");
                reader_name.setText("");
                sex.setText("");
                age.setText("");
                maxnum.setText("");
                keepmoney.setText("");
                password.setText("");
            }
            else{
                read_tem.setReader_id(reader_id.getText());
                read_tem.setReader_name(reader_name.getText());
                read_tem.setMaxnum(Integer.parseInt(maxnum.getText()));
                read_tem.setKeepmoney(Integer.parseInt(keepmoney.getText()));
                read_tem.setBookBorrow(0);
                read_tem.setSex(sex.getText());
                read_tem.setAge(Integer.parseInt(age.getText()));
                read_tem.setPassword(password.getText());
                read_tem.setBook_list(null);
                WritData writer=new WritData();
                writer.WriteReader(read_tem);
                JOptionPane.showMessageDialog(null, "添加成功！");
                ReaderAdd .this.dispose();
            }
        }

    }
    class reader_idFocusListener extends FocusAdapter{
        @Override
        public void focusLost(FocusEvent e) {
            super.focusLost(e);
        }
    }

    class reader_idListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
        }
    }
    class CancleActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ReaderAdd.this.setVisible(false);
        }
    }


}