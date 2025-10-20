//package com.riset.antivirus.model
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "scan_history")
//data class ScanHistory(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val result: String,
//    val timestamp: String
//)


package com.riset.antivirus.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scan_history")
data class ScanHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Nama file atau sumber file (opsional, bisa null jika scan lokal)
    val fileName: String? = null,

    // Hash hasil scan (misalnya "MD5: xxx, SHA256: xxx")
    val result: String,

    // Jenis scan: "Local Scan" / "File Scan"
    val scanType: String,

    // Tanggal dan waktu dalam format string
    val timestamp: String
)
