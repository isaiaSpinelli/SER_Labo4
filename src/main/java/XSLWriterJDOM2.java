import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XSLWriterJDOM2 {

    private Element xslRoot;
    private Element document;
    private Document documentType;


    public XSLWriterJDOM2(String continent, String language, String superficieMin, String superficieMax){
        System.out.println(continent + " " + language + " " + superficieMin + " " + superficieMax);
    }

    /**
     * Permet d'instancier la structure principal du fichier XSL l'en-tête root et document
     */
    public XSLWriterJDOM2() {
        try {
            xslRoot = new Element("xsl");
            xslRoot.setAttribute("xmlns2","http://www.opengis.net/kml/2.2");
            document =  new Element("Document");
            document.setAttribute("id","root_doc");
            xslRoot.addContent(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
