package com.example.android.bipolartask.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.bipolartask.Fragments.nature_fragment;
import com.example.android.bipolartask.Fragments.pets_fragment;

public class viewPagerAdapter extends FragmentPagerAdapter {

    public viewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new pets_fragment();
            case 0:
                return new nature_fragment();
        }

        return null; //does not happen
    }

    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Pets";
            case 0:
                return "Nature";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
