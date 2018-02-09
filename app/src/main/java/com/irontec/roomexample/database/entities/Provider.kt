package com.irontec.roomexample.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by axier on 7/2/18.
 */

@Entity
class Provider constructor(uid: Int, name: String){

    @PrimaryKey
    var uid: Int = uid

    @ColumnInfo(name = "name")
    var name: String? = name
}
