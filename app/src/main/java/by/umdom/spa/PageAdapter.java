package by.umdom.spa;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import by.umdom.spa.fragment.FragmentApp;
import by.umdom.spa.fragment.FragmentBath;
import by.umdom.spa.fragment.FragmentMode;
import by.umdom.spa.fragment.FragmentSetBluetooth;

/**
 * Created by abdalla on 2/18/18.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentSetBluetooth();
            case 1:
                return new FragmentApp();
            case 2:
                return new FragmentMode();
            case 3:
                return new FragmentBath();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
