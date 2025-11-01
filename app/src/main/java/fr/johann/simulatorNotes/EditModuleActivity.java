package fr.johann.simulatorNotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditModuleActivity extends AppCompatActivity {

    private TextView moduleName, moduleCoefficient;
    private EditText moduleGrade;
    private Button saveButton;
    private Module module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_module);

        moduleName = findViewById(R.id.moduleName);
        moduleCoefficient = findViewById(R.id.moduleCoefficient);
        moduleGrade = findViewById(R.id.moduleGrade);
        saveButton = findViewById(R.id.saveButton);

        // Récupérer les données envoyées par l'activité principale
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("module")) {
            module = (Module) intent.getSerializableExtra("module");
            if (module != null) {
                moduleName.setText(module.getName());
                moduleCoefficient.setText(String.valueOf(module.getCoefficient()));
                moduleGrade.setText(String.valueOf(module.getGrade()));
            }
        }

        // Enregistrer les modifications et retourner à l'activité principale
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newGradeStr = moduleGrade.getText().toString();
                if (!TextUtils.isEmpty(newGradeStr)) {
                    try {
                        double newGrade = Double.parseDouble(newGradeStr);
                        module.setGrade(newGrade); // Mettre à jour la note via le setter

                        // Retourner les modifications à l'activité principale
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("updatedModule", module);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } catch (NumberFormatException e) {
                        Toast.makeText(EditModuleActivity.this, "Veuillez entrer un nombre valide", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditModuleActivity.this, "La note ne peut pas être vide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
