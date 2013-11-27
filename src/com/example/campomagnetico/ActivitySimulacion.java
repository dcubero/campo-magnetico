package com.example.campomagnetico;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import Apartados.Apartado;
import Apartados.Datos;
import Apartados.Medida;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class ActivitySimulacion extends Activity {
	
	protected Intent intent;
	/** Un seekBar por apartado*/
	protected SeekBar seekbarA;
	protected SeekBar seekbarB;
	protected SeekBar seekbarC;

	/** Un Textview por seekbar */
	protected TextView seektextA;
	protected TextView seektextB;
	protected TextView seektextC;
	
	/** Un Textview por apartado*/
	protected TextView tesla1;
	protected TextView tesla2;
	protected TextView tesla3;
	
	/** Un boton por apartado */
	protected Button tomarMedA;
	protected Button tomarMedB;
	protected Button tomarMedC;
	
	/** Un Array de Medidas por apartado */
	protected Datos datosA = new Datos(1);
	protected Datos datosB = new Datos(2);
	protected Datos datosC = new Datos(3);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulacion);
		
		//Tabs
		Resources res = getResources();
		 
		TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
		tabs.setup();
		 
		TabHost.TabSpec spec=tabs.newTabSpec("tab1");
		spec.setContent(R.id.tabConductorA);
		spec.setIndicator("Conductor A", res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);
		 
		spec=tabs.newTabSpec("tab2");
		spec.setContent(R.id.tabConductorB);
		spec.setIndicator("Conductor B", res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);
		
		spec=tabs.newTabSpec("tab3");
		spec.setContent(R.id.tabConductorC);
		spec.setIndicator("Conductor C", res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);
		 
		tabs.setCurrentTab(0);
		//Fin Tabs
		
		//Inicializacion de teslamometros
		final Apartado apartado1 = new Apartado(1);
		final Apartado apartado2 = new Apartado(2);
		final Apartado apartado3 = new Apartado(3);
		
		//Fin Teslamometros
		
		tomarMedA = (Button) findViewById(R.id.ButtonA);
		tomarMedB = (Button) findViewById(R.id.buttonB);
		tomarMedC = (Button) findViewById(R.id.buttonC);
		
		//Botones
			tomarMedA.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					int progress = seekbarA.getProgress();
					Medida medA = new Medida(progress * 5, apartado1.getB(progress*5), progress * 5 * 2);
					datosA.add_dato(medA);
				}
			});
				
			tomarMedB.setOnClickListener(new OnClickListener() {
					
				@Override
				public void onClick(View arg0) {
					int progress = seekbarB.getProgress();
					Medida medB = new Medida(progress * 5, apartado1.getB(progress*5), progress * 5 * 2);
					datosB.add_dato(medB);
				}
			});

			tomarMedC.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					int progress = seekbarC.getProgress();
					Medida medC = new Medida(progress * 5, apartado1.getB(progress*5), progress * 5 * 2);
					datosC.add_dato(medC);
				}
			});
		//Fin Botones
		
		
		//Seekbars y sus textview asociados

		seekbarA = (SeekBar) findViewById(R.id.seekBarA);
		seektextA = (TextView) findViewById(R.id.textSeekA);
		tesla1 = (TextView) findViewById(R.id.Tesla1);
		seekbarA.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			/**
			  * Evento encargado del control del estado del seekbar
			  */
			 public void onProgressChanged(SeekBar seekBar, int progress,
			    		boolean fromUser) {
					
			    	// TODO Añadir una variable global que tenga el contenido?
					seektextA.setText("" + (progress*5) + " mA");
					if(progress == 0){
						tesla1.setText("0 (T)");
					}else{
						tesla1.setText(""+ apartado1.getB(progress*5) + " (mT)");
					}
			    }

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// Nada que hacer
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Nada que hacer
				
			}
			
		});
		
		seekbarB = (SeekBar) findViewById(R.id.seekBarB);
		seektextB = (TextView) findViewById(R.id.textSeekB);
		tesla2 = (TextView) findViewById(R.id.Tesla2);
		seekbarB.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			/**
			  * Evento encargado del control del estado del seekbar
			  */
			 public void onProgressChanged(SeekBar seekBar, int progress,
			    		boolean fromUser) {
					BigDecimal prog = new BigDecimal(-0.04 + 0.0025 * progress);
					prog = prog.setScale(5, RoundingMode.HALF_UP);
			    	// TODO Añadir una variable global que tenga el contenido?
					seektextB.setText("" + (prog.multiply(new BigDecimal(100)).doubleValue()) + " cm");
					tesla2.setText(""+ apartado2.getB(prog.doubleValue()) + " (mT)");
			    }

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// Nada que hacer
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Nada que hacer
				
			}
			
		});
		
		seekbarC = (SeekBar) findViewById(R.id.seekBarC);
		seektextC = (TextView) findViewById(R.id.textSeekC);
		tesla3 = (TextView) findViewById(R.id.Tesla3);
		seekbarC.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			/**
			  * Evento encargado del control del estado del seekbar
			  */
			 public void onProgressChanged(SeekBar seekBar, int progress,
			    		boolean fromUser) {
					BigDecimal prog = new BigDecimal(-0.04 + 0.0025 * progress);
					prog = prog.setScale(5, RoundingMode.HALF_UP);
			    	// TODO Añadir una variable global que tenga el contenido?
					seektextC.setText("" + (prog.multiply(new BigDecimal(100)).doubleValue()) + " cm");
					tesla3.setText(""+ apartado3.getB(prog.doubleValue()) + " (mT)");
			    }

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// Nada que hacer
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Nada que hacer
				
			}
			
		});
		
		//Fin SeekViews		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_simulacion, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_inicio:
	        	onBackPressed();
	        	
	            return true;
	        case R.id.action_acercaDe:
	        	intent = new Intent("acerca_de");
	        	startActivity(intent);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	 
	 

}
