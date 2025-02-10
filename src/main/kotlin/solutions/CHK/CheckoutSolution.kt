package solutions.CHK

import java.util.*

object CheckoutSolution {
    fun checkout(skus: String): Int {
        skus.groupingBy { it }.eachCount().let {
            return it.calculateCartTotal()
        }
    }

    private fun Map<Char, Int>.calculateCartTotal(): Int {
        val consolidationItemsMap = this.toMutableMap()
        consolidationItemsMap['E']?.let {
            consolidationItemsMap['B'] = consolidationItemsMap['B']?.minus(consolidationItemsMap['E']!!.div(2)) ?: 0
        }
        return consolidationItemsMap.entries.sumOf { (item, quantity) ->
            when (item) {
                'A' -> quantity / 3 * 130 + quantity % 3 * 50
                'B' -> quantity / 2 * 45 + quantity % 2 * 30
                'C' -> quantity * 20
                'D' -> quantity * 15
                'E' -> quantity * 40
                else -> return -1
            }
        }
    }
}
