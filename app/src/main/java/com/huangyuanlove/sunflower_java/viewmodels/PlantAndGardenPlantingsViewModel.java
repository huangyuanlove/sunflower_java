package com.huangyuanlove.sunflower_java.viewmodels;

import com.huangyuanlove.sunflower_java.data.GardenPlanting;
import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.data.PlantAndGardenPlantings;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PlantAndGardenPlantingsViewModel {

    private PlantAndGardenPlantings plantings;
    private Plant plant;
    private GardenPlanting gardenPlanting;
    public PlantAndGardenPlantingsViewModel(PlantAndGardenPlantings plantings) {
        this.plantings = plantings;
        plant = plantings.plant;
        gardenPlanting = plantings.gardenPlantings.get(0);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);


    public int getWateringInterval(){
        return plant.getWateringInterval();
    }
    public String getImageUrl(){
        return plant.getImageUrl();
    }

    public String name(){
        return plant.getName();
    }
    public String plantId(){
        return plant.getPlantId();
    }
    public String getPlantDateString(){
        return dateFormat.format(gardenPlanting.getPlantDate().getTime());
    }




}
