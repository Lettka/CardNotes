package com.example.cardnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {
    private final List<CardNote> cardList;
    private OnCardClickListener clickListener;

    public CardsAdapter(List<CardNote> cardList) {
        this.cardList = cardList;
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
        holder.bind(cardList.get(position));
    }

    @Override
    public int getItemCount() {
        return cardList.size();
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
        }

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

            like.setOnClickListener(v -> {
                cardNote.setLike(!cardNote.isLike());
            });
        }

    }

    interface OnCardClickListener {
        void onCardClick(View view, int position);
    }

}
