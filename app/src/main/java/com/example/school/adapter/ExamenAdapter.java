package com.example.school.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.school.DAO.ExamenDAO;
import com.example.school.Exam.AddExam;
import com.example.school.Exam.AfficheExamen;
import com.example.school.MainActivity;
import com.example.school.models.Examen;
import com.example.school.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.school.database.database;

public class ExamenAdapter extends RecyclerView.Adapter<ExamenAdapter.ViewHolder> {
    private List<Examen> examentList;
    private Context context;
    private ExamenDAO examenDAO;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ExamenAdapter(List<Examen> examentList, Context context) {
        this.examentList = examentList;
        this.context = context;
        this.examenDAO = database.getInstance(context).examenDAO();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.examen_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Examen examen = examentList.get(position);
        holder.typeTextView.setText("type: " +examen.getType());
        holder.semestreTextView.setText("semestre: " +examen.getSemestre());
        holder.dateTextView.setText("matiere: " +examen.getDate());
        holder.matiereTextView.setText("date: " +examen.getMatiere());

        // Optional: Load image if available, using Glide
//        Glide.with(context)
//                .load(event.getImageUrl())  // Assumes getImageUrl() returns a URL for event images
//                .placeholder(R.drawable.noimage)
//                .into(holder.eventImageView);

        holder.iconEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddExam.class);
            intent.putExtra("examId", examen.getIdExamen());
            ((AfficheExamen) context).startActivityForResult(intent, AfficheExamen.REQUEST_CODE_ADD_EDIT);
        });

        holder.iconDelete.setOnClickListener(v -> deleteExamen(examen));
    }

    @Override
    public int getItemCount() {
        return examentList.size();
    }

    private void deleteExamen(Examen event) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Exam")
                .setMessage("Are you sure you want to delete this exam?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    executorService.execute(() -> {
                        examenDAO.DeleteExamen(event);
                        ((AfficheExamen) context).runOnUiThread(() -> {
                            examentList.remove(event);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Exam deleted", Toast.LENGTH_SHORT).show();
                        });
                    });
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeTextView, semestreTextView, dateTextView, matiereTextView;
        ImageView eventImageView, iconEdit, iconDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            semestreTextView = itemView.findViewById(R.id.semestre_text);
            dateTextView = itemView.findViewById(R.id.date_text);
            typeTextView = itemView.findViewById(R.id.type_text);
            matiereTextView = itemView.findViewById(R.id.matiere_text);
//            eventImageView = itemView.findViewById(R.id.event_image);
            iconEdit = itemView.findViewById(R.id.icon_edit);
            iconDelete = itemView.findViewById(R.id.icon_delete);
        }
    }
}