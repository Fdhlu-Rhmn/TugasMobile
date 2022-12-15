package com.d121201085.aplikasimanage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.d121201085.aplikasimanage.model.Tugas


@Database(entities = [Tugas::class], version = 1, exportSchema = false)
abstract class TugasDatabase: RoomDatabase() {
    abstract fun tugasDao() : TugasDao

    companion object{
        @Volatile
        private var INSTANCE: TugasDatabase? =null

        fun getDatabase(context: Context): TugasDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized( this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TugasDatabase::class.java,
                    "tugas_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}