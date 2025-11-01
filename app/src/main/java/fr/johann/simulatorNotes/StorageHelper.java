package fr.johann.simulatorNotes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class StorageHelper {

    private static final String PREF_NAME = "notes_prefs";
    private static final String KEY_UE_LIST = "ue_list";

    public static void saveUeList(Context context, List<Ue> ueList) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(ueList);
        editor.putString(KEY_UE_LIST, json);
        editor.apply();
    }

    public static List<Ue> loadUeList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_UE_LIST, null);

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Ue>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
