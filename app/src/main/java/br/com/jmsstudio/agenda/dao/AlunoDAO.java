package br.com.jmsstudio.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.jmsstudio.agenda.model.Aluno;

/**
 * Created by jms on 05/12/16.
 */

public class AlunoDAO extends SQLiteOpenHelper {
    public AlunoDAO(Context context) {
        super(context, "agenda", null, 1);
    }

    private final String tableName = "aluno";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String sql = "CREATE TABLE " + tableName  +
                " (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String sql = "DROP TABLE IF EXISTS " + tableName;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("nome", aluno.getNome());
        data.put("endereco", aluno.getEndereco());
        data.put("telefone", aluno.getTelefone());
        data.put("site", aluno.getSite());
        data.put("nota", aluno.getNota());

        db.insert(tableName, null, data);
    }

    public List<Aluno> listAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + tableName;

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

            alunos.add(aluno);
        }

        cursor.close();

        return alunos;
    }

    public void remove(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = new String[]{aluno.getId().toString()};
                
        db.delete(tableName, "id = ?", params);
    }
}
