package benicio.solutions.wastewiseeats.util;

import android.app.Activity;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDelegate;

public class HackUtils {

    public static void configWindow(Activity context) {
        context.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
