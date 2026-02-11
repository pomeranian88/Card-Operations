/*
 * Author: Zev Thompson
 * 
 * Collab Statement: Worked on all the code & thinking of this by myself. Discussed makeup of Deck.kt briefly with Ian.
 * 
 * Reflection:  The only challenge here was understanding how the LinkedList was set up -- it took a little bit for me
 *              to realize that the LinkedList wasn't stored as any object, but was just a series of loose nodes that 
 *              contained internal info about the next item. Writing on paper helped! Assignment took ~1 hour.
 */

fun main() {
    val deck = Deck("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
    println(deck.countDown())
}
