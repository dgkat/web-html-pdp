package cartPage.navigation

import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Coroutine scope for managing state in Kotlin/JS
private val scope = MainScope()

// MutableStateFlow to track the current route
private val _currentRoute = MutableStateFlow(window.location.pathname)
val currentRoute = _currentRoute.asStateFlow()

// Function to navigate between pages
fun navigateTo(path: String) {
    window.history.pushState(null, "", path) // Change URL without reloading
    _currentRoute.value = path // Update StateFlow
}

// Listen for browser back/forward events
fun setupHistoryListener() {
    window.onpopstate = {
        _currentRoute.update {
            window.location.pathname
        }
    }
}

/*
//TODO paste urls like http://localhost:8080/cart/payment should be handled by backend and return index.html
If you're using Ktor to serve your Kotlin/JS app, you need to ensure all unknown routes return index.html.

Modify your routing block:

kotlin
Copy code
routing {
    staticResources("/", "static") // Serve static files (CSS, JS, etc.)

    // Catch-all route: Serves index.html for all unknown paths
    get("{...}") {
        call.respondFile(File("path/to/index.html"))
    }
}*/
