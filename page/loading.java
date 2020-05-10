package page;

import jdbc.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;

/**
 * 
 * @author Window
 * @version 0.1.20200510.1233
 *
 */
public class loadingPage {
	
	public loadingPage(){
		//loading();
	}
	
	public void loading() {
		//loading page
		JFrame f = new JFrame("大圣能源");
				
		//主窗体设置大小
		f.setSize(300, 450);
		
		//主窗体设置位置
		f.setLocation(810,290);
				
		//主窗体中的组件位置设置位绝对定位
		f.setLayout(null);
		
		JPanel p = new JPanel();
		p.setBounds(30,190,240,80);
		p.setLayout(new GridLayout(2, 2));
		
		//
		JLabel userMail = new JLabel("Mail");
		//输入框
		JTextField tfMail = new JTextField(30);
		tfMail.setText("Please input your mail!");
		tfMail.setFont(new Font("宋体",Font.PLAIN,10));
		
		//
		JLabel userPwd = new JLabel("Password");
		//输入框
		JPasswordField psPwd = new JPasswordField(30);
		psPwd.setText(null);
		
		p.add(new JPanel().add(userMail));
		p.add(new JPanel(new FlowLayout(1)).add(tfMail));
		p.add(new JPanel(new FlowLayout(0)).add(userPwd));
		p.add(new JPanel(new FlowLayout(1)).add(psPwd));
		
		//login button
		JButton login = new JButton("Login");
		login.setBounds(110,290,80,20);
		
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var u = new jdbcMethod();
				if(tfMail.getText()!=null && psPwd.getPassword()!=null) {					
					u.getManagerPwd(tfMail.getText());
					String inputPwd = String.valueOf(psPwd.getPassword());
					//判断账户是否可以正常使用
					if(u.getIfNormal() && u.getIfManager() ) {
						//判断密码账号密码是否一致
						String pwd = u.getManagerPwd();
						System.out.println(inputPwd+"\t"+pwd);
						if(pwd.equals(inputPwd)) {
							var m = new Manager(tfMail.getText(), inputPwd);
							f.setVisible(false);
							m.homePage();
						}else {
							System.out.println("账号和密码不一致");	
						}						
					}else if(u.getIfNormal() && !u.getIfManager()) {
						//进入用户主页面
						System.out.println("进入用户主页面");
					}else if(!u.getIfNormal()){
						System.out.println("用户账号异常不可使用");	
					}else {
						System.out.println("用户不存在");	
					}																				
				}else {
					//弹出弹框，邮箱名和密码不能为空
					System.out.println("账号密码不能为空");
				}
			}
		});
		
		//reset password button
		JButton resetPwd = new JButton("Forget Password");
		
		resetPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var r = new resetPwd();
				r.creatPage();
			}
		});
		
		//sign up button
		JButton signUp = new JButton("Sign Up");
		
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var s = new signupPage();
				//f.setVisible(false);
				s.creatPage();
				
			}
		});
		
		//Panel include reset password and sign up
		JPanel pwdSign = new JPanel();
		pwdSign.setBounds(30,350,240,40);
		pwdSign.add(resetPwd);
		pwdSign.add(signUp);
		
		f.add(p);
		f.add(login);
		f.add(pwdSign);
		
		f.setResizable(false);
				
		//关闭窗体的时候，退出程序
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//让窗体变得可见
		f.setVisible(true);
	}
	
}

