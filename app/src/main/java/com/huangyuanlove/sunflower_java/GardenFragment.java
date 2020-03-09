package com.huangyuanlove.sunflower_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.huangyuanlove.sunflower_java.adapter.GardenPlantingAdapter;
import com.huangyuanlove.sunflower_java.adapter.SunflowerPagerAdapter;
import com.huangyuanlove.sunflower_java.data.AppDatabase;
import com.huangyuanlove.sunflower_java.data.GardenPlantingDao;
import com.huangyuanlove.sunflower_java.data.GardenPlantingRepository;
import com.huangyuanlove.sunflower_java.data.PlantAndGardenPlantings;
import com.huangyuanlove.sunflower_java.databinding.FragmentGardenBinding;
import com.huangyuanlove.sunflower_java.viewmodels.GardenPlantingListViewModel;
import com.huangyuanlove.sunflower_java.viewmodels.GardenPlantingListViewModelFactory;

import java.util.List;

public class GardenFragment extends Fragment {
    private FragmentGardenBinding binding;

    private GardenPlantingListViewModel gardenPlantingListViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGardenBinding.inflate(inflater, container, false);
        GardenPlantingAdapter adapter = new GardenPlantingAdapter();
        binding.gardenList.setAdapter(adapter);
        GardenPlantingDao gardenPlantingDao = AppDatabase.getDatabase(requireContext()).gardenPlantingDao();
        GardenPlantingRepository gardenPlantingRepository = GardenPlantingRepository.getInstance(gardenPlantingDao);
        gardenPlantingListViewModel = new GardenPlantingListViewModelFactory(gardenPlantingRepository).create(GardenPlantingListViewModel.class);


        binding.addPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPlantListPage();
            }
        });
        subscribeUi(adapter, binding);

        return binding.getRoot();
    }

    private void subscribeUi(GardenPlantingAdapter adapter, FragmentGardenBinding binding) {
        gardenPlantingListViewModel.getPlantAndGardenPlantings().observe(getViewLifecycleOwner(), new Observer<List<PlantAndGardenPlantings>>() {
            @Override
            public void onChanged(List<PlantAndGardenPlantings> plantAndGardenPlantings) {
                adapter.submitList(plantAndGardenPlantings);
            }
        });

    }

    private void navigateToPlantListPage() {
        ViewPager2 viewPager2 = requireActivity().findViewById(R.id.view_pager);
        viewPager2.setCurrentItem(SunflowerPagerAdapter.PLANT_LIST_PAGE_INDEX);

    }


}
