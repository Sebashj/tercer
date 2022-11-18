package Vista;

import java.awt.EventQueue;
import java.awt.Image;

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

import Dao.DaoCategoria;
import Modelo.Categoria;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class vCategoria extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	DaoCategoria dao=new DaoCategoria();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblCategoria;
	ArrayList<Categoria> lista = new ArrayList<Categoria>();
	int fila=-1;
	Categoria Categoria;
	private JTextField txtcategoria;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCategoria frame = new vCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtcategoria.setText("");
		
	}

	public vCategoria() {
		setLocationRelativeTo(null);
		setTitle("CRUDCategoria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 463);
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
		lblid.setBounds(106, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria");
		lblNewLabel_1.setBounds(31, 71, 86, 23);
		contentPane.add(lblNewLabel_1);
		
		btnAgregar = new JButton("Agregar");
		
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtcategoria.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Categoria user=new Categoria();
					user.setCategoria(txtcategoria.getText());
					
					if (dao.insertarcategoria(user)) {
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
		btnAgregar.setBounds(20, 117, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarcategoria(lista.get(fila).getIdCategoria())) {
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
		btnEliminar.setBounds(119, 117, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				
			}
		});
		btnBorrar.setBounds(218, 117, 89, 23);
		contentPane.add(btnBorrar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(txtcategoria.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "campos vacios");
					return;
					}
					Categoria.setCategoria(txtcategoria.getText());
					if (dao.editarcategoria(Categoria)) {
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
		btnEditar.setBounds(317, 117, 89, 23);
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 398, 238);
		contentPane.add(scrollPane);
		
		tblCategoria = new JTable();
		tblCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblCategoria.getSelectedRow();
				Categoria=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdCategoria());
				txtcategoria.setText(""+Categoria.getCategoria());
				
				
			}
		});
		tblCategoria.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblCategoria);
		
		modelo.addColumn("ID");
		modelo.addColumn("categoria");
		tblCategoria.setModel(modelo);
		refrescarTabla();
		
		txtcategoria = new JTextField();
		txtcategoria.setBounds(122, 72, 86, 20);
		contentPane.add(txtcategoria);
		txtcategoria.setColumns(10);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchcategorias();
		for(Categoria u: lista) {
			Object o[]=new Object [2];
			o[0]=u.getIdCategoria();
			o[1]=u.getCategoria();
			modelo.addRow(o);
		}
		tblCategoria.setModel(modelo);
	}
}