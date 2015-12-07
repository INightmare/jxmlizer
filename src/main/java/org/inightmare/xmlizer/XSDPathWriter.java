package org.inightmare.xmlizer;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Used to write schema path containing attributes.
 * 
 * @author klebedev
 * @since 2015-12
 */
public interface XSDPathWriter {
    
    void writePath(XMLStreamWriter writer, Class<?> type) throws XMLStreamException;
    
}
