package solutions.CHK

import java.util.*

object CheckoutSolution {
    fun checkout(skus: String): Int {
        skus.groupingBy { it }.eachCount().let {
            return it.calculateCartTotal()
        }
    }

    private fun Map<Char, Int>.calculateCartTotal(): Int{
        return this.entries.sumOf { (item, quantity) ->
            when (item) {
                'A' -> quantity / 3 * 130 + quantity % 3 * 50
                'B' -> quantity / 2 * 45 + quantity % 2 * 30
                'C' -> quantity * 20
                'D' -> quantity * 15
                else -> return -1
            }
        }
    }
}


