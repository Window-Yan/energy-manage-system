package page;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * @author Window
 * @version 0.1.20200510.1520
 *
 */
public class Household {
	String mail = null;
	String pwd = null;
	
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
		right.setBackground(Color.white);
		
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
			}			
		});
		
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historyPanel(right);
			}			
		});
		
		f.add(left);
		f.add(right);
		
		
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
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
		
	}
	
	private void budgetPanel(JPanel p) {
		
	}
	
    private void historyPanel(JPanel p) {
		
	}
}
