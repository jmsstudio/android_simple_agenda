package br.com.jmsstudio.agenda.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.fragment.DetalhesProvaFragment;
import br.com.jmsstudio.agenda.fragment.ListaProvasFragment;
import br.com.jmsstudio.agenda.model.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ftx = fragmentManager.beginTransaction();

        ftx.replace(R.id.provas_main_frame, new ListaProvasFragment());

        if (isLandscapeOrientation()) {
            ftx.replace(R.id.provas_second_frame, new DetalhesProvaFragment());
        }

        ftx.commit();
    }

    private boolean isLandscapeOrientation() {
        return getResources().getBoolean(R.bool.isLandscape);
    }

    public void selectProva(Prova prova) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        DetalhesProvaFragment detalhesProvaFragment;

        FragmentTransaction fragmentTx = fragmentManager.beginTransaction();

        if (!isLandscapeOrientation()) {
            detalhesProvaFragment = new DetalhesProvaFragment();

            Bundle parametros = new Bundle();
            parametros.putSerializable("prova", prova);

            detalhesProvaFragment.setArguments(parametros);

            fragmentTx.replace(R.id.provas_main_frame, detalhesProvaFragment);
            fragmentTx.addToBackStack(null);
        } else {
            detalhesProvaFragment = (DetalhesProvaFragment) fragmentManager.findFragmentById(R.id.provas_second_frame);
            detalhesProvaFragment.popularCampos(prova);
        }

        fragmentTx.commit();
    }
}
