package com.example.cardnotes;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {
    private final CardSource source;
    private final Activity activity;
    private OnCardClickListener clickListener;
    private int menuPosition = -1;

    public CardsAdapter(Activity activity, CardSource source) {
        this.activity = activity;
        this.source = source;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(int menuPosition) {
        this.menuPosition = menuPosition;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item_layout, parent, false);
        return new CardViewHolder(view);
    }

    public void setClickListener(OnCardClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(source.getCardNote(position));
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView = itemView.findViewById(R.id.cardNote);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        CheckBox like = itemView.findViewById(R.id.like);
        TextView date = itemView.findViewById(R.id.date);
        TextView title = itemView.findViewById(R.id.title);
        TextView description = itemView.findViewById(R.id.description);

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            activity.registerForContextMenu(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        void bind(CardNote cardNote) {
            imageView.setImageResource(cardNote.getImage());
            like.setChecked(cardNote.isLike());
            date.setText(cardNote.getDate());
            title.setText(cardNote.getTitleNotes());
            description.setText(cardNote.getContextNotes());

            cardView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onCardClick(v, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(view -> {
                menuPosition = getLayoutPosition();
                itemView.showContextMenu(10, 10);
                return false;
            });

            like.setOnClickListener(v -> {
                cardNote.setLike(!cardNote.isLike());
            });
        }

    }

    interface OnCardClickListener {
        void onCardClick(View view, int position);
    }

}
