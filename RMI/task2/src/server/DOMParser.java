package server;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import interfaces.Participant;


public class DOMParser {
	
	public static Document parse(String file)
	{
		Schema schema = null;
		try {
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory sf = SchemaFactory.newInstance(language);
		schema = sf.newSchema(new File("Schema.xsd"));
		} catch (SAXException e1) {e1.printStackTrace();}
		
		Document doc = null;
		DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
		xmlFactory.setSchema(schema);
		
		xmlFactory.setValidating(false);
		xmlFactory.setNamespaceAware(true);
		xmlFactory.setIgnoringElementContentWhitespace(true);
		ErrorHandler handler = new MyErorrHandler();
		DocumentBuilder xml;
			
				try {
					xml = xmlFactory.newDocumentBuilder();
					xml.setErrorHandler(handler);
					doc = xml.parse(new File(file));
				} catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}
		return doc;	
	}
	
	public static void transformDocToData(Document doc, DataParticipant dp)
	{
		dp.clear();
		Element rootElement = doc.getDocumentElement();
		NodeList childs = rootElement.getChildNodes();
		for (int i=0;i<childs.getLength();i++)
		{
			Element conferee = (Element)childs.item(i); 
			Element name = (Element) conferee.getFirstChild();
			String strName = ((Text)name.getFirstChild()).getData().trim();
			
			System.out.println(name.getTagName() + " - " + strName + "\n");
			
			Element surname = (Element) name.getNextSibling();
			String strSurname = ((Text)surname.getFirstChild()).getData().trim();
			
			Element organization = (Element) surname.getNextSibling();
			String strOrg = ((Text)organization.getFirstChild()).getData().trim();
			
			Element report = (Element) organization.getNextSibling();
			String strReport = ((Text)report.getFirstChild()).getData().trim();
			
			Element email = (Element) report.getNextSibling();
			String strEmail = ((Text)email.getFirstChild()).getData().trim();
			
			dp.addParticipant(new Participant(strName,strSurname,strOrg,strReport,strEmail));
		}
	}
	
	public static Document transformDataToDoc(DataParticipant dp)
	{
		Document doc;
		try {
		doc = createDocument();
		ArrayList<Participant> arr = dp.getArr();
		Element rootElement = doc.createElement("RegisteredConferees");
		for(int i=0;i<arr.size();i++)
		{
			Element conferee = doc.createElement("Conferee");
			
			Class cls = arr.get(i).getClass();
			Field [] f = cls.getDeclaredFields();
			for (Field field:f)
			{
				if (!Modifier.isStatic(field.getModifiers()))
				{
				field.setAccessible(true);
				Element el = doc.createElement(field.getName());
				el.setTextContent(field.get(arr.get(i)).toString());
				conferee.appendChild(el);
				}
			}
			rootElement.appendChild(conferee);
		}
		doc.appendChild(rootElement);
		return doc;
		} catch (ParserConfigurationException | DOMException | IllegalArgumentException | IllegalAccessException e) {e.printStackTrace();}
				
		return null;
		
		
	}
	
	public static Document createDocument() throws ParserConfigurationException
	{
		DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder xml = xmlFactory.newDocumentBuilder();
		return xml.newDocument();	
	}
	
	
	
	

}
