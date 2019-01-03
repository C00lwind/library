package Data.Readers;

import Data.Tool.ReadData;
import Data.Tool.WritData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyFrame extends JFrame{
    private static Reader reader;
    private JTextField  reader_name;      //用户名
    private JTextField   sex;             //性别
    private JTextField   age;            //年龄
    private JTextField   keepmoney;      //押金
    private JTextField   maxnum;        //最大借书数
    private JPasswordField password;   //密码
    private JTextField   reader_id;  //用户名id
    private JButton save;
    private JButton cancle;
    ArrayList<Reader> reader_list1=new ArrayList<>();
    int num;

    public static Reader getReader() {
        return reader;
    }

    public static void setReader(Reader reader) {
        ModifyFrame.reader = reader;
    }

    public ArrayList<Reader> getReader_list1() {
        return reader_list1;
    }

    public void setReader_list1(ArrayList<Reader> reader_list1) {
        this.reader_list1 = reader_list1;
    }

    public ModifyFrame(int Read_num, ArrayList<Reader> reader_list){
        super();
        ReadData read=new ReadData();
        reader_list1=read.readReader();
        num=read.getnumReader(reader_list.get(Read_num).getReader_id());
        final BorderLayout borderLayout =new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
//        setIconifiable(true);                     //设置窗体可最小化
//        setClosable(true);                       //设置窗体可关闭
        setTitle("读者信息修改");						        // 设置窗体标题
        setBounds(100, 100, 396, 260);	// 设置窗体位置和大小

        final JPanel mainPanel =new JPanel();        //创建中心面板
        mainPanel.setBorder(new EmptyBorder(5,10,5,10));//设置边框
        final GridLayout gridLayout =new GridLayout(0,4);//创建表格布局管理器
        gridLayout.setVgap(5);					//设置组件之间垂直距离
        gridLayout.setHgap(5);					//设置组件之间平行距离
        mainPanel.setLayout(gridLayout);		//设置布局
        getContentPane().add(mainPanel);		//将中心面板加入到窗体

        final JLabel reader_nameLabel = new JLabel();//创建作者标签
        reader_nameLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_nameLabel.setText("读者姓名：");//设置标签文本
        mainPanel.add(reader_nameLabel);//添加到中心面板

        reader_name= new JTextField(reader_list.get(Read_num).getReader_name());//创建读者姓名文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(reader_name);//添加到中心面板

        final JLabel reader_sexLabel = new JLabel();//创建性别标签
        // reader_sexLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_sexLabel.setText("性别：");//设置标签文本
        mainPanel.add(reader_sexLabel);//添加到中心面板

        sex = new JTextField(reader_list.get(Read_num).getSex());//创建性别文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(sex);//添加到中心面板

        final JLabel ageLabel = new JLabel();//创建年龄标签
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        ageLabel.setText("年龄：");//设置标签文本
        mainPanel.add(ageLabel);//添加到中心面板

        age = new JTextField(Integer.toString(reader_list.get(Read_num).getAge()));//创建年龄文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(age);//添加到中心面板


        final JLabel borrowmaxLabel = new JLabel();//创建作者标签
        borrowmaxLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        borrowmaxLabel.setText("最大借书数：");//设置标签文本
        mainPanel.add(borrowmaxLabel);//添加到中心面板

        maxnum = new JTextField(Integer.toString(reader_list.get(Read_num).getMaxnum()));//创建最大借书数文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(maxnum);//添加到中心面板

        final JLabel passwordLabel = new JLabel();//创建作者标签
        //passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        passwordLabel.setText("密码：");//设置标签文本
        mainPanel.add(passwordLabel);//添加到中心面板

        password = new JPasswordField(reader_list.get(Read_num).getPassword());//创建最大借书数文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
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
        save.setText("提交");//设置按钮文本
        bottomPanel.add(save);//添加到底部面板

        cancle=new JButton();//创建取消按钮
        cancle.addActionListener(new CancleActionListener());//注册监听器
        cancle.setText("取消");//设置按钮文本
        bottomPanel.add(cancle);

        final JLabel imageLabel = new JLabel();//图片标签
//        ImageIcon bookAddIcon=Icon.add("bookAdd.jpg");//图片图标
//        imageLabel.setIcon(bookAddIcon);//设置标签显示图片
//        imageLabel.setPreferredSize(new Dimension(400, 80));//设置标签的大小
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
            Reader read_tem=new Reader();
//            read_tem=read.getReader(reader_id.getText());
//                if (read_tem.getReader_id()!=null){   //如果读者id已经修改，但是与别的读者相同
//                    JOptionPane.showMessageDialog(null,"该用户Id已存在！");
//                    reader_id.setText(reader_list1.get(num).getReader_id());
//                    reader_name.setText(reader_list1.get(num).getReader_name());
//                    sex.setText(reader_list1.get(num).getSex());
//                    age.setText(Integer.toString(  reader_list1.get(num).getAge()));
//                    maxnum.setText(Integer.toString(reader_list1.get(num).getMaxnum()));
//                    keepmoney.setText(Integer.toString(  reader_list1.get(num).getKeepmoney()));
//                    password.setText(reader_list1.get(num).getPassword());
//                }
//                else{   //读者id已修改，且与别的读者id不重复
                   // reader_list1.get(num).setReader_id(reader_id.getText());
                    reader_list1.get(num).setReader_name(reader_name.getText());
                    reader_list1.get(num).setMaxnum(Integer.parseInt(maxnum.getText()));
                   // reader_list1.get(num).setKeepmoney(Integer.parseInt(keepmoney.getText()));
                    reader_list1.get(num).setSex(sex.getText());
                    reader_list1.get(num).setAge(Integer.parseInt(age.getText()));
                    reader_list1.get(num).setPassword(password.getText());
                    WritData writer=new WritData();
                    writer.WriteReader(reader_list1);
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    dispose();
               // }

            }



    }

    class CancleActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           setVisible(false);
        }
    }

}
