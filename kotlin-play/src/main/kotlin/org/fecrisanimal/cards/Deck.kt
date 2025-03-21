package cards

import kotlin.random.Random

class Deck (
    private val allCards: MutableList<Card> = mutableListOf()
) {

   init {
       repeat(4) {
           allCards.add(Goat())
       }
       repeat(4) {
           allCards.add(Duck())
       }
       repeat(4) {
           allCards.add(Horse())
       }
   }

   public fun drawCard() : Card {
       val randomIndex = Random.nextInt(allCards.size)
       val card = allCards[randomIndex]
       allCards.removeAt(randomIndex)
       return card
   }

}