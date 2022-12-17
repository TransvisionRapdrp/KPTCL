package com.example.kptcl.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Outagemodel : Serializable {
    @SerializedName("TentativeDateofOutage")
    @Expose
    var tentativeDateofOutage: String? = null

    @SerializedName("NameofTown")
    @Expose
    var nameofTown: String? = null

    @SerializedName("NameofPowerHourse")
    @Expose
    var nameofPowerHourse: String? = null

    @SerializedName("NameofFeeder")
    @Expose
    var nameofFeeder: String? = null

    @SerializedName("AreaAffected")
    @Expose
    var areaAffected: String? = null

    @SerializedName("TentativeFrom")
    @Expose
    var tentativeFrom: String? = null

    @SerializedName("TentativeTo")
    @Expose
    var tentativeTo: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
    var day: String? = null
    var date: String? = null
}