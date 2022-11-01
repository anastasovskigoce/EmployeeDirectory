package com.example.employeedirectory.data

import android.util.Log
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
) {
    companion object {
        const val INVALID_EMP_ID = "-1"
        const val TAG = "EmployeeEnt"

        fun convertFromDB(employeeNetwork: EmployeeEnt): Employee {
            if (employeeNetwork.id == null) {
                Log.e(TAG, "invalaid employeed ID sent, investigate")
                return Employee(
                    id = INVALID_EMP_ID,
                    fullName = employeeNetwork.fullName,
                    phoneNumber = employeeNetwork.phoneNumber,
                    email = employeeNetwork.email,
                    biography = employeeNetwork.biography,
                    photoUrlSmall = employeeNetwork.photoUrlSmall,
                    photoUrlLarge = employeeNetwork.photoUrlLarge,
                    team = employeeNetwork.team,
                    employeeType = employeeNetwork.employeeType
                )
            }

            return Employee(
                id = employeeNetwork.id,
                fullName = employeeNetwork.fullName,
                phoneNumber = employeeNetwork.phoneNumber,
                email = employeeNetwork.email,
                biography = employeeNetwork.biography,
                photoUrlSmall = employeeNetwork.photoUrlSmall,
                photoUrlLarge = employeeNetwork.photoUrlLarge,
                team = employeeNetwork.team,
                employeeType = employeeNetwork.employeeType
            )
        }
    }
}


enum class EmployeeEnum {
    FULL_TIME,
    PART_TIME,
    CONTRACTOR
}


@Entity
data class EmployeeEnt(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "email_address") val email: String?,
    @ColumnInfo(name = "bio") val biography: String?,
    @ColumnInfo(name = "photo_url_small") val photoUrlSmall: String?,
    @ColumnInfo(name = "photo_url_large") val photoUrlLarge: String?,
    @ColumnInfo(name = "team") val team: String?,
    @ColumnInfo(name = "employee_type") val employeeType: EmployeeEnum
) {
    companion object {
        const val INVALID_EMP_ID = "-1"
        const val TAG = "EmployeeEnt"

        fun convertFromNetwork(employeeNetwork: Employee): EmployeeEnt {
            if (employeeNetwork.id == null) {
                Log.e(TAG, "invalaid employeed ID sent, investigate")
                return EmployeeEnt(
                    id = INVALID_EMP_ID,
                    fullName = employeeNetwork.fullName,
                    phoneNumber = employeeNetwork.phoneNumber,
                    email = employeeNetwork.email,
                    biography = employeeNetwork.biography,
                    photoUrlSmall = employeeNetwork.photoUrlSmall,
                    photoUrlLarge = employeeNetwork.photoUrlLarge,
                    team = employeeNetwork.team,
                    employeeType = employeeNetwork.employeeType
                )
            }

            return EmployeeEnt(
                id = employeeNetwork.id,
                fullName = employeeNetwork.fullName,
                phoneNumber = employeeNetwork.phoneNumber,
                email = employeeNetwork.email,
                biography = employeeNetwork.biography,
                photoUrlSmall = employeeNetwork.photoUrlSmall,
                photoUrlLarge = employeeNetwork.photoUrlLarge,
                team = employeeNetwork.team,
                employeeType = employeeNetwork.employeeType
            )
        }
    }
}
