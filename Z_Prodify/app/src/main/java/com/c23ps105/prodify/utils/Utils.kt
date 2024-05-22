package com.c23ps105.prodify.utils

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.Patterns
import com.c23ps105.prodify.R
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Locale

inline fun <reified T> T.cat(message: Any?) =
    Log.i("CatLog ${T::class.java.simpleName}", message.toString())

private const val FILENAME_FORMAT = "dd-MMM-yyyy"
private const val MAXIMAL_SIZE = 1000000

val timeStamp: String = SimpleDateFormat(
    FILENAME_FORMAT,
    Locale.US
).format(System.currentTimeMillis())

fun createFile(application: Application): File {
    val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
        File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    val outputDirectory = if (
        mediaDir != null && mediaDir.exists()
    ) mediaDir else application.filesDir

    return File(outputDirectory, "$timeStamp.jpg")
}

fun isEmailValid(email: CharSequence): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun categoryTextTransform(text: String): String {
    return text.lowercase()
        .split("_")
        .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
}

fun reduceFileImage(file: File): File {
    val bitmap = BitmapFactory.decodeFile(file.path)
    var compressQuality = 100
    var streamLength: Int
    do {
        val bmpStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
        val bmpPicByteArray = bmpStream.toByteArray()
        streamLength = bmpPicByteArray.size
        compressQuality -= 5
    } while (streamLength > MAXIMAL_SIZE)
    bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
    return file
}

fun textRequestBody(text: Any? = null): RequestBody {
    return text.toString().toRequestBody("text/plain".toMediaType())
}

fun number(text: Any? = null): RequestBody {
    return text.toString().toRequestBody("text/plain".toMediaType())
}

fun imageMultipart(file: File, requestName: String): MultipartBody.Part {
    val requestImageFile = file.asRequestBody("image/*".toMediaType())
    return MultipartBody.Part.createFormData(
        requestName,
        file.name,
        requestImageFile
    )
}

fun String.toClipboard(requireActivity: Context, label: String) {
    val clipboard =
        requireActivity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, this)
    clipboard.setPrimaryClip(clip)
}