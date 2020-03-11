package com.huangyuanlove.sunflower_java.data;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.huangyuanlove.sunflower_java.utilities.Constants;
import com.huangyuanlove.sunflower_java.workers.SeedDatabaseWorker;

@Database(entities = {GardenPlanting.class, Plant.class}, version = 1, exportSchema = true)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GardenPlantingDao gardenPlantingDao();

    public abstract PlantDao plantDao();

    private static volatile AppDatabase INSTANCE;


    private static AppDatabase buildDatabase(Context context ) {
        return Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            OneTimeWorkRequest request =   new OneTimeWorkRequest.Builder(SeedDatabaseWorker.class).build();
                            WorkManager.getInstance(context).enqueue(request);
                        }
                    }).build();
    }

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }


}
