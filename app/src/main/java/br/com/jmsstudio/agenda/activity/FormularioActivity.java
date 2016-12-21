package br.com.jmsstudio.agenda.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.dao.AlunoDAO;
import br.com.jmsstudio.agenda.helper.FormularioHelper;
import br.com.jmsstudio.agenda.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    public static final int REQUEST_CAMERA = 1;
    private FormularioHelper formularioHelper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button btnFoto = (Button) findViewById(R.id.formulario_btn_foto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);


                intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentFoto, REQUEST_CAMERA);
            }
        });

        formularioHelper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null) {
            formularioHelper.preencheFormulario(aluno);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            ImageView formularioFoto = (ImageView) findViewById(R.id.formulario_foto);
            Bitmap imagem = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 300, 300, true);
            formularioFoto.setImageBitmap(imagemReduzida);
            formularioFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
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

                AlunoDAO dao = new AlunoDAO(this);
                if (aluno.getId() == null) {
                    dao.insert(aluno);
                } else {
                    dao.update(aluno);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
