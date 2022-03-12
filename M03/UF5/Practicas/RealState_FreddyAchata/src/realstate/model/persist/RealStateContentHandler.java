/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realstate.model.persist;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import realstate.model.Adress;
import realstate.model.Estate;
import realstate.model.RealEstate;



/**
 *
 * @author freddySoft
 */
public class RealStateContentHandler extends DefaultHandler{
    private RealEstate realestate;
    private Estate estate;
    private Adress adress;
    private String character;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("The document has started");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("The document has ended");
    }

    @Override
    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
       character = "";
       if (qName.equalsIgnoreCase("Estate")) { 
           estate = new Estate();
           estate.setType(attributes.getValue("type"));
       }
       if (qName.equalsIgnoreCase("RealState")) { 
           realestate = new RealEstate();
       }
       if (qName.equalsIgnoreCase("Address")) { 
           adress = new Adress();
       }
   }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
     if (qName.equalsIgnoreCase("Id")) {
         estate.setId(character);
    } else if (qName.equalsIgnoreCase("Surface")) {
        estate.setSurface(Double.parseDouble(character));
    } else if (qName.equalsIgnoreCase("Condition")) {
         estate.setCondition(character);
    }else if (qName.equalsIgnoreCase("Certificate")) {
         estate.setCertificate(Boolean.parseBoolean(character));
    } else if (qName.equalsIgnoreCase("Adress")) {
         estate.setAdress(new Adress());
    }else if (qName.equalsIgnoreCase("Price")) {
         estate.setPrice(Double.parseDouble(character));
    }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        character= new String (ch, start, length);
    }


}
