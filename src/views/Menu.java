package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Models.Message;
import Models.SendEmailRequest;
import controller.MenuController;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static Menu frame;
	JPanel panelFile;
	JPanel panelMenu;
	JPanel panelFicherosFtp;
	private JPanel panelEmail;
	JFileChooser fc ;
	MenuController controller;
	private final Action action = new SwingAction();
	private JTextField emailTo;
	private JTextField emailSub;
	private ArrayList<JPanel> panelEmails = new ArrayList<JPanel>();
	private JMenuItem mntmRecibirCorreo;
	private JScrollPane panelPane;
	private JPanel emailIndex;
	private JTextArea details;
	private JCheckBox emailCheckBox;

	/**
	 * Launch the application.
	 */
	// constructor que pida por parametro un menuController*****************+
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new Menu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
//	 public Menu() {
//		// TODO Auto-generated constructor stub
//	//}
	public Menu(MenuController controller) {
		this.controller = controller;
		fc= new JFileChooser();
		setBackground(UIManager.getColor("Button.shadow"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 677, 26);
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(60, 179, 113));
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivos");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNuevoArchivo = new JMenuItem("Subir Archivo");
		mntmNuevoArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								
				String boton = "subir";
				fc.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Documentos"));
				menuFilechooserSubirFichero(boton);			
			}
		});
		mntmNuevoArchivo.setBackground(new Color(60, 179, 113));
		mntmNuevoArchivo.setOpaque(true);
		mntmNuevoArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNuevoArchivo);

		JMenuItem mntmBorrarArchivo = new JMenuItem("Archivos FTP");
		mntmBorrarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuListaFicherosFtp(controller.getHomeDirectory());
			}
		});
		mntmBorrarArchivo.setBackground(new Color(60, 179, 113));
		mntmBorrarArchivo.setOpaque(true);
		mntmBorrarArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmBorrarArchivo);

//		JMenuItem mntmModificarArchvo = new JMenuItem("Modificar archvo");
//		mntmModificarArchvo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				String boton = "modificar";
//				menuFilechooserSubirFichero(boton);
//			}
//		});
//		mntmModificarArchvo.setBackground(new Color(60, 179, 113));
//		mntmModificarArchvo.setOpaque(true);
//		mntmModificarArchvo.setForeground(new Color(255, 255, 255));
//		mnNewMenu.add(mntmModificarArchvo);

		JMenu mnEmail = new JMenu("E-mail");
		mnEmail.setAction(action);
		mnEmail.setForeground(new Color(255, 255, 255));
		menuBar.add(mnEmail);
		
		JMenuItem mntmEnviarCorreo = new JMenuItem("Enviar Correo");
		mntmEnviarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuEnvioDeCorreo();
			}
		});
		mntmEnviarCorreo.setBackground(new Color(60, 179, 113));
		mntmEnviarCorreo.setOpaque(true);
		mntmEnviarCorreo.setForeground(new Color(255, 255, 255));
		mnEmail.add(mntmEnviarCorreo);
		
		mntmRecibirCorreo = new JMenuItem("Recibir Correo");
//		mntmRecibirCorreo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				menuReciboDeCorreo(new ArrayList<Message>());
//			}
//		});
		mntmRecibirCorreo.setBackground(new Color(60, 179, 113));
		mntmRecibirCorreo.setOpaque(true);
		mntmRecibirCorreo.setForeground(new Color(255, 255, 255));
		mnEmail.add(mntmRecibirCorreo);

		JMenu mnAcercaDe = new JMenu("Acerca de");
		mnAcercaDe.setForeground(new Color(255, 255, 255));
		menuBar.add(mnAcercaDe);

		JMenu menu = new JMenu("");
		mnAcercaDe.add(menu);
		
