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

    public FormularioHelper(FormularioActivity activity) {
        this.activity = activity;
    }

    public Aluno getAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(getEditTextFieldValue(R.id.formulario_nome));
        aluno.setEndereco(getEditTextFieldValue(R.id.formulario_endereco));
        aluno.setTelefone(getEditTextFieldValue(R.id.formulario_telefone));
        aluno.setSite(getEditTextFieldValue(R.id.formulario_website));
        aluno.setNota(getRatingBarFieldValue(R.id.formulario_nota));

        return aluno;
    }

    private String getEditTextFieldValue(Integer field) {
        return ((TextView) this.activity.findViewById(field)).getText().toString();
    }

    private Double getRatingBarFieldValue(Integer field) {
        return Double.valueOf(((RatingBar) this.activity.findViewById(field)).getProgress());
    }
}
