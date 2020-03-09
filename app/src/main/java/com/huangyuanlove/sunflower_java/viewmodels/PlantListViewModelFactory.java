package com.huangyuanlove.sunflower_java.viewmodels;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.huangyuanlove.sunflower_java.data.PlantRepository;

public class PlantListViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private PlantRepository plantRepository;

    public PlantListViewModelFactory(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new PlantListViewModel(plantRepository);
    }
}
