/*
 *  Copyright 2013 Giedrius 'inightmare' Graževičius
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.inightmare.xmlizer.misc;

import java.util.Stack;

/**
 *
 * @author giedrius
 */
public class UnmarshallerStack {

    private Stack<StackElement> stack = new Stack<StackElement>();
    
    public void push(Object value) {
        stack.push(new StackElement(value));
    }
    
    public void push(String propertyName, Class propertyType) {
        stack.push(new StackElement(propertyName, propertyType));
    }
    
    public void push(String propertyName, Class propertyType, Object propertyValue) {
        stack.push(new StackElement(propertyName, propertyType, propertyValue));
    }
    
    public StackElement pop() {
        return stack.pop();
    }
    
    public StackElement peek() {
        return stack.peek();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public int size() {
        return stack.size();
    }
    
    public class StackElement {

        private String propertyName;
        private Class<?> propertyType;
        private Object propertyValue;
        private PropertyInfo propertyInfo;

        public StackElement() {
        }

        public StackElement(Object propertyValue) {
            this.propertyValue = propertyValue;
            this.propertyType = propertyValue.getClass();
        }

        public StackElement(String propertyName, Class propertyType) {
            this.propertyName = propertyName;
            this.propertyType = propertyType;
        }

        public StackElement(String propertyName, Class propertyType, Object propertyValue) {
            this.propertyName = propertyName;
            this.propertyType = propertyType;
            this.propertyValue = propertyValue;
        }

        /**
         * @return the propertyName
         */
        public String getPropertyName() {
            return propertyName;
        }

        /**
         * @return the propertyType
         */
        public Class<?> getPropertyType() {
            return propertyType;
        }

        /**
         * @return the propertyValue
         */
        public Object getPropertyValue() {
            return propertyValue;
        }
        
        public void setPropertyValue(Object value) {
            this.propertyValue = value;
        }

        /**
         * @return the propertyInfo
         */
        public PropertyInfo getPropertyInfo() {
            return propertyInfo;
        }

        /**
         * @param propertyInfo the propertyInfo to set
         */
        public void setPropertyInfo(PropertyInfo propertyInfo) {
            this.propertyInfo = propertyInfo;
        }
    }
}
