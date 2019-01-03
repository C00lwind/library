package Data.BorrowAndBack;

import Data.Book.BookInformation;
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
public class ReaderBookBorrow extends JInternalFrame {
    JTextField reader_id;
    JTextField Book_name;
    JTextField Book_id;
    JButton Borrow;
    JButton cancle;
    ArrayList<BookInformation> booklist=new ArrayList<>();
    ArrayList<Reader> readerlist=new ArrayList<>();
    String  readerid=null;
    public ReaderBookBorrow(String reader_id){
        super();
        this.readerid=reader_id;
        final BorderLayout borderLayout =new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
        setIconifiable(true);                     //设置窗体可最小化
        setClosable(true);                       //设置窗体可关闭
        setTitle("图书借阅");						        // 设置窗体标题
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
        Book_idLabel.setText("图书id：");//设置标签文本
        mainPanel.add(Book_idLabel);//添加到中心面板

        Book_id= new JTextField();//创建读者姓名文本框
        Book_idLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
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
        Borrow=new JButton();//创建添加按钮
        Borrow.addActionListener(new ReaderBook_borrowActionListener());//注册监听器
        Borrow.setText("借阅");//设置按钮文本
        bottomPanel.add(Borrow);//添加到底部面板

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
    class ReaderBook_borrowActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int num;//读者的序号
            int booknum;//书在链表中的序号
            ReadData read=new ReadData();
            WritData writer=new WritData();
            booklist=read.readBookInformation();
            readerlist=read.readReader();

            num=read.getnumReader(readerid);
                    booknum = read.readBookInformation(Book_id.getText());
                    if (booknum == -1) {
                        JOptionPane.showMessageDialog(null, "没有此书！");
                        init();
                    } else {
                        if (readerlist.get(num).getBookBorrow() < readerlist.get(num).getMaxnum()) {//可以借书
                            if (booklist.get(booknum).getState().equals("0")){
                                booklist.get(booknum).setState("1");
                                Reader reader = readerlist.get(num);
                                ArrayList<BookInformation> bookInformationArrayList = reader.getBook_list();
                                bookInformationArrayList.add(booklist.get(booknum));
                                readerlist.get(num).setBookBorrow(readerlist.get(num).getBookBorrow() + 1);

                                writer.WriteReader(readerlist);
                                writer.WriteBookInformation(booklist);
                                JOptionPane.showMessageDialog(null, "借阅成功！");
                            }else{
                                JOptionPane.showMessageDialog(null,"该书已被借阅！");
                                init();
                                ReaderBookBorrow.this.dispose();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "借书已达上限！");
                            init();
                        }

                    }
                }
            }

    class CancleActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ReaderBookBorrow.this.dispose();
        }
    }
}

