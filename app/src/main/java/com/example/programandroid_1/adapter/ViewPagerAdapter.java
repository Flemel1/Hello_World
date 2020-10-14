package com.example.programandroid_1.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.programandroid_1.R;
import com.example.programandroid_1.fragment.FragmentBottom;
import com.example.programandroid_1.fragment.FragmentTop;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final int[] TAB_TITLES = new int[]{R.string.tab_one, R.string.tab_two};
    private final Context mContext;

    public ViewPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return new FragmentTop();
            case 1 : return new FragmentBottom();
            default: return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
