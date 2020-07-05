package com.mikeni.diccionario.model

import android.os.Parcel
import android.os.Parcelable

data class DefinitionResponse(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
) : Parcelable {
    data class Meaning(
        val definitions: List<Definition>,
        val partOfSpeech: String
    ) {
        data class Definition(
            val definition: String
        )
    }

    constructor(source: Parcel) : this(
        ArrayList<Meaning>().apply { source.readArrayList(Meaning::class.java.classLoader) },
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(meanings)
        writeString(origin)
        writeString(phonetic)
        writeString(word)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DefinitionResponse> =
            object : Parcelable.Creator<DefinitionResponse> {
                override fun createFromParcel(source: Parcel): DefinitionResponse =
                    DefinitionResponse(source)

                override fun newArray(size: Int): Array<DefinitionResponse?> = arrayOfNulls(size)
            }
    }
}