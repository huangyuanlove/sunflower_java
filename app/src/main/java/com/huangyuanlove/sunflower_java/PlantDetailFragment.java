package com.huangyuanlove.sunflower_java;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ShareCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.huangyuanlove.sunflower_java.data.AppDatabase;
import com.huangyuanlove.sunflower_java.data.GardenPlanting;
import com.huangyuanlove.sunflower_java.data.GardenPlantingDao;
import com.huangyuanlove.sunflower_java.data.GardenPlantingRepository;
import com.huangyuanlove.sunflower_java.data.Plant;
import com.huangyuanlove.sunflower_java.data.PlantAndGardenPlantings;
import com.huangyuanlove.sunflower_java.data.PlantDao;
import com.huangyuanlove.sunflower_java.data.PlantRepository;
import com.huangyuanlove.sunflower_java.databinding.FragmentPlantDetailBinding;
import com.huangyuanlove.sunflower_java.viewmodels.PlantDetailViewModel;
import com.huangyuanlove.sunflower_java.viewmodels.PlantDetailViewModelFactory;

import java.util.List;

public class PlantDetailFragment extends Fragment {

    private PlantDetailViewModel plantDetailViewModel;
    private String plantId;
    private boolean isToolbarShown = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       PlantDetailFragmentArgs args = PlantDetailFragmentArgs.fromBundle(getArguments());
        plantId = args.getPlantId();

        FragmentPlantDetailBinding binding = FragmentPlantDetailBinding.inflate(LayoutInflater.from(requireContext()), container, false);
        PlantDao plantDao = AppDatabase.getDatabase(requireContext()).plantDao();
        GardenPlantingDao gardenPlantingDao = AppDatabase.getDatabase(requireContext()).gardenPlantingDao();

        PlantRepository plantRepository = PlantRepository.getInstance(plantDao);
        GardenPlantingRepository gardenPlantingRepository = GardenPlantingRepository.getInstance(gardenPlantingDao);


        binding.setLifecycleOwner(getViewLifecycleOwner());
        plantDetailViewModel = new PlantDetailViewModelFactory(plantRepository, gardenPlantingRepository, plantId).create(PlantDetailViewModel.class);
        binding.setViewModel(plantDetailViewModel);
        binding.setCallback(new Callback() {
            @Override
            public void add(Plant plant) {
                if (plant != null) {
                    hideAppBarFab(binding.fab);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            plantDetailViewModel.addPlantToGarden();
                        }
                    }).start();

                    Snackbar.make(binding.getRoot(), R.string.added_plant_to_garden, Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

        binding.plantDetailScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                boolean shouldShowToolbar = scrollY > binding.toolbar.getHeight();
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar;

                    // Use shadow animator to add elevation if toolbar is shown
                    binding.appbar.setActivated(shouldShowToolbar);

                    // Show the plant name if toolbar is shown
                    binding.toolbarLayout.setTitleEnabled(shouldShowToolbar);
                }
            }
        });


        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });



        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_share:
                        createShareIntent();
                        return true;
                    default:
                        return false;
                }


            }
        });

        setHasOptionsMenu(true);
        return binding.getRoot();
    }


    private void createShareIntent() {
        Plant plant = plantDetailViewModel.getPlant().getValue();
        String shareText = null;
        if (plant == null) {
            shareText = "";
        } else {
            getString(R.string.share_text_plant, plant.getName());
        }


        Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                .setText(shareText)
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(shareIntent);
    }

    private void hideAppBarFab(FloatingActionButton fab) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        FloatingActionButton.Behavior behavior = (FloatingActionButton.Behavior) params.getBehavior();
        behavior.setAutoHideEnabled(false);
        params.setBehavior(behavior);
        fab.setLayoutParams(params);
        fab.hide();
    }

    public static interface Callback {
        void add(Plant plant);
    }

}
