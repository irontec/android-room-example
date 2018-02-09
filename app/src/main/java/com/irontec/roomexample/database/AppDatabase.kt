package com.irontec.roomexample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.irontec.roomexample.database.daos.BillDao
import com.irontec.roomexample.database.daos.CustomerDao
import com.irontec.roomexample.database.daos.ProviderDao
import com.irontec.roomexample.database.entities.Bill
import com.irontec.roomexample.database.entities.Customer
import com.irontec.roomexample.database.entities.Provider

/**
 * Created by axier on 7/2/18.
 */

@Database(entities = [(Customer::class), (Provider::class), (Bill::class)], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    abstract fun providerDao(): ProviderDao

    abstract fun billDao(): BillDao

    companion object {

        /**
         * The only instance
         */
        private var sInstance: AppDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "example")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }
    }

}
