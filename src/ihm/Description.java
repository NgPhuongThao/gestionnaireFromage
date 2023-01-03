package ihm;
import modèle.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Description {

	private JFrame frame;
	private Fromage fromage;
	private JComboBox comboPoidsEtPrix;
	private JSpinner spinnerQuantité;
	private Article article;

	public JFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * Open the application
	 */
	public Description(String désignationFromage,JButton BoutonPanier, Articles articles) {
		this.fromage = articles.getFromage(désignationFromage);
		initialize(BoutonPanier);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JButton BoutonPanier) {
		frame = new JFrame("Description de "+this.fromage.getDésignation());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logoFenetre.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 487, 414);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNomFromage = new JLabel(this.fromage.getDésignation());
		lblNomFromage.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNomFromage.setBounds(10, 0, 429, 46);
		frame.getContentPane().add(lblNomFromage);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 51, 453, 251);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea textAreaDescription = new JTextArea(this.fromage.getDescription());
		textAreaDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setEditable(false);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setBounds(0, 0, 453, 251);
		panel.add(textAreaDescription);
		JButton btnAjouterPanier = new JButton("Ajouter au panier");
		construireContrôleurBoutonAjouterPanier(btnAjouterPanier,BoutonPanier);
		btnAjouterPanier.setBounds(332, 316, 131, 21);
		frame.getContentPane().add(btnAjouterPanier);
		
		JButton btnAnnuler = new JButton("Annuler");
		construireContrôleurBoutonAnnuler(btnAnnuler);
		btnAnnuler.setBounds(378, 346, 85, 21);
		frame.getContentPane().add(btnAnnuler);
		

		spinnerQuantité = new JSpinner();
		spinnerQuantité.setEnabled(false);
		spinnerQuantité.setBounds(10, 346, 65, 20);
		spinnerQuantité.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		frame.getContentPane().add(spinnerQuantité);
		
		List<String> listePrix = new LinkedList<String>();
		for (Article a : this.fromage.getArticles()) {
			if (a.getQuantitéEnStock()>0) {
				listePrix.add(a.toStringIHM());
			}
		}
		comboPoidsEtPrix = new JComboBox(listePrix.toArray());
		if (listePrix.size() > 0) {
			comboPoidsEtPrix.setSelectedIndex(0);
			this.article = BlancJus.ARTICLES.getArticle(this.fromage.getDésignation(), (String) comboPoidsEtPrix.getSelectedItem());
			spinnerQuantité.enable();
			spinnerQuantité.setModel(new SpinnerNumberModel(1, 1, article.getQuantitéEnStock(), 1));
		}
		comboPoidsEtPrix.setBounds(10, 313, 300, 22);
		frame.getContentPane().add(comboPoidsEtPrix);
		
		construireContrôleurComboPoidsEtPrix();
	}

	private void construireContrôleurComboPoidsEtPrix() {
		comboPoidsEtPrix.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				article = BlancJus.ARTICLES.getArticle(fromage.getDésignation(), (String) comboPoidsEtPrix.getSelectedItem());			
				spinnerQuantité.setModel(new SpinnerNumberModel(1, 1, article.getQuantitéEnStock(), 1));
			}
		});
	}

	private void construireContrôleurBoutonAjouterPanier(JButton btnAjouterPanier,JButton BoutonPanier) {
		btnAjouterPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				for (Article a : fromage.getArticles()) {
					if (a.toStringIHM().equals(comboPoidsEtPrix.getSelectedItem())) {
						BlancJus.PANIER.addLine(new LigneDeCommande(fromage.getDésignation(),a.getClé(), a.getPrixTTC(), (int) spinnerQuantité.getValue()),a);
						BlancJus.miseAJourBoutonPanier(BoutonPanier);
						break;
					}
				}
			}
		});
	}

	private void construireContrôleurBoutonAnnuler(JButton btnAnnuler) {
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
	}
}
