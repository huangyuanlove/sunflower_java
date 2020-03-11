package com.huangyuanlove.sunflower_java.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface GardenPlantingDao {


    @Query("SELECT * FROM garden_plantings")
    LiveData<List<GardenPlanting>> getGardenPlantings();

    @Query("SELECT EXISTS(SELECT * FROM garden_plantings WHERE plant_id = :plantId LIMIT 1)")
    LiveData<Boolean> isPlanted(String plantId);

    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    LiveData<List<PlantAndGardenPlantings>> getPlantedGardens();

    @Insert
    long insertGardenPlanting(GardenPlanting gardenPlanting);

    @Delete
    void deleteGardenPlanting(GardenPlanting gardenPlanting);


}
