package br.com.Adapters;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.beans.CaixaEpecial;
import br.com.dao.CaixaDAO;
import br.com.view.CadastrarCaixas;

public class CaixaAdapter extends BaseAdapter {
	private Context context;
	private List<CaixaEpecial> list;
	private ListView MyListView;

	public CaixaAdapter(Context context, List<CaixaEpecial> list, ListView lv) {
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
		return list.get(arg0).getId_caixa();
	}

	@SuppressLint("DefaultLocale") @Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		final int auxPosition = position;
		final View layout;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (list.get(auxPosition).getChave_teste() == 0) {
			layout = inflater.inflate(
					br.com.projetosdecaixasdesomautomotivo.R.layout.veiculo,
					arg2, false);
		} else {
			layout = inflater.inflate(
					br.com.projetosdecaixasdesomautomotivo.R.layout.dados_full_caixa,
					arg2, false);

			TextView categoria = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txttipo);
			char cat = list.get(auxPosition).getTipo();
			if (cat == 'S') {
					categoria.setText("Selado");
			} else if (cat == 'D') {
				categoria.setText("Dutado");
			} else {
			categoria.setText("Não cadastrado");
			}

			TextView espessura = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtEspessura);
			espessura.setText(String.valueOf(list.get(position).getEspMadeira()));
			TextView comprimento = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtComprimento);
			comprimento.setText(String.valueOf(list.get(position)
					.getComprimento()));
			TextView altura = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtAltura);
			altura.setText(String.valueOf(list.get(position).getAltura()));
			
			TextView larInf = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtLarInf);
			larInf.setText(String.valueOf(list.get(position).getLarguraInf()));
			TextView larSup = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtLarSup);
			larSup.setText(String.valueOf(list.get(position).getLarguraSup()));
			
			
			TextView diaDuto = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtDiaDuto);
			TextView compDuto = (TextView) layout
					.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.txtCompDuto);
			if(list.get(auxPosition).getTipo()=='D'){
			diaDuto.setText(String.valueOf(list.get(position).getDutoDiamentro()));
			
			compDuto.setText(String.valueOf(list.get(position).getDutoComprimento()));
			}else{
				diaDuto.setText("  --  ");
				
				compDuto.setText("  --  ");
			}
		}

		TextView tv = (TextView) layout
				.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.nome);
		tv.setText(("V:  "+list.get(auxPosition).getNome_veiculo()+"\nS:  "+list.get(auxPosition).getNome_sub()).toUpperCase());

		ImageView editarBt = (ImageView) layout
				.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.editar);
		editarBt.setOnClickListener(new ImageView.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 
				Intent intent = new Intent(context, CadastrarCaixas.class);
				intent.putExtra("id", list.get(auxPosition).getId_caixa());
				//intent.putExtra("categoria", list.get(auxPosition)
				//	.getCategoria_veiculo());
				//intent.putExtra("largura", list.get(auxPosition).getLargura());
				//intent.putExtra("comprimento", list.get(auxPosition)
				//		.getComprimento());
				//intent.putExtra("altura", list.get(auxPosition).getAltura());
				//vintent.putExtra("id", list.get(auxPosition).getId_veiculo());
				context.startActivity(intent);
			}
		});

		ImageView deletarBt = (ImageView) layout
				.findViewById(br.com.projetosdecaixasdesomautomotivo.R.id.deletar);

		deletarBt.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
				alertDialog.setTitle("ATENÇÂO");
				alertDialog.setMessage("Você tem certeza que deseja excluir essa caixa?");
				alertDialog.setNegativeButton("Não",new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
				    	Toast.makeText(context,
								"Veículo não foi excluído!",
								Toast.LENGTH_SHORT).show();
				    }
				});
				alertDialog.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
				    			CaixaDAO caixaDAO = new CaixaDAO(context);
				    			caixaDAO.deletar(list.get(auxPosition));

								list.remove(auxPosition);
								notifyDataSetChanged();
								Toast.makeText(context,
										"Caixa excluída com sucesso!",
										Toast.LENGTH_SHORT).show();
							}
						});
				alertDialog.setIcon(android.R.drawable.ic_menu_delete);
				alertDialog.show();
			}
		});

		MyListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						if (list.get(position).getChave_teste() == 0) {
							//for (Caixa caixa_2 : list) {
								//caixa_2.setChave_teste(0);
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
