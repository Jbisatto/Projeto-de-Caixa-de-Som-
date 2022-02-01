package br.com.view;

import java.util.List;

import br.com.Adapters.EquipamentoAdapter;
import br.com.Adapters.EquipamentoAdapter_caixa;
import br.com.Adapters.VeiculoAdapter_caixa;
import br.com.beans.Equipamento;
import br.com.beans.Veiculo;
import br.com.dao.EquipamentoDAO;
import br.com.dao.VeiculoDAO;
import br.com.projetosdecaixasdesomautomotivo.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi") public class Lista_veiculo_equipamento extends Activity {
	private static ListView lista;
	List<Veiculo> list_veiculo;
	List<Equipamento> list_equipamento;
	VeiculoDAO veiculoDao;
	EquipamentoDAO equipamentoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_veiculo_equipamento);
		lista = (ListView) findViewById(R.id.listView1);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(new 
	               ColorDrawable(Color.parseColor("#696969")));
		
		if ((getIntent().getExtras().get("funcao")).equals(1)) {
			veiculoDao = new VeiculoDAO(this);
			list_veiculo = veiculoDao.buscar_all();
			lista.setAdapter(new VeiculoAdapter_caixa(this,
					list_veiculo, lista));
			lista
					.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
						@Override
						public boolean onItemLongClick(AdapterView<?> parent,
								View view, int position, long id) {
							setResult(
									RESULT_OK,
									getIntent().putExtra(
											"id",
											list_veiculo.get(position)
													.getId_veiculo()));
							finish();
							return false;
						}
					});
		}else if ((getIntent().getExtras().get("funcao")).equals(2)) {
			equipamentoDAO=new EquipamentoDAO(this);
			list_equipamento = equipamentoDAO.buscar_all();
			lista.setAdapter(new EquipamentoAdapter_caixa(this,
					list_equipamento, lista));
			lista
					.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
						@Override
						public boolean onItemLongClick(AdapterView<?> parent,
								View view, int position, long id) {
							setResult(
									RESULT_OK,
									getIntent().putExtra(
											"id",
											list_equipamento.get(position)
													.getId_equipamento()));
							finish();
							return false;
						}
					});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_veiculo_equipamento, menu);
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
