package benicio.solutions.wastewiseeats.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.solutions.wastewiseeats.MainActivity;
import benicio.solutions.wastewiseeats.R;
import benicio.solutions.wastewiseeats.VisualizarMaisInformacoesActivity;
import benicio.solutions.wastewiseeats.databinding.LayoutDoacaoBinding;
import benicio.solutions.wastewiseeats.model.FuncionarioModel;

public class AdapterFuncionario extends RecyclerView.Adapter<AdapterFuncionario.FuncionarioViewHolder> {

    List<FuncionarioModel> lista;
    Activity c;

    public AdapterFuncionario(List<FuncionarioModel> lista, Activity c) {
        this.lista = lista;
        this.c = c;
    }

    @NonNull
    @Override
    public FuncionarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FuncionarioViewHolder(LayoutDoacaoBinding.inflate(c.getLayoutInflater()).getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FuncionarioViewHolder holder, int position) {
        FuncionarioModel funcionarioModel = lista.get(position);

        holder.nome.setText("Nome:\n" + funcionarioModel.getNome());
        holder.data.setText("Cargo:\n" + funcionarioModel.getCargo());
        holder.aceitacao.setText("Telefone:\n" + funcionarioModel.getTelefone());

        holder.visualizar.setOnClickListener(v -> {
            Intent i = new Intent(c, VisualizarMaisInformacoesActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("func", funcionarioModel);
            i.putExtra("info", 0);
            c.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public final class FuncionarioViewHolder extends RecyclerView.ViewHolder {

        TextView nome, data, aceitacao, visualizar;
        public FuncionarioViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.text_nome);
            data = itemView.findViewById(R.id.text_data);
            aceitacao = itemView.findViewById(R.id.text_aceitacao);
            visualizar = itemView.findViewById(R.id.visualizarItem);
        }
    }
}
