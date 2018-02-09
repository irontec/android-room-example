package com.irontec.roomexample.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.irontec.roomexample.database.entities.Bill

/**
 * Created by axier on 7/2/18.
 */

@Dao
interface BillDao {

    @get:Query("SELECT * FROM bill")
    val all: List<Bill>

    @Query("SELECT * FROM bill WHERE uid IN (:billIds)")
    fun loadAllByIds(billIds: Array<Int>): List<Bill>

    @Query("SELECT * FROM bill WHERE customer_id = :uid")
    fun findByCustomerId(uid: Int): List<Bill>

    @Insert
    fun insertAll(bills: List<Bill>)

    @Insert
    fun insert(bill: Bill)

    @Delete
    fun delete(bill: Bill)

}
