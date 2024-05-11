package benicio.solutions.wastewiseeats;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import benicio.solutions.wastewiseeats.adapter.AdapterDoacao;
import benicio.solutions.wastewiseeats.databinding.ActivityCriarContaEspecificaBinding;
import benicio.solutions.wastewiseeats.databinding.ActivityMinhasDoacoesBinding;
import benicio.solutions.wastewiseeats.model.DoacaoModel;
import benicio.solutions.wastewiseeats.model.UserModel;
import benicio.solutions.wastewiseeats.util.HackUtils;
import benicio.solutions.wastewiseeats.util.PersistencesUtils;

public class MinhasDoacoesActivity extends AppCompatActivity {

    private ActivityMinhasDoacoesBinding mainBinding;

    private UserModel userAtual;

    private AdapterDoacao adapterDoacao;

    private List<DoacaoModel> listaDoacao = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMinhasDoacoesBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        HackUtils.configWindow(this);

        userAtual = (UserModel) getIntent().getSerializableExtra("user");

        mainBinding.rv.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.rv.setHasFixedSize(true);
        adapterDoacao = new AdapterDoacao(listaDoacao, this);
        mainBinding.rv.setAdapter(adapterDoacao);

        switch (userAtual.getTipoConta()) {
            case 0:// conta do tipo centro de doacao

                break;
            case 1: // conta de dono de restaurante

                mainBinding.title.setText("Desperdícios");
                mainBinding.subTitle.setText("Informações do restaurante");


                mainBinding.layoutRestaurante.setVisibility(View.VISIBLE);
                mainBinding.grafico.setOnClickListener(v -> {
                    Intent i = new Intent(this, GraficoActivity.class);
                    i.putExtra("user", userAtual);
                    startActivity(i);
                });

                mainBinding.novo.setOnClickListener(v -> {
                    Intent i = new Intent(this, CriarDoacaoActivity.class);
                    i.putExtra("user", userAtual);
                    startActivity(i);
                });

                mainBinding.subTitle.setOnClickListener(v -> {
                    Intent i = new Intent(this, InformacaoRestauranteActivity.class);
                    i.putExtra("user", userAtual);
                    startActivity(i);
                });
                break;
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();

        if (adapterDoacao != null) {
            listaDoacao.clear();
            listaDoacao.addAll(PersistencesUtils.returnListDoacao(this));
            adapterDoacao.notifyDataSetChanged();
        }
    }

}