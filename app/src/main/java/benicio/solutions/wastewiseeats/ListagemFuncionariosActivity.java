package benicio.solutions.wastewiseeats;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import benicio.solutions.wastewiseeats.adapter.AdapterFuncionario;
import benicio.solutions.wastewiseeats.databinding.ActivityListagemFuncionariosBinding;
import benicio.solutions.wastewiseeats.databinding.LayoutAlertDialogBinding;
import benicio.solutions.wastewiseeats.databinding.LayoutCriarFuncionarioBinding;
import benicio.solutions.wastewiseeats.databinding.LayoutDoacaoBinding;
import benicio.solutions.wastewiseeats.model.FuncionarioModel;
import benicio.solutions.wastewiseeats.model.UserModel;
import benicio.solutions.wastewiseeats.util.HackUtils;
import benicio.solutions.wastewiseeats.util.PersistencesUtils;

public class ListagemFuncionariosActivity extends AppCompatActivity {

    private ActivityListagemFuncionariosBinding mainBinding;
    private UserModel userAtual;
    private Dialog dialogCdastro;

    private List<FuncionarioModel> listaFuncionarios = new ArrayList<>();
    private AdapterFuncionario adapterFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityListagemFuncionariosBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        HackUtils.configWindow(this);

        userAtual = (UserModel) getIntent().getSerializableExtra("user");

        dialogCdastro = configDialog();

        mainBinding.adicionar.setOnClickListener(v -> dialogCdastro.show());


        mainBinding.rvFuncionarios.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.rvFuncionarios.setHasFixedSize(true);
        adapterFuncionario = new AdapterFuncionario(listaFuncionarios, this);
        mainBinding.rvFuncionarios.setAdapter(adapterFuncionario);

        atualizarListagemFuncionario();

    }


    private Dialog configDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(ListagemFuncionariosActivity.this);

        LayoutCriarFuncionarioBinding layoutCriarFuncionarioBinding = LayoutCriarFuncionarioBinding.inflate(getLayoutInflater());

        layoutCriarFuncionarioBinding.salvar.setOnClickListener(v -> {
            List<FuncionarioModel> listaExistente = PersistencesUtils.returnListFuncionario(this);
            FuncionarioModel novoFuncionario = new FuncionarioModel();

            novoFuncionario.setNome(layoutCriarFuncionarioBinding.nome.getText().toString());
            novoFuncionario.setCargo(layoutCriarFuncionarioBinding.cargo.getText().toString());
            novoFuncionario.setTelefone(layoutCriarFuncionarioBinding.telefone.getText().toString());
            novoFuncionario.setDocuemnto(layoutCriarFuncionarioBinding.documento.getText().toString());
            novoFuncionario.setNomeRestaurante(userAtual.getNome());

            listaExistente.add(novoFuncionario);

            PersistencesUtils.saveListFuncionarios(listaExistente, this);
            dialogCadastroShow();
            layoutCriarFuncionarioBinding.nome.setText("");
            layoutCriarFuncionarioBinding.cargo.setText("");
            layoutCriarFuncionarioBinding.telefone.setText("");
            layoutCriarFuncionarioBinding.documento.setText("");

            atualizarListagemFuncionario();
        });

        b.setView(layoutCriarFuncionarioBinding.getRoot());

        return b.create();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void atualizarListagemFuncionario() {
        listaFuncionarios.clear();

        for (FuncionarioModel f : PersistencesUtils.returnListFuncionario(this)) {
            if (f.getNomeRestaurante().equals(userAtual.getNome())) {
                listaFuncionarios.add(f);
            }
        }

        adapterFuncionario.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    private void dialogCadastroShow() {

        AlertDialog.Builder b = new AlertDialog.Builder(ListagemFuncionariosActivity.this);
        LayoutAlertDialogBinding layoutAlertDialogBinding = LayoutAlertDialogBinding.inflate(getLayoutInflater());
        layoutAlertDialogBinding.dialogMsg.setText("Funcionário cadastrado \n" +
                "com sucesso!");
        b.setCancelable(false);
        b.setView(layoutAlertDialogBinding.getRoot());
        Dialog D = b.create();
        D.show();

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
                    D.dismiss();
                    dialogCdastro.dismiss();
                });
            }
        }.start();
    }
}