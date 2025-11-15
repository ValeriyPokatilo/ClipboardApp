package app.xl.clipboardapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform