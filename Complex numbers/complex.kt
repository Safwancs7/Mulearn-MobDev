import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.exp
import kotlin.math.sqrt

data class Complex(val real: Double, val imag: Double) {

    fun add(other: Complex): Complex {
        return Complex(real + other.real, imag + other.imag)
    }

    fun subtract(other: Complex): Complex {
        return Complex(real - other.real, imag - other.imag)
    }

    fun multiply(other: Complex): Complex {
        return Complex(
            real * other.real - imag * other.imag,
            real * other.imag + imag * other.real
        )
    }

    fun divide(other: Complex): Complex {
        val denominator = other.real * other.real + other.imag * other.imag
        if (denominator == 0.0) {
            throw ArithmeticException("Division by zero complex number")
        }
        return Complex(
            (real * other.real + imag * other.imag) / denominator,
            (imag * other.real - real * other.imag) / denominator
        )
    }

    fun conjugate(): Complex {
        return Complex(real, -imag)
    }

    fun absoluteValue(): Double {
        return sqrt(real * real + imag * imag)
    }

    fun exponential(): Complex {
        val expReal = exp(real)
        return Complex(
            expReal * cos(imag),
            expReal * sin(imag)
        )
    }

    override fun toString(): String {
        return if (imag >= 0)
            "$real + ${imag}i"
        else
            "$real - ${-imag}i"
    }
}

fun main() {
    val z1 = Complex(3.0, 4.0)
    val z2 = Complex(1.0, -2.0)

    println("z1 = $z1")
    println("z2 = $z2")

    println("Addition: ${z1.add(z2)}")
    println("Subtraction: ${z1.subtract(z2)}")
    println("Multiplication: ${z1.multiply(z2)}")
    println("Division: ${z1.divide(z2)}")

    println("Conjugate of z1: ${z1.conjugate()}")
    println("Absolute value of z1: ${z1.absoluteValue()}")
    println("Exponential of z1: ${z1.exponential()}")
}
