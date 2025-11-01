package fr.johann.simulatorNotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ueRecyclerView;
    private UeAdapter ueAdapter;
    private TextView overallAverageTextView;
    private List<Ue> ueList;
    private Semester semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ueRecyclerView = findViewById(R.id.ueRecyclerView);
        ueRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        overallAverageTextView = findViewById(R.id.overallAverageTextView);

        ueList = StorageHelper.loadUeList(this);

        if (ueList == null) {
            ueList = loadSampleData(); // première fois, données par défaut
        }
        ueAdapter = new UeAdapter(ueList);
        ueRecyclerView.setAdapter(ueAdapter);

        // Créer le semestre et afficher la moyenne générale
        semester = new Semester("Semestre 1", "01/09/2024", ueList);
        displayOverallAverage();
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Module updatedModule = (Module) data.getSerializableExtra("updatedModule");

            if (updatedModule != null) {
                // 🔁 Parcourir les UE et modules
                for (Ue ue : ueList) {
                    boolean updated = false;
                    for (int i = 0; i < ue.getModules().size(); i++) {
                        Module m = ue.getModules().get(i);
                        if (m.getName().equals(updatedModule.getName())) {
                            ue.getModules().set(i, updatedModule);
                            ue.updateAverage(); // ✅ recalculer la moyenne de cette UE
                            updated = true;

                        }
                    }
                    if (updated) {}; /// on ne fait pas de break, car on veut modifier toutes les occurrences du même id
                }

                // 🔄 Rafraîchir l’affichage
                ueAdapter.notifyDataSetChanged();

                // 🔢 Mettre à jour la moyenne générale
                updateAverage();

                // 💾 Sauvegarder les nouvelles valeurs
                StorageHelper.saveUeList(this, ueList);
            }
        }
    }

    private void updateAverage() {
        double total = 0;
        double coefSum = 0;

        // ✅ Calcul sur tous les modules de toutes les UE
        for (Ue ue : ueList) {
            for (Module m : ue.getModules()) {
                total += m.getGrade() * m.getCoefficient();
                coefSum += m.getCoefficient();
            }
        }

        double average = coefSum != 0 ? total / coefSum : 0;

        overallAverageTextView.setText("Moyenne Générale : " + String.format("%.2f", average));
    }


    private void displayOverallAverage() {
        Double overallAverage = semester.getOverallAverage();
        if (overallAverage != null) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
            overallAverageTextView.setText("Moyenne Générale : " + df.format(overallAverage));
        } else {
            overallAverageTextView.setText("Moyenne Générale : N/A");
        }
    }


    private List<Ue> loadSampleData() {
        List<Ue> ueList = new ArrayList<>();

        // Exemple de modules pour une UE
        List<Module> modulesUe1 = new ArrayList<>();
        modulesUe1.add(new Module("R5.A.04 - Qualité algorithmique", 3, 3));
        modulesUe1.add(new Module("R5.A.05 - Programmation avancée", 10, 10));
        modulesUe1.add(new Module("R5.A.06 - Sensibilisation à la programmation multimédia", 8, 3));
        modulesUe1.add(new Module("R5.A.07 - Automatisation de la chaîne de production", 8, 8));
        modulesUe1.add(new Module("R5.A.08 - Qualité de développement", 10, 8));
        modulesUe1.add(new Module("R5.A.09 - Virtualisation avancée", 8, 8));
        modulesUe1.add(new Module("R5.A.10 - Nouveaux paradigmes de base de données", 10, 14));
        modulesUe1.add(new Module("R5.A.13 - Économie durable et numérique", 8, 3));
        modulesUe1.add(new Module("R5.A.14 - Anglais", 8, 3));
        modulesUe1.add(new Module("S5.A.01 - Développement avancée", 13, 40));

        List<Module> modulesUe2 = new ArrayList<>();
        modulesUe2.add(new Module("R5.A.04 - Qualité algorithmique", 3, 7));
        modulesUe2.add(new Module("R5.A.05 - Programmation avancée", 10, 8));
        modulesUe2.add(new Module("R5.A.06 - Sensibilisation à la programmation multimédia", 3, 3));
        modulesUe2.add(new Module("R5.A.08 - Qualité de développement", 10, 6));
        modulesUe2.add(new Module("R5.A.09 - Virtualisation avancée", 8, 3));
        modulesUe2.add(new Module("R5.A.10 - Nouveaux paradigmes de base de données", 10, 5));
        modulesUe2.add(new Module("R5.A.11 - Méthodes d'optimisation pour l'aide à la décision", 2, 8));
        modulesUe2.add(new Module("R5.A.10 - Modélisations mathématiques", 8, 15));
        modulesUe2.add(new Module("R5.A.14 - Anglais", 8, 5));
        modulesUe2.add(new Module("S5.A.01 - Développement avancée", 13, 40));

        List<Module> modulesUe6 = new ArrayList<>();
        modulesUe6.add(new Module("R5.01 - Initiation au management d'une équipe de projet informatique", 8, 11));
        modulesUe6.add(new Module("R5.A.02 - Projet personnel et professionnel", 8, 15));
        modulesUe6.add(new Module("R5.03 - Politique de communication", 8, 7));
        modulesUe6.add(new Module("R5.A.06 - Sensibilisation à la programmation multimédia", 8, 3));
        modulesUe6.add(new Module("R5.A.07 - Automatisation de la chaîne de production", 8, 3));
        modulesUe6.add(new Module("R5.A.13 - Économie durable et numérique", 8, 6));
        modulesUe6.add(new Module("R5.A.14 - Anglais", 8, 15));
        modulesUe6.add(new Module("S5.A.01 - Développement avancée", 13, 40));

        // Création d'UEs avec coefficient
        ueList.add(new Ue("UE51 - Compétence 1", modulesUe1, 1.0));
        ueList.add(new Ue("UE52 - Compétence 2", modulesUe2, 1.0));
        ueList.add(new Ue("UE56 - Compétence 6", modulesUe6, 1.0));

        return ueList;
    }
}
