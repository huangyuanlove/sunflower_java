package com.huangyuanlove.sunflower_java.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.data.PlantRepository;

import java.util.List;

public class PlantListViewModel extends ViewModel {

    private PlantRepository plantRepository;
    private static final int NO_GROW_ZONE = -1;
    private MutableLiveData<Integer> growZoneNumber = new MutableLiveData<>(NO_GROW_ZONE);

    public PlantListViewModel(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }


    public LiveData<List<Plant>> getPlants() {
        if (growZoneNumber.getValue() == null) {
            return plantRepository.getPlants();
        }
        if (growZoneNumber.getValue() == NO_GROW_ZONE) {
            return plantRepository.getPlants();
        }
        return plantRepository.getPlantsWithGrowZoneNumber(growZoneNumber.getValue());

    }

    public void setGrowZoneNumber(int num) {
        growZoneNumber.setValue(num);
    }

    public void clearGrowZoneNumber() {
        growZoneNumber.setValue(NO_GROW_ZONE);
    }

    boolean isFiltered() {
        return growZoneNumber.getValue() != null && growZoneNumber.getValue() != NO_GROW_ZONE;
    }


}
