package benicio.solutions.wastewiseeats;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import benicio.solutions.wastewiseeats.databinding.ActivityMinhasDoacoesBinding;
import benicio.solutions.wastewiseeats.databinding.ActivityVisualizarMaisInformacoesBinding;
import benicio.solutions.wastewiseeats.databinding.LayoutAlertDialogBinding;
import benicio.solutions.wastewiseeats.model.DoacaoModel;
import benicio.solutions.wastewiseeats.model.FuncionarioModel;
import benicio.solutions.wastewiseeats.util.HackUtils;
import benicio.solutions.wastewiseeats.util.PersistencesUtils;

public class VisualizarMaisInformacoesActivity extends AppCompatActivity {

    private ActivityVisualizarMaisInformacoesBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityVisualizarMaisInformacoesBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        HackUtils.configWindow(this);

        StringBuilder infoBuilder = new StringBuilder();

        if (getIntent().getIntExtra("info", 0) == 0) {
            FuncionarioModel funcionarioModel = (FuncionarioModel) getIntent().getSerializableExtra("func");
            infoBuilder.append("Nome: ").append(funcionarioModel.getNome()).append("\n");
            infoBuilder.append("Cargo: ").append(funcionarioModel.getCargo()).append("\n");
            infoBuilder.append("Documento: ").append(funcionarioModel.getDocuemnto()).append("\n");
            infoBuilder.append("Telefone: ").append(funcionarioModel.getTelefone()).append("\n");
        } else {
            DoacaoModel doacao = (DoacaoModel) getIntent().getSerializableExtra("doacao");
            infoBuilder.append("Nome: ").append(doacao.getNome()).append("\n");
            infoBuilder.append("Horário Retirada: ").append(doacao.getHorarioRetirada()).append("\n");
            infoBuilder.append("Lista de Alimentos: ").append(doacao.getListaAlimentos()).append("\n");
            infoBuilder.append("Aceitação: ").append(doacao.getAceitacao()).append("\n");

            
            mainBinding.aceitar.setVisibility(View.VISIBLE);
            mainBinding.aceitar.setOnClickListener(v -> {
                List<DoacaoModel> doacoesExstentes = PersistencesUtils.returnListDoacao(this);

                for (DoacaoModel d : doacoesExstentes) {
                    if (d.getIdDoacao().equals(doacao.getIdDoacao())) {
                        d.setAceitacao("Sim");
                        AlertDialog.Builder b = new AlertDialog.Builder(VisualizarMaisInformacoesActivity.this);
                        LayoutAlertDialogBinding dialogBinding = LayoutAlertDialogBinding.inflate(getLayoutInflater());
                        dialogBinding.dialogMsg.setText("Doação aceita \n" +
                                "com sucesso!");
                        b.setCancelable(false);
                        b.setView(dialogBinding.getRoot());
                        b.create().show();
                        PersistencesUtils.saveListDoacoes(doacoesExstentes, this);

                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(3000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }

                                runOnUiThread(() -> finish());
                            }
                        }.start();


                    }
                }
            });
        }


        mainBinding.textInfo.setText(infoBuilder.toString());

        mainBinding.voltar.setOnClickListener(v -> finish());


    }
}