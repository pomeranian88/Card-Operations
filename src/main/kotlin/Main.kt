/*
 * Author: Zev Thompson
 * 
 * Collab Statement: Worked on all the code for Part 2 by myself.
 * 
 * Reflection:  The second part was not so hard, as it was mainly just an extension of some of the ideas in the first
 *              part. Much of what I was working to do was just not have excess loopings and variables, because it would
 *              be easy to write each function with tons of variables to swap around, but much conciser and possibly of
 *              lower run-time to, for example, use a mutableList in tripleCut() to store the positions of the jokers.
 *              Part two took about 1.5 hours, most of it spent drawing out diagrams & writing pseudocode to understand
 *              a proper order of operations.
 */

fun main() {
    val deck = Deck("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
    println(deck.getString(28))
}
