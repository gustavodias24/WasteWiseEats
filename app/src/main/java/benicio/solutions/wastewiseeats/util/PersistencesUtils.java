package benicio.solutions.wastewiseeats.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import benicio.solutions.wastewiseeats.model.DoacaoModel;
import benicio.solutions.wastewiseeats.model.FuncionarioModel;
import benicio.solutions.wastewiseeats.model.UserModel;

public class PersistencesUtils {
    public static final String PREFS_NAME = "user_prefs";
    public static final String KEY_USER = "user_prefs";
    public static final String KEY_USER_FUNCIONARIO = "funcionario_prefs";
    public static final String KEY_USER_DOACAO = "doacao_prefs";


    public static void saveList(List<UserModel> list, Context c) {
        SharedPreferences preferences = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER, new Gson().toJson(list)).apply();
    }

    public static List<UserModel> returnList(Context c) {
        SharedPreferences preferences = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        List<UserModel> lista = new Gson().fromJson(preferences.getString(KEY_USER, ""), new TypeToken<List<UserModel>>() {
        }.getType());
        if (lista == null) {
            lista = new ArrayList<>();
        }

        return lista;
    }

    public static void saveListFuncionarios(List<FuncionarioModel> list, Context c) {
        SharedPreferences preferences = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_FUNCIONARIO, new Gson().toJson(list)).apply();
    }

    public static List<FuncionarioModel> returnListFuncionario(Context c) {
        SharedPreferences preferences = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        List<FuncionarioModel> lista = new Gson().fromJson(preferences.getString(KEY_USER_FUNCIONARIO, ""), new TypeToken<List<FuncionarioModel>>() {
        }.getType());
        if (lista == null) {
            lista = new ArrayList<>();
        }

        return lista;
    }

    public static void saveListDoacoes(List<DoacaoModel> list, Context c) {
        SharedPreferences preferences = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_DOACAO, new Gson().toJson(list)).apply();
    }

    public static List<DoacaoModel> returnListDoacao(Context c) {
        SharedPreferences preferences = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        List<DoacaoModel> lista = new Gson().fromJson(preferences.getString(KEY_USER_DOACAO, ""), new TypeToken<List<DoacaoModel>>() {
        }.getType());
        if (lista == null) {
            lista = new ArrayList<>();
        }

        return lista;
    }
}
