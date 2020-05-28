package page;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import jdbc.usageJDBC;

/**
 * @author Window
 * @version 0.1.20200527.2310
 * 20200527.2310 增加了显示面板时读取上一次示数的代码
 *
 */
public class Household {
	String mail = null;
	String pwd = null;
	
	double eleRead, gasRead;
	
	private double curEleTariff = 14.60;
	private double curGasTariff = 3.88;
	
	String display = "当前能源使用价格\n电费:	"+ curEleTariff + "	元/kWh\n燃气费:	" + curGasTariff + "	元/kWh";
	
	public Household(String mail, String pwd) {
		this.mail = mail;
		this.pwd = pwd;
	}
	
	public void homePage() {
		JFrame f = new JFrame("大圣能源");
		f.setSize(800, 600);
		f.setLocation(560, 240);
		f.setLayout(null);
		
		JPanel left = new JPanel(new FlowLayout());
		left.setBounds(0, 0, 150, 600);
		left.setBackground(new Color(75,0,130));;
		JPanel right = new JPanel();
		right.setBounds(150,0,650,600);
		right.setLayout(null);
		
		JButton profile = new JButton(mail);
		Dimension preSize = new Dimension(130,130);
		profile.setPreferredSize(preSize);
		JButton home = new JButton("首页");
		Dimension size = new Dimension(130,50);
		home.setPreferredSize(size);
		JButton budget = new JButton("预算");
		budget.setPreferredSize(size);
		JButton history = new JButton("历史使用");
		history.setPreferredSize(size);
		
		left.add(profile);
		left.add(home);
		left.add(budget);
		left.add(history);
		
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profilePage(f);
			}			
		});
		
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel(right);
				f.repaint();
			}			
		});
		
		budget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				budgetPanel(right);
				f.repaint();
			}			
		});
		
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historyPanel(right);
				f.repaint();
			}			
		});
		
		f.add(left);
		f.add(right);
		
		
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		homePanel(right);
		f.repaint();
	}
	
	/**
	 * 个人资料页面
	 * @param f
	 */
	private void profilePage(JFrame f) {		
		//根据外窗体实例化
		JDialog j = new JDialog(f);
		j.setModal(true);
		
		j.setTitle("个人资料");
		j.setSize(150, 300);
		j.setLocation(560,240);
		j.setLayout(null);
		
		JLabel jt = new JLabel("mail: "+mail);
		jt.setBounds(10, 10, 110, 30);
		
		JButton resetPwd = new JButton("修改密码");
		resetPwd.setBounds(10,140,110,50);
		
		JButton b = new JButton("关闭");
		b.setBounds(10,200,110,50);
		
		j.add(jt);
		j.add(resetPwd);
		j.add(b);
		
		j.setResizable(false);
		j.setVisible(true);
				
	} 
	
	private void homePanel(JPanel p) {
		p.removeAll();
		
		JPanel a = new JPanel();
		a.setBounds(10,5,380,250);
		a.setBackground(Color.yellow);
		energyOperationPanel(a);
		
		JPanel b = new JPanel(new BorderLayout());
		b.setBounds(400,5,220,250);
		b.setBackground(Color.white);
		
		JPanel c = new JPanel(new BorderLayout());
		c.setBounds(10,260,610,290);
		c.setBackground(Color.gray);
		
		StyledDocument styledDoc = new DefaultStyledDocument();//文档模型
		SimpleAttributeSet attrSet = new SimpleAttributeSet();
		JTextPane curTariff = new JTextPane(styledDoc);
		curTariff.setBackground(Color.lightGray);
		curTariff.setEditable(false);//设置不可编辑
		try {
			styledDoc.insertString(styledDoc.getLength(),display, attrSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		b.add(curTariff,BorderLayout.SOUTH);
		
		p.add(a);
		p.add(b);
		p.add(c);
		p.updateUI();
	}
	
	private void budgetPanel(JPanel p) {
		
	}
	
    private void historyPanel(JPanel p) {
		p.removeAll();
    	
    	JPanel a = new JPanel();
		a.setBounds(10,10,610,58);
		a.setBackground(Color.darkGray);
		JButton day = new JButton("按天查询");
		JButton month = new JButton("按月查询");
		JButton year = new JButton("按年查询");
		
		a.add(day);
		a.add(month);
		a.add(year);
		
		JPanel b = new JPanel();
		b.setBounds(10,80,500,470);
		b.setBackground(Color.white);
		
		JPanel c = new JPanel();
		c.setBounds(520,80,100,470);
		c.setBackground(Color.white);
		
		p.add(a);
		p.add(b);
		p.add(c);
		p.updateUI();
	}
    
    private void energyOperationPanel(JPanel j) {
    	j.removeAll();
    	
    	Dimension d = new Dimension(185,120);
    	
    	var v = new usageJDBC();
    	
    	double[] readings = v.retrive(mail);
    	
    	System.out.println(readings[0]+"\n"+readings[1]);
    	
    	setEleRead(readings[0]);
    	setGasRead(readings[1]);
    	
    	JPanel a = new JPanel(new BorderLayout());
    	a.setSize(d);
    	energyPanel(a, "电");
    	reading(a, getEleRead()); 	
    	
    	JPanel b = new JPanel(new BorderLayout());
    	b.setSize(185,120);
    	energyPanel(b, "天然气");
    	reading(b, getGasRead());
    	
    	j.add(a);
    	j.add(new JSplitPane(JSplitPane.VERTICAL_SPLIT));
    	j.add(b);
    	j.updateUI();
    }
    
    private void energyPanel(JPanel j, String s) {
    	JLabel title = new JLabel(s,0);
    	title.setBackground(Color.gray);
    	
    	JButton onOff = new JButton("开/关");
    	
    	onOff.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    		}
    	});
    	
    	j.add(title,BorderLayout.NORTH);
    	j.add(onOff,BorderLayout.SOUTH);
    	j.updateUI();  	
    }
    
    private void reading(JPanel j, double read) {
    	JPanel readPane = new JPanel();
    	
    	int r = (int)(read*100);
    	
    	int reads[] = new int[6];
    	   	
    	for(int l=0; l<6; l++) {
    		reads[l] = r/(int)Math.pow(10, (5-l));
    		r = r - reads[l]*(int)Math.pow(10, (5-l));
    	}
    	
    	JLabel jls[] = {new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel("."),new JLabel(""),new JLabel(""),new JLabel("kWh")};
    	
    	for(int i=0; i<8; i++) {
    		jls[i].setBackground(Color.white);
    		if(i>4 && i<7)
    			jls[i].setText(String.valueOf(reads[i-1]));
    		else if (i<4)
    			jls[i].setText(String.valueOf(reads[i]));
    		readPane.add(jls[i]);
    	}
    	
    	j.add(readPane,BorderLayout.CENTER);
    	j.updateUI();  	
    }
    
    public double getEleRead() {
		return eleRead;
	}

	public void setEleRead(double eleRead) {
		this.eleRead = eleRead;
	}

	public double getGasRead() {
		return gasRead;
	}

	public void setGasRead(double gasRead) {
		this.gasRead = gasRead;
	}
}
