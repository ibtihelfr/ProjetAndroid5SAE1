package com.example.school.ClasseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.school.Classe.AddEditClasse;
import com.example.school.Classe.ListeClasse;
import com.example.school.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.school.database.database;

import com.example.school.DAO.ClasseDAO;

import com.example.school.models.Classe;

public class ClasseAdapter extends RecyclerView.Adapter<ClasseAdapter.ViewHolder> {

    private List<Classe> classeList;
    private Context context;
    private ClasseDAO classeDAO;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public ClasseAdapter(List<Classe> classeList, Context context) {
        this.classeList = classeList;
        this.context = context;
        this.classeDAO = database.getInstance(context).classeDAO();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.classe_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classe classe = classeList.get(position);
        holder.niveauTextView.setText("Niveau :" + classe.getNiveau());
        holder.classNameTextView.setText("Nom de Classe :"+classe.getClassName());
        holder.emploiTextView.setText("Date Emploi :"+classe.getEmploi());

        holder.iconEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditClasse.class);
            intent.putExtra("IdClasse", classe.getIdClass());
            ((ListeClasse) context).startActivityForResult(intent, ListeClasse.REQUEST_CODE_ADD_EDIT);
        });

        holder.iconDelete.setOnClickListener(v -> deleteClasse(classe));
    }

    @Override
    public int getItemCount() {
        return classeList.size();
    }

    private void deleteClasse(Classe classe) {
        new AlertDialog.Builder(context)
                .setTitle("Delete class")
                .setMessage("Are you sure you want to delete this exam?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    executorService.execute(() -> {
                        classeDAO.DeleteClasse(classe);
                        ((ListeClasse) context).runOnUiThread(() -> {
                            classeList.remove(classe);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Class deleted", Toast.LENGTH_SHORT).show();
                        });
                    });
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView niveauTextView, classNameTextView, emploiTextView;
        ImageView eventImageView, iconEdit, iconDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            niveauTextView = itemView.findViewById(R.id.niveau_text);
            classNameTextView = itemView.findViewById(R.id.class_name_text);
            emploiTextView = itemView.findViewById(R.id.emploi_text);

            iconEdit = itemView.findViewById(R.id.icon_edit);
            iconDelete = itemView.findViewById(R.id.icon_delete);
        }
    }

}
