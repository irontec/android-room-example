package com.irontec.roomexample.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.irontec.roomexample.database.entities.Customer

/**
 * Created by axier on 7/2/18.
 */

@Dao
interface CustomerDao {

    @get:Query("SELECT * FROM customer")
    val all: List<Customer>

    @Query("SELECT * FROM customer WHERE uid IN (:customersId)")
    fun loadAllByIds(customersId: Array<Int>): List<Customer>

    @Query("SELECT * FROM customer WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Customer

    @Insert
    fun insertAll(customers: List<Customer>)

    @Delete
    fun delete(client: Customer)

}
