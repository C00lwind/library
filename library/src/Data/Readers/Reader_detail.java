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

public class Reader_detail extends JFrame {
    private ArrayList<Reader> list=null;  //读者链表
    private int Reader_num;    //读者序号
    private static Reader reader;
    private JTextField  reader_name;      //用户名
    private JTextField   sex;             //性别
    private JTextField   age;            //年龄
    private JTextField   keepmoney;      //押金
    private JTextField   maxnum;        //最大借书数
    //  private JPasswordField password;   //密码
    private JTextField   reader_id;  //用户名id
    private JTextField   borrownum;   //已借书数
    private JTextField   booklist; //所借书单
    private JButton Modify;
    private JButton Delete;
    private JButton Cancle;
    private ArrayList<BookInformation> booklist1=null;

    public ArrayList<Reader> getList() {
        return list;
    }

    public void setList(ArrayList<Reader> list) {
        this.list = list;
    }

    public int getReader_num() {
        return Reader_num;
    }

    public void setReader_num(int reader_num) {
        Reader_num = reader_num;
    }

    public Reader_detail(int Row,int currentpage,int pagesize,ArrayList<Reader> reader_list){
        super();
        String stem=null;
        Reader_num=(currentpage-1)*pagesize+Row;
        list=new ArrayList<>();
        list=reader_list;

        final BorderLayout borderLayout =new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
//        setIconifiable(true);                     //设置窗体可最小化
//        setClosable(true);                       //设置窗体可关闭
        setTitle("读者详细信息");						        // 设置窗体标题
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


        reader_id=new JTextField(list.get(Reader_num).getReader_id(),13);//创建书号文本框
        reader_id.setColumns(13);//设置文本边框
        mainPanel.add(reader_id);
        reader_id.setEditable(false);

        final JLabel reader_nameLabel = new JLabel();//创建作者标签
        reader_nameLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_nameLabel.setText("读者姓名：");//设置标签文本
        mainPanel.add(reader_nameLabel);//添加到中心面板

        stem=list.get(Reader_num).getReader_name();
        reader_name= new JTextField(stem);//创建读者姓名文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(reader_name);//添加到中心面板
        reader_name.setEditable(false);
        final JLabel reader_sexLabel = new JLabel();//创建性别标签
        // reader_sexLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_sexLabel.setText("性别：");//设置标签文本
        mainPanel.add(reader_sexLabel);//添加到中心面板

        sex = new JTextField(list.get(Reader_num).getSex());//创建性别文本框
        mainPanel.add(sex);//添加到中心面板
        sex.setEditable(false);
        final JLabel ageLabel = new JLabel();//创建年龄标签
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        ageLabel.setText("年龄：");//设置标签文本
        mainPanel.add(ageLabel);//添加到中心面板

        age = new JTextField(Integer.toString(list.get(Reader_num).getAge()));//创建年龄文本框
        mainPanel.add(age);//添加到中心面板
        age.setEditable(false);
        final JLabel borrowmaxLabel = new JLabel();//创建作者标签
        borrowmaxLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        borrowmaxLabel.setText("最大借书数：");//设置标签文本
        mainPanel.add(borrowmaxLabel);//添加到中心面板

        maxnum = new JTextField(Integer.toString(list.get(Reader_num).getMaxnum()));//创建最大借书数文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(maxnum);//添加到中心面板
        maxnum.setEditable(false);
        final JLabel borrownumLabel = new JLabel();//创建作者标签
        borrownumLabel.setText("已借书数：");//设置标签文本
        mainPanel.add(borrownumLabel);//添加到中心面板
        borrownum = new JTextField(Integer.toString(list.get(Reader_num).getBookBorrow()));//创建最大借书数文本框
        mainPanel.add(borrownum);//添加到中心面板
        borrownum .setEditable(false);

        String s=null;
       booklist1=new ArrayList<>();
       if(list.get(Reader_num).getBook_list().equals(null)) {
            s=" ";
        }else{
           booklist1=list.get(Reader_num).getBook_list();
            for(int i = 0; i<booklist1.size(); i++){
                s=booklist1.get(i).getBook_name()+'-'+booklist1.get(i).getBook_id()+';';
            }
        }
        final JLabel booklistLabel = new JLabel();//创建作者标签
        booklistLabel.setText("已借书单：");//设置标签文本
        mainPanel.add(booklistLabel);//添加到中心面板
        booklist = new JTextField(s);//创建最大借书数文本框
        mainPanel.add(booklist);//添加到中心面板
        booklist.setEditable(false);
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
        Modify=new JButton();//创建添加按钮
        Modify.addActionListener(new ModifyReaderActionListener());//注册监听器
        Modify.setText("修改");//设置按钮文本
        bottomPanel.add(Modify);//添加到底部面板

        Delete=new JButton();//创建取消按钮
        Delete.addActionListener(new DeleteActionListener());//注册监听器
        Delete.setText("删除");//设置按钮文本
        bottomPanel.add(Delete);

        Cancle=new JButton();
        Cancle.addActionListener(new CancleActionListener());
        Cancle.setText("取消");
        bottomPanel.add(Cancle);

        final JLabel imageLabel = new JLabel();//图片标签
//        ImageIcon bookAddIcon=Icon.add("bookAdd.jpg");//图片图标
//        imageLabel.setIcon(bookAddIcon);//设置标签显示图片
//        imageLabel.setPreferredSize(new Dimension(400, 80));//设置标签的大小
        imageLabel.setBorder(new LineBorder(SystemColor.
                activeCaptionBorder, 1, false));//设置边框
        getContentPane().add(imageLabel, BorderLayout.NORTH);//添加到窗体中
        imageLabel.setText("读者信息(LOGO图片)");//设置标签文本

        setVisible(true);
    }
    class DeleteActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            WritData write=new WritData();
            ReadData read=new ReadData();
            ArrayList<Reader> readlist=new ArrayList<>();
            readlist=read.readReader();
            int num=0;
            num=read.getnumReader(list.get(Reader_num).getReader_id());
            readlist.remove(num);
            write.WriteReader(readlist);
            setVisible(false);
        }
    }
    class CancleActionListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
        }
    }
    class ModifyReaderActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //创建新窗体

            new ModifyFrame(Reader_num,list);
        }
    }
}
