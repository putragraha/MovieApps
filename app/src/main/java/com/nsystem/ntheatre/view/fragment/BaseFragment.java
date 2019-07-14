package com.nsystem.ntheatre.view.fragment;

import android.widget.Toast;

import android.app.Fragment;

import com.nsystem.ntheatre.internal.di.HasComponent;

public abstract class BaseFragment extends Fragment {

    void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
