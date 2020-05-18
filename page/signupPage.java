package page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

import jdbc.jdbcMethod;

/**
 * 
 * @author Window
 * @version 0.1.20200518.2215
 *
 */
public class signupPage {
	
	boolean isOpen = true;
	private String pwd = null;
	private String pwdd = null;	

	public signupPage() {
				
	}
	
	public void creatPage() {
		JFrame f = new JFrame("Sign Up");
		
		f.setSize(300,400);
		
		f.setLocation(810,290);
		
		f.setLayout(null);
		
		JPanel n = new JPanel(new BorderLayout());
		n.setBounds(10,80,265,250);
		//n.setBackground(Color.black);
		JPanel info = new JPanel(new GridLayout(3, 2));
		JPanel signDecision = new JPanel(new GridLayout(1, 2));
		
		//
		JLabel l0 = new JLabel("Mail");
		JLabel l2 = new JLabel("Password");
		JLabel l4 = new JLabel("Password");
		
		JTextField l1 = new JTextField();
		JPasswordField l3 = new JPasswordField();
		JPasswordField l5 = new JPasswordField();
		
		info.add(l0);
		info.add(l1);
		info.add(l2);
		info.add(l3);
		info.add(l4);
		info.add(l5);		
		
		JButton yes = new JButton("Yes");
		//yes.setBounds(150,400,150,250);
		
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPwd(l3.getPassword());
				setPwdd(l5.getPassword());
				if(l1.getText()!=null && l3.getPassword()!=null && l5.getPassword()!=null) {
					if(getPwd().equals(getPwdd())) {
						//验证邮箱是否存在
						//执行SQL添加操作
						var v = new jdbcMethod();
						v.insert(1, l1.getText(), getPwd());
					}else {
						JOptionPane.showMessageDialog(null, "两次输入密码不一致","Warning",JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "不能为空","Warning",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JButton no = new JButton("Cancel");
		//no.setBounds(150,400,150,50);
		
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				isOpen = false;
				f.setVisible(false);
			}
		});
		
		signDecision.add(no);
		signDecision.add(yes);		
		
		n.add(info,BorderLayout.NORTH);
		n.add(signDecision,BorderLayout.SOUTH);
		f.add(n);	
		
		f.setResizable(false);
		
		//关闭窗体的时候，退出程序
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		//让窗体变得可见
		f.setVisible(true);
	}
	
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(char[] pwd) {
		this.pwd = String.valueOf(pwd);
	}

	public String getPwdd() {
		return pwdd;
	}

	public void setPwdd(char[] pwdd) {
		this.pwdd = String.valueOf(pwdd);
	}
}
