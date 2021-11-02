package com.example.loginregistermvp.modul.profile;

import android.view.View;

import com.irfankhoirul.mvp_core.base.BaseFragmentHolderActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeFragment() {
        btBack.setVisibility(View.GONE);
        ProfileFragment profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);
    }
}
