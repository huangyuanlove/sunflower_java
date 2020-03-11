package com.huangyuanlove.sunflower_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.huangyuanlove.sunflower_java.adapter.PlanAdapter;
import com.huangyuanlove.sunflower_java.data.AppDatabase;
import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.data.PlantDao;
import com.huangyuanlove.sunflower_java.data.PlantRepository;
import com.huangyuanlove.sunflower_java.databinding.FragmentPlantListBinding;
import com.huangyuanlove.sunflower_java.viewmodels.PlantListViewModel;
import com.huangyuanlove.sunflower_java.viewmodels.PlantListViewModelFactory;

import java.util.List;

public class PlantListFragment extends Fragment {
    FragmentPlantListBinding binding;
    private PlantListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlantListBinding.inflate(inflater, container, false);
        PlanAdapter adapter = new PlanAdapter();
        binding.plantList.setAdapter(adapter);
        PlantDao plantDao = AppDatabase.getDatabase(requireContext()).plantDao();
        PlantListViewModelFactory factory =  new PlantListViewModelFactory(PlantRepository.getInstance(plantDao));
        viewModel = factory.create(PlantListViewModel.class);

        setHasOptionsMenu(true);
        subscribeUi(adapter);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.filter_zone){
            updateData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateData() {
        if(viewModel.isFiltered()){
            viewModel.clearGrowZoneNumber();
        }else{
            viewModel.setGrowZoneNumber(9);
        }
    }


    private void subscribeUi(PlanAdapter adapter) {
        viewModel.getPlants().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
                adapter.submitList(plants);
            }
        });
    }
}
