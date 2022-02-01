package br.com.view;

import java.text.DecimalFormat;

import br.com.beans.Caixa;
import br.com.beans.Equipamento;
import br.com.beans.Veiculo;
import br.com.dao.CaixaDAO;
import br.com.dao.EquipamentoDAO;
import br.com.dao.VeiculoDAO;
import br.com.projetosdecaixasdesomautomotivo.MostrarCaixaMedidas;
import br.com.projetosdecaixasdesomautomotivo.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CadastrarCaixas extends Activity {
	Button mais, menos, calcular, cancelar, confirmar, verCaixa;
	TextView espesura, interno, externo, volumeSub, hipotenusa;
	RadioButton selado, dutado;
	ImageView buscar_veiculo, buscar_sub;
	EditText nomeVeiculo, nomeSub, comprimento, altura, lInferior, lSuperiorr,
			dutopolegadas, dutocomprimento;
	float comp, alt, linf, lsup, esp2;
	int esp, chave;;
	EquipamentoDAO equipamentoDAO;
	VeiculoDAO veiculoDAO;
	CaixaDAO caixaDAO;
	Equipamento equipamento;
	Veiculo veiculo;
	Caixa caixa;
	DecimalFormat df = new DecimalFormat("0.00");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_caixas);
		if (savedInstanceState == null) {
			equipamento = new Equipamento();
			veiculo = new Veiculo();
			caixa = new Caixa();
		}

		getActionBar().setTitle("Cadastrar Caixa");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#696969")));
		mais = (Button) findViewById(R.id.btMais);
		cancelar = (Button) findViewById(R.id.btCanCaixa);
		confirmar = (Button) findViewById(R.id.btOKCaixa);
		calcular = (Button) findViewById(R.id.btCalcular);
		verCaixa = (Button) findViewById(R.id.btCaixa);
		buscar_sub = (ImageView) findViewById(R.id.btProcurar_sub);
		buscar_veiculo = (ImageView) findViewById(R.id.btProcurar_veiculo);
		menos = (Button) findViewById(R.id.btMenos);
		espesura = (TextView) findViewById(R.id.txtEspesura);
		interno = (TextView) findViewById(R.id.volumeInterno);
		volumeSub = (TextView) findViewById(R.id.volumeSub);
		externo = (TextView) findViewById(R.id.volumeExterno);
		hipotenusa = (TextView) findViewById(R.id.valorHipotenusa);
		selado = (RadioButton) findViewById(R.id.radioSelado);
		dutado = (RadioButton) findViewById(R.id.radioDutado);
		nomeVeiculo = (EditText) findViewById(R.id.edtNomeVeiculo);
		comprimento = (EditText) findViewById(R.id.edt_comprimento_caixa);
		altura = (EditText) findViewById(R.id.edt_altura_caixa);
		lInferior = (EditText) findViewById(R.id.edt_largura_infeior_caixa);
		lSuperiorr = (EditText) findViewById(R.id.edt_largura_superior_caixa);
		dutopolegadas = (EditText) findViewById(R.id.edtDutoPolegadas);
		dutocomprimento = (EditText) findViewById(R.id.edtComprimento_duto);
		nomeVeiculo.setFocusable(false);
		nomeSub = (EditText) findViewById(R.id.edtNomeSub);
		nomeSub.setFocusable(false);
		chave = 0;

		buscar_veiculo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(),
						Lista_veiculo_equipamento.class);
				intent.putExtra("funcao", 1);
				startActivityForResult(intent, 88);

			}
		});

		buscar_sub.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(),
						Lista_veiculo_equipamento.class);
				intent.putExtra("funcao", 2);
				startActivityForResult(intent, 44);

			}
		});

		selado.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				dutado.setChecked(false);
				dutopolegadas.setText("  --  ");
				dutocomprimento.setText("  --  ");
				dutopolegadas.setFocusable(false);
				dutocomprimento.setFocusable(false);
				caixa.setTipo('S');
				if (equipamento.getVolume_selado() != 0.0) {
					volumeSub.setText(" "
							+ String.valueOf(df.format(equipamento
									.getVolume_selado())) + " litros");
				}
				return false;
			}
		});

		dutado.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				selado.setChecked(false);
				dutopolegadas.setText("");
				dutocomprimento.setText("");
				dutopolegadas.setFocusableInTouchMode(true);
				dutocomprimento.setFocusableInTouchMode(true);
				caixa.setTipo('D');
				if (equipamento.getVolume_dutado() != 0.0) {
					volumeSub.setText(" "
							+ String.valueOf(df.format(equipamento
									.getVolume_dutado())) + " litros");
				}
				return false;
			}
		});

		lerSharedPreferences();

		mais.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				esp = Integer.parseInt(espesura.getText().toString().trim());
				if (esp < 40) {
					esp++;
				}
				salvaSharedPreferences("espesura", String.valueOf(esp));
				espesura.setText(String.valueOf(esp));
			}
		});

		menos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				esp = Integer.parseInt(espesura.getText().toString().trim());
				if (esp > 10) {
					esp--;
				}
				salvaSharedPreferences("espesura", String.valueOf(esp));
				espesura.setText(String.valueOf(esp));
			}
		});

		verCaixa.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (verificaPreenchimento()) {
					Intent intent = new Intent(getApplicationContext(),
							MostrarCaixaMedidas.class);
					intent.putExtra("A", altura.getText().toString().trim());
					intent.putExtra("B", comprimento.getText().toString()
							.trim());
					intent.putExtra("C", lInferior.getText().toString().trim());
					intent.putExtra("D", lSuperiorr.getText().toString().trim());
					intent.putExtra("E", hipotenusa.getText().toString().trim());
					startActivity(intent);
				}
			}
		});

		calcular.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				verificaPreenchimento();
			}
		});

		cancelar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});

		confirmar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Verifica se está vazio
				if (nomeVeiculo.getText().toString().trim().equals("")) {
					nomeVeiculo.setError("Selecione um veículo!!!");
				} else if (nomeSub.getText().toString().trim().equals("")) {
					nomeSub.setError("Selecione um Subwoofer!!!");
				} else if (!(selado.isChecked() || dutado.isChecked())) {
					selado.setError("Marque o tipo");
				} else if (comprimento.getText().toString().trim().isEmpty()) {
					comprimento.setError("Informe o comprimento da caixa!!!");
				} else if (altura.getText().toString().trim().isEmpty()) {
					altura.setError("Informe a altura da caixa!!!");
				} else if (lInferior.getText().toString().trim().isEmpty()) {
					lInferior
							.setError("Informe a largura inferior da caixa!!!");
				} else if (lSuperiorr.getText().toString().trim().isEmpty()) {
					lSuperiorr
							.setError("Informe a largura superior da caixa!!!");
				} else if (dutopolegadas.getText().toString().trim().isEmpty()) {
					dutopolegadas.setError("Informe o diametro do duto!!!");
				} else if (dutocomprimento.getText().toString().trim()
						.isEmpty()) {
					dutocomprimento
							.setError("Informe o comprimento do duto!!!");
				} else

				// Verifica se digitou zero(0)
				if (comprimento.getText().toString().trim().equals("0")) {
					comprimento.setError("Essa medida não é válida!!!");
				} else if (altura.getText().toString().trim().equals("0")) {
					altura.setError("Essa medida não é válida!!!");
				} else if (lInferior.getText().toString().trim().equals("0")) {
					lInferior.setError("Essa medida não é válida!!!");
				} else if (lSuperiorr.getText().toString().trim().equals("0")) {
					lSuperiorr.setError("Essa medida não é válida!!!");
				} else if (dutopolegadas.getText().toString().trim()
						.equals("0")) {
					dutopolegadas.setError("Essa medida não é válida!!!");
				} else if (dutocomprimento.getText().toString().trim()
						.equals("0")) {
					dutocomprimento.setError("Essa medida não é válida!!!");
				} else

				// Verifica se as medidas estão dentro do valor do veiculo
				if (veiculo.getComprimento() < Float.parseFloat(comprimento
						.getText().toString().trim())) {
					comprimento
							.setError("O comprimento máximo para esse veículo é "
									+ veiculo.getComprimento() + "cm !!!");

				} else if (veiculo.getAltura() < Float.parseFloat(altura
						.getText().toString().trim())) {
					altura.setError("A Altura máxima para esse veículo é "
							+ veiculo.getAltura() + "cm !!!");

				} else if (veiculo.getLargura() < Float.parseFloat(lInferior
						.getText().toString().trim())) {
					lInferior.setError("A Largura máxima para esse veículo é "
							+ veiculo.getLargura() + "cm !!!");

				} else if (veiculo.getLargura() < Float.parseFloat(lSuperiorr
						.getText().toString().trim())) {
					lSuperiorr.setError("A Largura máxima para esse veículo é "
							+ veiculo.getLargura() + "cm !!!");

				} else

				// Verifica se o volume esta menor que o volume recomendado pelo
				// sub
				if ((selado.isChecked() && calculoInterno('I') < equipamento
						.getVolume_selado())
						|| (dutado.isChecked() && calculoInterno('I') < equipamento
								.getVolume_dutado())) {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							CadastrarCaixas.this);
					alertDialog.setTitle("ATENÇÂO");
					alertDialog
							.setMessage("O volume desta caixa é menor que o valor cadastrado no subwoofer, deseja cadastrar assim mesmo?");
					alertDialog.setNegativeButton("Não",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Log.d("Alert Dialog", "Não");
								}
							});
					alertDialog.setPositiveButton("Sim",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									if (chave == 0) {
										caixaDAO = new CaixaDAO(
												getApplicationContext());
										carregaCaixa();
										caixaDAO.inserir(caixa);
										Toast.makeText(
												getApplicationContext(),
												"Caixa cadastrada com sucesso!!!",
												Toast.LENGTH_SHORT).show();
									} else {
										caixaDAO = new CaixaDAO(
												getApplicationContext());
										carregaCaixa();
										caixaDAO.atualizar(caixa);
										Toast.makeText(
												getApplicationContext(),
												"Caixa Atualizada com sucesso!!!",
												Toast.LENGTH_SHORT).show();
									}
									finish();
								}
							});
					alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
					alertDialog.show();
				} else {
					if (chave == 0) {
						caixaDAO = new CaixaDAO(getApplicationContext());
						carregaCaixa();
						caixaDAO.inserir(caixa);
						Toast.makeText(getApplicationContext(),
								"Caixa cadastrada com sucesso!!!",
								Toast.LENGTH_SHORT).show();
					} else {
						caixaDAO = new CaixaDAO(getApplicationContext());
						carregaCaixa();
						caixaDAO.atualizar(caixa);
						Toast.makeText(getApplicationContext(),
								"Caixa Atualizada com sucesso!!!",
								Toast.LENGTH_SHORT).show();
					}
					finish();
				}
			}
		});

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				chave = 1;
				char tipo;
				getActionBar().setTitle("Editar Caixa");
				caixaDAO = new CaixaDAO(getApplicationContext());
				veiculoDAO = new VeiculoDAO(this);
				equipamentoDAO = new EquipamentoDAO(this);
				caixa = caixaDAO
						.buscar_um(getIntent().getExtras().getInt("id"));
				veiculo = veiculoDAO.buscar_um(caixa.getId_veiculo());
				equipamento = equipamentoDAO.busca_umr(caixa.getId_sub());
				nomeVeiculo.setText(veiculo.getNome_veiculo().toUpperCase());
				nomeSub.setText(equipamento.getNome_equipamento().toUpperCase());
				espesura.setText(String.valueOf(caixa.getEspMadeira()));
				comprimento.setText(String.valueOf(caixa.getComprimento()));
				altura.setText(String.valueOf(caixa.getAltura()));
				lInferior.setText(String.valueOf(caixa.getLarguraInf()));
				lSuperiorr.setText(String.valueOf(caixa.getLarguraSup()));
				dutopolegadas.setText(String.valueOf(caixa.getDutoDiamentro()));
				dutocomprimento.setText(String.valueOf(caixa
						.getDutoComprimento()));
				tipo = caixa.getTipo();
				if (tipo == 'S') {
					selado.setChecked(true);
				} else if (tipo == 'D') {
					dutado.setChecked(true);
				}
			}
		}
	}

	public void carregaCaixa() {
		caixa.setEspMadeira(Integer.parseInt(espesura.getText().toString()));
		caixa.setComprimento(Float.parseFloat(comprimento.getText().toString()));
		caixa.setAltura(Float.parseFloat(altura.getText().toString()));
		caixa.setLarguraInf(Float.parseFloat(lInferior.getText().toString()));
		caixa.setLarguraSup(Float.parseFloat(lSuperiorr.getText().toString()));
		if (dutado.isChecked()) {
			caixa.setDutoDiamentro(Float.parseFloat(dutopolegadas.getText()
					.toString()));
			caixa.setDutoComprimento(Float.parseFloat(dutocomprimento.getText()
					.toString()));
		}
		caixa.setvInterno(calculoInterno('I'));
		caixa.setvExterno(calculoInterno('E'));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_caixas, menu);
		return true;
	}

	public void salvaSharedPreferences(String chave, String valor) {

		SharedPreferences settings = getSharedPreferences("MinhaPeferencia",
				MODE_MULTI_PROCESS);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(chave, valor);
		editor.commit();
	}

	public void lerSharedPreferences() {
		SharedPreferences settings = getSharedPreferences("MinhaPeferencia",
				MODE_MULTI_PROCESS);
		espesura.setText(settings.getString("espesura", "10"));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == 88) {
				veiculoDAO = new VeiculoDAO(this);
				int id = data.getExtras().getInt("id");
				caixa.setId_veiculo(id);
				veiculo = veiculoDAO.buscar_um(id);
				nomeVeiculo.setText(veiculo.getNome_veiculo().toUpperCase());
			} else if (requestCode == 44) {
				equipamentoDAO = new EquipamentoDAO(this);
				int id = data.getExtras().getInt("id");
				caixa.setId_sub(id);
				equipamento = equipamentoDAO.busca_umr(id);
				nomeSub.setText(equipamento.getNome_equipamento().toUpperCase());
				if (selado.isChecked()) {
					volumeSub.setText(" "
							+ String.valueOf(df.format(equipamento
									.getVolume_selado())) + " litros");

				} else if (dutado.isChecked()) {
					volumeSub.setText(" "
							+ String.valueOf(df.format(equipamento
									.getVolume_dutado())) + " litros");
				}

			} else {

			}
		}
	}

	public float calculoInterno(char aux) {
		float resultado;
		lerSharedPreferences();
		comp = Float.parseFloat(comprimento.getText().toString());
		alt = Float.parseFloat(altura.getText().toString());
		linf = Float.parseFloat(lInferior.getText().toString());
		lsup = Float.parseFloat(lSuperiorr.getText().toString());
		esp2 = Float.parseFloat(espesura.getText().toString()) / 10 * 2;
		if (aux == 'I') {
			resultado = ((((linf - esp2) + (lsup - esp2)) / 2) * (comp - esp2) * (alt - esp2)) / 1000;
		} else {
			resultado = (((linf + lsup) / 2) * comp * alt) / 1000;
		}
		if (aux == 'H') {
			resultado = (float) Math
					.sqrt((((linf - lsup) * (linf - lsup)) + (alt * alt)));
		}
		return resultado;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("veiculo", veiculo);
		outState.putSerializable("equipamento", equipamento);
		outState.putSerializable("caixa", caixa);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		veiculo = (Veiculo) savedInstanceState.get("veiculo");
		equipamento = (Equipamento) savedInstanceState.get("equipamento");
		caixa = (Caixa) savedInstanceState.get("caixa");
		if (selado.isChecked()) {
			volumeSub.setText(" "
					+ String.valueOf(df.format(equipamento.getVolume_selado()))
					+ " litros");
		} else if (dutado.isChecked()) {
			volumeSub.setText(" "
					+ String.valueOf(df.format(equipamento.getVolume_dutado()))
					+ " litros");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean verificaPreenchimento() {
		boolean chave = false;
		if (comprimento.getText().toString().trim().isEmpty()) {
			comprimento.setError("Informe o comprimento da caixa!!!");
		} else if (altura.getText().toString().trim().isEmpty()) {
			altura.setError("Informe a altura da caixa!!!");
		} else if (lSuperiorr.getText().toString().trim().isEmpty()) {
			lSuperiorr.setError("Informe a largura superior da caixa!!!");
		} else if (lInferior.getText().toString().trim().isEmpty()) {
			lInferior.setError("Informe a largura inferior da caixa!!!");
		} else if (comprimento.getText().toString().trim().equals("0")) {
			comprimento.setError("Essa medida não é válida!!!");
		} else if (altura.getText().toString().trim().equals("0")) {
			altura.setError("Essa medida não é válida!!!");
		} else if (lInferior.getText().toString().trim().equals("0")) {
			lInferior.setError("Essa medida não é válida!!!");
		} else if (lSuperiorr.getText().toString().trim().equals("0")) {
			lSuperiorr.setError("Essa medida não é válida!!!");
		} else {
			chave = true;
			interno.setText("  " + df.format(calculoInterno('I')) + " litros");
			externo.setText("  " + df.format(calculoInterno('E')) + " litros");
			hipotenusa.setText("  " + df.format(calculoInterno('H')) + " cm");

		}
		return chave;
	}

}
