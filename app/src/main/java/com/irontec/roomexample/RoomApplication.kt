package com.irontec.roomexample

import android.app.Application
import com.irontec.roomexample.database.AppDatabase
import com.irontec.roomexample.database.entities.Customer
import com.irontec.roomexample.database.entities.Provider
import org.jetbrains.anko.doAsync

/**
 * Created by axier on 7/2/18.
 */

class RoomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        doAsync {
            val database = AppDatabase.getInstance(context = this@RoomApplication)

            if (database.customerDao().all.isEmpty()) {
                val customers: MutableList<Customer> = mutableListOf()
                for (index: Int in 0..20) {
                    val client = Customer(index, "Name" + index, "Surname" + index)
                    customers.add(index, client)
                }
                database.customerDao().insertAll(customers = customers)
            }

            if (database.providerDao().all.isEmpty()) {
                val providers: MutableList<Provider> = mutableListOf()
                for (index: Int in 0..20) {
                    val provider = Provider(index, "Provider " + index)
                    providers.add(index, provider)
                }

                database.providerDao().insertAll(providers = providers)
            }
        }
    }

}
