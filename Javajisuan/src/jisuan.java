import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class jisuan {
    public static void main(String[] args) {
        new operation();
    }
}
class operation extends JFrame implements ActionListener {

    //文本框内的参数定义
    JTextField titlenumber;//输入题数
    JTextField title;//随机出题
    JTextField useranswer;//输入答案
    JTextField yesOfno;//判断对错
    JTextField result;//最终结果
   //线程定义
    private Timer timer;
  //标签定义
    JLabel L0;
    JLabel L1;
    JLabel L2;
    JLabel L3;
    JLabel L4;
    JLabel L5;

    //按钮定义
    JButton Btn_start, Btn_next,Btn_submit,Btn_color;
    public static JPanel p1;//面板容器
    public JPanel p2;
    public JPanel p3;

    String parten="0.00";//设置格式为“0.00”
    DecimalFormat decimal=new DecimalFormat(parten);//数字格式化，取2位小数
    String str;
    double seconds;//时间
    long startTime,endTime;
    char[]ch={'+','-','*','÷'};//存放加减乘除的符号数组
    float answer =0;//程序判断的答案
    int usertitlenumber;//用户输入的题目数

    int count=0;//已输出的题目数
    int right=0;//计算答对的题目数
    int wrong=0;//计算答错的题目数

    operation()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//建立窗口，不能放大
        this.setSize(400,250);//窗口大小
        L0=new JLabel("四则运算升级版");
        L1=new JLabel("输入题数：（设置1-5题）");
        L2=new JLabel("输出题目：");
        L3=new JLabel("输入答案：");
        L4=new JLabel("判断对错：");
        L5=new JLabel("最终成绩：");

        titlenumber =new JTextField(17);
        Btn_start =new JButton("开始");
        Btn_color=new JButton("背景颜色");

        Btn_color.addActionListener(this);
        title=new JTextField(25);
        useranswer =new JTextField(25);
        useranswer.addActionListener(this);
        yesOfno =new JTextField(25);
        result =new JTextField(25);



        Timer timertask = new Timer(1000, this);//间隔1秒
        this.timer = timertask;//添加时间
        Btn_submit =new JButton("提交答案");
        Btn_submit.addActionListener(this);
        Btn_next =new JButton("下一题");
        Btn_next.addActionListener(this);
        titlenumber.addActionListener(this);
        Btn_start.addActionListener(this);

        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();

        getContentPane().add(p1,"North");
        getContentPane().add(p2,"Center");
        getContentPane().add(p3,"South");

        p1.add(L0);
        p2.add(L1);p2.add(titlenumber);
        p2.add(L2);p2.add(title);
        p2.add(L3);p2.add(useranswer);
        p2.add(L4);p2.add(yesOfno);
        p2.add(L5);p2.add(result);
        p3.add(Btn_start);p3.add(Btn_submit);p3.add(Btn_next);p3.add(Btn_color);


