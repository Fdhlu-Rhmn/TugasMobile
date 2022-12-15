package com.d121201085.aplikasimanage.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_tugas")
data class Tugas (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "judul") var judul: String,
    @ColumnInfo(name = "deskripsi") var deskripsi: String,
    @ColumnInfo(name = "kategori") var kategori: String,
    ):Parcelable

