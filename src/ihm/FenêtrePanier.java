package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JTable;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modèle.*;


public class FenêtrePanier {

	private JFrame frame;
	private JTable table;
	private JTable tableRécapitulatif;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public FenêtrePanier(JButton boutonPanier) {
		initialize(boutonPanier);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JButton BoutonPanier) {
		frame = new JFrame("Votre panier");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logoFenetre.png"));
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setVgap(5);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel header = new JPanel();
		FlowLayout fl_header = (FlowLayout) header.getLayout();
		fl_header.setHgap(190);
		fl_header.setAlignment(FlowLayout.LEADING);
		frame.getContentPane().add(header, BorderLayout.NORTH);
		
		JPanel headerTitre = new JPanel();
		header.add(headerTitre);
		headerTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitre = new JLabel("Votre panier");
		lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
		headerTitre.add(lblTitre);
		
		JPanel headerBoutonAchat = new JPanel();
		header.add(headerBoutonAchat);
		headerBoutonAchat.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton boutonAchats = new JButton("Continuer les achats");
		construireContrôleurBoutonAchats(boutonAchats);
		headerBoutonAchat.add(boutonAchats);
		
		JPanel footer = new JPanel();
		frame.getContentPane().add(footer, BorderLayout.SOUTH);
		footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));
		
		JPanel footerOptionsDeLivraison = new JPanel();
		footer.add(footerOptionsDeLivraison);
		footerOptionsDeLivraison.setLayout(new GridLayout(3, 1, 0, 0));
		
		JRadioButton boutonRelais = new JRadioButton("Colissimo en point Relais 4.90\u20AC");
		boutonRelais.setSelected(true);
		buttonGroup.add(boutonRelais);
		footerOptionsDeLivraison.add(boutonRelais);
		
		JRadioButton boutonDomicile = new JRadioButton("Colissimo \u00E0 domicile 4.90\u20AC");
		buttonGroup.add(boutonDomicile);
		footerOptionsDeLivraison.add(boutonDomicile);
		
		JRadioButton boutonChronopost = new JRadioButton("Chronopost 9.90\u20AC");
		buttonGroup.add(boutonChronopost);
		footerOptionsDeLivraison.add(boutonChronopost);
		
		JPanel footerValiderOuVider = new JPanel();
		footer.add(footerValiderOuVider);
		footerValiderOuVider.setLayout(new GridLayout(2, 1, 0, 0));
		
		JButton boutonVider = new JButton("Vider le panier");
		viderLePanier(boutonVider,BoutonPanier);
		footerValiderOuVider.add(boutonVider);
		
		JButton boutonValider = new JButton("Valider le panier");
		construireContrôleurBoutonValider(boutonValider,boutonRelais,boutonDomicile,boutonChronopost);
		footerValiderOuVider.add(boutonValider);
		
		JScrollPane panelMain = new JScrollPane();
		frame.getContentPane().add(panelMain, BorderLayout.CENTER);
		
		Object[][] data = BlancJus.PANIER.toArray();
		String  title[] = {"Produits", "Prix", "Quantité", "Total"};
		tableRécapitulatif = new JTable(data,title);
		panelMain.setViewportView(tableRécapitulatif);
		

	}

	public void viderLePanier(JButton BoutonVider,JButton BoutonPanier) {
		BoutonVider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BlancJus.PANIER.vider(BlancJus.ARTICLES);
				BlancJus.miseAJourBoutonPanier(BoutonPanier);
				frame.setVisible(false);
			}
		});
	}

	private void construireContrôleurBoutonAchats(JButton BoutonAchats) {
		BoutonAchats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
	}

	private void construireContrôleurBoutonValider(JButton BoutonValider,JRadioButton BoutonRelais,JRadioButton BoutonDomicile,JRadioButton BoutonChronopost) {
		BoutonValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coordonnées coordonnées = new Coordonnées();
				coordonnées.getFrame().setVisible(true);
				if (BoutonRelais.isSelected() || BoutonDomicile.isSelected()) {
					BlancJus.PANIER.setPrixLivraison(4.9f);
				} else {
					BlancJus.PANIER.setPrixLivraison(9.9f);
				}
			}
		});
	}

}
