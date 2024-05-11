package benicio.solutions.wastewiseeats;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import benicio.solutions.wastewiseeats.databinding.ActivityInformacaoRestauranteBinding;
import benicio.solutions.wastewiseeats.model.UserModel;
import benicio.solutions.wastewiseeats.util.HackUtils;

public class InformacaoRestauranteActivity extends AppCompatActivity {

    private ActivityInformacaoRestauranteBinding mainBinding;
    private UserModel userAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityInformacaoRestauranteBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        HackUtils.configWindow(this);

        userAtual = (UserModel) getIntent().getSerializableExtra("user");

        StringBuilder builderInfo = new StringBuilder();

        builderInfo.append("Nome: ").append(userAtual.getNome()).append("\n");
        builderInfo.append("Proprietário: ").append(userAtual.getProprietario()).append("\n");
        builderInfo.append("Documento: ").append(userAtual.getDocumento()).append("\n");
        builderInfo.append("Telefone: ").append(userAtual.getTele()).append("\n");
        builderInfo.append("E-mail: ").append(userAtual.getEmail()).append("\n");

        builderInfo.append("Horário de Doações: ").append(userAtual.getHorarioRetirada()).append("\n");

        builderInfo.append("\n\n").append("Endereço:").append("\n");
        builderInfo.append("Rua: ").append(userAtual.getRua()).append("\n");
        builderInfo.append("Número: ").append(userAtual.getNumero()).append("\n");
        builderInfo.append("Bairro: ").append(userAtual.getBairro()).append("\n");
        builderInfo.append("Cidade: ").append(userAtual.getCidade()).append("\n");
        builderInfo.append("Estado: ").append(userAtual.getEstado()).append("\n");
        builderInfo.append("Cep: ").append(userAtual.getCep()).append("\n");

        mainBinding.infoRestauranteText.setText(builderInfo.toString());

        mainBinding.btnListaFuncionario.setOnClickListener(v -> {
            Intent i = new Intent(this, ListagemFuncionariosActivity.class);
            i.putExtra("user", userAtual);
            startActivity(i);
        });
    }
}