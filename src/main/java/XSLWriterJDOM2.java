import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XSLWriterJDOM2 {

    private Element xslRoot;
    private Element document;
    private Document documentType;


    public XSLWriterJDOM2(String continent, String language, String superficieMin, String superficieMax){
        System.out.println(continent + " " + language + " " + superficieMin + " " + superficieMax);
        // //element[region="Europe"] (selectionne tous les pays en Erupre)
    }

    /**
     * Permet d'instancier la structure principal du fichier XSL l'en-tête root et document
     */
    public XSLWriterJDOM2() {
    }

    public static void main(String ... args) {

        try {


            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();


            Element racine = document.createElement("xsl:stylesheet");
            racine.setAttribute("version", "1.0");
            racine.setAttribute("xmlns:xsl", "http://www.w3.org/1999/XSL/Transform");

            // Crée et ajoute le xsl output
            Element output = document.createElement("xsl:output");
            output.setAttribute("method", "html");
            output.setAttribute("encoding", "UTF-8");
            output.setAttribute("indent", "yes");
            racine.appendChild(output);

            // Crée et ajoute le template


            // Ecriture de la racine dans le document
            document.appendChild(racine);

            // Ecriture dans le fichier XSL
            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(new File("./src/main/resources/countries.xsl"));
            StreamResult result = new StreamResult(writer);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
