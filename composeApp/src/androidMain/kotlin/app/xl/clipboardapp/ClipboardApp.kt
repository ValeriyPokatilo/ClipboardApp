package app.xl.clipboardapp

import android.app.Application
import app.xl.clipboardapp.platform.ClipboardManager

class ClipboardApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ClipboardManager.init(this)
    }
}
