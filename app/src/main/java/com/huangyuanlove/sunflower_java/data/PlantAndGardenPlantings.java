package com.huangyuanlove.sunflower_java.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Collections;
import java.util.List;


public class PlantAndGardenPlantings {

    @Embedded
  public  Plant plant;

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    List<GardenPlanting> gardenPlantings =  Collections.emptyList();

}
