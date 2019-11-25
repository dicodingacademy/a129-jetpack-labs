package com.dicoding.picodiploma.myunittest

// Cuboid == Balok
class CuboidModel {
    private var width: Double = 0.toDouble()
    private var length: Double = 0.toDouble()
    private var height: Double = 0.toDouble()

    val volume: Double
        get() = width * length * height

    val surfaceArea: Double
        get() {
            val wl = width * length
            val wh = width * height
            val lh = length * height

            return 2 * (wl + wh + lh)
        }

    val circumference: Double
        get() = 4 * (width + length + height)

    fun save(width: Double, length: Double, height: Double) {
        this.width = width
        this.length = length
        this.height = height
    }
}