package com.mqv.realtimechatapplication.activity.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class FirebaseUserViewModel extends ViewModel {
    protected final MutableLiveData<FirebaseUser> firebaseUser = new MutableLiveData<>();
    protected final CompositeDisposable cd = new CompositeDisposable();

    protected void loadFirebaseUser(){
        var user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseUser.setValue(user);
    }

    public LiveData<FirebaseUser> getFirebaseUser() {
        return firebaseUser;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        cd.dispose();
    }
}
