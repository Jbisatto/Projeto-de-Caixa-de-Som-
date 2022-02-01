package br.com.Adapters;

import java.util.List;

import br.com.beans.Veiculo;
import br.com.dao.VeiculoDAO;
import br.com.projetosdecaixasdesomautomotivo.R;
import br.com.view.CadastrarCarro;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VeiculoAdapter_caixa extends BaseAdapter {
	private Context context;
	private List<Veiculo> list;
	private ListView MyListView;

	public VeiculoAdapter_caixa(Context context, List<Veiculo> list, ListView lv) {
		this.context = context;
		this.list = list;
		this.MyListView = lv;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0).getId_veiculo();
	}

	@SuppressLint("DefaultLocale") @Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		final int auxPosition = position;
		final View layout;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (list.get(auxPosition).getChave_teste() == 0) {
			layout = inflater.inflate(
					br.com.projetosdecaixasdesomautomotivo.R.layout.veiculo_caixa,
					arg2, false);
		} else {
			layout = inflater.inflate(
					br.com.projetosdecaixasdesomautomotivo.R.layout.dados_full_veiculo_caixa,
					arg2, false);

			TextView categoria = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtCategoria);
			char cat = list.get(auxPosition).getCategoria_veiculo();
			if (cat == 'N') {
				categoria.setText("Hatch");
			} else if (cat == 'C') {
				categoria.setText("Caminhonete");
			} else if (cat == 'S') {
				categoria.setText("SUV");
			} else if (cat == 'P') {
				categoria.setText("Pickup");
			} else if (cat == 'A') {
				categoria.setText("Sedan");
			} else {
				categoria.setText("Não cadastrado");
			}

			TextView largura = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtLargura);
			largura.setText(String.valueOf(list.get(position).getLargura()));
			TextView comprimento = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtComprimento);
			comprimento.setText(String.valueOf(list.get(position)
					.getComprimento()));
			TextView altura = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtAltura);
			altura.setText(String.valueOf(list.get(position).getAltura()));
		}

		TextView tv = (TextView) layout
				.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.nome);
		tv.setText((list.get(position).getNome_veiculo()).toUpperCase());

		

		MyListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						if (list.get(position).getChave_teste() == 0) {
							//for (Veiculo veiculo : list) {
								//veiculo.setChave_teste(0);
							//}
							list.get(position).setChave_teste(1);
						} else {
							list.get(position).setChave_teste(0);
						}
						notifyDataSetChanged();
					}
				});

		return layout;
	}

}
