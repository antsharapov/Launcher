package aero.basel.aaq.launcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LauncherActivity extends Activity {
    String pass="fm89034uwe", password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_launcher);

        Button kobra_btn = (Button) findViewById(R.id.kobra_button);
        Button sbp_btn = (Button) findViewById(R.id.blueprint_button);
        Button launcher_btn = (Button) findViewById(R.id.launcher_button);

        kobra_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("aero.basel.aaq.kobramobile");
                startActivity(intent);
            }
        });
        sbp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("aero.basel.aaq.serviceblueprint");
                startActivity(intent);
            }
        });

        launcher_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LauncherActivity.this);
                alertDialog.setTitle("Авторизация");
                alertDialog.setMessage("Введите пароль:");

                final EditText input = new EditText(LauncherActivity.this);
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
                                        Intent intent = new Intent(LauncherActivity.this, ApplistActivity.class);
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