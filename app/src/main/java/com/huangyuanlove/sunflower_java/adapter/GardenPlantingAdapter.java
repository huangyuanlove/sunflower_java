package com.huangyuanlove.sunflower_java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.huangyuanlove.sunflower_java.R;
import com.huangyuanlove.sunflower_java.data.PlantAndGardenPlantings;
import com.huangyuanlove.sunflower_java.databinding.ListItemGardenPlantingBinding;

public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings,GardenPlantingAdapter.ViewHolder> {


    protected GardenPlantingAdapter(@NonNull GardenPlantDiffCallback<PlantAndGardenPlantings> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_garden_planting,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            binding.setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }


        void bind(PlantAndGardenPlantings plantings){

        }

    }

}


class GardenPlantDiffCallback<T> extends DiffUtil.ItemCallback<PlantAndGardenPlantings> {


    @Override
    public boolean areItemsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
        return oldItem.plant.getPlantId().equals(newItem.plant.getPlantId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
        return oldItem.plant.equals(newItem.plant);
    }
}