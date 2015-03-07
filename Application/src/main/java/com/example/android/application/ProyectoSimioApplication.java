package com.proyectosimio.proyectosimio.application;

import android.app.Application;
import android.widget.ArrayAdapter;

import com.proyectosimio.proyectosimio.modelo.Publicacion;
import com.proyectosimio.proyectosimio.utils.AsyncConector;

public class ProyectoSimioApplication extends Application {
	private final static String URL = "http://feeds.esmas.com/data-feeds-esmas/canal5/35837_casting.xml";

	public void updatePublicaciones(ArrayAdapter<Publicacion> lvAdapter) {

		AsyncConector conector = new AsyncConector(lvAdapter, URL);
		conector.execute();
	}

}
