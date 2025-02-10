package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        skus.groupingBy { it }.eachCount().let {
            val clearCart = it.removeFreeItems()
            return clearCart.calculateCartTotal().minus(clearCart.calculateSpecialOffers())
        }
    }

    private fun Map<Char, Int>.calculateCartTotal(): Int {
        return this.entries.sumOf { (item, quantity) ->
            when (item) {
                'A' -> quantity * 50
                'B' -> quantity * 30
                'C' -> quantity * 20
                'D' -> quantity * 15
                'E' -> quantity * 40
                'F' -> quantity * 10
                'G' -> quantity * 20
                'H' -> quantity * 10
                'I' -> quantity * 35
                'J' -> quantity * 60
                'K' -> quantity * 70
                'L' -> quantity * 90
                'M' -> quantity * 15
                'N' -> quantity * 40
                'O' -> quantity * 10
                'P' -> quantity * 50
                'Q' -> quantity * 30
                'R' -> quantity * 50
                'S' -> quantity * 20
                'T' -> quantity * 20
                'U' -> quantity * 40
                'V' -> quantity * 50
                'W' -> quantity * 20
                'X' -> quantity * 17
                'Y' -> quantity * 20
                'Z' -> quantity * 21
                else -> return -1
            }
        }
    }

    private fun Map<Char, Int>.calculateSpecialOffers(): Int {
        return this.entries.sumOf { (item, quantity) ->
            when (item) {
                'A' -> quantity / 5 * 50 + (quantity % 5) / 3 * 20
                'B' -> quantity / 2 * 15
                'H' -> quantity / 10 * 20 + (quantity % 10) / 5 * 5
                'K' -> quantity / 2 * 10
                'P' -> quantity / 5 * 50
                'Q' -> quantity / 3 * 10
                'V' -> quantity / 3 * 20 + (quantity % 3) / 2 * 10
                else -> 0
            }
        }
    }

    private fun Map<Char, Int>.calculateGroupOffers(): Int {
        val groupOfferTrigger = this.entries.sumOf { (item, quantity) ->
            when (item) {
                GroupOffer -> quantity
                else -> 0
            }
        }
        return this.entries.sumOf { (item, quantity) ->
            when (item) {
                'A' -> quantity / 5 * 50 + (quantity % 5) / 3 * 20
                'B' -> quantity / 2 * 15
                'H' -> quantity / 10 * 20 + (quantity % 10) / 5 * 5
                'K' -> quantity / 2 * 10
                'P' -> quantity / 5 * 50
                'Q' -> quantity / 3 * 10
                'V' -> quantity / 3 * 20 + (quantity % 3) / 2 * 10
                else -> 0
            }
        }
    }

    private fun Map<Char, Int>.removeFreeItems(): Map<Char, Int>{
        val consolidationItemsMap = this.toMutableMap()
        consolidationItemsMap['E']?.let {
            consolidationItemsMap['B'] = consolidationItemsMap['B']?.minus(consolidationItemsMap['E']!!.div(2)) ?: 0
        }
        consolidationItemsMap['F']?.let {
            consolidationItemsMap['F'] = consolidationItemsMap['F']?.minus(consolidationItemsMap['F']!!.div(3)) ?: 0
        }
        consolidationItemsMap['N']?.let {
            consolidationItemsMap['M'] = consolidationItemsMap['M']?.minus(consolidationItemsMap['N']!!.div(3)) ?: 0
        }
        consolidationItemsMap['R']?.let {
            consolidationItemsMap['Q'] = consolidationItemsMap['Q']?.minus(consolidationItemsMap['R']!!.div(3)) ?: 0
        }
        consolidationItemsMap['U']?.let {
            consolidationItemsMap['U'] = consolidationItemsMap['U']?.minus(consolidationItemsMap['U']!!.div(4)) ?: 0
        }
        return consolidationItemsMap
    }
}

enum class GroupOffer(val quantity: Int, val price: Int) {
    STXYZ(5, 50),
}

