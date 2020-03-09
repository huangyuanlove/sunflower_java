package com.huangyuanlove.sunflower_java.viewmodels;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.huangyuanlove.sunflower_java.data.GardenPlantingRepository;

public class GardenPlantingListViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private GardenPlantingRepository gardenPlantingRepository;

    public GardenPlantingListViewModelFactory(GardenPlantingRepository gardenPlantingRepository) {
        this.gardenPlantingRepository = gardenPlantingRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new GardenPlantingListViewModel(gardenPlantingRepository);
    }
}
