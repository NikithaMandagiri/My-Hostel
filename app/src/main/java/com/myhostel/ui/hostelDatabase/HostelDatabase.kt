package com.myhostel.ui.hostelDatabase

import android.content.Context
import android.content.SharedPreferences

class HostelDatabase(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("hostel_database", Context.MODE_PRIVATE)

    fun saveData(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue) ?: defaultValue
    }
}