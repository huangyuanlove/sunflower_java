package com.huangyuanlove.sunflower_java.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huangyuanlove.sunflower_java.data.GardenPlantingRepository;
import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.data.PlantRepository;

public class PlantDetailViewModel extends ViewModel {


    PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;

    public PlantDetailViewModel(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;
    }


    public void addPlantToGarden(){
        gardenPlantingRepository.createGardenPlanting(plantId);
    }
    public LiveData<Plant> getPlant(){
      return  plantRepository.getPlant(plantId);
    }
    public LiveData<Boolean> isPlanted(){
       return gardenPlantingRepository.isPlanted(plantId);
    }

}
