package fr.johann.simulatorNotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.List;

public class UeAdapter extends RecyclerView.Adapter<UeAdapter.UeViewHolder> {

    private List<Ue> ueList;

    // Constructeur
    public UeAdapter(List<Ue> ueList) {
        this.ueList = ueList;
    }

    @NonNull
    @Override
    public UeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ue, parent, false);
        return new UeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UeViewHolder holder, int position) {
        Ue ue = ueList.get(position);
        holder.ueTitle.setText(ue.getTitle());

        // ✅ Formatage de la moyenne à 2 décimales si elle existe
        if (ue.getAverage() != null) {
            DecimalFormat df = new DecimalFormat("#.##");
            holder.ueAverage.setText("Moyenne : " + df.format(ue.getAverage()));
        } else {
            holder.ueAverage.setText("Moyenne : N/A");
        }

        // Initialiser l'adaptateur pour les modules de cette UE
        ModuleAdapter moduleAdapter = new ModuleAdapter(ue.getModules());
        holder.moduleRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.moduleRecyclerView.setAdapter(moduleAdapter);

        // Gestion du clic pour développer/réduire la liste des modules
        holder.itemView.setOnClickListener(v -> {
            if (holder.moduleRecyclerView.getVisibility() == View.GONE) {
                holder.moduleRecyclerView.setVisibility(View.VISIBLE);
            } else {
                holder.moduleRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ueList.size();
    }

    static class UeViewHolder extends RecyclerView.ViewHolder {
        TextView ueTitle, ueAverage;
        RecyclerView moduleRecyclerView;

        public UeViewHolder(@NonNull View itemView) {
            super(itemView);
            ueTitle = itemView.findViewById(R.id.ueTitle);
            ueAverage = itemView.findViewById(R.id.ueAverage);
            moduleRecyclerView = itemView.findViewById(R.id.moduleRecyclerView);
            moduleRecyclerView.setVisibility(View.GONE); // Masqué par défaut
        }
    }
}
