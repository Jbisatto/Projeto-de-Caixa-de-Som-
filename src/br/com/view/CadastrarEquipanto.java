package br.com.view;

import br.com.beans.Equipamento;
import br.com.beans.Veiculo;
import br.com.dao.EquipamentoDAO;
import br.com.dao.VeiculoDAO;
import br.com.projetosdecaixasdesomautomotivo.R;
import br.com.projetosdecaixasdesomautomotivo.R.layout;
import br.com.projetosdecaixasdesomautomotivo.R.menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CadastrarEquipanto extends Activity {
	Button confirma, cancela;
	EditText nome, volume_selado, volume_dutado, diamentro, comprimento;
	Equipamento equipamento;
	EquipamentoDAO equipamentoDAO;
	int chave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_equipanto);
		getActionBar().setTitle("Cadastro de Subwoofer");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(new 
	               ColorDrawable(Color.parseColor("#696969")));
		equipamento = new Equipamento();
		equipamentoDAO = new EquipamentoDAO(getApplicationContext());
		nome = (EditText) findViewById(R.id.edtNome);
		volume_selado = (EditText) findViewById(R.id.edtVolumeSelado);
		volume_dutado = (EditText) findViewById(R.id.edtVolumeDutado);
		diamentro = (EditText) findViewById(R.id.edtDiamentro);
		comprimento = (EditText) findViewById(R.id.edtComprimento);
		confirma = (Button) findViewById(R.id.btConfirmar);
		cancela = (Button) findViewById(R.id.btCancelar);
		chave = 0;

		confirma.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (nome.getText().toString().trim().isEmpty()) {
					nome.setError("Informe o nome do SubWoofer!!!");
				} else if (volume_selado.getText().toString().trim().isEmpty()) {
					volume_selado
							.setError("Informe o volume para caixas Seladas!!!");
				} else if (volume_dutado.getText().toString().trim().isEmpty()) {
					volume_dutado
							.setError("Informe o volume para caixas Dutadas!!!");
				} else if (diamentro.getText().toString().trim().isEmpty()) {
					diamentro.setError("Informe o diâmetro do Duto!!!");
				} else if (comprimento.getText().toString().trim().isEmpty()) {
					comprimento.setError("Informe o comprimento do Duto!!!");
				} else {
					equipamento.setNome_equipamento(nome.getText().toString());
					equipamento.setVolume_selado(Double
							.parseDouble(volume_selado.getText().toString()));
					equipamento.setVolume_dutado(Double
							.parseDouble(volume_dutado.getText().toString()));
					equipamento.setDiamentro(Double.parseDouble(diamentro
							.getText().toString()));
					equipamento.setComprimento(Double.parseDouble(comprimento.getText().toString()));
					if (chave == 0) {
						equipamentoDAO.inserir(equipamento);
						Toast.makeText(getApplicationContext(),
								"Equipamento cadastrado com sucesso!!!",
								Toast.LENGTH_SHORT).show();
					} else {
						equipamentoDAO.atualizar(equipamento);
						Toast.makeText(getApplicationContext(),
								"Equipamento Atualizado com sucesso!!!",
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
				getActionBar().setTitle("Editar Subwoofer");
				equipamento.setId_equipamento(getIntent().getExtras().getInt(
						"id"));
				nome.setText(getIntent().getExtras().getString("nome"));
				volume_selado.setText(String.valueOf(getIntent().getExtras()
						.getDouble("volume_selado")));
				volume_dutado.setText(String.valueOf(getIntent().getExtras()
						.getDouble("volumen_dutado")));
				comprimento.setText(String.valueOf(getIntent().getExtras()
						.getDouble("comprimento")));
				diamentro.setText(String.valueOf(getIntent().getExtras()
						.getDouble("diametro")));

			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_equipanto, menu);
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
