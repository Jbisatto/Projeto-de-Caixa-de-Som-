package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.banco.DB;
import br.com.beans.Caixa;
import br.com.beans.CaixaEpecial;
import br.com.beans.Veiculo;

public class CaixaDAO {
	private SQLiteDatabase bd;

	// DB banco=new DB();

	public CaixaDAO(Context ctx) {
		DB auxDB = new DB(ctx);
		bd = auxDB.getWritableDatabase();
	}

	public void inserir(Caixa caixa) {
		ContentValues valores = new ContentValues();
		valores.put("_id_veiculo", String.valueOf(caixa.getId_veiculo()));
		valores.put("_id_equipamento", String.valueOf(caixa.getId_sub()));
		valores.put("tipo", String.valueOf(caixa.getTipo()));
		valores.put("espMadeira", String.valueOf(caixa.getEspMadeira()));
		valores.put("comprimento", String.valueOf(caixa.getComprimento()));
		valores.put("altura", String.valueOf(caixa.getAltura()));
		valores.put("larguraInf", String.valueOf(caixa.getLarguraInf()));
		valores.put("larguraSup", String.valueOf(caixa.getLarguraSup()));
		valores.put("dutoDiamentro", String.valueOf(caixa.getDutoDiamentro()));
		valores.put("dutoComprimento",
				String.valueOf(caixa.getDutoComprimento()));
		valores.put("vInterno", String.valueOf(caixa.getvInterno()));
		valores.put("vExterno", String.valueOf(caixa.getvExterno()));

		bd.insert("caixa", null, valores);
	}

	public void atualizar(Caixa caixa) {
		ContentValues valores = new ContentValues();
		valores.put("_id_veiculo", String.valueOf(caixa.getId_veiculo()));
		valores.put("_id_equipamento", String.valueOf(caixa.getId_sub()));
		valores.put("tipo", String.valueOf(caixa.getTipo()));
		valores.put("espMadeira", String.valueOf(caixa.getEspMadeira()));
		valores.put("comprimento", String.valueOf(caixa.getComprimento()));
		valores.put("altura", String.valueOf(caixa.getAltura()));
		valores.put("larguraInf", String.valueOf(caixa.getLarguraInf()));
		valores.put("larguraSup", String.valueOf(caixa.getLarguraSup()));
		valores.put("dutoDiamentro", String.valueOf(caixa.getDutoDiamentro()));
		valores.put("dutoComprimento",
				String.valueOf(caixa.getDutoComprimento()));
		valores.put("vInterno", String.valueOf(caixa.getvInterno()));
		valores.put("vExterno", String.valueOf(caixa.getvExterno()));

		bd.update("caixa", valores, "_id_caixa = ?",
				new String[] { "" + caixa.getId_caixa() });
	}

	public void deletar(CaixaEpecial caixa) {
		bd.delete("caixa", "_id_caixa = " + caixa.getId_caixa(), null);
	}

