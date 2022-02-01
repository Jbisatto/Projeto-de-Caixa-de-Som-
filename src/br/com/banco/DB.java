package br.com.banco;

import br.com.dao.VeiculoDAO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB extends SQLiteOpenHelper {
	private static String Nome_Base = "projetoCaixa";
	private static int versao_banco = 2;
	String sql = "";

	public DB(Context context) {

		super(context, Nome_Base, null, versao_banco);
		Log.d("Jeffe", "21");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		sql = "create table veiculo"
				+ "(_id_veiculo integer primary key autoincrement, "
				+ "nome_veiculo text not null,"
				+ " 'categoria_veiculo' CHAR ,largura float,"
				+ " comprimento float, altura float);";
		db.execSQL(sql);
		sql = "create table equipamento"
				+ "(_id_equipamento integer primary key autoincrement, "
				+ "nome_equipamento text not null," + " volume_selado float,"
				+ " volume_dutado float," + " diamentro float,"
				+ " comprimento float);";
		db.execSQL(sql);

		sql = "create table caixa"
				+ "(_id_caixa integer primary key autoincrement, "
				+ "_id_veiculo integer,"
				+ "_id_equipamento integer,"
				+ "'tipo' CHAR,"
				+ "espMadeira integer,"
				+ "comprimento float,"
				+ "altura float,"
				+ "larguraInf float,"
				+ "larguraSup float,"
				+ "dutoDiamentro float,"
				+ "dutoComprimento float,"
				+ "vInterno float,"
				+ "vExterno float,FOREIGN KEY(_id_veiculo) REFERENCES veiculo(_id_veiculo),"
				+ "FOREIGN KEY(_id_equipamento) REFERENCES equipamento(_id_equipamento));";
		db.execSQL(sql);

		Log.d("Fey", "DB->onCreate() = " + db.getVersion());

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("Fey", "DB->onUpgrade() = " + db.getVersion() + " " + oldVersion
				+ "  " + newVersion);
		if (oldVersion == 1) {
			sql = "create table caixa"
					+ "(_id_caixa integer primary key autoincrement, "
					+ "_id_veiculo integer,"
					+ "_id_equipamento integer,"
					+ "'tipo' CHAR,"
					+ "espMadeira integer,"
					+ "comprimento float,"
					+ "altura float,"
					+ "larguraInf float,"
					+ "larguraSup float,"
					+ "dutoDiamentro float,"
					+ "dutoComprimento float,"
					+ "vInterno float,"
					+ "vExterno float,FOREIGN KEY(_id_veiculo) REFERENCES veiculo(_id_veiculo),"
					+ "FOREIGN KEY(_id_equipamento) REFERENCES equipamento(_id_equipamento));";
			db.execSQL(sql);
		}
	}

}
