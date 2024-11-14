package com.example.school.adapters;

import android.annotation.SuppressLint;
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

import com.example.school.DAO.ReclamationDAO;
import com.example.school.MainActivity;
import com.example.school.R;
import com.example.school.models.Reclamation;
import com.example.school.reclamation.ListReclamation;
import com.example.school.reclamation.ReclamationActivity;
import com.example.school.database.database;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.ViewHolder> {

    private final List<Reclamation> reclamationList;
    private final Context context;
    private final ReclamationDAO reclamationDAO;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ReclamationAdapter(List<Reclamation> reclamationList, Context context) {
        this.reclamationList = reclamationList;
        this.context = context;
        this.reclamationDAO = database.getInstance(context).reclamationDAO();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reclamation_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reclamation reclamation = reclamationList.get(position);
        holder.messageTextView.setText(reclamation.getMessage());

        holder.iconEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReclamationActivity.class);
            intent.putExtra("reclamationId", reclamation.getIdRec());
            ((ListReclamation) context).startActivityForResult(intent, ListReclamation.REQUEST_CODE_ADD_EDIT);
        });

        holder.iconDelete.setOnClickListener(v -> deleteReclamation(reclamation));
    }

    @Override
    public int getItemCount() {
        return reclamationList.size();
    }

    private void deleteReclamation(Reclamation reclamation) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Reclamation")
                .setMessage("Are you sure you want to delete this reclamation?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    executorService.execute(() -> {
                        reclamationDAO.DeleteReclamation(reclamation);
                        ((ListReclamation) context).runOnUiThread(() -> {
                            reclamationList.remove(reclamation);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Reclamation deleted", Toast.LENGTH_SHORT).show();
                        });
                    });
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        ImageView iconEdit, iconDelete;



        public ViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.message_text);
            iconEdit = itemView.findViewById(R.id.icon_edit);
            iconDelete = itemView.findViewById(R.id.icon_delete);
        }
    }
}
