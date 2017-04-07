package com.hyn.studioframe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.hyn.baselibrary.avc.core.BaseFragmentActivity;
import com.hyn.studioframe.R;
import com.hyn.studioframe.fragment.HomePageFragment;
import com.hyn.studioframe.fragment.MyFragment;
import com.hyn.studioframe.fragment.OtherFragment;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends BaseFragmentActivity {
    private static int currSel = 0;

    private RadioGroup group;

    private Fragment homeFragment = new HomePageFragment();
    private Fragment otherFragment = new OtherFragment();
    private Fragment other1Fragment = new OtherFragment();
    private Fragment myFragment = new MyFragment();
    private List<Fragment> fragmentList = Arrays.asList(homeFragment, otherFragment, other1Fragment, myFragment);

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initFootBar();
    }

    private void initFootBar() {
        group = (RadioGroup) findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.foot_bar_home:
                        currSel = 0;
                        break;
                    case R.id.foot_bar_im:
                        currSel = 1;
                        break;
                    case R.id.foot_bar_interest:
                        currSel = 2;
                        break;
                    case R.id.main_footbar_user:
                        currSel = 3;
                        break;
                }
                addFragmentToStack(currSel);
            }
        });
        addFragmentToStack(0);
    }

    private void addFragmentToStack(int cur) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentList.get(cur);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment f = fragmentList.get(i);
            if (i == cur && f.isAdded()) {
                fragmentTransaction.show(f);
            } else if (f != null && f.isAdded() && f.isVisible()) {
                fragmentTransaction.hide(f);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = fragmentList.get(currSel);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
