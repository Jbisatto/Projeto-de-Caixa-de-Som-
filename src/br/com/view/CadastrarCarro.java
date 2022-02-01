package br.com.view;

import java.util.ArrayList;
import java.util.List;

import br.com.banco.DB;
import br.com.beans.Veiculo;
import br.com.dao.VeiculoDAO;
import br.com.projetosdecaixasdesomautomotivo.R;
import br.com.projetosdecaixasdesomautomotivo.R.layout;
import br.com.projetosdecaixasdesomautomotivo.R.menu;
import android.os.Bundle;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CadastrarCarro extends Activity {
	RadioButton radio0;
	RadioButton radio1;
	RadioButton radio2;
	RadioButton radio3, radio4;
	Button confirma, cancela;
	EditText nome, largura, comprimento, altura;
	TextView edtINF;
	Veiculo veiculo;
	VeiculoDAO veiculoDAO;
	int chave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_carro);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(new 
	               ColorDrawable(Color.parseColor("#696969")));
		veiculo = new Veiculo();
		veiculoDAO = new VeiculoDAO(getApplicationContext());
		radio0 = (RadioButton) findViewById(R.id.radio0);
		radio1 = (RadioButton) findViewById(R.id.radio1);
		radio2 = (RadioButton) findViewById(R.id.radio2);
		radio3 = (RadioButton) findViewById(R.id.radio3);
		radio4 = (RadioButton) findViewById(R.id.radio4);
		nome = (EditText) findViewById(R.id.edtNome);
		edtINF = (TextView) findViewById(R.id.txtCat);
		largura = (EditText) findViewById(R.id.edtlargura);
		comprimento = (EditText) findViewById(R.id.edtComprimento);
		altura = (EditText) findViewById(R.id.edtAltura);
		confirma = (Button) findViewById(R.id.btConfirmar);
		cancela = (Button) findViewById(R.id.btCancelar);
		chave = 0;
		radio0.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				radio1.setChecked(false);
				radio2.setChecked(false);
				radio3.setChecked(false);
				radio4.setChecked(false);
				veiculo.setCategoria_veiculo('N');
				return false;
			}
		});
		radio1.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				radio0.setChecked(false);
				radio2.setChecked(false);
				radio3.setChecked(false);
				radio4.setChecked(false);
				veiculo.setCategoria_veiculo('C');
				return false;
			}
		});
		radio2.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				radio1.setChecked(false);
				radio0.setChecked(false);
				radio3.setChecked(false);
				radio4.setChecked(false);
				veiculo.setCategoria_veiculo('S');
				return false;
			}
		});
		radio3.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				radio1.setChecked(false);
				radio2.setChecked(false);
				radio0.setChecked(false);
				radio4.setChecked(false);
				veiculo.setCategoria_veiculo('P');
				return false;
			}
		});
		radio4.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				radio1.setChecked(false);
				radio2.setChecked(false);
				radio0.setChecked(false);
				radio3.setChecked(false);
				veiculo.setCategoria_veiculo('A');
				return false;
			}
		});
		confirma.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (nome.getText().toString().trim().isEmpty()) {
					nome.setError("Informe o nome do Veículo!!!");
				} else if (largura.getText().toString().trim().isEmpty()) {
					largura.setError("Informe a largura máxima!!!");
				} else if (comprimento.getText().toString().trim().isEmpty()) {
					comprimento.setError("Informe o comprimento máximo!!!");
				} else if (altura.getText().toString().trim().isEmpty()) {
					altura.setError("Informe a altura máxima!!!");
				} else if (!(radio0.isChecked() || radio1.isChecked()
						|| radio2.isChecked() || radio3.isChecked() || radio4
						.isChecked())) {
					edtINF.setError("Marque uma opção!!!");
				} else {
					Log.d("confirma", "Tudo ok");
					veiculo.setNome_veiculo(nome.getText().toString());
					veiculo.setLargura(Double.parseDouble(largura.getText()
							.toString()));
					veiculo.setComprimento(Double.parseDouble(comprimento
							.getText().toString()));
					veiculo.setAltura(Double.parseDouble(altura.getText()
							.toString()));
					if (chave == 0) {
						veiculoDAO.inserir(veiculo);
						Toast.makeText(getApplicationContext(),
								"Veículo cadastrado com sucesso!!!",
								Toast.LENGTH_SHORT).show();
					} else {
						veiculoDAO.atualizar(veiculo);
						Toast.makeText(getApplicationContext(),
								"Veículo Atualizado com sucesso!!!",
								Toast.LENGTH_SHORT).show();
					}
					finish();
				}

			}
		});
		cancela.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				chave = 1;
				char categoria;
				getActionBar().setTitle("Editar Veículo");
				veiculo.setId_veiculo(getIntent().getExtras().getInt("id"));
				nome.setText(getIntent().getExtras().getString("nome"));
				largura.setText(String.valueOf(getIntent().getExtras()
						.getDouble("largura")));
				comprimento.setText(String.valueOf(getIntent().getExtras()
						.getDouble("comprimento")));
				altura.setText(String.valueOf(getIntent().getExtras()
						.getDouble("altura")));
				categoria = getIntent().getExtras().getChar("categoria");
				if (categoria == 'N') {
					radio0.setChecked(true);
					radio1.setChecked(false);
					radio2.setChecked(false);
					radio3.setChecked(false);
					radio4.setChecked(false);
					veiculo.setCategoria_veiculo('N');
				} else if (categoria == 'C') {
					radio0.setChecked(false);
					radio1.setChecked(true);
					radio2.setChecked(false);
					radio3.setChecked(false);
					radio4.setChecked(false);
					veiculo.setCategoria_veiculo('C');
				} else if (categoria == 'S') {
					radio0.setChecked(false);
					radio1.setChecked(false);
					radio2.setChecked(true);
					radio3.setChecked(false);
					radio4.setChecked(false);
					veiculo.setCategoria_veiculo('S');
				} else if (categoria == 'P') {
					radio0.setChecked(false);
					radio1.setChecked(false);
					radio2.setChecked(false);
					radio3.setChecked(true);
					radio4.setChecked(false);
					veiculo.setCategoria_veiculo('P');
				} else if (categoria == 'A') {
					radio0.setChecked(false);
					radio1.setChecked(false);
					radio2.setChecked(false);
					radio3.setChecked(false);
					radio4.setChecked(true);
					veiculo.setCategoria_veiculo('A');
				} else {
					radio0.setChecked(false);
					radio1.setChecked(false);
					radio2.setChecked(false);
					radio3.setChecked(false);
					radio4.setChecked(false);
				}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_carro, menu);
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
