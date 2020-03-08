package com.huangyuanlove.sunflower_java.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlantRepository {

    private PlantDao plantDao;
    public PlantRepository(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public LiveData<List<Plant>> getPlants(){
        return plantDao.getPlants();
    }

    public LiveData<Plant> getPlant(String plantId){
        return plantDao.getPlant(plantId);
    }

    public LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber){
        return plantDao.getPlantsWithGrowZoneNumber(growZoneNumber);
    }



    volatile private static PlantRepository instance;

    public static PlantRepository getInstance(PlantDao plantDao){
        if(instance ==null){
            synchronized (PlantRepository.class){
                if(instance ==null){
                    instance = new PlantRepository(plantDao);
                }
            }
        }
        return instance;

    }


}
