package app.xl.clipboardapp.platform

import platform.UIKit.UIPasteboard

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ClipboardManager actual constructor() {
    actual fun copyText(text: String) {
        UIPasteboard.generalPasteboard.string = text
    }
}