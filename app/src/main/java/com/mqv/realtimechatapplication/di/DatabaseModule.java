package com.mqv.realtimechatapplication.di;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mqv.realtimechatapplication.data.MyDatabase;
import com.mqv.realtimechatapplication.util.Const;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class DatabaseModule {
    @Singleton
    @Provides
    public MyDatabase provideDatabase(@ApplicationContext Context context){
        return Room.databaseBuilder(context, MyDatabase.class, Const.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
}
