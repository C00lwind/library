package Data.BorrowAndBack;

import Data.Book.BookInformation;
import Data.Tool.Query;
import Data.Tool.ReadData;
import Data.Readers.Reader;
import Data.Tool.WritData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by h3m on 2019/1/2.
 */
public class ReaderBookBack extends JInternalFrame {
    JTextField reader_id;
    JTextField Book_name;
    JTextField Book_id;
    JButton Back;
    JButton cancle;
    ArrayList<BookInformation> booklist=new ArrayList<>();
    ArrayList<Reader> readerlist=new ArrayList<>();
    String readerid=null;
    public ReaderBookBack(String reader_id){
        super();
        readerid=reader_id;
        final BorderLayout borderLayout =new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
        setIconifiable(true);                     //设置窗体可最小化
        setClosable(true);                       //设置窗体可关闭
        setTitle("图书归还");						        // 设置窗体标题
        setBounds(100, 100, 396, 260);	// 设置窗体位置和大小
        setSize(400,100);
        final JPanel mainPanel =new JPanel();        //创建中心面板
        mainPanel.setBorder(new EmptyBorder(5,10,5,10));//设置边框
        final GridLayout gridLayout =new GridLayout(0,4);//创建表格布局管理器
        gridLayout.setVgap(5);					//设置组件之间垂直距离
        gridLayout.setHgap(5);					//设置组件之间平行距离
        mainPanel.setLayout(gridLayout);		//设置布局
        getContentPane().add(mainPanel);		//将中心面板加入到窗体

        final JLabel Book_nameLable=new JLabel();//创建读者编号标签
        Book_nameLable.setText("图书名字： ");//设置标签文本
        mainPanel.add(Book_nameLable);          //添加到中心面板

        Book_name=new JTextField(13);//创建书号文本框
        mainPanel.add(Book_name);

        final JLabel Book_idLabel = new JLabel();//创建作者标签
        Book_idLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        Book_idLabel.setText("图书id：");//设置标签文本
        mainPanel.add(Book_idLabel);//添加到中心面板

        Book_id= new JTextField();//创建读者姓名文本框
        mainPanel.add(Book_id);//添加到中心面板

        final JPanel bottomPanel = new JPanel();//创建底部面板
        bottomPanel.setBorder(new LineBorder(SystemColor.
                activeCaptionBorder, 1, false));//设置边框
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体中
        final FlowLayout flowLayout = new FlowLayout();//流布局管理器
        flowLayout.setVgap(2);	//设置组件之间垂直距离
        flowLayout.setHgap(30);//设置组件之间平行距离
        flowLayout.setAlignment(FlowLayout.RIGHT);//设置对齐方式
        bottomPanel.setLayout(flowLayout);//设置底部面板布局
        Back=new JButton();//创建添加按钮
        Back.addActionListener(new ReaderBook_backActionListener());//注册监听器
        Back.setText("归还");//设置按钮文本
        bottomPanel.add(Back);//添加到底部面板

        cancle=new JButton();//创建取消按钮
        cancle.addActionListener(new CancleActionListener());//注册监听器
        cancle.setText("取消");//设置按钮文本
        bottomPanel.add(cancle);

        setVisible(true);
    }
    public void init(){
        reader_id.setText("");
        Book_id.setText("");
        Book_name.setText("");
    }
    class ReaderBook_backActionListener implements ActionListener {//还书更改读者和书的状态信息
        @Override
        public  void actionPerformed(ActionEvent e){
            int num;//读者的序号
            int booknum;//书在链表中的序号
            ReadData read=new ReadData();
            WritData writer=new WritData();
            booklist=read.readBookInformation();
            readerlist=read.readReader();
            num=read.getnumReader(readerid);
                if(readerlist.get(num).getBook_list()==null){
                    JOptionPane.showMessageDialog(null,"该读者没有借书！");
                }else{
                    Query query = new Query();
                    int i = query.ReaderBookCheck(readerlist.get(num),Book_id.getText());
                    if(i != -1){//找到该书
                        booknum = read.readBookInformation(Book_id.getText());
                        booklist.get(booknum).setState("0");
                        readerlist.get(num).getBook_list().remove(i);
                        readerlist.get(num).setBookBorrow(readerlist.get(num).getBookBorrow()-1);
                        writer.WriteReader(readerlist);
                        writer.WriteBookInformation(booklist);
                        JOptionPane.showMessageDialog(null,"归还成功！");
                    } else{
                        JOptionPane.showMessageDialog(null,"读者与书号不匹配！");
                        init();
                    }

            }
        }
    }
    class CancleActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
