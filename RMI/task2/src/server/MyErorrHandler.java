package server;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErorrHandler implements ErrorHandler{
	
	

	@Override
	public void error(SAXParseException spe) throws SAXException {
		System.err.println("Error - " + spe);
		System.err.println("line = " + spe.getLineNumber() + " col = " + spe.getColumnNumber());	
	}

	@Override
	public void fatalError(SAXParseException spe) throws SAXException {
		System.err.println("Error - " + spe);
		System.err.println("line = " + spe.getLineNumber() + " col = " + spe.getColumnNumber());
	}

	@Override
	public void warning(SAXParseException spe) throws SAXException {
		System.err.println("Error - " + spe);
		System.err.println("line = " + spe.getLineNumber() + " col = " + spe.getColumnNumber());
	}
}
