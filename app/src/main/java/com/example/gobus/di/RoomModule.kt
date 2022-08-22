package com.example.gobus.di

import android.content.Context
import com.example.gobus.data.local.dao.RoomData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomDataSource(context: Context): RoomData{
        return RoomData.buildDatabase(context)
    }
}