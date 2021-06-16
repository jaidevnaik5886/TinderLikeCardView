package com.shaadi.peopleinteractive.responses.matches


import com.google.gson.annotations.SerializedName
import com.shaadi.peopleinteractive.common.ListItem
import com.shaadi.peopleinteractive.database.MatchEntity
import com.shaadi.peopleinteractive.utils.Constants
import java.io.Serializable

data class MatchResults(
    @SerializedName("id")
    val id: Id,
    @SerializedName("cell")
    val cell: String,
    @SerializedName("dob")
    val dob: Dob,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("login")
    val login: Login,
    @SerializedName("name")
    val name: Name,
    @SerializedName("nat")
    val nat: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("registered")
    val registered: Registered
) : ListItem, Serializable {

    fun <T : Any?> convertTo(clazz: Class<T>?): T? {
        if (clazz == MatchEntity::class.java) {
            return MatchEntity(
                name.title + " " + name.first + " " + name.last,
                picture.medium,
                Constants.DEFAULT
            ) as T
        }
        return null
    }
}
