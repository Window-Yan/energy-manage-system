package page;

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

/**
 * 
 * @author Window
 * @version 0.1.20200510.1518
 *
 */
public class Manager {
	private String mail = null;
	private String pwd = null;
    
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
			}			
		});
		
		setTariff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTariffPanel(right);
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
		
	}
	
	private void userOperationPanel(JPanel p) {
		
	}
	
    private void setTariffPanel(JPanel p) {
		
	}
}
