package br.com.jmsstudio.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.helper.ImageHelper;
import br.com.jmsstudio.agenda.model.Aluno;

/**
 * Created by jms on 23/12/16.
 */
public class ListAlunosAdapter extends BaseAdapter {
    private List<Aluno> alunos;
    private Context context;

    public ListAlunosAdapter(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_item_aluno, parent, false);
        }

        TextView textViewNome = (TextView) view.findViewById(R.id.item_nome);
        TextView textViewTelefone = (TextView) view.findViewById(R.id.item_telefone);
        TextView textViewEndereco = (TextView) view.findViewById(R.id.item_endereco);
        TextView textViewSite = (TextView) view.findViewById(R.id.item_site);
        ImageView imageViewAluno = (ImageView) view.findViewById(R.id.item_foto);

        textViewNome.setText(aluno.getNome());

        textViewTelefone.setText(aluno.getTelefone());

        if (textViewEndereco != null) {
            textViewEndereco.setText(aluno.getEndereco());
        }

        if (textViewSite != null) {
            textViewSite.setText(aluno.getSite());
        }

        ImageHelper.loadImageIntoView(aluno.getFotoPath(), 100, 100, imageViewAluno, false);

        return view;
    }
}
