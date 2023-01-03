package ihm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Coordonnées {

	private JFrame frame;
	private JTextField textNom;
	private JTextField textPrénom;
	private JTextField textAdresse1;
	private JTextField textAdresse2;
	private JTextField textCodePostal;
	private JTextField textVille;
	private JTextField textTelephone;
	private JTextField textEmail;

	public Window getFrame() {
		return this.frame;
	}
	
	/**
	 * Create the application.
	 */
	public Coordonnées() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Vos coordonnées");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logoFenetre.png"));
		frame.setResizable(false);
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setVgap(10);
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblTitre = new JLabel("Coordonn\u00E9es");
		lblTitre.setFont(new Font("Arial", Font.PLAIN, 20));
		frame.getContentPane().add(lblTitre, BorderLayout.NORTH);
		
		JPanel footer = new JPanel();
		FlowLayout fl_Footer = (FlowLayout) footer.getLayout();
		fl_Footer.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(footer, BorderLayout.SOUTH);
		
		JButton boutonOK = new JButton("OK");
		
		footer.add(boutonOK);
		
		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		footer.add(boutonAnnuler);
		
		JPanel panelMain = new JPanel();
		frame.getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(8, 1, 0, 5));
		
		JPanel panelNom = new JPanel();
		panelMain.add(panelNom);
		panelNom.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lblNom = new JLabel("Nom* :");
		panelNom.add(lblNom);
		
		textNom = new JTextField();
		textNom.setToolTipText("");
		panelNom.add(textNom);
		textNom.setColumns(35);
		
		JPanel panelPrenom = new JPanel();
		panelMain.add(panelPrenom);
		panelPrenom.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom* :");
		panelPrenom.add(lblPrenom);
		
		textPrénom = new JTextField();
		panelPrenom.add(textPrénom);
		textPrénom.setColumns(33);
		
		JPanel panelAdresse1 = new JPanel();
		panelMain.add(panelAdresse1);
		panelAdresse1.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lbllAdresse1 = new JLabel("Adresse 1* :");
		panelAdresse1.add(lbllAdresse1);
		
		textAdresse1 = new JTextField();
		panelAdresse1.add(textAdresse1);
		textAdresse1.setColumns(32);
		
		JPanel panelAdresse2 = new JPanel();
		panelMain.add(panelAdresse2);
		panelAdresse2.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lblAdresse2 = new JLabel("Adresse 2 :");
		panelAdresse2.add(lblAdresse2);
		
		textAdresse2 = new JTextField();
		panelAdresse2.add(textAdresse2);
		textAdresse2.setColumns(32);
		
		JPanel panelCodePostal = new JPanel();
		panelMain.add(panelCodePostal);
		panelCodePostal.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lblCodePostal = new JLabel("Code postal* :");
		panelCodePostal.add(lblCodePostal);
		
		textCodePostal = new JTextField();
		panelCodePostal.add(textCodePostal);
		textCodePostal.setColumns(31);
		
		JPanel panelVille = new JPanel();
		panelMain.add(panelVille);
		panelVille.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lblVille = new JLabel("Ville* :");
		panelVille.add(lblVille);
		
		textVille = new JTextField();
		panelVille.add(textVille);
		textVille.setColumns(35);
		
		JPanel panelTelephone = new JPanel();
		panelMain.add(panelTelephone);
		panelTelephone.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lblTelephone = new JLabel("T\u00E9l\u00E9phone :");
		panelTelephone.add(lblTelephone);
		
		textTelephone = new JTextField();
		panelTelephone.add(textTelephone);
		textTelephone.setColumns(32);
		
		JPanel panelEmail = new JPanel();
		panelMain.add(panelEmail);
		panelEmail.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		JLabel lblEmail = new JLabel("Email* :");
		panelEmail.add(lblEmail);
		
		textEmail = new JTextField();
		panelEmail.add(textEmail);
		textEmail.setColumns(34);
		
		
		fenetreFacture(boutonOK,this.textNom,this.textPrénom,this.textVille,this.textEmail,this.textCodePostal,this.textAdresse1);
	}

	private void fenetreFacture(JButton BoutonOK,JTextField LabelNom,JTextField LabelPrenom,JTextField LabelVille,JTextField LabelEmail,JTextField LabelCodePostal,JTextField LabelAdress) {
		BoutonOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int missing = 0;
				if (LabelNom.getText().equals("")) {
					LabelNom.setBackground(new Color(255,132,132));
					missing++;
				} else {
					LabelNom.setBackground(new Color(240,240,240));
				}
				
				if (LabelPrenom.getText().equals("")) {
					LabelPrenom.setBackground(new Color(255,132,132));
					missing++;
				} else {
					LabelPrenom.setBackground(new Color(240,240,240));
				}
				
				if (LabelVille.getText().equals("")) {
					LabelVille.setBackground(new Color(255,132,132));
					missing++;
				} else {
					LabelVille.setBackground(new Color(240,240,240));
				}
				
				if (LabelEmail.getText().equals("")) {
					LabelEmail.setBackground(new Color(255,132,132));
					missing++;
				} else {
					LabelEmail.setBackground(new Color(240,240,240));
				}
				
				if (LabelCodePostal.getText().equals("")) {
					LabelCodePostal.setBackground(new Color(255,132,132));
					missing++;
				} else {
					LabelCodePostal.setBackground(new Color(240,240,240));
				}
				
				if (LabelAdress.getText().equals("")) {
					LabelAdress.setBackground(new Color(255,132,132));
					missing++;
				} else {
					LabelAdress.setBackground(new Color(240,240,240));
				}
				
				if (missing == 0) { 
					DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");

					String nomPrenom = LabelNom.getText()+" "+LabelPrenom.getText()+" ("+LabelEmail.getText()+")";
					String dateLieu = "Le "+date.format(LocalDateTime.now())+" A "+LabelVille.getText();
					String recapCommande = "";

					for (int i = 0; i < BlancJus.PANIER.getPanier().size(); i++) {
						recapCommande += (i+1)+" : ";
						recapCommande += BlancJus.PANIER.toArray()[i][0]+", Prix TTC : "+BlancJus.PANIER.toArray()[i][1]
								+"€, Quantité : "+BlancJus.PANIER.toArray()[i][2]+", Prix Total : "+BlancJus.PANIER.toArray()[i][3]+"€\r\n";
					}
					Facture dialog = new Facture(nomPrenom,recapCommande,dateLieu);
					dialog.getFrame().setVisible(true);
					frame.setVisible(false);
				}
			}
		});
	}

}