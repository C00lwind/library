package Data.Readers;

import Data.Tool.ReadData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class Readerdefend extends JInternalFrame {
    private int currentPage = 1;//当前页数
    private int pagesize = 24;//每页数据量
    private int lastpage = 1;//最后一页
    JTextField Readname=null;
    JTextField Readid=null;
    JTable table = null;
    ArrayList<Reader> list = null, list1 = null,list2=null;
    JButton button = null;
    DefaultTableModel dtm = null;//表格
    JScrollPane jsp = null;//滚动条设置


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getLastpage() {
        return lastpage;
    }

    public void setLastpage(int lastpage) {
        this.lastpage = lastpage;
    }

    public Readerdefend() {
        super();

        ReadData read = new ReadData();
        list = new ArrayList<>();
        list = read.readReader();

        final BorderLayout borderLayout = new BorderLayout();//创建边框布局管理器
        getContentPane().setLayout(borderLayout);//设置布局
        setIconifiable(true);                     //设置窗体可最小化
        setClosable(true);
        setTitle("读者信息");                                // 设置窗体标题
        setBounds(100, 100, 396, 260);    // 设置窗体位置和大小

        final JPanel mainPanel = new JPanel();        //创建中心面板
        mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
        final GridLayout gridLayout = new GridLayout(0, 4);//创建表格布局管理器
        gridLayout.setVgap(5);                    //设置组件之间垂直距离
        gridLayout.setHgap(5);                    //设置组件之间平行距离
        mainPanel.setLayout(gridLayout);        //设置布局
        getContentPane().add(mainPanel);        //将中心面板加入到窗体

        String[] columnNames = {"序号", "用户名", "用户id"};
        dtm = new DefaultTableModel(columnNames, 0);

        table = new JTable(dtm);
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        getContentPane().add(jsp);

        setTitle("读者信息");
        setBounds(100, 100, 500, 500);

        showTable(currentPage);

        JPanel panel = new JPanel();
        JPanel bottomPanel=new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel,BorderLayout.SOUTH);
        JButton button4=new JButton("搜索");
        button4.addActionListener(new Searchactionlistener());
        panel.add(button4);
        final JLabel reader_nameLable=new JLabel();//创建读者编号标签
        reader_nameLable.setText("读者姓名： ");//设置标签文本
        panel.add(reader_nameLable);          //添加到中心面板

        Readname=new JTextField();//创建书号文本框
        Readname.setColumns(13);//设置文本边框
        panel.add(Readname);


        final JLabel reader_idLabel = new JLabel();//创建作者标签
        reader_idLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
        reader_idLabel.setText("读者编号：");//设置标签文本
        panel.add(reader_idLabel);//添加到中心面板

        Readid= new JTextField();//创建读者姓名文本框
        Readid.setColumns(13);
        //writer.setDocument(new Document(10));//设置作者文本框最大输入值为10
        panel.add(Readid);//添加到中心面板



        JButton button = new JButton("首页");
        button.addActionListener(new MyTable());
        button.setActionCommand("首页");
        bottomPanel.add(button);
        JButton button1 = new JButton("上一页");
        button1.addActionListener(new MyTable());
        bottomPanel.add(button1);
        JButton button2 = new JButton("下一页");
        button2.addActionListener(new MyTable());
        bottomPanel.add(button2);
        JButton button3 = new JButton("末页");
        button3.addActionListener(new MyTable());
        bottomPanel.add(button3);
        //getContentPane().add(panel);

        setVisible(true);
    }
     class Searchactionlistener implements ActionListener{
         @Override
         public void actionPerformed(ActionEvent e) {
             ReadData read=new ReadData();
             ArrayList<Reader> reader1=null,reader2=null;
             reader1=read.readReader();
             reader2=new ArrayList<>();
             list2=new ArrayList<>();
             if(Readname.getText().equals("")){
                 reader2=reader1;
             }
             else{
                 if (reader1.equals(null)){
                          reader2=reader1;
                 }else {
                         for (int i = 0; i < reader1.size(); i++) {
                             if (reader1.get(i).getReader_name().equals(Readname.getText())) {
                                 reader2.add(reader1.get(i));
                         }
                     }

                 }
             }
             reader1=null;
             reader1=reader2;
             reader2=new ArrayList<>();
             if(Readid.getText().equals("")){
                 reader2 =reader1;
             }else{
                  if (reader1.equals(null)){

                  }else{
                      for (int i=0;i< reader1.size();i++){
                          if (reader1.get(i).getReader_id().equals(Readid.getText())){
                              reader2.add(reader1.get(i));
                          }
                      }
                  }
             }
             list=reader2;
             showTable(list);

         }
     }


    class MyTable implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("首页")) {
                showTable(1);
            }
            if (e.getActionCommand().equals("上一页")) {
                if (getCurrentPage() <= 1) {
                    setCurrentPage(2);
                }
                showTable(getCurrentPage() - 1);
            }
            if (e.getActionCommand().equals("下一页")) {
                if (getCurrentPage() < getLastpage()) {
                    showTable(getCurrentPage() + 1);
                } else {
                    showTable(getLastpage());
                }
            }
            if (e.getActionCommand().equals("末页")) {
                showTable(getLastpage());
            }
        }
    }


    //有问题
    public void showTable(ArrayList<Reader> reader){
        dtm.setRowCount(0);//清空表格
        setCurrentPage(0);
        if (reader==null){

        }else{
            Reader r = new Reader();
            for (int i = 0; i < reader.size(); i++) {
                Vector rowV = new Vector();
                rowV.add(Integer.toString(i));
                r = (Reader) reader.get(i);
                rowV.add(r.getReader_name());
                rowV.add(r.getReader_id());
                dtm.addRow(rowV);

            }
        }

    }

    public void showTable(int currentPage) {
        dtm.setRowCount(0);
        setCurrentPage(currentPage);
        int size;
        if (list == null) {

        } else {
            lastpage=list.size()/pagesize+1;
            if (currentPage == lastpage) {
                size = list.size();
            } else {
                size = pagesize * currentPage;
            }
            list1 = new ArrayList<>();
            for (int i = 0; i < (size - (currentPage - 1) * pagesize); i++) {
                list1.add(list.get((currentPage - 1) * pagesize + i));
            }
            Reader r = new Reader();
            for (int i = 0; i < list1.size(); i++) {
                Vector rowV = new Vector();
                rowV.add(Integer.toString(i));
                r = (Reader) list1.get(i);
                rowV.add(r.getReader_name());
                rowV.add(r.getReader_id());
                dtm.addRow(rowV);

            }

            table.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
                    //得到选中的行列的索引值
                    int r = table.getSelectedRow();
                    Reader_detail objk= new Reader_detail(r,currentPage,pagesize,list);
                    ReadData readtem=new ReadData();
                    list=readtem.readReader();
                    setCurrentPage(1);
                    if (list==null){
                        setLastpage(1);
                        setCurrentPage(1);
                    }
                    else{
                        setLastpage(list.size());
                        setCurrentPage(1);
                    }
                    showTable(1);
                }
            });
            // table.getColumnModel().getColumn(2).setCellEditor(editor);
            /*table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //单选
            table.setSelectionBackground(Color.YELLOW);
            table.setSelectionForeground(Color.RED);
            table.setRowHeight(10);*/
        }
    }


}