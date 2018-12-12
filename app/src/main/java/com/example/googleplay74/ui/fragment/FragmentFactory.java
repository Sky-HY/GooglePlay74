package com.example.googleplay74.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * fragment简单工厂
 */
public class FragmentFactory {
    private static Map<Integer, Fragment> fragmentMap = new HashMap<>();

    public static BaseFragment createFragment(int pos) {
        BaseFragment fragment = null;
        fragment = (BaseFragment) fragmentMap.get(pos);
        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;

                default:
                    break;
            }
            fragmentMap.put(pos, fragment);
        }
        return fragment;
    }
}
