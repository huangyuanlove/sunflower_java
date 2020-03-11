package com.huangyuanlove.sunflower_java.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.huangyuanlove.sunflower_java.data.AppDatabase;
import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.utilities.Constants;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class SeedDatabaseWorker extends Worker {
    public SeedDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try{

            InputStream inputStream = getApplicationContext().getResources().getAssets().open(Constants.PLANT_DATA_FILENAME);
            JsonReader jsonReader =   new JsonReader(new InputStreamReader(inputStream));
            List<Plant> plantList = new Gson().fromJson(jsonReader,new TypeToken<List<Plant>>(){}.getType());
            AppDatabase.getDatabase(getApplicationContext()).plantDao().insertAll(plantList);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure();
        }
    }
}
