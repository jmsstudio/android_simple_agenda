package br.com.jmsstudio.agenda.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.helper.FormularioHelper;
import br.com.jmsstudio.agenda.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper formularioHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formularioHelper = new FormularioHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.formulario_menu_ok:
                Aluno aluno = formularioHelper.getAluno();

                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
