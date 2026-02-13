fun main(){

    val cars = listOf(
        Car("Toyota", "Corolla", "2020", "250000"),
        Car("Toyota", "Corolla", "2020", "250000"),
        Car("BMW", "X5", "2022", "1200000"),
        Car("Hyundai", "Elantra", "2021", "400000"),
        Car("Mercedes", "C200", "2019", "950000"),
        Car("Ford", "Mustang", "2021", "1500000"),
        Car("Kia", "Sportage", "2020", "500000"),
        Car("Honda", "Civic", "2022", "600000"),
        Car("Tesla", "Model 3", "2023", "2000000")
    )


    val brandInput = safeInput("Enter the brand that you want:", true)

    val newCarList = if (brandInput != null) { // لو اليوز مدخلش null
        cars.filter { it.brand.equals(brandInput, ignoreCase = true) }   //x
    } else emptyList()



    val modelInput = safeInput("Enter the model you want:", true)

    val newCarList3= filterlist(modelInput,newCarList)

    val yearInput = safeInput("Enter the year you want:", true)
    val newCarList4 = yearInput?.let {
        filterlist(it, newCarList3.map { car -> car.copy(model = car.year) }) // map year to model for filterlist
    } ?: newCarList3


    val priceInput = safeInput("Enter the price you want:", true)
    val newCarList5 = priceInput?.let {
        filterlist(it, newCarList4.map { car -> car.copy(model = car.price) }) // map price to model for filterlist
    } ?: newCarList4
    if (newCarList5.isNotEmpty()) {
        println("Prices of the matching cars:")
        newCarList5.forEach { println(it.price) }  // only print price
    } else {
        println("No cars match all criteria.")
    }

    val finalPrice=safeInput("choose final price you want:", true)
    println("how do you want to pay visa/cash) ?")

    if (newCarList5.isNotEmpty()) {
        println("\n--- Payment Process ---")
        val paymentMethod = safeInput("How do you want to pay? (visa/cash):", true)

        when (paymentMethod) {
            "visa" -> {
                println("Redirecting to secure gateway...")
                println("Please enter your 16-digit card number:")
                val cardNumber = readlnOrNull()
                if (cardNumber?.length == 16) {
                    println("Payment Successful! Enjoy your new car.")
                } else {
                    println("Invalid card number. Transaction failed.")
                }
            }
            "cash" -> {
                println("Please visit our nearest branch to complete the payment.")
                println("Your reference code is: ${ (1000..9999).random() }")
            }
            else -> {
                println("Invalid payment method selected.")
            }
        }
    } else {
        println("No process to complete because no cars were found.")
    }
}

//fun to take data form your in safe way
fun safeInput(prompt: String, toLowerCase: Boolean = true): String? {
    println(prompt)
    return readlnOrNull()               // read input safely
        ?.trim()                        // remove leading/trailing spaces
        ?.takeIf { it.isNotEmpty() }    // return null if input is empty
        ?.let { if (toLowerCase) it.lowercase() else it } // optional lowercase
}
fun filterlist(input: String?,newCarList: List<Car>): List<Car> {

    val newCarList1= if(input!=null){
        newCarList.filter { it.model.equals(input, ignoreCase = true) }
    }else{
        emptyList()
    }
    if (newCarList1.isNotEmpty()) {
        println("Cars found for that '$input':")
        newCarList1.forEach { println(it) }

    }else{
        println("No cars found for that '$input'.")
    }
    return newCarList1

}



