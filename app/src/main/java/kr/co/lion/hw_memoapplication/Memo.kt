package kr.co.lion.hw_memoapplication

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class Memo() : Parcelable {
    var title: String = ""
    var content: String = ""
    lateinit var date: LocalDate

    constructor(parcel: Parcel) : this() {
        title = parcel.readString().toString()
        content = parcel.readString().toString()
        date = LocalDate.parse(parcel.readString().toString(), DateTimeFormatter.ISO_LOCAL_DATE)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(date.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    companion object CREATOR : Parcelable.Creator<Memo> {
        override fun createFromParcel(parcel: Parcel): Memo {
            return Memo(parcel)
        }

        override fun newArray(size: Int): Array<Memo?> {
            return arrayOfNulls(size)
        }
    }
}