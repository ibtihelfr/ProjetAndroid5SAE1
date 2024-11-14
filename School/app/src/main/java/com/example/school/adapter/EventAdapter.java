package com.example.school.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.school.evenement.FormulaireEvent;
import com.example.school.MainActivity;
import com.example.school.R;
import com.example.school.evenement.listEvent;
import com.example.school.models.Evenement;
import com.example.school.database.database;
import com.example.school.DAO.EvenementDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.ViewHolder>  {

    private List<Evenement> eventList;
    private Context context;
    private EvenementDAO eventDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public EventAdapter(List<Evenement> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
        this.eventDao = database.getInstance(context).evenementDAO();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Evenement event = eventList.get(position);
        holder.heureTextView.setText(event.getHeure());
        holder.prixTextView.setText(String.valueOf(event.getPrix()));
        holder.descriptionTextView.setText(event.getDescription());
        holder.typeTextView.setText(event.getType());

        // Optional: Load image if available, using Glide
//        Glide.with(context)
//                .load(event.getImageUrl())  // Assumes getImageUrl() returns a URL for event images
//                .placeholder(R.drawable.noimage)
//                .into(holder.eventImageView);

        holder.iconEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, FormulaireEvent.class);
            intent.putExtra("idEvent", event.getIdEvent());
            ((listEvent) context).startActivityForResult(intent, listEvent.REQUEST_CODE_ADD_EDIT);
        });

        holder.iconDelete.setOnClickListener(v -> deleteEvenement(event));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    private void deleteEvenement(Evenement event) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Event")
                .setMessage("Are you sure you want to delete this event?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    executorService.execute(() -> {
                        eventDao.DeleteEvenement(event);
                        ((listEvent) context).runOnUiThread(() -> {
                            eventList.remove(event);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Event deleted", Toast.LENGTH_SHORT).show();
                        });
                    });
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView heureTextView, prixTextView, descriptionTextView, typeTextView;
        ImageView eventImageView, iconEdit, iconDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            heureTextView = itemView.findViewById(R.id.heure_text);
            prixTextView = itemView.findViewById(R.id.prix_text);
            descriptionTextView = itemView.findViewById(R.id.description_text);
            typeTextView = itemView.findViewById(R.id.type_text);
            eventImageView = itemView.findViewById(R.id.event_image);
            iconEdit = itemView.findViewById(R.id.icon_edit);
            iconDelete = itemView.findViewById(R.id.icon_delete);
        }
    }
}
