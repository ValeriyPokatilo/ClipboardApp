package app.xl.clipboardapp.platform

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ClipboardManager actual constructor() {

    actual fun copyText(text: String) {
        val ctx = appContext ?: error("ClipboardManager not initialized")

        val clipboard = ctx.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText("Copied text", text)
        clipboard.setPrimaryClip(clip)
    }

    companion object {
        private var appContext: Context? = null

        fun init(context: Context) {
            appContext = context.applicationContext
        }
    }
}
