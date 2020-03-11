package com.huangyuanlove.sunflower_java.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huangyuanlove.sunflower_java.data.GardenPlantingRepository;
import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.data.PlantRepository;

public class PlantDetailViewModel extends ViewModel {


    private PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;
    private LiveData<Plant> plant;
    private LiveData<Boolean> isPlanted;

    public PlantDetailViewModel(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;
        plant = plantRepository.getPlant(plantId);
        isPlanted = gardenPlantingRepository.isPlanted(plantId);
    }


    public void addPlantToGarden() {
        gardenPlantingRepository.createGardenPlanting(plantId);
    }

    public LiveData<Plant> getPlant() {
        return plant;
    }

    public LiveData<Boolean> isPlanted() {
        return isPlanted;
    }


}
