/*
 *  Copyright 2007, Plutext Pty Ltd.
 *   
 *  This file is part of Docx4J.

    Docx4J is free software: you can redistribute it and/or modify
    it under the terms of version 3 of the GNU General Public License 
    as published by the Free Software Foundation.

    Docx4J is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License   
    along with Docx4J.  If not, see <http://www.gnu.org/licenses/>.
    
 */

package org.docx4j.openpackaging.parts.WordprocessingML;



import org.apache.log4j.Logger;

import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.relationships.Namespaces;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBElement; 

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MainDocumentPart extends DocumentPart  {
	
	private static Logger log = Logger.getLogger(MainDocumentPart.class);
	
	
	
//	org.docx4j.jaxb.document.Document wmlDocumentEl;
	
	
	public MainDocumentPart(PartName partName) throws InvalidFormatException {
		super(partName);
		
		// Used if this Part is added to [Content_Types].xml 
		setContentType(new  org.docx4j.openpackaging.contenttype.ContentType( 
				org.docx4j.openpackaging.contenttype.ContentTypes.WORDPROCESSINGML_DOCUMENT));

		// Used when this Part is added to a rels 
		setRelationshipType(Namespaces.DOCUMENT);
		
	}
		
	
//	public org.docx4j.jaxb.document.Document getDocumentObj() {
//		return wmlDocumentEl;
//	}
//	public void setDocumentObj(org.docx4j.jaxb.document.Document wmlDocumentEl) {
//		this.wmlDocumentEl = wmlDocumentEl;
//	}
		
	
	/* Create a Java object tree from the XML document 
	 * which looks something like:
	 * 
	 * <w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main" ..>
		 * 		<w:body>
	 *			<w:p ..>
	 */
//	private void unmarshall(Document doc) {
//		
//		try {
//		    
//		    org.dom4j.io.DOMWriter writer = new org.dom4j.io.DOMWriter();
//		    org.w3c.dom.Document w3cDoc = writer.write(doc);
//		    
//			JAXBContext jc = JAXBContext.newInstance("org.docx4j.jaxb.document");
//			Unmarshaller u = jc.createUnmarshaller();
//			
//			// Will throw javax.xml.bind.UnmarshalException
//			// if an unexpected element is encountered.
//			u.setEventHandler(new org.docx4j.JaxbValidationEventHandler());
//			
//			JAXBElement<?> root = (JAXBElement<?>)u.unmarshal(w3cDoc);
//			
//			wmlDocumentEl = (org.docx4j.jaxb.document.Document)root.getValue();
//			
//			System.out.println( "unmarshalled " );
//			
//						
//
//		} catch (Exception e ) {
//			e.printStackTrace();
//		}
//		
//	}
	
    /**
     * Unmarshal XML data from the specified InputStream and return the 
     * resulting content tree.  Validation event location information may
     * be incomplete when using this form of the unmarshal API.
     *
     * <p>
     * Implements <a href="#unmarshalGlobal">Unmarshal Global Root Element</a>.
     * 
     * @param is the InputStream to unmarshal XML data from
     * @return the newly created root object of the java content tree 
     *
     * @throws JAXBException 
     *     If any unexpected errors occur while unmarshalling
     */
    public Object unmarshal( java.io.InputStream is ) throws JAXBException {
    	
		try {
		    		    
			Unmarshaller u = jc.createUnmarshaller();
			
			u.setEventHandler(new org.docx4j.JaxbValidationEventHandler());
			
			JAXBElement<?> root = (JAXBElement<?>)u.unmarshal( is );
			
//			wmlDocumentEl = (org.docx4j.jaxb.document.Document)root.getValue();
			jaxbElement = (org.docx4j.jaxb.document.Document)root.getValue();
			
			System.out.println( "unmarshalled " );									

		} catch (Exception e ) {
			e.printStackTrace();
		}
    	
		return jaxbElement;
    	
    }



	private void debugPrint( Document coreDoc) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
		    XMLWriter writer = new XMLWriter( System.out, format );
		    writer.write( coreDoc );
		} catch (Exception e ) {
			e.printStackTrace();
		}	    
	}
	
	
	
	
//	/* Create an XML document from the Java object tree
//	 * 
//	 */
//	public Document marshall() {
//		
//		// create a new Document with root element
//		// <w:document 
//		//		xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main" 
//		//		xmlns:ve="http://schemas.openxmlformats.org/markup-compatibility/2006" 
//		//		xmlns:o="urn:schemas-microsoft-com:office:office" 
//		//		xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships" 
//		//		xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math" 
//		//		xmlns:v="urn:schemas-microsoft-com:vml" 
//		//		xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing" 
//		//		xmlns:w10="urn:schemas-microsoft-com:office:word" 
//		//		xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml">
//		// 		<w:body>
//		//			<w:p ..>
//
//		try {
//			JAXBContext jc = JAXBContext.newInstance("org.docx4j.jaxb.document");
//			Marshaller marshaller=jc.createMarshaller();
//			
//			javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			dbf.setNamespaceAware(true);
//			org.w3c.dom.Document doc = dbf.newDocumentBuilder().newDocument();
//
//			marshaller.marshal(wmlDocumentEl, doc);
//			
//			// Now convert the W3C document to a dom4j document
//			org.dom4j.io.DOMReader xmlReader = new org.dom4j.io.DOMReader();
//			
//			/*  Should be able to do ..
//			 * 
//			 *  dom4j has DocumentResult that extends Result, so you can do:
//
//				DocumentResult dr = new DocumentResult();
//				marshaller.marshal( object, dr );
//				o = dr.getDocument();
//
//			 * 
//			 * 
//			 */
//		    return xmlReader.read(doc);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	
	
}

	