	public List<CaixaEpecial> buscar_all() {
		List<CaixaEpecial> list = new ArrayList<CaixaEpecial>();
		//String[] colunas = new String[] { "_id_caixa", "_id_veiculo",
		//		"_id_equipamento", "tipo", "espMadeira", "comprimento",
		//		"altura", "larguraInf", "larguraSup", "dutoDiamentro",
		//		"dutoComprimento", "vInterno", "vExterno" };

		//Cursor cursor = bd.query("caixa", colunas, null, null, null, null,
		//		"_id_veiculo ASC");

		
		String sql = "Select caixa._id_caixa,veiculo.nome_veiculo, equipamento.nome_equipamento,"
				+ " caixa.tipo, caixa.espMadeira, caixa.comprimento, caixa.altura,"
				+ " caixa.larguraInf, caixa.larguraSup, caixa.dutoDiamentro,"
				+ " caixa.dutoComprimento, caixa.vInterno, caixa.vExterno from veiculo,"
				+ " equipamento, caixa where veiculo._id_veiculo=caixa._id_veiculo " +
				"and equipamento._id_equipamento=caixa._id_equipamento order by nome_veiculo;";
		Cursor cursor=bd.rawQuery(sql, null);
		
		
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				CaixaEpecial u = new CaixaEpecial();
				u.setId_caixa(cursor.getInt(0));
				u.setNome_veiculo(cursor.getString(1));
				u.setNome_sub(cursor.getString(2));
				u.setTipo(cursor.getString(3).charAt(0));
				u.setEspMadeira(cursor.getInt(4));
				u.setComprimento(cursor.getDouble(5));
				u.setAltura(cursor.getDouble(6));
				u.setLarguraInf(cursor.getDouble(7));
				u.setLarguraSup(cursor.getDouble(8));
				u.setDutoDiamentro(cursor.getDouble(9));
				u.setDutoComprimento(cursor.getDouble(10));
				u.setvInterno(cursor.getDouble(11));
				u.setvExterno(cursor.getDouble(12));

				list.add(u);

			} while (cursor.moveToNext());
		}

		return (list);
	}

	public List<CaixaEpecial> buscar(String nome) {
		List<CaixaEpecial> list = new ArrayList<CaixaEpecial>();
		//String[] colunas = new String[] { "_id_caixa", "_id_veiculo",
		//		"_id_equipamento", "tipo", "espMadeira", "comprimento",
		//		"altura", "larguraInf", "larguraSup", "dutoDiamentro",
		//		"dutoComprimento", "vInterno", "vExterno" };
		String sql = "Select caixa._id_caixa,veiculo.nome_veiculo, equipamento.nome_equipamento,"
				+ " caixa.tipo, caixa.espMadeira, caixa.comprimento, caixa.altura,"
				+ " caixa.larguraInf, caixa.larguraSup, caixa.dutoDiamentro,"
				+ " caixa.dutoComprimento, caixa.vInterno, caixa.vExterno from veiculo,"
				+ " equipamento, caixa where veiculo._id_veiculo=caixa._id_veiculo " +
				"and equipamento._id_equipamento=caixa._id_equipamento and (veiculo.nome_veiculo like '%"
				+ nome + "%' or equipamento.nome_equipamento like '%"
				+ nome + "%') order by nome_veiculo;";
		Cursor cursor=bd.rawQuery(sql, null);
		//Cursor cursor = bd.query("caixa", colunas, "_id_veiculo like '%" + id
		//		+ "%'", null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				CaixaEpecial u = new CaixaEpecial();
				u.setId_caixa(cursor.getInt(0));
				u.setNome_veiculo(cursor.getString(1));
				u.setNome_sub(cursor.getString(2));
				u.setTipo(cursor.getString(3).charAt(0));
				u.setEspMadeira(cursor.getInt(4));
				u.setComprimento(cursor.getDouble(5));
				u.setAltura(cursor.getDouble(6));
				u.setLarguraInf(cursor.getDouble(7));
				u.setLarguraSup(cursor.getDouble(8));
				u.setDutoDiamentro(cursor.getDouble(9));
				u.setDutoComprimento(cursor.getDouble(10));
				u.setvInterno(cursor.getDouble(11));
				u.setvExterno(cursor.getDouble(12));
				list.add(u);

			} while (cursor.moveToNext());
		}

		return (list);
	}

	public Caixa buscar_um(int id) {
		Caixa u = new Caixa();
		String[] colunas = new String[] { "_id_caixa", "_id_veiculo",
				"_id_equipamento", "tipo", "espMadeira", "comprimento",
				"altura", "larguraInf", "larguraSup", "dutoDiamentro",
				"dutoComprimento", "vInterno", "vExterno" };

		Cursor cursor = bd.query("caixa", colunas, "_id_caixa=" + id, null,
				null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {
				u.setId_caixa(cursor.getInt(0));
				u.setId_veiculo(cursor.getInt(1));
				u.setId_sub(cursor.getInt(2));
				u.setTipo(cursor.getString(3).charAt(0));
				u.setEspMadeira(cursor.getInt(4));
				u.setComprimento(cursor.getDouble(5));
				u.setAltura(cursor.getDouble(6));
				u.setLarguraInf(cursor.getDouble(7));
				u.setLarguraSup(cursor.getDouble(8));
				u.setDutoDiamentro(cursor.getDouble(9));
				u.setDutoComprimento(cursor.getDouble(10));
				u.setvInterno(cursor.getDouble(11));
				u.setvExterno(cursor.getDouble(12));

			} while (cursor.moveToNext());
		}

		return u;
	}

}
