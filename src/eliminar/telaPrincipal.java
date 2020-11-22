package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.DateFormat;

import javax.swing.Action;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class telaPrincipal extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					telaPrincipal frame = new telaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public telaPrincipal() {
		setTitle("Painel de controle");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1360, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cadastro");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cliente");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ordem de servi\u00E7o");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Usu\u00E1rio");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("Relat\u00F3rio");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Relat\u00F3rios de servi\u00E7o");
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_3 = new JMenu("Ajuda");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Sobre");
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_4 = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Sair");
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.YELLOW);
		desktopPane.setBounds(-26, -90, 1020, 780);
		contentPane.add(desktopPane);
		desktopPane.setLayout(null);
		
		JLabel Icon = new JLabel("New label");
		Icon.setIcon(new ImageIcon(telaPrincipal.class.getResource("/Icones/iconfinder_report-news-analytic_4900851.png")));
		Icon.setBounds(1051, 409, 250, 249);
		contentPane.add(Icon);
		
		JLabel lblUser = new JLabel("Usu\u00E1rio");
		lblUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblUser.setBounds(1097, 71, 164, 75);
		contentPane.add(lblUser);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Calibri", Font.BOLD, 24));
		lblData.setBounds(1097, 184, 182, 53);
		contentPane.add(lblData);
		
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
