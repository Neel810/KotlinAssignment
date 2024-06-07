package com.example.kotlinassignment.data.room_database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.contentplay.data.room_database.converters.ListDataConverter
import com.example.kotlinassignment.data.model.ListDataModel
import com.example.kotlinassignment.data.model.ListDataModelAPI
import com.example.kotlinassignment.data.room_database.dao.ListDataDAO


@Database(entities = [ListDataModel::class], version = 1, exportSchema = false)
@TypeConverters(ListDataConverter::class)
abstract class ListDataDatabase: RoomDatabase(){

    abstract fun listDataDao(): ListDataDAO

    companion object {

        @Volatile
        private  var INSTANCE: ListDataDatabase? = null

        fun getDatabase(context: Context) : ListDataDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListDataDatabase::class.java,
                    "list_data"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}