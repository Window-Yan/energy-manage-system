package page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

import jdbc.jdbcMethod;

/**
 * 
 * @author Window
 * @version 0.1.20200522.1635
 *	下一步：
 *	1.邮箱输入格式 ***@***
 *	2.真正意义上验证邮箱，给邮箱发验证码
 *
 */
public class resetPwd {
	private String email = null;

	public void creatPage() {
		JFrame f = new JFrame("Reset Password");
		
		f.setSize(300,350);
		
		f.setLocation(810,290);
		
		f.setLayout(null);
		
		JPanel p = new JPanel(new BorderLayout());
		p.setBounds(10,100,265,200);
		
		JPanel mailPanel = new JPanel(new GridLayout(1, 2));
		JPanel resetDecision = new JPanel(new GridLayout(1, 0));
		
		//
		JLabel mail = new JLabel("Mail");
		
		JTextField m = new JTextField();
		
		JButton verify = new JButton("Verify Mail");
		
		verify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//验证邮箱代码
				var v = new jdbcMethod();
				if(v.retrieve(m.getText()) && m.getText()!=null) {					
					setEmail(m.getText());
					resetFrame(f);
				}else
					JOptionPane.showMessageDialog(null, "邮箱不存在或者邮箱输入为空","Warning",JOptionPane.WARNING_MESSAGE);

			}
		});
		
		mailPanel.add(mail);
		mailPanel.add(m);
		resetDecision.add(verify);
		
		p.add(mailPanel, BorderLayout.NORTH);
		p.add(resetDecision, BorderLayout.SOUTH);
		
		
		f.add(p);
		
		f.setResizable(false);
		
		//关闭窗体的时候，退出程序
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//让窗体变得可见
		f.setVisible(true);
	}
	
	public void resetFrame(JFrame f) {
		JDialog jd = new JDialog(f);
		jd.setModal(true);
		
		jd.setTitle("Reset Password");
		jd.setSize(300,350);		
		jd.setLocation(810,290);		
		jd.setLayout(null);
		
		JPanel p = new JPanel(new BorderLayout());
		p.setBounds(10,100,265,200);
		
		JPanel pwd = new JPanel(new GridLayout(2, 2));
		JPanel resetDecision = new JPanel(new GridLayout(1, 0));
		
		//
		JLabel newPwd = new JLabel("New Password");
		JLabel newPwdd = new JLabel("Input Again");
		
		JPasswordField nPwd = new JPasswordField();
		JPasswordField nPwdd = new JPasswordField();
		
		JButton reset = new JButton("Reset");
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String npwd = String.valueOf(nPwd.getPassword());
				String npwdd = String.valueOf(nPwdd.getPassword());
				if(npwd.length()!=0 && npwdd.length()!=0) {					
					if(npwd.equals(npwdd)) {
						//调用sql update 方法						
						try{
							var v = new jdbcMethod();
							v.update(1,email,"pwd",npwd);
							f.setVisible(false);
							JOptionPane.showMessageDialog(null, "重置密码成功",null,JOptionPane.PLAIN_MESSAGE);
						}catch(Exception ex){
							
						}						
					}else
						JOptionPane.showMessageDialog(null, "两次输入密码不一致","Warning",JOptionPane.WARNING_MESSAGE);
					
				}else 
					JOptionPane.showMessageDialog(null, "密码不能为空","Warning",JOptionPane.WARNING_MESSAGE);				
			}
		});
		
		pwd.add(newPwd);
		pwd.add(nPwd);
		pwd.add(newPwdd);
		pwd.add(nPwdd);
		resetDecision.add(reset);
		
		p.add(pwd, BorderLayout.NORTH);
		p.add(resetDecision, BorderLayout.SOUTH);	
		
		jd.add(p);
		
		jd.setResizable(false);
				
		//让窗体变得可见
		jd.setVisible(true);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