        this.setResizable(false);//设置不可以改变大小
        setLocationRelativeTo(null);//设置窗口居中显示
        this.setVisible(true);//设置窗口可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认程序关闭并释放内存
    }

    static boolean colorchange=true;

    public void actionPerformed(ActionEvent e)//这是一个事件监听器，处理单击鼠标时触发的事件
    {


        if(e.getSource()==Btn_color)//点击背景按钮
        {
            Color ch = JColorChooser.showDialog(this, "颜色选择器", this.getBackground());
                    if (ch != null) {
                        this.getContentPane().setBackground(ch);
                        p1.setBackground(ch);
                        p1.repaint();
                        p2.setBackground(ch);
                        p2.repaint();
                        this.getContentPane().repaint();
                    }
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        }
        if(e.getSource()== Btn_start)//点击开始按钮
        {
            try
            {

                int n2=5;
                if(n2<(Integer.parseInt(titlenumber.getText())))
                {//当选择题目个数超过5个，弹出对话框
                    JOptionPane.showMessageDialog(null, "选择题数不能超过5个！");
                    titlenumber.setText("");//清空输入框内容
                    closeWindows();

                }
                timer.start();//开始计时

            }
            catch(NullPointerException o)
            {
                System.out.print("输入有误");
            }
            randomJiSuan();
            startTime= System.currentTimeMillis(); //获取开始时间

        }
        else{

            if(e.getSource()==Btn_submit)//点击提交按钮
            {
                str=decimal.format(answer);
                if(Float.parseFloat(str)==Float.parseFloat(useranswer.getText()))//判断输入的答案对错
                {
                    right++;
                    yesOfno.setText("输入的答案正确!");
                    yesOfno.setForeground(Color.black);//当答案是错的，字体显示黑色
                    p1.setBackground( Color.green);
                    p2.setBackground(Color.green);
                }
                else
                {
                    wrong++;
                    yesOfno.setText("正确答案是:"+Float.parseFloat(str));
                    yesOfno.setForeground(Color.red);//当答案是错的，字体显示红色
                    p1.setBackground( Color.gray);
                    p2.setBackground(Color.gray);
                }
            }
            else if(e.getSource()== Btn_next)//点击下一题按钮
            {
                str=decimal.format(answer);

                count++;//当未超过题目数时，继续出随机题
                if(count!=Integer.parseInt(titlenumber.getText()))
                {
                    title.setText(null);
                    useranswer.setText(null);
                    yesOfno.setText(null);
                    randomJiSuan();
                }

                else//当超过输入的题目数时，弹框结束
                {

                    timer.stop();
                    double endTime= System.currentTimeMillis() ;//获取结束时间
                    seconds=endTime-startTime;//计算时间
                    seconds=seconds/1000;
                    seconds=(int)seconds;
                    result.setText( "答对"+right+"道题目,答错"+wrong+"道题目!"+"答题时间为："+seconds+"秒");
                    JOptionPane.showMessageDialog(null, "结束！欢迎使用");

                }
            }

        }

    }

    private void closeWindows() {//程序关闭后结束进程
        this.dispose();
    }

    private void randomJiSuan()//定义随机产生运算
    {
        int x=(int)(Math.random()*2);

        switch(x)
        {
            case 0:
                jisuan1();//两个运算符
                break;
            case 1:
                jisuan2();//求阶乘
                break;
            case 2:
                jisuan3();//负数
                break;

        }
    }
    private void jisuan1()
    {
        int a=(int)(Math.random()*10+1);
        int b=(int)(Math.random()*10+1);
        int c=(int)(Math.random()*10+1);
        int d;
        int i1=(int)(Math.random()*3);//0、1加减//2、3乘除
        int i2=(int)(Math.random()*3);
        if (i1<=1){
            if (i2<=1){
                title.setText(a+" "+String.valueOf(ch[i1])+" "+b+" "+String.valueOf(ch[i2])+" "+c);
                jisuan(i1,a,b);
                d=(int)answer;
                jisuan(i2,d,c);
            }
            else {
                title.setText(a+" "+String.valueOf(ch[i1])+" "+b+" "+String.valueOf(ch[i2])+" "+c);
                jisuan(i2,b,c);
                d=(int)answer;
                jisuan(i1,a,d);
            }
        }
        else {
            title.setText(a+" "+String.valueOf(ch[i1])+" "+b+" "+String.valueOf(ch[i2])+" "+c);
            jisuan(i1,a,b);
            d=(int)answer;
            jisuan(i2,d,c);
        }
    }
    private void jisuan(int i,int a,int b)
    {
        switch(i)
        {
            case 0:
                answer=a+b;
                break;
            case 1:
                answer=a-b;
                break;
            case 2:
                answer=a*b;
                break;
            default:
                answer=(float)a/(float)b;
                break;
        }
    }
    private void jisuan2()
    {
        int a=(int)(Math.random()*10+1);
        title.setText(a+"!");
        answer =1;
        for(int i=1;i<=a;i++)
        {
            answer = answer *i;
        }
    }
    private void jisuan3()
    {
        int a=(int)(Math.random()*99+1)*-1;
        int b=(int)(Math.random()*10+1);
       int j=(int)(Math.random()*1);
       int i=(int)(Math.random()*3);//0、1加减//2、3乘除
        if(j==0){
            title.setText(a+String.valueOf(ch[i])+" "+b);
            jisuan(i,a,b);
        }
        else {
            title.setText(b+String.valueOf(ch[i])+" "+"("+a+") ");
            jisuan(i,b,a);
        }

    }
}