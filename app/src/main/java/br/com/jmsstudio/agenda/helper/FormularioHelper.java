package br.com.jmsstudio.agenda.helper;

import android.widget.RatingBar;
import android.widget.TextView;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.activity.FormularioActivity;
import br.com.jmsstudio.agenda.model.Aluno;

/**
 * Created by jms on 05/12/16.
 */
public class FormularioHelper {

    private FormularioActivity activity;
    private Aluno aluno;
    private TextView nomeField;
    private TextView enderecoField;
    private TextView telefoneField;
    private TextView siteField;
    private RatingBar notaField;

    public FormularioHelper(FormularioActivity activity) {
        this.activity = activity;

        nomeField = (TextView) this.activity.findViewById(R.id.formulario_nome);
        enderecoField = (TextView) this.activity.findViewById(R.id.formulario_endereco);
        telefoneField = (TextView) this.activity.findViewById(R.id.formulario_telefone);
        siteField = (TextView) this.activity.findViewById(R.id.formulario_website);
        notaField = (RatingBar) this.activity.findViewById(R.id.formulario_nota);

        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(nomeField.getText().toString());
        aluno.setEndereco(enderecoField.getText().toString());
        aluno.setTelefone(telefoneField.getText().toString());
        aluno.setSite(siteField.getText().toString());
        aluno.setNota(Double.valueOf(notaField.getProgress()));

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        this.aluno = aluno;

        nomeField.setText(this.aluno.getNome());
        enderecoField.setText(this.aluno.getEndereco());
        telefoneField.setText(this.aluno.getTelefone());
        siteField.setText(this.aluno.getSite());
        notaField.setProgress(this.aluno.getNota().intValue());
    }
}
