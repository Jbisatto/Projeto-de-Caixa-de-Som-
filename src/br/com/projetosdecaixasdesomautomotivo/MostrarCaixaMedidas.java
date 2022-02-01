package br.com.projetosdecaixasdesomautomotivo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

@SuppressLint("NewApi") public class MostrarCaixaMedidas extends Activity {
	TextView  A,B,C,D,E;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_caixa_medidas);
				getActionBar().setTitle("Mostrar Caixa com suas Medidas");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#696969")));
		A= (TextView)findViewById(R.id.textViewA);
		B= (TextView)findViewById(R.id.textViewB);
		C= (TextView)findViewById(R.id.textViewC);
		D= (TextView)findViewById(R.id.textViewD);
		E= (TextView)findViewById(R.id.textViewE);
		A.setText(getIntent().getExtras().getString("A")+" cm");
		B.setText(getIntent().getExtras().getString("B")+" cm");
		C.setText(getIntent().getExtras().getString("C")+" cm");
		D.setText(getIntent().getExtras().getString("D")+" cm");
		E.setText(getIntent().getExtras().getString("E"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_caixa_medidas, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

}
