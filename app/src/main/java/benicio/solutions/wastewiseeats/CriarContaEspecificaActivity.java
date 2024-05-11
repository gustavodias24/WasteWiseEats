package benicio.solutions.wastewiseeats;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import benicio.solutions.wastewiseeats.databinding.ActivityCriarContaBinding;
import benicio.solutions.wastewiseeats.databinding.ActivityCriarContaEspecificaBinding;
import benicio.solutions.wastewiseeats.databinding.ActivityMainBinding;
import benicio.solutions.wastewiseeats.databinding.LayoutAlertDialogBinding;
import benicio.solutions.wastewiseeats.model.UserModel;
import benicio.solutions.wastewiseeats.util.HackUtils;
import benicio.solutions.wastewiseeats.util.PersistencesUtils;

public class CriarContaEspecificaActivity extends AppCompatActivity {


    private ActivityCriarContaEspecificaBinding mainBinding;
    private Bundle b;
    private UserModel userModel;
    private String tipoConta;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCriarContaEspecificaBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        HackUtils.configWindow(this);
        b = getIntent().getExtras();

        assert b != null;
        userModel = (UserModel) getIntent().getSerializableExtra("user");
        tipoConta = b.getString("tipoConta", "");

        mainBinding.tipoConta.setText(tipoConta);

        if (tipoConta.equals("Restaurante")) {
            userModel.setTipoConta(1);
            mainBinding.especificoCentro.setVisibility(View.GONE);
        } else {
            userModel.setTipoConta(0);
            mainBinding.especificoRestaurante.setVisibility(View.GONE);
        }

        mainBinding.salvar.setOnClickListener(v -> {
            String proprietario, documento, tele, horarioInicio, horarioFim, horarioRetirada, docRestaurante;
            String rua, numero, bairro, cep, cidade, estado;

            proprietario = mainBinding.proprietario.getText().toString();
            documento = mainBinding.documento.getText().toString();
            tele = mainBinding.tele.getText().toString();
            horarioInicio = mainBinding.horarioInicio.getText().toString();
            horarioFim = mainBinding.horarioFim.getText().toString();
            horarioRetirada = mainBinding.horarioRetirada.getText().toString();
            docRestaurante = mainBinding.docRestaurante.getText().toString();
            rua = mainBinding.rua.getText().toString();
            numero = mainBinding.numero.getText().toString();
            bairro = mainBinding.bairro.getText().toString();
            cep = mainBinding.cep.getText().toString();
            cidade = mainBinding.cidade.getText().toString();
            estado = mainBinding.estado.getText().toString();

            userModel.setProprietario(proprietario);
            userModel.setDocumento(documento);
            userModel.setTele(tele);
            userModel.setHorarioInicio(horarioInicio);
            userModel.setHorarioFim(horarioFim);
            userModel.setHorarioRetirada(horarioRetirada);
            userModel.setDocumentoRestaturante(docRestaurante);
            userModel.setRua(rua);
            userModel.setNumero(numero);
            userModel.setBairro(bairro);
            userModel.setCep(cep);
            userModel.setCidade(cidade);
            userModel.setEstado(estado);

            List<UserModel> listaExistente = PersistencesUtils.returnList(this);
            listaExistente.add(userModel);
            PersistencesUtils.saveList(listaExistente, this);


            AlertDialog.Builder b = new AlertDialog.Builder(CriarContaEspecificaActivity.this);
            b.setView(LayoutAlertDialogBinding.inflate(getLayoutInflater()).getRoot());
            b.setCancelable(false);
            b.create().show();

            new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    runOnUiThread(() -> {
                        finish();
                        Intent i = new Intent(CriarContaEspecificaActivity.this, MinhasDoacoesActivity.class);
                        i.putExtra("user" , userModel);
                        startActivity(i);
                    });
                }
            }.start();
        });


    }
}