//		ArrayList<Message> emails = new ArrayList<Message>();
//		emails.add(new Message(1, "asd1", "asd1", new Date(), "asdasd1"));
//		emails.add(new Message(2, "asd2", "asd2", new Date(), "asdasd2"));
//		emails.add(new Message(3, "asd3", "asd3", new Date(), "asdasd3"));
//		emails.add(new Message(4, "asd4", "asd4", new Date(), "asdasd4"));
//		emails.add(new Message(5, "asd5", "asd5", new Date(), "asdasd5"));
//		emails.add(new Message(6, "asd6", "asd6", new Date(), "asdasd6"));
//		emails.add(new Message(6, "asd6", "asd6", new Date(), "asdasd6"));
//		emails.add(new Message(6, "asd6", "asd6", new Date(), "asdasd6"));
//		emails.add(new Message(6, "asd6", "asd6", new Date(), "asdasd6"));
//		emails.add(new Message(6, "asd6", "asd6", new Date(), "asdasd6"));
//		emails.add(new Message(6, "asd6", "asd6", new Date(), "asdasd6"));
//		emails.add(new Message(6, "asd6", "asd6", new Date(), "asdasd6"));
//		
//
//		vaciarVentana();
//		panelEmail = new JPanel();
//		contentPane.updateUI();
//		panelEmail.setBounds(0, 27, 677, 376);
//		JScrollPane panelPane = new JScrollPane(panelEmail);
//		panelPane.setBounds(0, 27, 677, 376);
//		panelPane.setVisible(true);
//		contentPane.add(panelPane);
//		panelEmail.setLayout(new GridLayout(0, 2));
//		JPanel emailIndex = new JPanel(new GridLayout(0, 1));
//		JPanel emailDetails = new JPanel(new GridLayout(0, 1));
//		JTextArea details = new JTextArea();
//		for (Message email : emails) {
//			JPanel panelEmailWrapper = new JPanel(new GridLayout(0, 1));
//			panelEmails.add(panelEmailWrapper);
//			JLabel from = new JLabel("De: " + email.getFrom());
//			JLabel sub = new JLabel("Asunto: " + email.getSubject());
//			panelEmailWrapper.add(from);
//			panelEmailWrapper.add(sub);
//			
//			panelEmailWrapper.addMouseListener(new MouseListener() {
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					
//				}
//				
//				@Override
//				public void mousePressed(MouseEvent e) {
//				}
//				
//				@Override
//				public void mouseExited(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					details.setText("From: " + email.getFrom() + " \n Subject: " + email.getSubject() +
//							" \n MessageBody: " + email.getMessageBody() + " \n Date: " + email.getDate() 
//					);
//				}
//			});
//			
//			emailIndex.add(panelEmails.get(panelEmails.size() - 1));
//		}
//		
//		emailDetails.add(details);
//		
//		panelEmail.add(emailIndex);
//		panelEmail.add(emailDetails);
//		panelEmail.setVisible(true);
//		
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void updateEmailIndex(ArrayList<Message> emails) {
		panelEmails.clear();
		emailIndex.removeAll();
		for (Message email : emails) {
			JPanel panelEmailWrapper = new JPanel(new GridLayout(0, 1));
			panelEmails.add(panelEmailWrapper);
			JLabel from = new JLabel("De: " + email.getFrom());
			JLabel sub = new JLabel("Asunto: " + email.getSubject());
			panelEmailWrapper.add(from);
			panelEmailWrapper.add(sub);
			
			panelEmailWrapper.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					details.setText("From: " + email.getFrom() + " \n Subject: " + email.getSubject() +
							" \n MessageBody: " + email.getMessageBody() + " \n Date: " + email.getDate() 
					);
					controller.flagAsAdded(email);
				}
			});
			emailIndex.add(panelEmails.get(panelEmails.size() - 1));
		}

		contentPane.updateUI();
	}
	public void menuReciboDeCorreo(ArrayList<Message> emails) {
		vaciarVentana();
		panelEmail = new JPanel();
		contentPane.updateUI();
		panelEmail.setBounds(0, 27, 650, 356);
		panelPane = new JScrollPane(panelEmail);
		panelPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelPane.setBounds(0, 27, 650, 356);
		panelPane.setVisible(true);
		contentPane.add(panelPane);
		panelEmail.setLayout(new GridLayout(0, 1));
		emailCheckBox = new JCheckBox("Get all emails");
		emailCheckBox.setBounds(10,10,150,30);
		emailCheckBox.setSelected(true);
		emailCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				controller.changeStateOfRecievingEmails(((JCheckBox)e.getSource()).isSelected());
			}
		});
		panelEmail.add(emailCheckBox);
		if(emailIndex == null) {
			emailIndex = new JPanel(new GridLayout(0, 1));
			emailIndex.setBounds(0, 27, 325, 356);
		}
		JPanel emailDetails = new JPanel(new GridLayout(0, 1));
		emailDetails.setBounds(325, 27, 325, 356);
		details = new JTextArea(50, 10);
		updateEmailIndex(emails);
