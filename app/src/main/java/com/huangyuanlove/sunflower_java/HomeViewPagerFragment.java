package com.huangyuanlove.sunflower_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.huangyuanlove.sunflower_java.adapter.SunflowerPagerAdapter;
import com.huangyuanlove.sunflower_java.databinding.FragmentHomeViewPagerBinding;

public class HomeViewPagerFragment extends Fragment {
    private FragmentHomeViewPagerBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeViewPagerBinding.inflate(inflater,  container, false);

        binding.viewPager.setAdapter(new SunflowerPagerAdapter(this));
        new TabLayoutMediator(binding.tabs, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setIcon(getTabIcon(position));
                tab.setText(getTabTitle(position));
            }
        }).attach();

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);

        return binding.getRoot();
    }

    private int getTabIcon(int position) {
        switch (position) {
            case SunflowerPagerAdapter.MY_GARDEN_PAGE_INDEX:
                return R.drawable.garden_tab_selector;
            case SunflowerPagerAdapter.PLANT_LIST_PAGE_INDEX:
                return R.drawable.plant_list_tab_selector;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    private String getTabTitle(int position) {
        switch (position) {
            case SunflowerPagerAdapter.MY_GARDEN_PAGE_INDEX:
                return "My Garden";
            case SunflowerPagerAdapter.PLANT_LIST_PAGE_INDEX:
                return "Plan List";
            default:
                throw new IndexOutOfBoundsException();
        }
    }


}
