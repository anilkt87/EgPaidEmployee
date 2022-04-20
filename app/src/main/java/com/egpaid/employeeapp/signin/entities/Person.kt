package com.egpaid.employeeapp.signin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Person")
data class Person(
        @PrimaryKey
        @Expose
        var id: String = INSTANCE_ID,
        @SerializedName("result")
        @Expose
        val result: ArrayList<ResultItem?>? = null,

        @SerializedName("_meta")
        @Expose
        val meta: Meta? = null
) {
    companion object {
        const val INSTANCE_ID = "PERSON_INSTANCE"
    }
}