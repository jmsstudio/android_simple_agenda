package br.com.jmsstudio.agenda.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.jmsstudio.agenda.converter.AlunoConverter;
import br.com.jmsstudio.agenda.dao.AlunoDAO;
import br.com.jmsstudio.agenda.model.Aluno;
import br.com.jmsstudio.agenda.util.WebClientUtil;

/**
 * Created by jms on 28/12/16.
 */
public class AlunoRequestTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog progressDialog;

    public AlunoRequestTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Enviando dados", "Os dados estão sendo enviados...", true, true);
    }

    @Override
    protected String doInBackground(Void... voids) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.listAlunos();
        dao.close();

        AlunoConverter converter = new AlunoConverter();
        String jsonAlunos = converter.toJSON(alunos);

        return WebClientUtil.sendJsonData(jsonAlunos);
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}
