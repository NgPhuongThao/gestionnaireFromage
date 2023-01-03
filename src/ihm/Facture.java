package ihm;
import java.awt.EventQueue;

import modèle.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Facture {

	private JFrame frame;
	private FactureClass facture;

	/**
	 * Create the application.
	 */
	public Facture( String nom,String recap, String datelieu) {
		facture = new FactureClass(BlancJus.PANIER);
		initialize(nom,recap,datelieu);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nom, String recap, String datelieu) {
		frame = new JFrame("Votre facture");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logoFenetre.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 490);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFacture = new JLabel("Facture");
		lblFacture.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblFacture.setBounds(20, 11, 149, 43);
		frame.getContentPane().add(lblFacture);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelMain.setBounds(20, 64, 543, 357);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(null);
		
		JLabel lblFromageriePour = new JLabel("Fromagerie pour vous servir");
		lblFromageriePour.setBounds(210, 10, 255, 13);
		panelMain.add(lblFromageriePour);
		
		JLabel lblDateLieu_Valeur = new JLabel(datelieu);
		lblDateLieu_Valeur.setBounds(22, 45, 191, 13);
		panelMain.add(lblDateLieu_Valeur);
		
		JLabel lblInfoClient = new JLabel("INFORMATION CLIENT");
		lblInfoClient.setBounds(22, 78, 164, 13);
		panelMain.add(lblInfoClient);
		
		JLabel lblRecapCommande = new JLabel("RECAP. DE LA COMMANDE");
		lblRecapCommande.setBounds(23, 101, 191, 13);
		panelMain.add(lblRecapCommande);
		
		JLabel lblTotalCommande = new JLabel("TOTAL COMMANDE");
		lblTotalCommande.setBounds(23, 289, 108, 13);
		panelMain.add(lblTotalCommande);
		
		JLabel lblTransport = new JLabel("TRANSPORT");
		lblTransport.setBounds(23, 300, 127, 13);
		panelMain.add(lblTransport);
		
		JLabel lblPrixTotal = new JLabel("TOTAL TTC");
		lblPrixTotal.setBounds(23, 323, 82, 13);
		panelMain.add(lblPrixTotal);
		
		JLabel lblPrixTotal_valeur = new JLabel(String.format("%10.2f", this.facture.getTotal())+" €");
		lblPrixTotal_valeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrixTotal_valeur.setBounds(178, 323, 82, 13);
		panelMain.add(lblPrixTotal_valeur);
		
		JLabel lblTotalCommande_Valeur = new JLabel(String.format("%10.2f", BlancJus.PANIER.total())+" €");
		lblTotalCommande_Valeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalCommande_Valeur.setBounds(178, 289, 82, 13);
		panelMain.add(lblTotalCommande_Valeur);
		
		JLabel lblInfoClient_Valeur = new JLabel(nom);
		lblInfoClient_Valeur.setVerticalAlignment(SwingConstants.TOP);
		lblInfoClient_Valeur.setBounds(210, 77, 367, 20);
		panelMain.add(lblInfoClient_Valeur);
		
		JLabel lblPrixTransport = new JLabel(String.format("%10.2f", BlancJus.PANIER.getPrixLivraison())+" €");
		lblPrixTransport.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrixTransport.setBounds(178, 300, 82, 13);
		panelMain.add(lblPrixTransport);
		
		JTextArea textAreaRecap = new JTextArea(recap);
		textAreaRecap.setBackground(SystemColor.menu);
		textAreaRecap.setEditable(false);
		textAreaRecap.setBounds(23, 124, 510, 155);
		panelMain.add(textAreaRecap);
	}

	public Window getFrame() {
		return this.frame;
	}
}
