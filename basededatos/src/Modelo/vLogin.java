package Modelo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	private JButton btnCancelar;
	private JButton btnentrada;
	private JPasswordField txtpassword;
	private JLabel btnlogo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vLogin frame = new vLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(33, 21, 63, 22);
		contentPane.add(lblNewLabel);

		txtusuario = new JTextField();
		txtusuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtusuario.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		txtusuario.setBounds(33, 53, 96, 19);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(33, 82, 63, 22);
		contentPane.add(lblPassword);

		btnentrada = new JButton("Entrada");
		btnentrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = txtusuario.getText();
				String password = String.valueOf(txtpassword.getPassword());
				JOptionPane.showMessageDialog(null, "usuario: " + usuario + "\nPassword: " + password);
			}
		});
		btnentrada.setBounds(33, 175, 85, 21);
		contentPane.add(btnentrada);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
			}
		});
		btnCancelar.setBounds(168, 175, 85, 21);
		contentPane.add(btnCancelar);

		txtpassword = new JPasswordField();
		txtpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtpassword.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		txtpassword.setBounds(33, 114, 96, 22);
		contentPane.add(txtpassword);

		btnlogo = new JLabel("");
		btnlogo.setBounds(210, 28, 163, 108);
		contentPane.add(btnlogo);

		Image a = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.PNG"));

		int ancho = btnlogo.getWidth();
		int alto = btnlogo.getHeight();

		btnlogo.setIcon(new ImageIcon(a.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
	}
}