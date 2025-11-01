package fr.johann.simulatorNotes;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    private List<Module> moduleList;

    // Constructeur
    public ModuleAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_module, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        Module module = moduleList.get(position);

        holder.moduleName.setText(module.getName());
        holder.moduleCoefficient.setText("Coef. " + module.getCoefficient());
        holder.moduleGrade.setText(module.getGrade() != null ? String.valueOf(module.getGrade()) : "N/A");

        // ✅ Clic sur un module pour l'éditer
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditModuleActivity.class);
            intent.putExtra("module", module);
            ((Activity) v.getContext()).startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    // ✅ Classe interne pour le ViewHolder
    static class ModuleViewHolder extends RecyclerView.ViewHolder {
        TextView moduleName, moduleCoefficient, moduleGrade;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleName = itemView.findViewById(R.id.moduleName);
            moduleCoefficient = itemView.findViewById(R.id.moduleCoefficient);
            moduleGrade = itemView.findViewById(R.id.moduleGrade);
        }
    }
}