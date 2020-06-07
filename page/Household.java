package page;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

import jdbc.usageJDBC;

/**
 * @author Window
 * @version 0.1.20200607.2101
 * 20200527.2310 增加了显示面板时读取上一次示数的代码
 * 20200607.2101 支持能源虚拟示数，并且关闭窗口时会记录最后示数;增加了实时检测是否超预算的代码;增加了显示时间的版块;增加了设置预算功能
 *
 */
public class Household {
	String mail = null;
	String pwd = null;
	
	double eleRead, gasRead, bill, budget= 0;

	private double curEleTariff = 14.60; //electricity tariff
	private double curGasTariff = 3.88; //gas tariff
	
	String display, curBill = null;
	
	public Household(String mail, String pwd) {
		this.mail = mail;
		this.pwd = pwd;
		
		var v = new usageJDBC(); //创建新对象   	
    	double[] readings = v.retrive(mail); //查询当前用户的最后一次能源示数
    	//将获取到的示数通过setter设置eleRead, gasRead的值
    	setEleRead(readings[0]);
    	setGasRead(readings[1]);
    	setBill(readings[2]);
    	setBudget(readings[3]);    	
    	//display = "当前能源使用价格\n电费:\t"+ curEleTariff + "\t元/kWh\n燃气费:\t" + curGasTariff + "\t元/kWh\n当前预算\n预算:\t" + getBudget() + "\t元";
    	
		homePage();
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
		
		f.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				var v = new usageJDBC();
				if(getBudget()==0)
					v.insert(mail, new SimpleDateFormat("yyyy-MM-dd HH:mm"), new double[] {getEleRead(), getGasRead(), getEleRead()*curEleTariff+getGasRead()*curGasTariff, 10000});
				else
					v.insert(mail, new SimpleDateFormat("yyyy-MM-dd HH:mm"), new double[] {getEleRead(), getGasRead(), getEleRead()*curEleTariff+getGasRead()*curGasTariff, getBudget()});
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
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
		
		JLabel time = new JLabel("",JLabel.CENTER);
		time.setText(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())));
		
		JPanel budgetWarn = new JPanel(new BorderLayout());
		JTextArea curUse = new JTextArea("");
		curUse.setBackground(Color.green);
		curUse.setText(getCurBill());
		budgetWarn.add(curUse,BorderLayout.CENTER);
		
		//新建线程用于检测是否超预算
		Thread t =new Thread(){
			public void run() {
				//检测homePanel是否acitve
				while(true) {
					bill = getEleRead()*curEleTariff+getGasRead()*curGasTariff;
					System.out.println("Being monitoring...");
					if(bill>budget) {						
						curUse.setBackground(Color.red);
						curUse.setText(getCurBill() + "\n\t本月已超预算");
						//JOptionPane.showMessageDialog(null, "本月能源使用已超预算","",JOptionPane.WARNING_MESSAGE);						
					}else
						budgetWarn.setBackground(Color.green);
					try {
						Thread.sleep(1000);
					}catch (InterruptedException e) {}
				}					
			}
		};
		t.start();
		
		StyledDocument styledDoc = new DefaultStyledDocument();//文档模型
		SimpleAttributeSet attrSet = new SimpleAttributeSet();
		JTextPane curTariff = new JTextPane(styledDoc);
		curTariff.setBackground(Color.lightGray);
		curTariff.setEditable(false);//设置不可编辑
		try {
			styledDoc.insertString(styledDoc.getLength(),getDisplay(), attrSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		b.add(time, BorderLayout.NORTH);
		b.add(budgetWarn, BorderLayout.CENTER);
		b.add(curTariff,BorderLayout.SOUTH);
		
		p.add(a);
		p.add(b);
		p.add(c);
		p.updateUI();
	}
	
	private void budgetPanel(JPanel p) {
		p.removeAll();
    	
    	JPanel a = new JPanel();
		a.setBounds(10,10,610,58);
		a.setBackground(Color.white);
		JLabel budget = new JLabel("下月预算");
		JTextField budgetValue = new JTextField(20);
		JButton setBudget = new JButton("修改预算");
		
		a.add(budget);
		a.add(budgetValue);
		a.add(setBudget);
		
		JPanel b = new JPanel(new BorderLayout());
		b.setBounds(10,80,610,470);
		b.setBackground(Color.white);
		
		JPanel c = new JPanel(new GridLayout(4,3));
		c.setBackground(Color.gray);
		JLabel evaluate = new JLabel("预算估计");
		JLabel eleMonUse = new JLabel("预计每月用电");
		JTextField ele = new JTextField(10);
		JLabel gasMonUse = new JLabel("预计每月用电");
		JTextField gas = new JTextField(10);
		JButton eva = new JButton("估计每月预算");
		JLabel budgetMon = new JLabel();
		
		c.add(new JLabel());
		c.add(evaluate);
		c.add(new JLabel());
		c.add(eleMonUse);
		c.add(ele);
		c.add(new JLabel("元/kWh", 0));
		c.add(gasMonUse);
		c.add(gas);
		c.add(new JLabel("元/kWh", JLabel.CENTER));
		c.add(eva);
		c.add(budgetMon);
		c.add(new JLabel("元", 0));
		
		b.add(c,BorderLayout.NORTH);
		
		setBudget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((budgetValue.getText()).length()!=0) {
					setBudget(Double.valueOf(budgetValue.getText()));
					JOptionPane.showMessageDialog(null, "预算设置成功！", null, JOptionPane.INFORMATION_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "预算不能为空", "Warnning", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		eva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((ele.getText()).length()!=0 && (gas.getText()).length()!=0) {
					double eValue= Double.valueOf(ele.getText());
					double gValue= Double.valueOf(gas.getText());
					double b = eValue * curEleTariff + gValue * curGasTariff;
					budgetMon.setText(String.valueOf(b));
					c.updateUI();
				}else
					JOptionPane.showMessageDialog(null, "请输入预计使用，可以为0！", "Warnning", JOptionPane.WARNING_MESSAGE);
			}
		});
				
		p.add(a);
		p.add(b);
		p.updateUI();
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
    
    /*
     * 此方法用于现实首页能源示数面板
     * The function of method is to create energy panel on the home page
     */
    private void energyOperationPanel(JPanel j) {
    	j.removeAll();
    	
    	Dimension d = new Dimension(185,120);   	
    	
    	JPanel a = new JPanel(new BorderLayout());
    	a.setSize(d);
    	elePanel(j, a, "电");
    	reading(a, getEleRead()); 	
    	
    	JPanel b = new JPanel(new BorderLayout());
    	b.setSize(185,120);
    	gasPanel(j, b, "天然气");
    	reading(b, getGasRead());
    	
    	j.add(a);
    	j.add(new JSplitPane(JSplitPane.VERTICAL_SPLIT));
    	j.add(b);
    	j.updateUI();
    }
    
    private void elePanel(JPanel jp, JPanel j, String s) {
    	JLabel title = new JLabel(s,0);
    	title.setBackground(Color.gray);
    	
    	JButton onOff = new JButton("开/关");
    	
    	onOff.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			energyUse(true);
    			energyOperationPanel(jp);
    		}
    	});
    	
    	j.add(title,BorderLayout.NORTH);
    	j.add(onOff,BorderLayout.SOUTH);
    	j.updateUI();  	
    }
    
    private void gasPanel(JPanel jp, JPanel j, String s) {
    	JLabel title = new JLabel(s,0);
    	title.setBackground(Color.gray);
    	
    	JButton onOff = new JButton("开/关");
    	
    	onOff.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			energyUse(false);
    			energyOperationPanel(jp);
    		}
    	});
    	
    	j.add(title,BorderLayout.NORTH);
    	j.add(onOff,BorderLayout.SOUTH);
    	j.updateUI();  	
    }
    /*
     * 此方法用于把0000.00格式的示数在能源面板中正确显示
     */
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
    /*
     * 此方法用于生成虚拟能源使用值
     */
    public void energyUse(boolean ifEle) {
    	DecimalFormat df = new DecimalFormat( "0.00" );
    	double d = Math.random();
    	String str = df.format(d);
    	d = Double.parseDouble(str);
    	
    	if (ifEle)
    		setEleRead(getEleRead() + d);
    	else
    		setGasRead(getGasRead() + d);

    }
    
	public String getCurBill() {
		curBill = "当前能源使用\n电:\t" + getEleRead() + "\tkWh\n燃气:\t" + getGasRead() + "\tkWh\n当前账单\n金额:\t" + new DecimalFormat("0.00").format(getEleRead()*curEleTariff+getGasRead()*curGasTariff) + "\t元";
		return curBill;
	}
    
    public String getDisplay() {
		return "当前能源使用价格\n电费:\t" + curEleTariff + "\t元/kWh\n燃气费:\t" + curGasTariff + "\t元/kWh\n当前预算\n预算:\t" + getBudget() + "\t元";
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
	
	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
}
