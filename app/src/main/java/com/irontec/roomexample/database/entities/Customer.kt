package com.irontec.roomexample.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by axier on 7/2/18.
 */

@Entity
class Customer constructor(uid: Int, firstName: String, lastName: String) {

    @PrimaryKey
    var uid: Int = uid

    @ColumnInfo(name = "first_name")
    var firstName: String? = firstName

    @ColumnInfo(name = "last_name")
    var lastName: String? = lastName
}
