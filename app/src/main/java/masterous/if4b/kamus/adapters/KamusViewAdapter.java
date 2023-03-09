package masterous.if4b.kamus.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import masterous.if4b.kamus.databinding.ItemKamusBinding;
import masterous.if4b.kamus.models.Kamus;
import masterous.if4b.kamus.utilities.ItemClickListener;

public class KamusViewAdapter extends RecyclerView.Adapter<KamusViewAdapter.ViewHolder> {
    private ArrayList<Kamus> data = new ArrayList<>();
    private ItemClickListener<Kamus> itemClickListener;

    public KamusViewAdapter(ItemClickListener<Kamus> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(ArrayList<Kamus> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KamusViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemKamusBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KamusViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Kamus kamus = data.get(pos);
        holder.itemKamusBinding.tvTitle.setText(kamus.getTitle());
        holder.itemKamusBinding.tvDescription.setText(kamus.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(kamus, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemKamusBinding itemKamusBinding;

        public ViewHolder(@NonNull ItemKamusBinding itemView) {
            super(itemView.getRoot());
            itemKamusBinding = itemView;
        }
    }
}
