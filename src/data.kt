data class Car(
    val brand: String,
    val model: String,
    val year: String,
    val price: String
)
open class Payment {
    open fun processPayment(amount: String) {
        println("Processing a generic payment of $amount...")
    }
}

class VisaPayment(val cardNumber: String) : Payment() {
    override fun processPayment(amount: String) {
        if (cardNumber.length == 16) {
            println("Visa Payment of $amount successful using card: ****${cardNumber.takeLast(4)}")
        } else {
            println("Visa Payment Failed: Invalid card number.")
        }
    }
}

class CashPayment : Payment() {
    override fun processPayment(amount: String) {
        val refCode = (1000..9999).random()
        println("Cash Payment initiated for $amount.")
        println("Please pay at the branch using Reference Code: $refCode")
    }
}