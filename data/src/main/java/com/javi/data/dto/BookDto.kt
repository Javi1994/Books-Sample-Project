package com.javi.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "Default Name",
    @ColumnInfo(name = "description ") val description: String = "Default Description",
    @ColumnInfo(name = "author") val author: String = "Default Author"
)