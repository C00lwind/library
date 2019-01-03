package Data.BorrowAndBack;

import Data.Book.BookInformation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Book_details extends JFrame {
    private ArrayList<BookInformation> list=null;  //读者链表
    private int Book_num;    //读者序号
    private JTextField  Book_name;      //书名
    private JTextField   Book_id;             //图书编号
    private JTextField   type_id;            //类型编号
    private JTextField   writer;      //作者
    private JTextField   publisher;        //出版社
    private JTextField   price;  //价格
    private JTextField   state;   //状态
    private JButton Cancle;
    public Book_details(int num, ArrayList<BookInformation> book_list){
        super();
        list=book_list;
        Book_num=num;
        final BorderLayout borderLayout =new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
        setTitle("图书详细信息");						        // 设置窗体标题
        setBounds(100, 100, 396, 260);	// 设置窗体位置和大小

        final JPanel mainPanel =new JPanel();        //创建中心面板
        mainPanel.setBorder(new EmptyBorder(5,10,5,10));//设置边框
        final GridLayout gridLayout =new GridLayout(0,4);//创建表格布局管理器
        gridLayout.setVgap(5);					//设置组件之间垂直距离
        gridLayout.setHgap(5);					//设置组件之间平行距离
        mainPanel.setLayout(gridLayout);		//设置布局
        getContentPane().add(mainPanel);		//将中心面板加入到窗体
        final JLabel book_idLable=new JLabel();//创建读者编号标签
        book_idLable.setText("图书编号： ");//设置标签文本
        mainPanel.add(book_idLable);          //添加到中心面板
        Book_id=new JTextField(list.get(num).getBook_id(),13);//创建书号文本框
        Book_id.setColumns(13);//设置文本边框
        mainPanel.add(Book_id);

        final JLabel book_nameLabel = new JLabel();//创建作者标签
        book_nameLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        book_nameLabel.setText("书名：");//设置标签文本
        mainPanel.add(book_nameLabel);//添加到中心面板

        Book_name= new JTextField(list.get(num).getBook_name());//创建读者姓名文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(Book_name);//添加到中心面板

        final JLabel type_idLable=new JLabel();//创建读者编号标签
        type_idLable.setText("图书类别： ");//设置标签文本
        mainPanel.add(type_idLable);          //添加到中心面板
        type_id=new JTextField(Integer.toString(list.get(num).getType_id()),13);//创建书号文本框
        type_id.setColumns(13);//设置文本边框
        mainPanel.add(type_id);

        final JLabel writerLabel = new JLabel();//创建作者标签
        writerLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        writerLabel.setText("作者：");//设置标签文本
        mainPanel.add(writerLabel);//添加到中心面板

        writer= new JTextField(list.get(num).getWriter());//创建读者姓名文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(writer);//添加到中心面板

        final JLabel publisherLable=new JLabel();//创建读者编号标签
        publisherLable.setText("出版社： ");//设置标签文本
        mainPanel.add(publisherLable);          //添加到中心面板
        publisher=new JTextField(list.get(num).getPublisher(),13);//创建书号文本框
        publisher.setColumns(13);//设置文本边框
        mainPanel.add(publisher);

        final JLabel priceLabel = new JLabel();//创建作者标签
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        priceLabel.setText("价格：");//设置标签文本
        mainPanel.add(priceLabel);//添加到中心面板

        price= new JTextField(String.valueOf(list.get(num).getPrice()));//创建读者姓名文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(price);//添加到中心面板



        final JLabel stateLabel = new JLabel();//创建作者标签
//        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        stateLabel.setText("状态：");//设置标签文本
        mainPanel.add(stateLabel);//添加到中心面板

        state= new JTextField(String.valueOf(list.get(num).getState()));//创建读者姓名文本框
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        mainPanel.add(state);//添加到中心面板
        setVisible(true);
        final JPanel bottomPanel = new JPanel();//创建底部面板
        bottomPanel.setBorder(new LineBorder(SystemColor.
                activeCaptionBorder, 1, false));//设置边框
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体中
        final FlowLayout flowLayout = new FlowLayout();//流布局管理器
        flowLayout.setVgap(2);	//设置组件之间垂直距离
        flowLayout.setHgap(30);//设置组件之间平行距离
        flowLayout.setAlignment(FlowLayout.RIGHT);//设置对齐方式
        bottomPanel.setLayout(flowLayout);//设置底部面板布局
        Cancle=new JButton();
        Cancle.addActionListener(new CancleActionListener());
        Cancle.setText("取消");
        bottomPanel.add(Cancle);

    }
    class CancleActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            setVisible(false);
        }
    }
}
