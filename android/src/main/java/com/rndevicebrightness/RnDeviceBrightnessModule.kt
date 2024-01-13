package com.rndevicebrightness

import android.animation.ValueAnimator
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import kotlin.math.log

class RnDeviceBrightnessModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun getBrightness(promise: Promise) {
    try {
      val lp = currentActivity?.window?.attributes
      promise.resolve(lp?.screenBrightness ?: -1f)
    } catch (e: Exception) {
      promise.reject("BRIGHTNESS_ERROR", "Error retrieving screen brightness", e)
    }
  }

  @ReactMethod
  fun getSystemBrightness(promise: Promise) {
    try {
      val contentResolver = reactApplicationContext.contentResolver
      val brightness = Settings.System.getInt(
        contentResolver,
        Settings.System.SCREEN_BRIGHTNESS
      )
      val normalizedBrightness = brightness / 255.0
      promise.resolve(normalizedBrightness)
    } catch (e: Exception) {
      promise.reject("BRIGHTNESS_ERROR", "Error retrieving system brightness", e)
    }
  }

  @ReactMethod
  fun setBrightness(level: Float) {
    try {
      val context: Context = reactApplicationContext
      val window = currentActivity?.window
      val layoutParams = window?.attributes

      // Clamp the brightness level between 0 and 1
      val clampedLevel = level.coerceIn(0f, 1f)

      // Check for WRITE_SETTINGS permission
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.System.canWrite(context)) {
          // Request WRITE_SETTINGS permission
          val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
          intent.data = Uri.parse("package:" + context.packageName)
          context.startActivity(intent)
          return
        }
      }

      currentActivity?.runOnUiThread {
          layoutParams?.screenBrightness = clampedLevel
          window?.attributes = layoutParams
      }

      // If you want to save the brightness level to system settings, you can use:
      // Settings.System.putInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS, (clampedLevel * 255).toInt())

    } catch (e: Exception) {
      // Handle the error if setting brightness fails
      e.printStackTrace()
    }
  }

  @ReactMethod
  fun setSystemBrightness(level: Float) {
    try {
      val context: Context = reactApplicationContext
      val window = currentActivity?.window
      val layoutParams = window?.attributes

      // Clamp the brightness level between 0 and 1
      val clampedLevel = level.coerceIn(0f, 1f)

      // Check for WRITE_SETTINGS permission
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.System.canWrite(context)) {
          // Request WRITE_SETTINGS permission
          val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
          intent.data = Uri.parse("package:" + context.packageName)
          context.startActivity(intent)
          return
        }
      }

      layoutParams?.screenBrightness = clampedLevel
      window?.attributes = layoutParams
      Settings.System.putInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS, (clampedLevel * 255).toInt())
    } catch (e: Exception) {
      // Handle the error if setting brightness fails
      e.printStackTrace()
    }
  }

  @ReactMethod
  fun setBrightnessAnimation(level: Float, animationDuration: Double = 1000.0) {
    val window = currentActivity?.window
    val layoutParams = window?.attributes
    val currentBrightness = layoutParams?.screenBrightness ?: -1f

    val valueAnimator = ValueAnimator.ofFloat(currentBrightness, level)
    valueAnimator.duration = animationDuration.toLong()

    valueAnimator.addUpdateListener { animation ->
      val animatedValue = animation.animatedValue as Float
      setBrightness(animatedValue)
    }

    valueAnimator.start()
  }

  companion object {
    const val NAME = "RnDeviceBrightness"
  }
}
