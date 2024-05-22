package com.example.begineerandroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String?,
    val age: Int?,
    val email: String?,
    val city: String?
) : Parcelable
//{
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readString()
//    ) {
//    }

//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(id)
//        parcel.writeString(name)
//        parcel.readValue(Int::class.java.classLoader) as? Int
//        parcel.writeString(email)
//        parcel.writeString(city)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Person> {
//        override fun createFromParcel(parcel: Parcel): Person {
//            return Person(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Person?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
