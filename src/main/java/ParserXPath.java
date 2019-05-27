import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

public class ParserXPath {

    /** Permet de récupèrer tous les continent différents dans un fichier XML
     * @return tous les continent différents
     */
    public static ArrayList<String> getAllContinent(){
        ArrayList<String> listContinent = new ArrayList<String>();
        listContinent.add("");

        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                    new InputSource("./src/main/resources/countries.xml"));


            XPath xpath = XPathFactory.newInstance().newXPath(); // //employee/name[text()='old']
            NodeList nodes = (NodeList) xpath.evaluate("/countries/element/region", doc,
                    XPathConstants.NODESET);

            // Pour tous les continents
            for (int idx = 0; idx < nodes.getLength(); idx++) {
                if (!listContinent.contains(nodes.item(idx).getTextContent())) {

                    listContinent.add(nodes.item(idx).getTextContent() );
                }
            }

        listContinent.sort(String::compareToIgnoreCase);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }


        return listContinent;

    }

    /** Permet de récupèrer toutes les langues différentes dans un fichier xml
     * @return  Toutes les langues différentes
     */
    public static ArrayList<String> getAllLanguage(){
        ArrayList<String> listLanguage = new ArrayList<String>();
        listLanguage.add("");

        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                    new InputSource("./src/main/resources/countries.xml"));


            XPath xpath = XPathFactory.newInstance().newXPath(); // //employee/name[text()='old']
            NodeList nodes = (NodeList) xpath.evaluate("/countries/element/languages/element/name", doc,
                    XPathConstants.NODESET);

            // Pour toutes les langues
            for (int idx = 0; idx < nodes.getLength(); idx++) {
                if (!listLanguage.contains(nodes.item(idx).getTextContent())) {

                    listLanguage.add(nodes.item(idx).getTextContent() );
                }
            }

            listLanguage.sort(String::compareToIgnoreCase);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }


        return listLanguage;

    }

}
