package benicio.solutions.wastewiseeats;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import benicio.solutions.wastewiseeats.databinding.ActivityGraficoBinding;
import benicio.solutions.wastewiseeats.databinding.ActivityMainBinding;
import benicio.solutions.wastewiseeats.model.DoacaoModel;
import benicio.solutions.wastewiseeats.util.HackUtils;
import benicio.solutions.wastewiseeats.util.PersistencesUtils;

public class GraficoActivity extends AppCompatActivity {

    private ActivityGraficoBinding mainBinding;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityGraficoBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        HackUtils.configWindow(this);

        mainBinding.voltar.setOnClickListener( v -> finish());

        configurarGrafico();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void configurarGrafico() {
// Criar um conjunto de dados de exemplo
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, PersistencesUtils.returnListDoacao(this).size()));

        BarDataSet dataSet = new BarDataSet(entries, "Quantidade de Doações"); // Adicione aqui o rótulo desejado para o conjunto de dados

// Adicionar cores aos valores
        dataSet.setColor(ContextCompat.getColor(this, R.color.ciano));

// Configurar o conjunto de dados
        BarData data = new BarData(dataSet);
        mainBinding.chart.setData(data);

// Configurar o estilo do gráfico
        mainBinding.chart.setFitBars(true);
        mainBinding.chart.getDescription().setEnabled(false);
        mainBinding.chart.animateY(1000);

        mainBinding.chart.invalidate();




        mainBinding.chart.invalidate();
    }
}