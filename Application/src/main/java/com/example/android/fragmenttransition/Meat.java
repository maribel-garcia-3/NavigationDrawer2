/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmenttransition;

import com.example.android.navigationdrawer.R;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * This represents a sample data.mm
 */
public class Meat {

    public int resourceId;
    public String title;

    public Meat(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public static final Meat[] MEATS = {
            new Meat(R.drawable.p1, "First"),
            new Meat(R.drawable.p2, "Second"),
            new Meat(R.drawable.p3, "Third"),
            new Meat(R.drawable.p4, "Fourth"),
            new Meat(R.drawable.p5, "Fifth"),
            new Meat(R.drawable.p6, "Sixth"),
            new Meat(R.drawable.p7, "Seventh"),
            new Meat(R.drawable.p8, "Eighth"),
            new Meat(R.drawable.p9, "Ninth"),
            new Meat(R.drawable.p10, "Tenth"),
            new Meat(R.drawable.p11, "Eleventh"),
    };


    public class ParsedCategoryDataSet {

        private String id = null;
        private String name = null;

        public String getId() {
            return id;
        }
        public void setId(String extractedString) {
            this.id = extractedString;
        }

        public String getName() {
            return name;
        }
        public void setName(String extractedString) {
            this.name = extractedString;
        }

        public String toString(){
            return "name = " + this.name;
        }
    }

    public class CategoryHandler extends DefaultHandler{

        public CategoryHandler() {
            super();
            this.myParsedCategoryDataSet = new Vector<ParsedCategoryDataSet>();
        }

        // Variables de control para saber cuando estamos en el interior de cada etiqueta
        @SuppressWarnings("unused")
        private boolean in_category = false;
        private boolean in_id = false;
        private boolean in_name = false;

        // En esta variable guardamos el texto encontrado entre las etiquetas
        StringBuilder builder;

        // Aquí guardamos cada objeto categoria
        private ParsedCategoryDataSet DataSet;

        // Vector donde se guardaran todas las categorías encontradas
        private Vector<ParsedCategoryDataSet> myParsedCategoryDataSet;

        public Vector<ParsedCategoryDataSet> getParsedCategoryDataSets() {
            return this.myParsedCategoryDataSet;
        }

        public Vector<ParsedCategoryDataSet> getParsedData() {
            return this.myParsedCategoryDataSet;
        }

        @Override
        public void startDocument() throws SAXException {
            // Comenzamos a leer el fichero xml, creamos el vector donde se guardarán las categorías
            this.myParsedCategoryDataSet = new Vector<ParsedCategoryDataSet>();
        }

        @Override
        public void endDocument() throws SAXException {
            // Ha terminado de leer el fichero, en este paso no hacemos nada
        }

        @Override
        public void startElement(String namespaceURI, String localName,
                                 String qName, Attributes atts) throws SAXException {
            if (localName.equals("category")) {
                // Ha encontrado la etiqueta principal de cada elemento "category"
                // Creamos un nuevo objeto categoría donde iremos guardando los datos
                this.in_category = true;
                DataSet = new ParsedCategoryDataSet();
            }else if (localName.equals("id")) {
                // Estamos dentro de la etiqueta "id", creamos el StringBuilder que utilizaremos
                // en el método characters para guardar el contenido
                this.in_id = true;
                builder = new StringBuilder();
            }else if (localName.equals("name")) {
                // Estamos dentro de la etiqueta "name", creamos el StringBuilder que utilizaremos
                // en el método characters para guardar el contenido
                this.in_name = true;
                builder = new StringBuilder();
            }
        }

        @Override
        public void endElement(String namespaceURI, String localName, String qName)
                throws SAXException {
            if (localName.equals("category")) {
                // Hemos llegado al final de la etiqueta principal de cada elemento "category"
                // Añadimos al vector el elemento leído
                this.in_category = false;
                myParsedCategoryDataSet.add(DataSet);
            }else if (localName.equals("id")) {
                // Ha encontrado la etiqueta de cierre de "id"
                this.in_id = false;
            }else if (localName.equals("name")) {
                // Ha encontrado la etiqueta de cierre de "name"
                this.in_name = false;
            }
        }

        @Override
        public void characters(char ch[], int start, int length) {

            // Si estamos dentro de la etiqueta "id"
            if(this.in_id){
                if (builder!=null) {
                    for (int i=start; i<start+length; i++) {
                        // Añadimos al StringBuilder (definido al encontrar el comienzo de la etiqueta "id")
                        // lo que haya entre las etiquetas de inicio y fin
                        builder.append(ch[i]);
                    }
                }
                // Lo asignamos al "id" del objeto categoría (DataSet)
                DataSet.setId(builder.toString());
            }

            // Si estamos dentro de la etiqueta "id"
            if(this.in_name){
                if (builder!=null) {
                    for (int i=start; i<start+length; i++) {
                        // Añadimos al StringBuilder (definido al encontrar el comienzo de la etiqueta "name")
                        // lo que haya entre las etiquetas de inicio y fin
                        builder.append(ch[i]);
                    }
                }
                // Lo asignamos al "name" del objeto categoría (DataSet)
                DataSet.setName(builder.toString());
            }

        }
    }

}
