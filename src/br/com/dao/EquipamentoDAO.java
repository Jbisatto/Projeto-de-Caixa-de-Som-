package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.banco.DB;
import br.com.beans.Equipamento;

public class EquipamentoDAO {
	private SQLiteDatabase bd;

	// DB banco=new DB();

	public EquipamentoDAO(Context ctx) {
		DB auxDB = new DB(ctx);
		bd = auxDB.getWritableDatabase();
	}
 
	public void inserir(Equipamento equipamento) {
		ContentValues valores = new ContentValues();
		valores.put("nome_equipamento", equipamento.getNome_equipamento());
		valores.put("volume_selado",String.valueOf(equipamento.getVolume_selado()));
		valores.put("volume_dutado", String.valueOf(equipamento.getVolume_dutado()));
		valores.put("diamentro", String.valueOf(equipamento.getDiamentro()));
		valores.put("comprimento", String.valueOf(equipamento.getComprimento()));

		bd.insert("equipamento", null, valores);
	}

	public void atualizar(Equipamento equipamento) {
		ContentValues valores = new ContentValues();
		valores.put("nome_equipamento", equipamento.getNome_equipamento());
		valores.put("volume_selado",String.valueOf(equipamento.getVolume_selado()));
		valores.put("volume_dutado", String.valueOf(equipamento.getVolume_dutado()));
		valores.put("diamentro", String.valueOf(equipamento.getDiamentro()));
		valores.put("comprimento", String.valueOf(equipamento.getComprimento()));

		bd.update("equipamento", valores, "_id_equipamento = ?", new String[] { ""
				+ equipamento.getId_equipamento() });
	}

	public void deletar(Equipamento equipamento) {
		bd.delete("equipamento", "_id_equipamento = " + equipamento.getId_equipamento(), null);
	}

	public List<Equipamento> buscar_all() {
		Log.d("buscar equipamento", "1");
		List<Equipamento> list = new ArrayList<Equipamento>();
		String[] colunas = new String[] { "_id_equipamento", "nome_equipamento",
				"volume_selado", "volume_dutado", "diamentro", "comprimento" };

		//Cursor cursor = bd.query("equipamento", colunas, null, null, null, null,
		//		"nome_equipamento ASC");
				Cursor cursor = bd.query("equipamento", colunas,null, null, null, null,
						null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

			Equipamento u = new Equipamento();
				u.setId_equipamento(cursor.getInt(0));
				u.setNome_equipamento(cursor.getString(1));
				u.setVolume_selado(cursor.getDouble(2));
				u.setVolume_dutado(cursor.getDouble(3));
				u.setDiamentro(cursor.getDouble(4));
				u.setComprimento(cursor.getDouble(5));
				
				list.add(u);

			} while (cursor.moveToNext());
		}
		Log.d("buscar equipamento", "2");
		return (list);
	}
	public List<Equipamento> buscar(String nome) {
		Log.d("buscar equipamento", "1");
		List<Equipamento> list = new ArrayList<Equipamento>();
		String[] colunas = new String[] { "_id_equipamento", "nome_equipamento",
				"volume_selado", "volume_dutado", "diamentro", "comprimento" };

		//Cursor cursor = bd.query("equipamento", colunas, null, null, null, null,
		//		"nome_equipamento ASC");
				Cursor cursor = bd.query("equipamento", colunas, "nome_equipamento like '%" + nome + "%'", null, null, null,
						null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

			Equipamento u = new Equipamento();
				u.setId_equipamento(cursor.getInt(0));
				u.setNome_equipamento(cursor.getString(1));
				u.setVolume_selado(cursor.getDouble(2));
				u.setVolume_dutado(cursor.getDouble(3));
				u.setDiamentro(cursor.getDouble(4));
				u.setComprimento(cursor.getDouble(5));
				
				list.add(u);

			} while (cursor.moveToNext());
		}
		Log.d("buscar equipamento", "2");
		return (list);
	}
	public Equipamento busca_umr(int id) {
		Equipamento u = new Equipamento();
		String[] colunas = new String[] { "_id_equipamento", "nome_equipamento",
				"volume_selado", "volume_dutado", "diamentro", "comprimento" };

		//Cursor cursor = bd.query("equipamento", colunas, null, null, null, null,
		//		"nome_equipamento ASC");
				Cursor cursor = bd.query("equipamento", colunas, "_id_equipamento="+ id, null, null, null,
						null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

			
				u.setId_equipamento(cursor.getInt(0));
				u.setNome_equipamento(cursor.getString(1));
				u.setVolume_selado(cursor.getDouble(2));
				u.setVolume_dutado(cursor.getDouble(3));
				u.setDiamentro(cursor.getDouble(4));
				u.setComprimento(cursor.getDouble(5));
				
				

			} while (cursor.moveToNext());
		}
		
		return u;
	}

}

