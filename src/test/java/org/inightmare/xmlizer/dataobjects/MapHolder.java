package org.inightmare.xmlizer.dataobjects;


import java.util.HashMap;
import java.util.Map;

    public class MapHolder {
        private String simpleProperty;
        private Map<String, String> aMap = new HashMap<String, String>();

        /**
         * @return the simpleProperty
         */
        public String getSimpleProperty() {
            return simpleProperty;
        }

        /**
         * @param simpleProperty the simpleProperty to set
         */
        public void setSimpleProperty(String simpleProperty) {
            this.simpleProperty = simpleProperty;
        }

        /**
         * @return the aMap
         */
        public Map<String, String> getAMap() {
            return aMap;
        }

        /**
         * @param aMap the aMap to set
         */
        public void setAMap(Map<String, String> aMap) {
            this.aMap = aMap;
        }
    }