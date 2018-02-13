package com.irontec.roomexample.database.entities

import android.arch.persistence.room.*

/**
 * Created by axier on 7/2/18.
 */

@Entity(foreignKeys =
    [
        ForeignKey(
                entity = Customer::class,
                parentColumns = arrayOf("uid"),
                childColumns = arrayOf("customer_id"),
                onDelete = ForeignKey.CASCADE
        )
    ], indices = [Index(value = "customer_id")]
)
class Bill constructor(amount: Int, customerId: Int) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "amount")
    var amount: Int = amount

    @ColumnInfo(name = "customer_id")
    var customerId: Int = customerId
}
