package com.example.employeedirectory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Employee(
    @Json(name = "uuid")
    val id: String?,
    @Json(name = "full_name")
    val fullName: String?,
    @Json(name = "phone_number")
    val phoneNumber: String?,
    @Json(name = "email_address")
    val email: String?,
    val biography: String?,
    @Json(name = "photo_url_small")
    val photoUrlSmall: String?,
    @Json(name = "photo_url_large")
    val photoUrlLarge: String?,
    val team: String?,
    @Json(name = "employee_type")
    val employeeType: EmployeeEnum
)

enum class EmployeeEnum {
    FULL_TIME,
    PART_TIME,
    CONTRACTOR
}

//TODO need a wrapper behind these two data classes that is going to be used by the UI

@Entity
data class EmployeeEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "email_address") val email: String?,
    @ColumnInfo(name = "biography") val biography: String?,
    @ColumnInfo(name = "photo_url_small") val photoUrlSmall: String?,
    @ColumnInfo(name = "photo_url_large") val photoUrlLarge: String?,
    @ColumnInfo(name = "team") val team: String?,
    @ColumnInfo(name = "employee_type") val employeeType: EmployeeEnum
) {
    companion object {
        fun convert(empl: Employee) : EmployeeEntity  = EmployeeEntity(
            id = empl.id ?: "",
            fullName = empl.fullName,
            phoneNumber = empl.phoneNumber,
            email = empl.email,
            biography = empl.biography,
            photoUrlSmall = empl.photoUrlSmall,
            photoUrlLarge = empl.photoUrlLarge,
            team = empl.team,
            employeeType = empl.employeeType
        )
    }
}