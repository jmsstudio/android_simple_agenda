package br.com.jmsstudio.agenda.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.model.Prova;

public class DetalhesProvaFragment extends Fragment {

    TextView tvMateria;
    TextView tvData;
    ListView lvTopicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CardView view = (CardView) inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        tvMateria = (TextView) view.findViewById(R.id.detalhes_prova_materia);
        tvData = (TextView) view.findViewById(R.id.detalhes_prova_data);
        lvTopicos = (ListView) view.findViewById(R.id.detalhes_prova_topicos);

        Bundle parametros = getArguments();
        if (parametros != null) {
            Prova prova = (Prova) parametros.getSerializable("prova");
            popularCampos(prova);
        }

        return view;
    }

    public void popularCampos(Prova prova) {
        tvMateria.setText(prova.getMateria());
        tvData.setText(prova.getDataAsString());

        ArrayAdapter<String> topicosAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, prova.getTopicos());
        lvTopicos.setAdapter(topicosAdapter);
    }

}
