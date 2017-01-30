package br.com.jmsstudio.agenda.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.jmsstudio.agenda.dao.AlunoDAO;
import br.com.jmsstudio.agenda.helper.LocatorHelper;
import br.com.jmsstudio.agenda.model.Aluno;

/**
 * Created by jms on 26/01/17.
 */
public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng coordenada = getCoordenadaDoEndereco("Santa Bárbara d'Oeste, SP, Brasil");

        if (coordenada != null) {
            centerMap(coordenada);
        }

        markAdressesOnMap(googleMap);
        new LocatorHelper(getContext(), this);
    }

    private void markAdressesOnMap(GoogleMap googleMap) {
        AlunoDAO alunoDAO = new AlunoDAO(getContext());
        List<Aluno> alunos = alunoDAO.listAlunos();

        for (Aluno aluno: alunos) {
            LatLng coordenadaAluno = getCoordenadaDoEndereco(aluno.getEndereco());
            if (coordenadaAluno != null) {
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenadaAluno);
                marcador.title(aluno.getNome());
                marcador.snippet(String.valueOf(aluno.getNota()));
                googleMap.addMarker(marcador);
            }
        }

        alunoDAO.close();
    }

    private LatLng getCoordenadaDoEndereco(String endereco) {
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addresses;
        LatLng coordenada = null;
        try {
            addresses = geocoder.getFromLocationName(endereco, 1);

            if (!addresses.isEmpty()) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();

                coordenada = new LatLng(latitude, longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coordenada;
    }

    public void centerMap(LatLng coordenada) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coordenada, 15);
        this.googleMap.moveCamera(update);
    }
}
