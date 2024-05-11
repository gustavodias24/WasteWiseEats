package benicio.solutions.wastewiseeats.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.solutions.wastewiseeats.R;
import benicio.solutions.wastewiseeats.VisualizarMaisInformacoesActivity;
import benicio.solutions.wastewiseeats.databinding.LayoutDoacaoBinding;
import benicio.solutions.wastewiseeats.model.DoacaoModel;

public class AdapterDoacao extends RecyclerView.Adapter<AdapterDoacao.DoacaoViewHolder> {

    List<DoacaoModel> lista;
    Activity c;

    public AdapterDoacao(List<DoacaoModel> lista, Activity c) {
        this.lista = lista;
        this.c = c;
    }

    @NonNull
    @Override
    public DoacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoacaoViewHolder(LayoutDoacaoBinding.inflate(c.getLayoutInflater()).getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DoacaoViewHolder holder, int position) {
        DoacaoModel doacaoModel = lista.get(position);

        holder.nome.setText("Nome:\n" + doacaoModel.getNome());
        holder.data.setText("Data:\n" + doacaoModel.getData());
        holder.aceitacao.setText("Aceitação:\n" + doacaoModel.getAceitacao());

        holder.visualizar.setOnClickListener(v -> {
            Intent i = new Intent(c, VisualizarMaisInformacoesActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("doacao", doacaoModel);
            i.putExtra("info", 1);
            c.startActivity(i);
        });


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public final class DoacaoViewHolder extends RecyclerView.ViewHolder {

        TextView nome, data, aceitacao, visualizar;

        public DoacaoViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.text_nome);
            data = itemView.findViewById(R.id.text_data);
            aceitacao = itemView.findViewById(R.id.text_aceitacao);
            visualizar = itemView.findViewById(R.id.visualizarItem);
        }
    }
}
