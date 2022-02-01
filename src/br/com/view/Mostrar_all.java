package br.com.view;

import java.util.List;

import br.com.Adapters.CaixaAdapter;
import br.com.Adapters.EquipamentoAdapter;
import br.com.Adapters.VeiculoAdapter;
import br.com.beans.Caixa;
import br.com.beans.CaixaEpecial;
import br.com.beans.Equipamento;
import br.com.beans.Veiculo;
import br.com.dao.CaixaDAO;
import br.com.dao.EquipamentoDAO;
import br.com.dao.VeiculoDAO;
import br.com.projetosdecaixasdesomautomotivo.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

@SuppressLint("NewApi")
public class Mostrar_all extends Activity {
	private static ListView listaveiculo;
	List<Veiculo> list_veiculo;
	List<Equipamento> list_equipamento;
	List<CaixaEpecial> list_caixa;
	VeiculoDAO veiculoDao;
	EquipamentoDAO equipamentoDAO;
	CaixaDAO caixaDAO;
	RadioButton rd1, rd2, rd3;
	EditText edProcurar;
	ImageView btProcurar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_all);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(new 
	               ColorDrawable(Color.parseColor("#696969")));
		veiculoDao = new VeiculoDAO(this);
		equipamentoDAO = new EquipamentoDAO(this);
		caixaDAO = new CaixaDAO(this);
		listaveiculo = (ListView) findViewById(R.id.listView1);
		getActionBar().setTitle("Consulta");

		rd1 = (RadioButton) findViewById(R.id.radio0);
		rd2 = (RadioButton) findViewById(R.id.radio1);
		rd3 = (RadioButton) findViewById(R.id.radio2);
		edProcurar = (EditText) findViewById(R.id.edtProcurar);
		btProcurar = (ImageView) findViewById(R.id.btProcurar);

		rd1.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub

				rd2.setChecked(false);
				rd3.setChecked(false);
				list_veiculo = veiculoDao.buscar_all();
				carregarListView();
				return false;
			}
		});

		rd2.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub

				rd1.setChecked(false);
				rd3.setChecked(false);
				list_equipamento = equipamentoDAO.buscar_all();
				carregarListView2();
				return false;
			}
		});
		rd3.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub

				rd2.setChecked(false);
				rd1.setChecked(false);
				list_caixa = caixaDAO.buscar_all();
				carregarListView3();
				return false;
			}
		});
		btProcurar.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (rd1.isChecked()) {
					list_veiculo = veiculoDao.buscar(edProcurar.getText()
							.toString());
					carregarListView();
				} else if (rd2.isChecked()) {
					list_equipamento = equipamentoDAO.buscar(edProcurar
							.getText().toString());
					carregarListView2();
				} else if (rd3.isChecked()) {
					list_caixa = caixaDAO.buscar(edProcurar.getText()
							.toString());
					carregarListView3();
				}
			}
		});

	}

	public void carregarListView() {
		listaveiculo.setAdapter(new VeiculoAdapter(this, list_veiculo,
				listaveiculo));

	}

	public void carregarListView2() {
		listaveiculo.setAdapter(new EquipamentoAdapter(this, list_equipamento,
				listaveiculo));

	}

	public void carregarListView3() {
		listaveiculo
				.setAdapter(new CaixaAdapter(this, list_caixa, listaveiculo));

	}

	@Override
	public void onResume() {
		super.onResume();
		if (rd1.isChecked()) {
			list_veiculo = veiculoDao.buscar(edProcurar.getText().toString());
			carregarListView();
		} else if (rd2.isChecked()) {
			list_equipamento = equipamentoDAO.buscar(edProcurar.getText()
					.toString());
			carregarListView2();
		} else if (rd3.isChecked()) {
			list_caixa = caixaDAO.buscar(edProcurar.getText().toString());
			carregarListView3();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_all, menu);
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
