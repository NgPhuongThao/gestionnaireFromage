package ihm;	
import modèle.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class BlancJus {

	private JFrame frame;
	private JTextField textRecherche;	
	private List<String> listeCourante;
	private JButton boutonPanier;
	
	static public Articles ARTICLES;
	static public Panier PANIER = new Panier();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GenerationFromages.main(args);
				try {
					BlancJus window = new BlancJus();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BlancJus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Fromagerie");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logoFenetre.png"));
		frame.setResizable(false);
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setVgap(10);
		frame.setBounds(100, 100, 750, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel header = new JPanel();
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new GridLayout(2, 1, 0, 5));
		
		JPanel headerHaut = new JPanel();
		header.add(headerHaut);
		headerHaut.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTitre = new JPanel();
		FlowLayout fl_panelTitre = (FlowLayout) panelTitre.getLayout();
		fl_panelTitre.setAlignment(FlowLayout.LEFT);
		headerHaut.add(panelTitre);
		
		JLabel titre = new JLabel("Fromagerie");
		titre.setIcon(new ImageIcon("src/img/fromage.png"));
		titre.setFont(new Font("Arial", Font.PLAIN, 20));
		panelTitre.add(titre);
		
		JPanel barreRecherche = new JPanel();
		FlowLayout fl_barreRecherche = (FlowLayout) barreRecherche.getLayout();
		fl_barreRecherche.setAlignment(FlowLayout.RIGHT);
		headerHaut.add(barreRecherche);
		
		textRecherche = new JTextField();
		textRecherche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRecherche.setColumns(20);
		barreRecherche.add(textRecherche);
		
		JButton boutonRecherche = new JButton("Rechercher");
		barreRecherche.add(boutonRecherche);
		
		JPanel headerNavigation = new JPanel();
		header.add(headerNavigation);
		headerNavigation.setLayout(new GridLayout(1, 4, 10, 0));
		
		JButton boutonTous = new JButton("Tous");
		headerNavigation.add(boutonTous);
		
		JButton boutonVache = new JButton("Vache");
		headerNavigation.add(boutonVache);
		
		JButton boutonChevre = new JButton("Chèvre");
		headerNavigation.add(boutonChevre);
		
		JButton boutonBrebis = new JButton("Brebis");
		headerNavigation.add(boutonBrebis);
		
		JPanel footer = new JPanel();
		frame.getContentPane().add(footer, BorderLayout.SOUTH);
		footer.setLayout(new GridLayout(0, 2, 20, 0));
		
		JTextPane textContact = new JTextPane();
		textContact.setBackground(SystemColor.menu);
		textContact.setEditable(false);
		textContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textContact.setText("Contactez-nous:\r\n-Tel : 07.82.84.85.87\r\n- Mail : jeanmicheldelacompta@gmail.com");
		footer.add(textContact);
		
		boutonPanier = new JButton("<html>Panier :<br> 0.00 \u20AC</html>");
		boutonPanier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		construireContrôleurBoutonPanier(boutonPanier);
		footer.add(boutonPanier);
		
		JScrollPane Main = new JScrollPane();
		frame.getContentPane().add(Main, BorderLayout.CENTER);
		
		ARTICLES = GenerationFromages.getArticles();
		this.listeCourante = Articles.listeDesignationFromages(ARTICLES.getLesFromages());
		JList listeFromages = new JList(listeCourante.toArray());
		listeFromages.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Main.setViewportView(listeFromages);
		
		construireContrôleurBoutonTous(boutonTous, listeFromages);
		construireContrôleurBoutonVache(boutonVache, listeFromages);
		construireContrôleurBoutonChèvre(boutonChevre, listeFromages);
		construireContrôleurBoutonRecherche(boutonRecherche, listeFromages);
		construireContrôleurBoutonBrebis(boutonBrebis, listeFromages);
		construireContrôleurListeFromages(listeFromages,this.boutonPanier);		
	}

	private void construireContrôleurBoutonPanier(JButton BoutonPanier) {
		BoutonPanier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenêtrePanier panier = new FenêtrePanier(BoutonPanier);
				panier.getFrame().setVisible(true);
			}
		});
	}

	private void construireContrôleurListeFromages(JList listeFromages,JButton BoutonPanier) {
		listeFromages.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					Description description = new Description((String) listeFromages.getSelectedValue(),BoutonPanier,ARTICLES);
					description.getFrame().setVisible(true);
					miseAJourBoutonPanier(BoutonPanier);
			       }
			}			
		});
	}
		
	private void construireContrôleurBoutonTous(JButton BoutonTous, JList listeFromages) {
		BoutonTous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listeCourante = Articles.listeDesignationFromages(ARTICLES.getLesFromages());
				listeFromages.setListData(listeCourante.toArray());
			}
		});
	}
	
	private void construireContrôleurBoutonVache(JButton BoutonVache, JList listeFromages) {
		BoutonVache.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listeCourante = Articles.listeDesignationFromages(ARTICLES.fromagesAuLaitDe(TypeLait.VACHE));
				listeFromages.setListData(listeCourante.toArray());
			}
		});
	}
	
	private void construireContrôleurBoutonChèvre(JButton BoutonChèvre, JList listeFromages) {
		BoutonChèvre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listeCourante = Articles.listeDesignationFromages(ARTICLES.fromagesAuLaitDe(TypeLait.CHEVRE));
				listeFromages.setListData(listeCourante.toArray());
			}
		});
	}
	
	private void construireContrôleurBoutonBrebis(JButton BoutonBrebis, JList listeFromages) {
		BoutonBrebis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listeCourante = Articles.listeDesignationFromages(ARTICLES.fromagesAuLaitDe(TypeLait.BREBIS));
				listeFromages.setListData(listeCourante.toArray());
			}
		});
	}

	private void construireContrôleurBoutonRecherche(JButton BoutonRecherche, JList listeFromages) {
		BoutonRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String recherche = textRecherche.getText();
				List<String> listeRecherchée = new ArrayList<String>();
				for (String i : listeCourante) {
					if ((i.toLowerCase()).contains(recherche.toLowerCase())) {
						listeRecherchée.add(i);
					}
				}
				listeFromages.setListData(listeRecherchée.toArray());
			}
		});
	}
	
	public static void miseAJourBoutonPanier(JButton BoutonPanier) {
		BoutonPanier.setText("Panier :\r\n "+String.format("%6.2f", PANIER.total())+" \u20AC");
	}
}