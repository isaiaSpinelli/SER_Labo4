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
    // Permet de faire la requête xPath
    private String valueRequet = "countries/element[./languages/element/name = 'French']";


    public XSLWriterJDOM2(String continent, String language, String superficieMin, String superficieMax){
        System.out.println(continent + " " + language + " " + superficieMin + " " + superficieMax);
        // element[region="Europe"] (selectionne tous les pays en Erupre)
        // countries/element[./languages/element/name = 'French'] (selectionne tous les pays qui parle le francais)

        if(!continent.equals("")){
            valueRequet = "countries/element[region='"+continent+"'";
        }
        if(!language.equals("")){
            // Si la valeur de la requete contient deja une requete
            if(!valueRequet.equals("")){
                // ajout le AND
                valueRequet += " and ";
            }
            valueRequet += "./languages/element/name = '"+language+"'" ;
        }
        if(!superficieMin.equals("")){
            // Si la valeur de la requete contient deja une requete
            if(!valueRequet.equals("")){
                // ajout le AND
                valueRequet += " and ";
            }
            valueRequet += "./area > '"+superficieMin+"'" ;
        }
        if(!superficieMax.equals("")){
            // Si la valeur de la requete contient deja une requete
            if(!valueRequet.equals("")){
                // ajout le AND
                valueRequet += " and ";
            }
        }

        valueRequet+="]";


        CreateFileXSL();



    }

    private void CreateFileXSL() {

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
            Element template = document.createElement("xsl:template");
            template.setAttribute("match", "/");
            output.appendChild(template);
            // ajoute la balise html
            Element html = document.createElement("html");
            template.appendChild(html);

            // HEAD

            // ajoute la balise head
            Element head = document.createElement("head");
            html.appendChild(head);
            // ajoute la title
            Element title = document.createElement("title");
            title.setTextContent("Cours HEIG-VD SER");
            head.appendChild(title);
            // ajoute des script
            Element script = document.createElement("script");
            script.setAttribute("src", "js/jquery-3.4.1.min.js");
            head.appendChild(script);
            Element script2 = document.createElement("script");
            script2.setAttribute("src", "js/bootstrap.min.js");
            head.appendChild(script2);
            // ajoute du link
            Element link = document.createElement("link");
            link.setAttribute("rel", "stylesheet");
            link.setAttribute("href", "css/bootstrap.min.css");
            head.appendChild(script2);

            // FIN HEAD


            // ajoute la balise body
            Element body = document.createElement("body");
            template.appendChild(body);

            // Crée et ajoute le for-each (En fonction des paramétres selectionné ) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Element forEach = document.createElement("xsl:for-each");
            // value à modifié !!!!!!!!!!!!!!!!!!!!!!!!!!!
            forEach.setAttribute("select", valueRequet);
            body.appendChild(forEach);

            // Ajout du bouton trigger modal
            Element text = document.createElement("xsl:text");
            text.setAttribute("disable-output-escaping", "no");
            text.setTextContent("test " + '<' + "!-- Button trigger modal --/> et < ");
            forEach.appendChild(text);


            // Ajout du modal





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

    public static void main(String ... args) {

        XSLWriterJDOM2 test = new XSLWriterJDOM2("","French","","");

    }


}