//		for (Message email : emails) {
//			JPanel panelEmailWrapper = new JPanel(new GridLayout(0, 1));
//			panelEmails.add(panelEmailWrapper);
//			JLabel from = new JLabel("De: " + email.getFrom());
//			JLabel sub = new JLabel("Asunto: " + email.getSubject());
//			panelEmailWrapper.add(from);
//			panelEmailWrapper.add(sub);
//			
//			panelEmailWrapper.addMouseListener(new MouseListener() {
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					
//				}
//				
//				@Override
//				public void mousePressed(MouseEvent e) {
//				}
//				
//				@Override
//				public void mouseExited(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					details.setText("From: " + email.getFrom() + " \n Subject: " + email.getSubject() +
//							" \n MessageBody: " + email.getMessageBody() + " \n Date: " + email.getDate() 
//					);
//				}
//			});
//			
//			emailIndex.add(panelEmails.get(panelEmails.size() - 1));
//		}
		
		emailDetails.add(details);
		
		panelEmail.add(emailIndex);
		panelEmail.add(emailDetails);
		panelEmail.setVisible(true);
	}
	
	public void menuEnvioDeCorreo() {
		vaciarVentana();
		panelFile = new JPanel();
		contentPane.updateUI();
		panelFile.setBounds(0, 27, 677, 376);
		contentPane.add(panelFile);
		panelFile.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Asunto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblNewLabel.setBounds(26, 62, 83, 16);
		panelFile.add(lblNewLabel);
		

		JTextField emailSub = new JTextField();
		emailSub.setBorder(new LineBorder(Color.BLACK));
		emailSub.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailSub.setBounds(157, 53, 493, 35);
		panelFile.add(emailSub);
		emailSub.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Para:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(28, 20, 54, 16);
		panelFile.add(lblNewLabel_1);
		
		JTextField emailTo = new JTextField();
		emailTo.setBorder(new LineBorder(Color.BLACK));
		emailTo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailTo.setBounds(157, 13, 493, 32);
		panelFile.add(emailTo);
		emailTo.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("Mensaje");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(26, 103, 74, 25);
		panelFile.add(lblNewLabel_2);
		
		JTextArea emailMsg = new JTextArea();
		emailMsg.setBorder(new LineBorder(new Color(0, 0, 0)));
		emailMsg.setTabSize(4);
		emailMsg.setFont(new Font("Monospaced", Font.PLAIN, 20));
		emailMsg.setRows(4);
		emailMsg.setBounds(19, 141, 646, 123);
		panelFile.add(emailMsg);
		

		JPanel errorWrapper = new JPanel();
		errorWrapper.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		errorWrapper.setBounds(208, 284, 256, 53);
		errorWrapper.setLayout(new BoxLayout(errorWrapper, BoxLayout.Y_AXIS));
		

		JPanel response = new JPanel();
		response.setFont(new Font("Tahoma", Font.PLAIN, 20));
		response.setBounds(0, 350, 677, 25);
		
		JButton btnSend = new JButton("Enviar");
		btnSend.setForeground(Color.WHITE);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSend.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorWrapper.removeAll();
				boolean isCorrecrSub = checkValue("Cabecera", emailSub.getText(), false, errorWrapper);
				boolean isCorrecrEmail = checkValue("Email", emailTo.getText(), true, errorWrapper);
				boolean isCorrecrMsg = checkValue("Mensaje", emailMsg.getText(), false, errorWrapper);

				contentPane.updateUI();
				if(isCorrecrEmail && isCorrecrMsg && isCorrecrSub) {
					System.out.println("All ok");
					SendEmailRequest emailRequest = new SendEmailRequest("", "", emailTo.getText(), 
									emailSub.getText(), emailMsg.getText());
					String sendEmailResponse = controller.sendEmail(emailRequest);
					JLabel label = new JLabel(sendEmailResponse);
					response.add(label);
				} else {
					System.out.println("Failure");
				}
			}
		});
		btnSend.setBackground(new Color(60, 179, 113));
		btnSend.setBounds(12, 277, 160, 60);
		panelFile.add(btnSend);
		
		JButton btnReset = new JButton("Resetear Valores");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emailSub.setText("");
				emailTo.setText("");
				emailMsg.setText("");
			}
		});
		btnReset.setBackground(new Color(60, 179, 113));
		btnReset.setBounds(505, 277, 160, 60);
		panelFile.add(btnReset);
		panelFile.setVisible(true);
		panelFile.add(errorWrapper);
		panelFile.add(response);
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean checkValue(String labelName, String value, boolean isEmail, JPanel errorWrapper) {
		if(value.trim().equalsIgnoreCase("")) {
			JLabel label = new JLabel(labelName + " no puede estar vacio");
			errorWrapper.add(label);
			return false;
		}
		if(isEmail) {
			if(isValidEmailAddress(value)) {
				return true;
			} else {
				JLabel label = new JLabel(labelName + " no es correcto. Debe contener...");
				errorWrapper.add(label);
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidEmailAddress(String email) {
		Pattern  regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher = regexPattern.matcher(email);
        return regMatcher.matches();
 }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void menuFilechooserSubirFichero(String boton) {	
		vaciarVentana();
		panelFile = new JPanel();
		contentPane.updateUI();
		panelFile.setBounds(0, 27, 677, 376);
		contentPane.add(panelFile);
		// Creamos el objeto JFileChooser
		// Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		panelFile.add(fc);
		JButton btnNewButton = new JButton(boton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File fichero = fc.getSelectedFile();
				try {
					controller.uploadFile(fichero);
				}catch(java.lang.NullPointerException e) {
					Utilities.showMessage("Error de subida. Solo se pueden subir ficheros", true);
				}
			}
		});
		btnNewButton.setBounds(123, 327, 97, 25);
		panelFile.add(btnNewButton);
		panelFile.setVisible(false);
		panelFile.setVisible(true);
	}
	
	public void menuListaFicherosFtp(String homeDirectory) {
		vaciarVentana();

		panelFicherosFtp = new JPanel();
		panelFicherosFtp.setLayout(null);
		contentPane.updateUI();
		panelFicherosFtp.setBounds(25, 27, 677, 376);
		contentPane.add(panelFicherosFtp);
		
		DefaultMutableTreeNode home = new DefaultMutableTreeNode(homeDirectory);
		DefaultTreeModel model = new DefaultTreeModel(home);
		controller.createDirectoryTree(model, "", home);
		JTree tree = new JTree(model);
		tree.setBounds(25, 25, 400, 325);
		
		JButton btnRemove = new JButton("Eliminar");
		btnRemove.setBounds(475, 25, 110, 35);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteFile(controller.getTreePath(tree.getSelectionPath(), 0));
				menuListaFicherosFtp(homeDirectory);
			}
		});
		
		JButton btnRename = new JButton("Renombrar");
		btnRename.setBounds(475, 85, 110, 35);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = JOptionPane.showInputDialog("Nuevo nombre");
				if(newName != null && !newName.equalsIgnoreCase("")) {
					newName = controller.getTreePath(tree.getSelectionPath(), 1) + newName;
					controller.renameFile(controller.getTreePath(tree.getSelectionPath(), 0), newName);
					menuListaFicherosFtp(homeDirectory);
				}
			}
		});
		
		JButton btnDownload = new JButton("Descargar");
		btnDownload.setBounds(475, 145, 110, 35);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = controller.getTreePath(tree.getSelectionPath(), 0);
				String[] pathComponents = path.split("/");
				controller.downloadFile(path, System.getProperty("user.home") 
						+ System.getProperty("file.separator")+ "Documents"
						,pathComponents [pathComponents.length - 1]);
			}
		});
		//
		JButton btnDirectory = new JButton("Nuevo Directorio");
		btnDirectory.setBounds(475, 205, 110, 35);
		btnDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String path = JOptionPane.showInputDialog("Nombre de carpeta");
                if(path != null && !path.equalsIgnoreCase("")) {
                    path = controller.getTreePath(tree.getSelectionPath(), 0) + path+"//";
                    try {
                        controller.createDirectory(path);
    				}catch(java.lang.NullPointerException e) {
    					Utilities.showMessage("Error al crear directorio. El nombre no puede estar repetido.", true);
    				}
                }
                menuListaFicherosFtp(homeDirectory);
            }
        });
			
		panelFicherosFtp.add(tree);
		panelFicherosFtp.add(btnRemove);
		panelFicherosFtp.add(btnRename);
		panelFicherosFtp.add(btnDownload);
		panelFicherosFtp.add(btnDirectory);
		panelFicherosFtp.setVisible(true);
	}
	
	public void vaciarVentana() {
		try{
			contentPane.remove(panelFile);
		}catch(java.lang.NullPointerException e) {}
		
		try{
			contentPane.remove(panelMenu);
		}catch(java.lang.NullPointerException e) {}
		
		try{
			contentPane.remove(panelEmail);
			contentPane.remove(panelPane);
		}catch(java.lang.NullPointerException e) {}
		
		try{
			contentPane.remove(panelFicherosFtp);
		}catch(java.lang.NullPointerException e) {}
		
		contentPane.setVisible(false);
		contentPane.setVisible(true);
	}

	public JPanel getEmailPanel() {
		return panelEmail;
	}
	public JMenuItem getMntmRecibirCorreo() {
		return mntmRecibirCorreo;
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