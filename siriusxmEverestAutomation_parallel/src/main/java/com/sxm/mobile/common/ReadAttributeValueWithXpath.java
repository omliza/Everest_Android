package com.sxm.mobile.common;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ReadAttributeValueWithXpath {

	public static String getAttributes(String xpathkeyword, String lookupKey, String xmlFile)  {

		/*DocumentBuilderFactory documentumentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentumentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder documentumentBuilder = documentumentBuilderFactory.newDocumentBuilder();
		Document document = documentumentBuilder.parse(xmlFile);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile(xpathkeyword + "/@" + lookupKey);
		String names = (String) expr.evaluate(document, XPathConstants.STRING);
		System.out.println("Righty attribute is : " + names);
		expr = xpath.compile(xpathkeyword + "/@" + lookupKey);
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nodes.getLength(); i++)
			System.out.println("Righty attribute is : " + nodes.item(i).getNodeValue());
		return names;*/
		
		String a =xmlFile;
		DocumentBuilderFactory xmlFact = DocumentBuilderFactory.newInstance();
		xmlFact.setNamespaceAware(false);
		DocumentBuilder builder = null;
		try {
			builder = xmlFact.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XPathFactory xpathFactory = XPathFactory.newInstance();
		String expr = xpathkeyword+"/@"+lookupKey;
		XPath xpath = xpathFactory.newXPath();
		Document doc = null;
		try {
			doc = builder.parse(new InputSource(new StringReader(a)));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imageUrl = null;
		try {
			imageUrl = (String) xpath.compile(expr).evaluate(doc ,XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("attribute value is   "+imageUrl.replace("\"", ""),true);
		return imageUrl.replace("\"", "");
	}
	}

