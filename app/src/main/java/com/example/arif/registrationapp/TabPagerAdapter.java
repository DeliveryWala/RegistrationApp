package com.example.arif.registrationapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Arif on 19/09/16.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount)
    {
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Login();
            case 1:
                return new Register();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
