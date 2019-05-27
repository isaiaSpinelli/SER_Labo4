import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class InterfaceRecherchePays extends JFrame {

    private JPanel panelRecherche = new JPanel(new FlowLayout());

    private JComboBox<String> continents = new JComboBox<>();
    private JComboBox<String> langages = new JComboBox<>();
    private JButton createXSL = new JButton("Générer XSL");
    private JTextField superficieMin = new JTextField(5);
    private JTextField superficieMax = new JTextField(5);

    public InterfaceRecherchePays(File xmlFile) {

        createXSL.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);

                // Récupèration des choix

                // Test si c'est les superficies entrez sont bien des doubles
                String superficieMinS = "";
                String superficieMaxS = "";

                try {
                    double superficieMinDouble = Double.parseDouble(superficieMin.getText());
                    superficieMinS = superficieMin.getText();
                } catch (Exception ex){
                    //System.out.println("Error : superficie min is not a double");
                }
                try {
                    double superficieMaxDouble = Double.parseDouble(superficieMax.getText());
                    superficieMaxS = superficieMax.getText();
                } catch (Exception ex){
                    //System.out.println("Error : superficie max is not a double");
                }

                // Récupèration du contient et du language
                String continentChoisi = continents.getItemAt(continents.getSelectedIndex());
                String languageChoisi = langages.getItemAt(langages.getSelectedIndex());

                // Création des fichiers XSL selon ce qui est demandé
                XSLWriterJDOM2 xslWriterJDOM2 = new XSLWriterJDOM2(continentChoisi,languageChoisi,superficieMinS,superficieMaxS);

            }

        });

        /**
         * Remplissage des listes de recherche (avec les continents et les langues parlées dans l'ordre alphabétique)
         */

        for (String continent : ParserXPath.getAllContinent()){
            continents.addItem(continent);
        }

        for (String language : ParserXPath.getAllLanguage()){
            langages.addItem(language);
        }


        setLayout(new BorderLayout());

        panelRecherche.add(new JLabel("Choix d'un continent"));
        panelRecherche.add(continents);

        panelRecherche.add(new JLabel("Choix d'une langue"));
        panelRecherche.add(langages);

        panelRecherche.add(new JLabel("Superficie minimume"));
        panelRecherche.add(superficieMin);

        panelRecherche.add(new JLabel("Superficie maximum"));
        panelRecherche.add(superficieMax);

        panelRecherche.add(createXSL);

        add(panelRecherche, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Interface de recherche de pays");


    }

    public static void main(String ... args) {

        new InterfaceRecherchePays(new File("countries.xml"));

    }
}
