package benicio.solutions.wastewiseeats;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.UUID;

import benicio.solutions.wastewiseeats.databinding.ActivityCriarDoacaoBinding;
import benicio.solutions.wastewiseeats.databinding.ActivityMinhasDoacoesBinding;
import benicio.solutions.wastewiseeats.databinding.LayoutAlertDialogBinding;
import benicio.solutions.wastewiseeats.model.DoacaoModel;
import benicio.solutions.wastewiseeats.model.UserModel;
import benicio.solutions.wastewiseeats.util.HackUtils;
import benicio.solutions.wastewiseeats.util.PersistencesUtils;

public class CriarDoacaoActivity extends AppCompatActivity {

    private ActivityCriarDoacaoBinding mainBinding;

    private UserModel userAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCriarDoacaoBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        HackUtils.configWindow(this);


        mainBinding.voltar.setOnClickListener(v -> finish());

        userAtual = (UserModel) getIntent().getSerializableExtra("user");

        mainBinding.salvar.setOnClickListener(v -> {
            DoacaoModel doacaoModel = new DoacaoModel();
            doacaoModel.setIdDoacao(UUID.randomUUID().toString());
            doacaoModel.setEmailDoador(userAtual.getEmail());
            doacaoModel.setAceitacao("Não");

            doacaoModel.setNome(mainBinding.nome.getText().toString());
            doacaoModel.setData(mainBinding.data.getText().toString());
            doacaoModel.setListaAlimentos(mainBinding.listaDeAlimentos.getText().toString());
            doacaoModel.setHorarioRetirada(mainBinding.horarioRetirada.getText().toString());

            List<DoacaoModel> listaDoacao = PersistencesUtils.returnListDoacao(this);
            listaDoacao.add(doacaoModel);
            PersistencesUtils.saveListDoacoes(listaDoacao, this);

            AlertDialog.Builder b = new AlertDialog.Builder(CriarDoacaoActivity.this);
            LayoutAlertDialogBinding alertDialogBinding = LayoutAlertDialogBinding.inflate(getLayoutInflater());
            alertDialogBinding.dialogMsg.setText("Doação criada \n" +
                    "com sucesso!");
            b.setCancelable(false);
            b.setView(alertDialogBinding.getRoot());
            b.create().show();

            new Thread(){
                @Override
                public void run() {
                    super.run();

                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    runOnUiThread( () -> finish());
                }
            }.start();

        });

    }
}