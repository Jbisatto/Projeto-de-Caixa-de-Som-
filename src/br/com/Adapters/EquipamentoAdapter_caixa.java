package br.com.Adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.beans.Equipamento;

public class EquipamentoAdapter_caixa extends BaseAdapter {
	private Context context;
	private List<Equipamento> list;
	private ListView MyListView;

	public EquipamentoAdapter_caixa(Context context, List<Equipamento> list,ListView lv) {
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
		return list.get(arg0).getId_equipamento();
	}

	@Override
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
					br.com.projetosdecaixasdesomautomotivo.R.layout.dados_full_equipamento_caixa,
					arg2, false);
			TextView categoria = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtCategoria);
			categoria.setText(String.valueOf(list.get(position)
					.getVolume_selado()));
			TextView largura = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtLargura);
			largura.setText(String.valueOf(list.get(position)
					.getVolume_dutado()));
			TextView comprimento = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtComprimento);
			comprimento.setText(String.valueOf(list.get(position)
					.getDiamentro()));
			TextView altura = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtAltura);
			altura.setText(String.valueOf(list.get(position).getComprimento()));
		}
		TextView tv = (TextView) layout
				.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.nome);
		tv.setText(list.get(position).getNome_equipamento().toUpperCase());

		
		MyListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						if (list.get(position).getChave_teste() == 0) {
							//for (Equipamento equipamento : list) {
								//equipamento.setChave_teste(0);
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
