package com.huangyuanlove.sunflower_java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.huangyuanlove.sunflower_java.HomeViewPagerFragment;
import com.huangyuanlove.sunflower_java.PlantDetailFragment;
import com.huangyuanlove.sunflower_java.PlantDetailFragmentArgs;
import com.huangyuanlove.sunflower_java.R;
import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.databinding.ListItemPlantBinding;

public class PlanAdapter extends ListAdapter<Plant, PlanAdapter.PlantViewHolder> {
    public PlanAdapter() {
        super(new  PlantDiffCallback<Plant>());
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlantViewHolder(ListItemPlantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class PlantViewHolder extends RecyclerView.ViewHolder {

        private ListItemPlantBinding binding;

        PlantViewHolder(final ListItemPlantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            binding.setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.getPlant() != null) {
                        navigateToPlant(binding.getPlant(), v);
                    }
                }
            });

        }

        private void navigateToPlant(Plant plant, View v) {
            PlantDetailFragmentArgs.Builder builder = new PlantDetailFragmentArgs.Builder(plant.getPlantId());

            Navigation.findNavController(v).navigate(R.id.action_view_pager_fragment_to_plant_detail_fragment, builder.build().toBundle());
        }

        public void bind(Plant plant) {
            binding.setPlant(plant);
            binding.executePendingBindings();

        }
    }

}


class PlantDiffCallback<T> extends DiffUtil.ItemCallback<Plant> {

    @Override
    public boolean areItemsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
        return oldItem.getPlantId().equals(newItem.getPlantId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
        return oldItem == newItem;
    }
}
