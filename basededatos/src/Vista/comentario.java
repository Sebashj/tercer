package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.daocomentarios;
import Modelo.comentarios;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class comentario extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtuser;
	private JTextField txttexto;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	daocomentarios dao=new daocomentarios();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblusuario;
	ArrayList<comentarios> lista = new ArrayList<comentarios>();
	int fila=-1;
	comentarios comentarios;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					comentario frame = new comentario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtuser.setText("");
		txttexto.setText("");
	}

	public comentario() {
		setLocationRelativeTo(null);
		setTitle("CRUDUSUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);
		
		txtuser = new JTextField();
		txtuser.setBounds(73, 60, 86, 20);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario");
		lblNewLabel_1_1.setBounds(10, 60, 53, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Text");
		lblNewLabel_1_2.setBounds(202, 60, 53, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txttexto = new JTextField();
		txttexto.setColumns(10);
		txttexto.setBounds(332, 60, 86, 20);
		contentPane.add(txttexto);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtuser.getText().equals("")||txttexto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					comentarios user=new comentarios();
					user.setTexto(txttexto.getText());
					user.setUsuario(txtuser.getText());
					if (dao.insertarcomentarios(user)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se agrego correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnAgregar.setBounds(10, 105, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar","ELIMINAR",JOptionPane.YES_NO_OPTION);
					if(opcion==0) {
					if (dao.eliminarcomentarios(lista.get(fila).getId())) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se elimino correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnEliminar.setBounds(216, 105, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				
			}
		});
		btnBorrar.setBounds(117, 105, 89, 23);
		contentPane.add(btnBorrar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txttexto.getText().equals("")||txtuser.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					comentarios.setUsuario(txtuser.getText());
					comentarios.setTexto(txttexto.getText());
					if (dao.editarcomentarios(comentarios)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					
				}
				
			}
		});
		btnEditar.setBounds(315, 105, 89, 23);
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 139, 398, 238);
		contentPane.add(scrollPane);
		
		tblusuario = new JTable();
		tblusuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblusuario.getSelectedRow();
				comentarios=lista.get(fila);
				lblid.setText(""+lista.get(fila).getId());
				txtuser.setText(""+comentarios.getUsuario());
				txttexto.setText(""+comentarios.getTexto());
				
			}
		});
		tblusuario.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblusuario);
		
		modelo.addColumn("ID");
		modelo.addColumn("USERT");
		modelo.addColumn("TEXTO");
		tblusuario.setModel(modelo);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchUsuarios();
		for(comentarios u: lista) {
			Object o[]=new Object [3];
			o[0]=u.getId();
			o[1]=u.getTexto();
			o[2]=u.getUsuario();
			modelo.addRow(o);
		}
		tblusuario.setModel(modelo);
	}
}
