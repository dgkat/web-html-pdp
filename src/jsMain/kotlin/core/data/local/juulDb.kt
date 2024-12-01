package core.data.local

external interface Product {
    var name: String
    var id: String
    var type: String
    var imageUrl: String
    var price: Float
    var description: String // List of features (nullable)
    var timestamp: Double
}