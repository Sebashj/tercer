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

import Dao.daoalumno;
import Modelo.alumno;

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

public class valumno extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtcorreo;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	daoalumno dao=new daoalumno();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblalumno;
	ArrayList<alumno> lista = new ArrayList<alumno>();
	int fila=-1;
	alumno alumno;
	private JLabel lblNewLabel_2;
	private JComboBox cboSemestre;
	private JComboBox cboGrupo;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					valumno frame = new valumno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtcorreo.setText("");
		txtnombre.setText("");
	}

	public valumno() {
		//setLocationRelativeTo(null);
		setTitle("CRUDALUMNO");
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
		
		JLabel lblNewLabel_1 = new JLabel("Semestre");
		lblNewLabel_1.setBounds(227, 60, 86, 23);
		contentPane.add(lblNewLabel_1);
		
		txtcorreo = new JTextField();
		txtcorreo.setBounds(106, 95, 86, 20);
		contentPane.add(txtcorreo);
		txtcorreo.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Correo");
		lblNewLabel_1_1.setBounds(20, 94, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setBounds(10, 60, 86, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(106, 63, 86, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(valumno.class.getResource("/Img/colores.PNG")));
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtcorreo.getText().equals("")||cboGrupo.getSelectedItem().equals("")||txtnombre.getText().equals("")||cboSemestre.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					alumno user=new alumno();
					user.setNombre(txtnombre.getText());
					user.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					user.setCorreo(txtcorreo.getText());
					user.setSemestre(cboSemestre.getSelectedItem().toString());
					
					if (dao.insertarAlumno(user)) {
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
		btnAgregar.setBounds(20, 141, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(valumno.class.getResource("/Img/rojo.PNG")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Image a = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/usuario.PNG"));

				int ancho = btnEliminar.getWidth();
				int alto = btnEliminar.getHeight();
				
				btnEliminar.setIcon(new ImageIcon(a.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarAlumno(lista.get(fila).getId())) {
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
		btnEliminar.setBounds(122, 141, 89, 23);
		contentPane.add(btnEliminar);
		
		btnBorrar = new JButton("borrar");
		btnBorrar.setIcon(new ImageIcon(valumno.class.getResource("/Img/naranga.PNG")));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				
			}
		});
		btnBorrar.setBounds(227, 141, 89, 23);
		contentPane.add(btnBorrar);
		
		btnEditar = new JButton("editar");
		btnEditar.setIcon(new ImageIcon(valumno.class.getResource("/Img/azul.PNG")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Image a = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/usuario.PNG"));

				int ancho = btnEditar.getWidth();
				int alto = btnEditar.getHeight();
				
				btnEditar.setIcon(new ImageIcon(a.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
				try {
					if(txtcorreo.getText().equals("")||cboGrupo.getSelectedItem().equals("")||txtnombre.getText().equals("")||cboSemestre.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					alumno.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					alumno.setNombre(txtnombre.getText());
					alumno.setCorreo(txtcorreo.getText());
					alumno.setSemestre(cboSemestre.getSelectedItem().toString());
					if (dao.editarAlumno(alumno)) {
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
		btnEditar.setBounds(326, 141, 89, 23);
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 398, 238);
		contentPane.add(scrollPane);
		
		tblalumno = new JTable();
		tblalumno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblalumno.getSelectedRow();
				alumno=lista.get(fila);
				lblid.setText(""+lista.get(fila).getId());
				txtcorreo.setText(""+alumno.getCorreo());
				txtnombre.setText(""+alumno.getNombre());
				cboGrupo.setSelectedItem(""+alumno.getGrupo());
				cboSemestre.setSelectedItem(""+alumno.getSemestre());
				
			}
		});
		tblalumno.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblalumno);
		
		modelo.addColumn("ID");
		modelo.addColumn("nombre");
		modelo.addColumn("grupo");
		modelo.addColumn("correo");
		modelo.addColumn("semestre");
		tblalumno.setModel(modelo);
		refrescarTabla();
		
		lblNewLabel_2 = new JLabel("Grupo");
		lblNewLabel_2.setBounds(227, 26, 60, 23);
		contentPane.add(lblNewLabel_2);
		
		cboSemestre = new JComboBox();
		cboSemestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Primero", "Segundo", "Tercero", "Cuarto", "Quinto", "Sexto"}));
		cboSemestre.setBounds(277, 94, 86, 22);
		contentPane.add(cboSemestre);
		
		cboGrupo = new JComboBox();
		cboGrupo.setModel(new DefaultComboBoxModel(new String[] {"105", "205", "312", "405", "505", "605"}));
		cboGrupo.setBounds(297, 26, 76, 22);
		contentPane.add(cboGrupo);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchAlumnos();
		for(alumno u: lista) {
			Object o[]=new Object [5];
			o[0]=u.getId();
			o[1]=u.getCorreo();
			o[2]=u.getGrupo();
			o[3]=u.getNombre();
			o[4]=u.getSemestre();
			modelo.addRow(o);
		}
		tblalumno.setModel(modelo);
	}
}