package Data.Readers;

import Data.Book.BookInformation;
import Data.Tool.ReadData;
import Data.Tool.WritData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReadersModify extends JInternalFrame {
    private static Reader reader;
    private JTextField reader_name;      //用户名
    private JTextField sex;             //性别
    private JTextField age;            //年龄
    private JTextField keepmoney;      //押金
    private JTextField maxnum;        //最大借书数
    private JPasswordField password;   //密码
    private JTextField reader_id;  //用户名id
    private JTextField booklist;
    private JButton save;
    private JButton cancle;
    ArrayList<Reader> reader_list1 = new ArrayList<>();
    int num; //记录读者序号
    String  Readerid=null;

    public static Reader getReader() {
        return reader;
    }

//    public static void setReader(Reader reader) {
//        reader = reader;
//    }

    public ArrayList<Reader> getReader_list1() {
        return reader_list1;
    }

    public void setReader_list1(ArrayList<Reader> reader_list1) {
        this.reader_list1 = reader_list1;
    }

    public ReadersModify(String Reader_id) {
        super();
        ReadData read = new ReadData();
       // reader_list1=read.readReader();
        reader = read.getReader(Reader_id);//获取登录者信息
        num = read.getnumReader(Reader_id);//获得登录者的序号
        Readerid =Reader_id;
        final BorderLayout borderLayout = new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
        setIconifiable(true);                     //设置窗体可最小化
        setClosable(true);                       //设置窗体可关闭
        setTitle("读者信息修改");                                // 设置窗体标题
        setBounds(100, 100, 396, 260);    // 设置窗体位置和大小

        final JPanel mainPanel = new JPanel();        //创建中心面板
        mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
        final GridLayout gridLayout = new GridLayout(0, 4);//创建表格布局管理器
        gridLayout.setVgap(5);                    //设置组件之间垂直距离
        gridLayout.setHgap(5);                    //设置组件之间平行距离
        mainPanel.setLayout(gridLayout);        //设置布局
        getContentPane().add(mainPanel);        //将中心面板加入到窗体



        final JLabel reader_nameLabel = new JLabel();//创建作者标签
        reader_nameLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_nameLabel.setText("读者姓名：");//设置标签文本
        mainPanel.add(reader_nameLabel);//添加到中心面板

        reader_name = new JTextField(reader.getReader_name());//创建读者姓名文本
        mainPanel.add(reader_name);//添加到中心面板

        final JLabel reader_sexLabel = new JLabel();//创建性别标签
        reader_sexLabel.setText("性别：");//设置标签文本
        mainPanel.add(reader_sexLabel);//添加到中心面板

        sex = new JTextField(reader.getSex());//创建性别文本框
        mainPanel.add(sex);//添加到中心面板

        final JLabel ageLabel = new JLabel();//创建年龄标签
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        ageLabel.setText("年龄：");//设置标签文本
        mainPanel.add(ageLabel);//添加到中心面板

        age = new JTextField(Integer.toString(reader.getAge()));//创建年龄文本
        mainPanel.add(age);//添加到中心面板


        final JLabel passwordLabel = new JLabel();//
        passwordLabel.setText("密码：");//设置标签文本
        mainPanel.add(passwordLabel);//添加到中心面板

        password = new JPasswordField(reader.getPassword());//创建最大借书数文本框
        mainPanel.add(password);//添加到中心面板


        String s=null;
        ArrayList<BookInformation> booklist1=new ArrayList<>();
        if(reader.getBook_list().equals(null)) {
            s=" ";
        }else{
            booklist1=reader.getBook_list();
            for(int i = 0; i<booklist1.size(); i++){
                s=booklist1.get(i).getBook_name()+'-'+booklist1.get(i).getBook_id()+';';
            }
        }
        final JLabel booklistLabel = new JLabel();//创建作者标签
        booklistLabel.setText("已借书单：");//设置标签文本
        mainPanel.add(booklistLabel);//添加到中心面板
        booklist = new JTextField(s);//创建最大借书数文本框
        booklist.setEditable(false);
        mainPanel.add(booklist);//添加到中心面板

        final JPanel bottomPanel = new JPanel();//创建底部面板
        bottomPanel.setBorder(new LineBorder(SystemColor.
                activeCaptionBorder, 1, false));//设置边框
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体中
        final FlowLayout flowLayout = new FlowLayout();//流布局管理器
        flowLayout.setVgap(2);    //设置组件之间垂直距离
        flowLayout.setHgap(30);//设置组件之间平行距离
        flowLayout.setAlignment(FlowLayout.RIGHT);//设置对齐方式
        bottomPanel.setLayout(flowLayout);//设置底部面板布局

        save = new JButton();//创建添加按钮
        save.addActionListener(new AddReaderActionListener());//注册监听器
        save.setText("提交");//设置按钮文本
        bottomPanel.add(save);//添加到底部面板

        cancle = new JButton();//创建取消按钮
        cancle.addActionListener(new CancleActionListener());//注册监听器
        cancle.setText("取消");//设置按钮文本
        bottomPanel.add(cancle);

        final JLabel imageLabel = new JLabel();//图片标签
        imageLabel.setBorder(new LineBorder(SystemColor.
                activeCaptionBorder, 1, false));//设置边框
        getContentPane().add(imageLabel, BorderLayout.NORTH);//添加到窗体中
        imageLabel.setText("读者信息添加(LOGO图片)");//设置标签文本

        setVisible(true);

    }

    class AddReaderActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ReadData read = new ReadData();
            Reader read_tem = new Reader();
            reader_list1=read.readReader();

                read_tem = read.getReader(Readerid);
                   // reader_list1.get(num).setReader_id(reader_id.getText());
                    reader_list1.get(num).setReader_name(reader_name.getText());
                   // reader_list1.get(num).setMaxnum(Integer.parseInt(maxnum.getText()));
                   // reader_list1.get(num).setKeepmoney(Integer.parseInt(keepmoney.getText()));
                    reader_list1.get(num).setSex(sex.getText());
                    reader_list1.get(num).setAge(Integer.parseInt(age.getText()));
                    reader_list1.get(num).setPassword(password.getText());
                    WritData writer = new WritData();
                    writer.WriteReader(reader_list1);//数据重新写入
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    ReadersModify.this.dispose();

        }
    }
    class CancleActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ReadersModify.this.dispose();
        }
    }
}

