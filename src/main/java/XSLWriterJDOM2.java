import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XSLWriterJDOM2 {


    // Permet de faire la requête xPath
    private String valueRequet = "";


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
            valueRequet += "'"+superficieMax+"'"+">"+"./area" ;
			
        }
        if(!valueRequet.equals("")){
            valueRequet+="]";
        }
        else{
            valueRequet = "countries/element";
        }



        CreateFileXSL();

    }

    private void CreateFileXSL() {

        try {

            Document document =    DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                    new InputSource("./src/main/resources/template.xsl"));
            Element stylesheet = document.getDocumentElement();

            //aller dans la boucle forech du template xsl
            Node html =  stylesheet.getElementsByTagName("html").item(0);
            Element body = (Element) ((Element) html).getElementsByTagName("body").item(0);
            Element ForEach = (Element) body.getElementsByTagName("xsl:for-each").item(0);

            //changer l' attribut select
            ForEach.setAttribute("select",valueRequet);

            // Ecriture dans le fichier XSL
            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(new File("./src/main/resources/countries.xsl"));
            StreamResult result = new StreamResult(writer);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

}
