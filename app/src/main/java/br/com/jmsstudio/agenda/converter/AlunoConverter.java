package br.com.jmsstudio.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import br.com.jmsstudio.agenda.model.Aluno;

/**
 * Created by jms on 28/12/16.
 */
public class AlunoConverter {
    public String toJSON(Iterable<Aluno> alunos) {
        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer
            .object().key("list")
                .array()
                    .object().key("aluno")
                        .array();

            for (Aluno aluno: alunos) {
                jsonStringer.object()
                    .key("id").value(aluno.getId())
                    .key("nome").value(aluno.getNome())
                    .key("endereco").value(aluno.getEndereco())
                    .key("telefone").value(aluno.getTelefone())
                    .key("site").value(aluno.getSite())
                    .key("nota").value(aluno.getNota())
                .endObject();
            }

            jsonStringer.endArray()
                    .endObject()
                .endArray()
            .endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonStringer.toString();
    }
}
