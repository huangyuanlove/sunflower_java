package com.huangyuanlove.sunflower_java.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GardenPlantingRepository {

    private GardenPlantingDao gardenPlantingDao;

    public GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    public void createGardenPlanting(String plantId) {
        GardenPlanting gardenPlanting = new GardenPlanting(plantId);
        gardenPlantingDao.insertGardenPlanting(gardenPlanting);
    }

    public void removeGardenPlanting(GardenPlanting gardenPlanting) {
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting);
    }

    public LiveData<Boolean> isPlanted(String plantId) {
        return gardenPlantingDao.isPlanted(plantId);
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantedGardens() {
        return gardenPlantingDao.getPlantedGardens();
    }


    volatile private static GardenPlantingRepository instance;

    public static GardenPlantingRepository getInstance(GardenPlantingDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (GardenPlantingRepository.class) {
                if (instance == null) {
                    instance = new GardenPlantingRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }


}
