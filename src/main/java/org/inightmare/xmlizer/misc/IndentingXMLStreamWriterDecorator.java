/*
 * Copyright 2013 giedrius.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.inightmare.xmlizer.misc;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author giedrius
 */
public class IndentingXMLStreamWriterDecorator implements XMLStreamWriter {

    private XMLStreamWriter decorable;
    
    private int numSpaces = 4;
    
    private int indentationLevel;
    
    public IndentingXMLStreamWriterDecorator(XMLStreamWriter decorable) {
        this.decorable = decorable;
    }
    
    public void setNumSpaces(int numSpaces) {
        this.numSpaces = numSpaces;
    }
    
    public void writeStartElement(String name) throws XMLStreamException {
        indent();
        decorable.writeStartElement(name);
        indentationLevel++;
    }

    public void writeStartElement(String string, String string1) throws XMLStreamException {
        indent();
        decorable.writeStartElement(string, string1);
        indentationLevel++;
    }

    public void writeStartElement(String string, String string1, String string2) throws XMLStreamException {
        indent();
        decorable.writeStartElement(string, string1, string2);
        indentationLevel++;
    }

    public void writeEmptyElement(String string, String string1) throws XMLStreamException {
        indent();
        decorable.writeEmptyElement(string, string1);
        indentationLevel++;
    }

    public void writeEmptyElement(String string, String string1, String string2) throws XMLStreamException {
        indent();
        decorable.writeEmptyElement(string, string1, string2);
        indentationLevel++;
    }

    public void writeEmptyElement(String string) throws XMLStreamException {
        indent();
        decorable.writeEmptyElement(string);
        indentationLevel++;
    }

    public void writeEndElement() throws XMLStreamException {
        indent();
        decorable.writeEndElement();
        indentationLevel--;
    }

    public void writeEndDocument() throws XMLStreamException {
        decorable.writeEndDocument();
    }

    public void close() throws XMLStreamException {
        decorable.close();
    }

    public void flush() throws XMLStreamException {
        decorable.flush();
    }

    public void writeAttribute(String string, String string1) throws XMLStreamException {
        decorable.writeAttribute(string, string1);
    }

    public void writeAttribute(String string, String string1, String string2, String string3) throws XMLStreamException {
        decorable.writeAttribute(string, string1, string2, string3);
    }

    public void writeAttribute(String string, String string1, String string2) throws XMLStreamException {
        decorable.writeAttribute(string, string1, string2);
    }

    public void writeNamespace(String string, String string1) throws XMLStreamException {
        decorable.writeNamespace(string, string1);
    }

    public void writeDefaultNamespace(String string) throws XMLStreamException {
        decorable.writeDefaultNamespace(string);
    }

    public void writeComment(String string) throws XMLStreamException {
        decorable.writeComment(string);
    }

    public void writeProcessingInstruction(String string) throws XMLStreamException {
        decorable.writeProcessingInstruction(string);
    }

    public void writeProcessingInstruction(String string, String string1) throws XMLStreamException {
        decorable.writeProcessingInstruction(string, string1);
    }

    public void writeCData(String string) throws XMLStreamException {
        decorable.writeCData(string);
    }

    public void writeDTD(String string) throws XMLStreamException {
        decorable.writeDTD(string);
    }

    public void writeEntityRef(String string) throws XMLStreamException {
        decorable.writeEntityRef(string);
    }

    public void writeStartDocument() throws XMLStreamException {
        decorable.writeStartDocument();
    }

    public void writeStartDocument(String string) throws XMLStreamException {
        decorable.writeStartDocument(string);
    }

    public void writeStartDocument(String string, String string1) throws XMLStreamException {
        decorable.writeStartDocument(string, string1);
    }

    public void writeCharacters(String string) throws XMLStreamException {
        decorable.writeCharacters(string);
    }

    public void writeCharacters(char[] chars, int i, int i1) throws XMLStreamException {
        decorable.writeCharacters(chars, i, i1);
    }

    public String getPrefix(String string) throws XMLStreamException {
        return decorable.getPrefix(string);
    }

    public void setPrefix(String string, String string1) throws XMLStreamException {
        decorable.setPrefix(string, string1);
    }

    public void setDefaultNamespace(String string) throws XMLStreamException {
        decorable.setDefaultNamespace(string);
    }

    public void setNamespaceContext(NamespaceContext nc) throws XMLStreamException {
        decorable.setNamespaceContext(nc);
    }

    public NamespaceContext getNamespaceContext() {
        return decorable.getNamespaceContext();
    }

    public Object getProperty(String string) throws IllegalArgumentException {
        return decorable.getProperty(string);
    }
    
    private void indent() throws XMLStreamException {
        int numSpacesToWrite = indentationLevel * numSpaces;
     
        newLine();
        
        for (int i = 0; i < numSpacesToWrite; i++) {
            decorable.writeCharacters(" ");
        }
    }
    
    private void newLine() throws XMLStreamException {
        decorable.writeCharacters("\n");
    }
}
