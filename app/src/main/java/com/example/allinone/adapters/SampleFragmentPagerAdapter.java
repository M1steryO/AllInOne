package com.example.allinone.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.allinone.fragments.PageFragment;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private final String[] tabTitles = new String[] { "Популярное", "Избранное"};

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
    }

    @Override public int getCount() {
        return PAGE_COUNT;
    }

    @NonNull
    @Override public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override public CharSequence getPageTitle(int position) {
        // генерируем заголовок в зависимости от позиции
        return tabTitles[position];
    }
}
