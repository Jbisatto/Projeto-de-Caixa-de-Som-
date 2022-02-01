package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.banco.DB;
import br.com.beans.Veiculo;

public class VeiculoDAO {
	private SQLiteDatabase bd;

	// DB banco=new DB();

	public VeiculoDAO(Context ctx) {
		DB auxDB = new DB(ctx);
		bd = auxDB.getWritableDatabase();
	}

	public void inserir(Veiculo veiculo) {
		ContentValues valores = new ContentValues();
		valores.put("nome_veiculo", veiculo.getNome_veiculo());
		valores.put("categoria_veiculo",
				String.valueOf(veiculo.getCategoria_veiculo()));
		valores.put("largura", String.valueOf(veiculo.getLargura()));
		valores.put("comprimento", String.valueOf(veiculo.getComprimento()));
		valores.put("altura", String.valueOf(veiculo.getAltura()));

		bd.insert("veiculo", null, valores);
	}

	public void atualizar(Veiculo veiculo) {
		ContentValues valores = new ContentValues();
		valores.put("nome_veiculo", veiculo.getNome_veiculo());
		valores.put("categoria_veiculo",
				String.valueOf(veiculo.getCategoria_veiculo()));
		valores.put("largura", String.valueOf(veiculo.getLargura()));
		valores.put("comprimento", String.valueOf(veiculo.getComprimento()));
		valores.put("altura", String.valueOf(veiculo.getAltura()));

		bd.update("veiculo", valores, "_id_veiculo = ?", new String[] { ""
				+ veiculo.getId_veiculo() });
	}

	public void deletar(Veiculo veiculo) {
		bd.delete("veiculo", "_id_veiculo = " + veiculo.getId_veiculo(), null);
	}

	public List<Veiculo> buscar_all() {
		List<Veiculo> list = new ArrayList<Veiculo>();
		String[] colunas = new String[] { "_id_veiculo", "nome_veiculo",
				"categoria_veiculo", "largura", "comprimento", "altura" };

		Cursor cursor = bd.query("veiculo", colunas, null, null, null, null,
				"nome_veiculo ASC");

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				Veiculo u = new Veiculo();
				u.setId_veiculo(cursor.getInt(0));
				u.setNome_veiculo(cursor.getString(1));
				u.setCategoria_veiculo(cursor.getString(2).charAt(0));
				u.setLargura(cursor.getDouble(3));
				u.setComprimento(cursor.getDouble(4));
				u.setAltura(cursor.getDouble(5));
				list.add(u);

			} while (cursor.moveToNext());
		}

		return (list);
	}

	public List<Veiculo> buscar(String nome) {
		List<Veiculo> list = new ArrayList<Veiculo>();
		String[] colunas = new String[] { "_id_veiculo", "nome_veiculo",
				"categoria_veiculo", "largura", "comprimento", "altura" };

		Cursor cursor = bd.query("veiculo", colunas, "nome_veiculo like '%"
				+ nome + "%'", null, null, null, "nome_veiculo ASC");

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				Veiculo u = new Veiculo();
				u.setId_veiculo(cursor.getInt(0));
				u.setNome_veiculo(cursor.getString(1));
				u.setCategoria_veiculo(cursor.getString(2).charAt(0));
				u.setLargura(cursor.getDouble(3));
				u.setComprimento(cursor.getDouble(4));
				u.setAltura(cursor.getDouble(5));
				list.add(u);

			} while (cursor.moveToNext());
		}

		return (list);
	}

	public Veiculo buscar_um(int id) {
		Veiculo u = new Veiculo();
		String[] colunas = new String[] { "_id_veiculo", "nome_veiculo",
		"categoria_veiculo", "largura", "comprimento", "altura" };

		Cursor cursor = bd.query("veiculo", colunas, "_id_veiculo=" + id, null, null, null,
				null);
		

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				
				u.setId_veiculo(cursor.getInt(0));
				u.setNome_veiculo(cursor.getString(1));
				u.setCategoria_veiculo(cursor.getString(2).charAt(0));
				u.setLargura(cursor.getDouble(3));
				u.setComprimento(cursor.getDouble(4));
				u.setAltura(cursor.getDouble(5));
				

			} while (cursor.moveToNext());
		}

		return u;
	}
}
