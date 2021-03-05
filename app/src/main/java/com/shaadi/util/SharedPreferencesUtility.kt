
package com.shaadi.util

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread

class SharedPreferencesUtility(
    private val context: Application
) {

    companion object {
        const val USER_NAME = "USER_NAME"
        const val USER_EMAIL = "USER_EMAIL"
        const val TOKEN = "TOKEN"
        const val USER_ID = "USER_ID"
        const val USER_AVATAR = "USER_AVATAR"
        const val S3URL = "S3URL"
        const val STRIP_CUSTOMER_ID = "stripe_customer_id"
    }

    fun getPrefInt(key: String): Int {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getInt(key, 0)
    }

    fun savePrefInt(
        key: String,
        value: Int
    ) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putInt(key, value)
            .apply()
    }

    fun getPrefBoolean(key: String): Boolean {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getBoolean(key, false)
    }

    fun savePrefBoolean(
        key: String,
        value: Boolean
    ) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getPrefLong(key: String): Long {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getLong(key, 0)
    }

    fun savePrefLong(
        key: String,
        value: Long
    ) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putLong(key, value)
            .apply()
    }

    fun getPrefString(key: String): String {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getString(key, "") ?: ""
    }

    fun savePrefString(
        key: String,
        value: String
    ) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(key, value)
            .apply()
    }

    fun getS3Url(): String {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getString(S3URL, "") ?: ""
    }

    fun saveS3Url(value: String) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(S3URL, value)
            .apply()
    }

    fun getToken(): String {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getString(TOKEN, "") ?: ""
    }

    fun saveToken(value: String) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(TOKEN, value)
            .apply()
    }

    fun getUserId(): Int {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getInt(USER_ID, 0)
    }

    fun saveUserId(value: Int) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putInt(USER_ID, value)
            .apply()
    }

    fun saveUserAvatar(value: String) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(USER_AVATAR, value)
            .apply()
    }

    fun getUserAvatar(): String {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getString(USER_AVATAR, "") ?: ""
    }

    fun getUserName(): String {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getString(USER_NAME, "") ?: ""
    }

    fun saveUserName(value: String) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(USER_NAME, value)
            .apply()
    }


       fun getEmail(): String {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getString(USER_EMAIL, "") ?: ""
    }

    fun saveEmail(value: String) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(USER_EMAIL, value)
            .apply()
    }



    fun getStripCustomerID(): String {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return prefs.getString(STRIP_CUSTOMER_ID, "") ?: ""
    }

    fun saveStripCustomerID(value: String) {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(STRIP_CUSTOMER_ID, value)
            .apply()
    }


    @WorkerThread
    fun clearSharedPref() {
        val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefs.edit()
            .clear()
            .apply()
    }
}