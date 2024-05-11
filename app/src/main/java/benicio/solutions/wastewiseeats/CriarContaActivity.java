package benicio.solutions.wastewiseeats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import benicio.solutions.wastewiseeats.databinding.ActivityCriarContaBinding;
import benicio.solutions.wastewiseeats.databinding.ActivityMainBinding;
import benicio.solutions.wastewiseeats.model.UserModel;
import benicio.solutions.wastewiseeats.util.HackUtils;

public class CriarContaActivity extends AppCompatActivity {

    private ActivityCriarContaBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCriarContaBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        HackUtils.configWindow(this);

        mainBinding.salvar.setOnClickListener(v -> Toast.makeText(this, "Salvo, prossiga.", Toast.LENGTH_SHORT).show());

        mainBinding.donoRestauranteBtn.setOnClickListener(v -> irParaContaEspecifica("Restaurante"));
        mainBinding.centroDoacao.setOnClickListener(v -> irParaContaEspecifica("Centro de doação"));

    }


    private void irParaContaEspecifica(String tipoConta) {
        Intent i = new Intent(this, CriarContaEspecificaActivity.class);
        i.putExtra("tipoConta", tipoConta);

        String email, nome, senha, confirmarSenha;

        email = mainBinding.email.getText().toString().trim();
        nome = mainBinding.nome.getText().toString();
        senha = mainBinding.senha.getText().toString().trim();
        confirmarSenha = mainBinding.confirmarSenha.getText().toString().trim();

        if (
                email.isEmpty() ||
                        nome.isEmpty() ||
                        senha.isEmpty() ||
                        confirmarSenha.isEmpty()
        ) {
            Toast.makeText(this, "Preencha Todas as Informações!", Toast.LENGTH_SHORT).show();
        } else {
            if (!senha.equals(confirmarSenha)) {
                Toast.makeText(this, "As Duas Senhas Precisam Ser Iguais", Toast.LENGTH_SHORT).show();
            } else {
                UserModel userModel = new UserModel();
                userModel.setEmail(email);
                userModel.setNome(nome);
                userModel.setSenha(senha);

                i.putExtra("user", userModel);
                startActivity(i);
            }
        }

    }
}