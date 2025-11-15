package app.xl.clipboardapp.platform

expect class ClipboardManager() {
    fun copyText(text: String)
}