package com.example.gobus.utils

import androidx.room.TypeConverter
import com.example.gobus.data.local.entity.BoundPointEntity
import com.example.gobus.data.local.entity.PointEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class GsonConverter {
    @TypeConverter
    fun fromBoundPointsToJSON(boundPointList: List<BoundPointEntity>): String{
        val type: Type = object : TypeToken<List<BoundPointEntity>>(){}.type
        return Gson().toJson(boundPointList, type)
    }

    @TypeConverter
    fun toBoundPointsList(jsonBoundPoints: String): List<BoundPointEntity>{
        val type: Type = object : TypeToken<List<BoundPointEntity?>?>() {}.type
        return Gson().fromJson(jsonBoundPoints, type)
    }


    @TypeConverter
    fun fromPointsToJSON(pointList: List<PointEntity>): String {
        val type: Type = object : TypeToken<List<PointEntity?>?>() {}.type
        return Gson().toJson(pointList, type)
    }

    @TypeConverter
    fun toPointsList(jsonPoint: String): List<PointEntity> {
        val type: Type = object : TypeToken<List<PointEntity?>?>() {}.type
        return Gson().fromJson(jsonPoint, type);
    }
}