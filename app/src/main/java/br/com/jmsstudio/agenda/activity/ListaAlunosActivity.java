package br.com.jmsstudio.agenda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.dao.AlunoDAO;
import br.com.jmsstudio.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        Button btnNovoAluno = (Button) findViewById(R.id.lst_alunos_btn_incluir);
        btnNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadAlunos() {
        List<Aluno> alunos;

        AlunoDAO dao = new AlunoDAO(this);
        alunos = dao.listAlunos();
        dao.close();


        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        ArrayAdapter<Aluno> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadAlunos();
    }
}
