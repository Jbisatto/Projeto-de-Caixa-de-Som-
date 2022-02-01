package br.com.view;

import br.com.projetosdecaixasdesomautomotivo.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi") public class Inicio extends Activity {
	ImageView carro, sub;
	Button consulta, caixa;
	TextView versao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		getActionBar().setTitle("Projetos de Caixas");
		getActionBar().setBackgroundDrawable(new 
	               ColorDrawable(Color.parseColor("#696969"))); 
		PackageInfo pInfo=null;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String version = pInfo.versionName;

		int verCode = pInfo.versionCode;
		versao=(TextView)findViewById(R.id.txtversao);
		versao.setText("Versão "+version);
		carro = (ImageView) findViewById(R.id.btCarro);
		sub = (ImageView) findViewById(R.id.btSub);
		consulta = (Button) findViewById(R.id.btConsultacarro);
		caixa = (Button) findViewById(R.id.btCaixas);
		carro.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						CadastrarCarro.class);
				startActivity(intent);
			}
		});
		sub.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						CadastrarEquipanto.class);
				startActivity(intent);
			}
		});
		consulta.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						Mostrar_all.class);
				startActivityForResult(intent, 2);

			}
		});
		caixa.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						CadastrarCaixas.class);
				startActivityForResult(intent, 2);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicio, menu);
		return true;
	}

}
