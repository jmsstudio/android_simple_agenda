package br.com.jmsstudio.agenda.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.adapter.ListAlunosAdapter;
import br.com.jmsstudio.agenda.dao.AlunoDAO;
import br.com.jmsstudio.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

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

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);

                Intent intentFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intentFormulario.putExtra("aluno", aluno);

                startActivity(intentFormulario);
            }
        });

        registerForContextMenu(listaAlunos);
    }

    private void loadAlunos() {
        List<Aluno> alunos;

        AlunoDAO dao = new AlunoDAO(this);
        alunos = dao.listAlunos();
        dao.close();

        ListAlunosAdapter listAlunosAdapter = new ListAlunosAdapter(alunos, this);
        listaAlunos.setAdapter(listAlunosAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, final View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

        String site = aluno.getSite().trim().length() > 0 ? aluno.getSite().trim() : "";

        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }

        MenuItem itemVisitarSite = menu.add("Visitar site");
        Intent intentVisitarSite = new Intent(Intent.ACTION_VIEW, Uri.parse(site));
        itemVisitarSite.setIntent(intentVisitarSite);

        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aluno.getTelefone().trim()));
                    startActivity(intentLigar);
                }
                return false;
            }
        });


        MenuItem itemSMS = menu.add("Enviar mensagem");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + aluno.getTelefone().trim()));
        itemSMS.setIntent(intentSMS);

        MenuItem itemMapa = menu.add("Encontrar no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + aluno.getEndereco().trim()));
        itemMapa.setIntent(intentMapa);


        MenuItem itemRemover = menu.add("Remover");
        itemRemover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.remove(aluno);
                dao.close();

                loadAlunos();

                return false;
            }
        });
    }
}
