package org.marton.studio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform