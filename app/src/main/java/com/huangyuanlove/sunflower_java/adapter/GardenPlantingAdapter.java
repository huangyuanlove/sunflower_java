package com.huangyuanlove.sunflower_java.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.huangyuanlove.sunflower_java.PlantDetailFragmentArgs;
import com.huangyuanlove.sunflower_java.R;
import com.huangyuanlove.sunflower_java.data.PlantAndGardenPlantings;
import com.huangyuanlove.sunflower_java.databinding.ListItemGardenPlantingBinding;
import com.huangyuanlove.sunflower_java.viewmodels.PlantAndGardenPlantingsViewModel;

public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {


    public GardenPlantingAdapter() {
        super(new GardenPlantDiffCallback<PlantAndGardenPlantings>());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_garden_planting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemGardenPlantingBinding binding;

         ViewHolder(@NonNull ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.getViewModel() != null && !TextUtils.isEmpty(binding.getViewModel().plantId())) {
                        navigateToPlant(binding.getViewModel().plantId(), v);
                    }


                }
            });

        }

        private void navigateToPlant(String plantId, View view) {
            PlantDetailFragmentArgs.Builder builder = new PlantDetailFragmentArgs.Builder(plantId);

            Navigation.findNavController(view).navigate(R.id.action_view_pager_fragment_to_plant_detail_fragment, builder.build().toBundle());
        }

        void bind(PlantAndGardenPlantings plantings) {
            binding.setViewModel(new PlantAndGardenPlantingsViewModel(plantings));
            binding.executePendingBindings();

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