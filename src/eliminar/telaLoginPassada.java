package eliminar;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.sql.*;
import Classes.ModuloConexao;
import Telas.TelaPrincipal;
import Telas.TelaPrincipal;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class telaLoginPassada {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JFrame frmMfSystemLogin;
	private JPasswordField passwordUser;
	private JTextField txtUser;
	private final Action action = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaLoginPassada window = new telaLoginPassada();
					
					window.frmMfSystemLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmMfSystemLogin = new JFrame();
		frmMfSystemLogin.setResizable(false);
		frmMfSystemLogin.setTitle("MF System - Login");
		frmMfSystemLogin.getContentPane().setBackground(Color.CYAN);
		frmMfSystemLogin.setBounds(100, 100, 450, 300);
		frmMfSystemLogin.getContentPane().setLayout(null);

		JLabel lblUser = new JLabel("Usu\u00E1rio:");
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUser.setBounds(42, 63, 46, 14);
		frmMfSystemLogin.getContentPane().add(lblUser);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSenha.setBounds(42, 108, 46, 14);
		frmMfSystemLogin.getContentPane().add(lblSenha);

		passwordUser = new JPasswordField();
		passwordUser.setBounds(114, 103, 162, 20);
		frmMfSystemLogin.getContentPane().add(passwordUser);

		txtUser = new JTextField();
		txtUser.setBounds(114, 58, 162, 20);
		frmMfSystemLogin.getContentPane().add(txtUser);
		txtUser.setColumns(10);

		JLabel lblStatus = new JLabel("\r\n");
		lblStatus.setIcon(new ImageIcon(telaLoginPassada.class.getResource("/Icones/ok.png")));
		lblStatus.setBounds(25, 174, 65, 65);
		frmMfSystemLogin.getContentPane().add(lblStatus);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogin.setBounds(187, 139, 89, 23);
		frmMfSystemLogin.getContentPane().add(btnLogin);

		conexao = ModuloConexao.conector();
		
		if(conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ok.png")));
		}else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/error.png")));
		}
		
	
	
	}
	
	public void logar() {
		String sql = "select * from tbusers where login =? and senha =?";
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1,txtUser.getText());
			pst.setString(2,passwordUser.getText());
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
                            TelaPrincipal principal = new TelaPrincipal();
                            String perfil  = rs.getString(6);
                            if(perfil.equals("admin")){
                                principal.UsuarioSubMenu.setEnabled(true);
                                principal.RelatoriosMenuPrincipal.setEnabled(true);
                                principal.lblUser.setText(rs.getString(2));
         
                                principal.setVisible(true);
                            }else{
                            principal.lblUser.setText(txtUser.getText());
                            principal.lblUser.setText(rs.getString(2));
                            principal.setVisible(true);
                            }
			}else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha invalidos..");
			}
			
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
		

	/**
	 * Create the application.
	 */
	public telaLoginPassada() {
		initialize();
               
                

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
		
		
		
	}
}
