package aero.basel.aaq.launcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class PreferencesActivity extends Activity {
String pass="fm89034uwe", password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Button pref_button = (Button) findViewById(R.id.pref_button);
        pref_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PreferencesActivity.this);
                alertDialog.setTitle("Авторизация");
                alertDialog.setMessage("Введите пароль:");

                final EditText input = new EditText(PreferencesActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Ок",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                password = input.getText().toString();
                                if (password.compareTo("") != 0) {
                                    if (pass.equals(password)) {
                                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.settings");
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Неверный пароль", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

                alertDialog.setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }

        });
    }
}
