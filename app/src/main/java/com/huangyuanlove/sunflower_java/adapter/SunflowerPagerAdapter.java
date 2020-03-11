package com.huangyuanlove.sunflower_java.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.huangyuanlove.sunflower_java.GardenFragment;
import com.huangyuanlove.sunflower_java.PlantListFragment;

import java.util.HashMap;
import java.util.Objects;


public class SunflowerPagerAdapter extends FragmentStateAdapter {


    public static final int MY_GARDEN_PAGE_INDEX = 0;
    public static final int PLANT_LIST_PAGE_INDEX = 1;
    private HashMap<Integer,Fragment> tabFragmentsCreators = new HashMap<>();
    public SunflowerPagerAdapter(@NonNull Fragment fragment) {


        super(fragment);
        tabFragmentsCreators.put(MY_GARDEN_PAGE_INDEX,new GardenFragment());
        tabFragmentsCreators.put(PLANT_LIST_PAGE_INDEX,new PlantListFragment());
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return Objects.requireNonNull(tabFragmentsCreators.get(position));
    }

    @Override
    public int getItemCount() {
        return tabFragmentsCreators==null?0:tabFragmentsCreators.size();
    }
}
