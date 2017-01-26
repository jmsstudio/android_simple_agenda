package br.com.jmsstudio.agenda.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.activity.ProvasActivity;
import br.com.jmsstudio.agenda.model.Prova;

/**
 * Created by jms on 24/01/17.
 */
public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CardView view = (CardView) inflater.inflate(R.layout.fragment_lista_provas, container, false);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date d1 = null;
        Date d2 = null;
        Date d3 = null;

        try {
            d1 = sdf.parse("10/07/2017");
            d2 = sdf.parse("18/06/2017");
            d3 = sdf.parse("05/05/2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<String> topicosIng = Arrays.asList("Verbo to be", "Simple past", "Present perfect");
        List<String> topicosEsp = Arrays.asList("Adjetivos", "Gêneros");
        List<String> topicosInf = Arrays.asList("Teoria da computação", "Engenharia de software", "Programação orientada a objetos");

        Prova provaIng = new Prova("Inglês", d1, topicosIng);
        Prova provaEsp = new Prova("Espanhol", d2, topicosEsp);
        Prova provaInf = new Prova("Informática", d3, topicosInf);

        List<Prova> provas = Arrays.asList(provaIng, provaEsp, provaInf);


        ArrayAdapter<Prova> provasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView listaProvas = (ListView) view.findViewById(R.id.provas_lista);
        listaProvas.setAdapter(provasAdapter);


        listaProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Prova prova = (Prova) adapterView.getItemAtPosition(position);
                Toast.makeText(getContext(), "Clicou na prova de " + prova.getMateria(), Toast.LENGTH_SHORT).show();

                ProvasActivity activity = (ProvasActivity) getActivity();
                activity.selectProva(prova);
            }
        });

        return view;
    }
}
