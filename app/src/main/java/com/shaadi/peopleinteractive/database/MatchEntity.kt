package com.shaadi.peopleinteractive.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shaadi.peopleinteractive.common.ListItem
import com.shaadi.peopleinteractive.utils.Constants
import java.io.Serializable


@Entity(
    tableName = "matches"
)
data class MatchEntity(
    val name: String?,
    val photo: String?,
    @Constants.Companion.STATUS
    var status: Int
) : Serializable,ListItem {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

//     fun <T : Any?> convertTo(clazz: Class<T>?): T? {
//        if (clazz == Match::class.java) {
//            return Match(id, name, photo, status) as T
//        }
//        return null
//    }

}
