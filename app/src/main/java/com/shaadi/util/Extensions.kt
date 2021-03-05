package com.shaadi.util

import android.Manifest
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.provider.OpenableColumns
import android.provider.Settings
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.shaadi.R
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.showSnack(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT
) {
    try {
        if (message.length > 1) {
            Snackbar.make(this, message, length)
                .apply {
                    setAction("Dismiss") { dismiss() }
                    setActionTextColor(Color.CYAN)
                    show()
                }
        }
    } catch (e: Exception) {
        // Ignored
    }
}

fun Fragment.showSettingsSnack() {
    try {
        Snackbar.make(
            this.requireView(), "Grant permissions from Settings",
            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
        )
            .apply {
                setAction("Launch Settings") {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", activity?.packageName, null)
                    intent.data = uri
                    startActivity(intent)
                    dismiss()
                }
                setActionTextColor(Color.CYAN)
                show()
            }
    } catch (e: Exception) {
        // Ignored
    }
}

/*fun Fragment.parseMessageResponse(response: String?): String {
    return try {
        Gson().fromJson(response, MessageResponse::class.java).message
    } catch (e: Exception) {
        "Something went wrong"
    }
}*/

fun androidx.appcompat.app.AppCompatActivity.showToast(message: String) {
    runOnUiThread {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

fun Fragment.checkPermission(): Boolean {
    context?.let {
        return ContextCompat.checkSelfPermission(
            it,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            it,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            it,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }


    return false
}

fun Fragment.checkPermissionCalendar(): Boolean {
    context?.let {
        return ContextCompat.checkSelfPermission(
            it,
            Manifest.permission.READ_CALENDAR
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            it,
            Manifest.permission.WRITE_CALENDAR
        ) == PackageManager.PERMISSION_GRANTED
    }


    return false
}

fun View.showToast(
    message: String,
    length: Int = Toast.LENGTH_SHORT
) {
    if (message.length > 1) {
        Toast.makeText(this.context, message, length)
            .show()
    }
}

fun androidx.fragment.app.Fragment.dismissKeyboard() {
    try {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    } catch (e: Exception) {
        // Ignored
    }
}

fun ProgressBar.showProgressBar(@NonNull status: Boolean, @NonNull window: Window) {
    try {
        if (status) {
            this.visibility = View.VISIBLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            this.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    } catch (e: Exception) {
        // Ignored
    }
}

fun Fragment.showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit) {
    val builder = AlertDialog.Builder(activity, R.style.AlertDialogLight)
    builder.dialogBuilder()
    val dialog = builder.create()
    dialog.show()
}

fun Context.showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit) {
    val builder = AlertDialog.Builder(this, R.style.CalendarLightN)
    builder.dialogBuilder()
    val dialog = builder.create()
    dialog.show()
}

fun AlertDialog.Builder.positiveButton(
    text: String = "YES",
    handleClick: (which: Int) -> Unit = {}
) {
    this.setPositiveButton(text) { _, which -> handleClick(which) }
}

fun AlertDialog.Builder.negativeButton(
    text: String = "CANCEL",
    handleClick: (which: Int) -> Unit = {}
) {
    this.setNegativeButton(text) { _, which -> handleClick(which) }
}

fun Context.callIntent(phoneNumber: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    } catch (e: Exception) {
        // Ignored
    }
}

fun Context.sendEmail(emailId: String) {
    try {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:$emailId")
        startActivity(Intent.createChooser(emailIntent, "Contact via mail"))
    } catch (e: Exception) {
        // Ignored
    }
}

fun ContentResolver.readAsRequestBody(uri: Uri): RequestBody =
    object : RequestBody() {
        override fun contentType(): MediaType? =
            this@readAsRequestBody.getType(uri)?.toMediaTypeOrNull()

        override fun writeTo(sink: BufferedSink) {
            this@readAsRequestBody.openInputStream(uri)?.source()?.use(sink::writeAll)
        }

        override fun contentLength(): Long =
            this@readAsRequestBody.query(uri, null, null, null, null)?.use { cursor ->
                val sizeColumnIndex: Int = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                cursor.getLong(sizeColumnIndex)
            } ?: super.contentLength()
    }


/** Converting from String to Date **/
fun String.getDateWithServerTimeStamp(): Date? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
    try {
        return dateFormat.parse(this)
    } catch (e: ParseException) {
        return null
    }
}

/** Converting from Date to String**/
fun Date.getStringTimeStampWithDate(): String {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return dateFormat.format(this)
}

