package com.st.frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.st.factory.ServiceFactory;


public class LoginFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel accountJLabel, passwordJLabel;
	private JTextField accountJTextField;
	private JPasswordField jPasswordField;
	private JButton loginJButton, cancelButton;
	private JPanel jPanel1, jPanel2, jPanel3;

	public LoginFrame() {
		init();
		setTitle("管理员登录界面");
		setVisible(true);
		setSize(400, 350);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void init() {
		setLayout(new GridLayout(4, 1));
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 70, 40);
		jPanel1 = new JPanel(flowLayout);
		jPanel2 = new JPanel(flowLayout);
		jPanel3 = new JPanel(flowLayout);
		accountJLabel = new JLabel("帐号");
		passwordJLabel = new JLabel("密码");
		accountJTextField = new JTextField(15);
		jPasswordField = new JPasswordField(15);
		loginJButton = new JButton("登录");
		loginJButton.addActionListener(this);
		cancelButton = new JButton("取消");
		jPanel1.add(accountJLabel);
		jPanel1.add(accountJTextField);
		jPanel2.add(passwordJLabel);
		jPanel2.add(jPasswordField);
		jPanel3.add(loginJButton);
		jPanel3.add(cancelButton);
		add(jPanel1);
		add(jPanel2);
		add(jPanel3);
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String account = accountJTextField.getText();
		String password = new String(jPasswordField.getPassword());
		boolean flag  = false;
		flag = ServiceFactory.getAdminServiceInstance().isLogin(account, password);
		if (flag == true) {
			JOptionPane.showMessageDialog(null, "登录成功");
			
		}else {
			JOptionPane.showMessageDialog(null, "登录失败");
		}
		
	}

}
