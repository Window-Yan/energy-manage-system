package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

/**
 * 除首页部分外，其余界面前端完成
 * @author Window
 * @version 0.1.20200524.1032
 *
 */
public class Manager {
	private String mail = null;
	private String pwd = null;
	
	private double curEleTariff = 14.60;
	private double curGasTariff = 3.88;
	
	String display = "当前能源使用价格\n电费:	"+ curEleTariff + "	元/kWh\n燃气费:	" + curGasTariff + "	元/kWh";
    
	public Manager(String mail, String pwd) {
		this.mail = mail;
		this.pwd = pwd;
	}
	
	public void homePage() {
		JFrame f= new JFrame("大圣能源管理系统");
		f.setSize(800, 600);
		f.setLocation(560, 240);
		f.setLayout(null);
		
		JPanel left = new JPanel();
		left.setBounds(0,0,150,600);
		left.setBackground(Color.blue);
		left.setLayout(new FlowLayout());
		
		JButton profil = new JButton();
		Dimension preSize = new Dimension(130,130);
		profil.setPreferredSize(preSize);
		ImageIcon icon = new ImageIcon(getClass().getResource("rootProfil.png"));
		icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		profil.setIcon(icon);		
		
		JButton home = new JButton("首页");
		Dimension prepredSize = new Dimension(130,50);
		home.setPreferredSize(prepredSize);
		
		JButton userOperation = new JButton("管理用户");
		userOperation.setPreferredSize(prepredSize);
		
		JButton setTariff = new JButton("设置费用");
		setTariff.setPreferredSize(prepredSize);
		
		left.add(profil);
		left.add(home);
		left.add(userOperation);
		left.add(setTariff);
		
		JPanel right = new JPanel();
		right.setBounds(150,0,650,600);
		right.setLayout(null);
		
		//profil 按键监听
		profil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton event = (JButton)e.getSource();
				if(event.equals(profil))
					profilPage(f);
			}
		});
		
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel(right);
				f.repaint();
			}			
		});
		
		userOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userOperationPanel(right);
				f.repaint();
			}			
		});
		
		setTariff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTariffPanel(right);
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
	private void profilPage(JFrame f) {		
		//根据外窗体实例化
		JDialog j = new JDialog(f);
		j.setModal(true);
		
		j.setTitle("个人资料");
		j.setSize(150, 300);
		j.setLocation(560,240);
		j.setLayout(null);
		
		JLabel jt = new JLabel("mail: "+mail);
		jt.setBounds(10, 10, 110, 30);
		
		JLabel jtp = new JLabel("pwd: "+pwd);
		jtp.setBounds(10, 50, 110, 30);
		
		JButton resetPwd = new JButton("修改密码");
		resetPwd.setBounds(10,140,110,50);
		
		JButton b = new JButton("关闭");
		b.setBounds(10,200,110,50);
		
		j.add(jt);
		j.add(jtp);
		j.add(resetPwd);
		j.add(b);
		
		j.setResizable(false);
		j.setVisible(true);				
	}
	
	private void homePanel(JPanel p) {
		p.removeAll();
		
		JPanel a = new JPanel();
		a.setBounds(10,5,380,250);
		a.setBackground(Color.white);
		
		JPanel b = new JPanel(new BorderLayout());
		b.setBounds(400,5,220,250);
		b.setBackground(Color.white);
		
		JPanel c = new JPanel();
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
	
	private void userOperationPanel(JPanel p) {
		p.removeAll();
		
		JPanel a = new JPanel(new BorderLayout());
		a.setBounds(10,10,610,58);
		a.setBackground(Color.gray);
		
		JTextField searchInput = new JTextField("请输入要查询的用户邮箱",20);
		JButton search = new JButton("搜索");
		
		a.add(searchInput,BorderLayout.CENTER);
		a.add(search,BorderLayout.EAST);
		
		//
		JPanel b = new JPanel(null);
		b.setBounds(10,78,610,400);
		b.setBackground(Color.gray);
		
		JTable infoTable = new JTable(5,5);
		infoTable.setBounds(5,5,470,390);
		
		JPanel c = new JPanel(new FlowLayout());
		c.setBounds(480,5,125,390);
		
		JButton query = new JButton("查看能源使用");
		JButton changeNormal = new JButton("修改用户状态");
		JButton changeManager = new JButton("修改用户权限");
		JButton delete = new JButton("删除用户");
		
		c.add(query);
		c.add(delete);
		c.add(changeNormal);
		c.add(changeManager);
		
		b.add(infoTable);
		b.add(c);
		
		p.add(a);
		p.add(b);
		p.updateUI();
	}
	
    private void setTariffPanel(JPanel p) {
    	
    	p.removeAll();
    	
    	JPanel a = new JPanel(new BorderLayout());
		a.setBounds(10,10,610,120);
		a.setBackground(Color.gray);
		
		JPanel b = new JPanel();
		JPanel c = new JPanel();
		
		JLabel electrcity = new JLabel("电费");
		JTextField eleInput = new JTextField("请输入新用电价格",20);
		JLabel eleTariff = new JLabel("元/kWh");
		JButton setEle = new JButton("修改");
		
		JLabel gas = new JLabel("天然气");
		JTextField gasInput = new JTextField("请输入新用气价格",20);
		JLabel gasTariff = new JLabel("元/kWh");
		JButton setGas = new JButton("修改");
		
		b.add(electrcity);
		b.add(eleInput);
		b.add(eleTariff);
		b.add(setEle);
		c.add(gas);
		c.add(gasInput);
		c.add(gasTariff);
		c.add(setGas);
		
		a.add(b,BorderLayout.NORTH);
		a.add(c,BorderLayout.CENTER);
		
		//
		JPanel d = new JPanel();
		d.setBounds(10,135,610,400);
		d.setBackground(Color.gray);
		
		StyledDocument styledDoc = new DefaultStyledDocument();//文档模型
		SimpleAttributeSet attrSet = new SimpleAttributeSet();
		JTextPane curTariff = new JTextPane(styledDoc);
		curTariff.setEditable(false);//设置不可编辑
		try {
			styledDoc.insertString(styledDoc.getLength(),display, attrSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		d.add(curTariff);
		
		p.add(a);
		p.add(d);
		p.updateUI();
	}
}
