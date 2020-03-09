package com.huangyuanlove.sunflower_java.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huangyuanlove.sunflower_java.data.GardenPlantingRepository;
import com.huangyuanlove.sunflower_java.data.PlantAndGardenPlantings;

import java.util.List;

public class GardenPlantingListViewModel extends ViewModel {

    private  LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;

    public GardenPlantingListViewModel(GardenPlantingRepository gardenPlantingRepository) {

        plantAndGardenPlantings = gardenPlantingRepository.getPlantedGardens();
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings() {
        return plantAndGardenPlantings;
    }
}
