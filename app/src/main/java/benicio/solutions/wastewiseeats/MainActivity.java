package benicio.solutions.wastewiseeats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import benicio.solutions.wastewiseeats.databinding.ActivityMainBinding;
import benicio.solutions.wastewiseeats.model.UserModel;
import benicio.solutions.wastewiseeats.util.HackUtils;
import benicio.solutions.wastewiseeats.util.PersistencesUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        HackUtils.configWindow(this);

        mainBinding.criarConta.setOnClickListener(v -> startActivity(new Intent(this, CriarContaActivity.class)));

        mainBinding.entrar.setOnClickListener(v -> {
            String email, senha;

            email = mainBinding.email.getText().toString().trim();
            senha = mainBinding.senha.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha o E-mail e a senha!", Toast.LENGTH_SHORT).show();
            } else {
                List<UserModel> listaUsuarios = PersistencesUtils.returnList(this);

                boolean encontrado = false;

                for (UserModel user : listaUsuarios) {
                    if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                        Toast.makeText(this, "Bem-Vindo de Volta!", Toast.LENGTH_SHORT).show();
                        finish();
                        encontrado = true;
                        Intent i = new Intent(this, MinhasDoacoesActivity.class);
                        i.putExtra("user", user);
                        startActivity(i);
                    }
                }


                if (!encontrado) {
                    Toast.makeText(this, "E-mail ou Senha Incorreto(s)!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}