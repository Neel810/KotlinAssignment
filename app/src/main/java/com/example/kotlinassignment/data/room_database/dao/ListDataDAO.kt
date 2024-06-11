package com.example.kotlinassignment.data.room_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinassignment.data.model.Content

@Dao
interface ListDataDAO {

    // Insert list data in database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addListDataToDB(movies: List<Content>)

    @Query("SELECT * FROM list_data")
    fun readAllListFromDB(): LiveData<List<Content>>

////    @Query("SELECT * FROM list_table WHERE genres LIKE :query")
////    fun readAllListFromDBWithSearch(query: String): LiveData<List<ListDataModel>>
//
//    @Query("DELETE FROM list_table")
//    fun deleteListDataFromDB()

}