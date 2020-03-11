package com.huangyuanlove.sunflower_java.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(
        tableName = "garden_plantings",
        foreignKeys = @ForeignKey(entity = Plant.class,parentColumns = {"id"},childColumns = {"plant_id"}),
        indices = {@Index("plant_id")}
)

public class GardenPlanting {

    @ColumnInfo(name = "plant_id")
    private String plantId;
    @ColumnInfo(name = "plant_date")
    private Calendar plantDate = Calendar.getInstance();
    @ColumnInfo(name = "last_watering_date")
    private Calendar lastWateringDate =  Calendar.getInstance();
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long gardenPlantingId = 0;

    public GardenPlanting(String plantId) {
        this.plantId = plantId;

    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public Calendar getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(Calendar plantDate) {
        this.plantDate = plantDate;
    }

    public Calendar getLastWateringDate() {
        return lastWateringDate;
    }

    public void setLastWateringDate(Calendar lastWateringDate) {
        this.lastWateringDate = lastWateringDate;
    }

    public long getGardenPlantingId() {
        return gardenPlantingId;
    }

    public void setGardenPlantingId(long gardenPlantingId) {
        this.gardenPlantingId = gardenPlantingId;
    }
}
