
package src.problems

import scala.annotation.tailrec

object ProjectEuler {

  def main(args: Array[String]): Unit = {
    println(problem2 + " == 4613732")
    println(problem4 + " == 906609")
    println(problem9 + " == 31875000")
    println(problem18(triangle("problem18_triangle")) + " == 1074")
    //println(problem67(triangle("problem67_triangle")) + " == 7273")
  }
  /*
   * Even Fibonacci numbers
   *
   * Each new term in the Fibonacci sequence is generated by adding the previous
   * two terms. By starting with 1 and 2, the first 10 terms will be:
   *
   * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
   *
   * By considering the terms in the Fibonacci sequence whose values do not
   * exceed four million, find the sum of the even-valued terms.
   */

  // K�yd��n l�pi kaikki fibonacin lukujonon arvot,
  // jotka ovat pienempi� kuin 4 miljoonaa. Jos luku on parillinen,
  // sen arvo lis�t��n summaan. Lopuksi palautetaan summa.

  def problem2(): Int = fib(1, 1, 0)

  @tailrec
  def fib(a: Int, b: Int, summa: Int): Int =
    if (a > 4000000) summa
    else fib(b, a + b, if (a % 2 == 0) a + summa else summa)

  /*
   * Largest palindrome product
   *
   * A palindromic number reads the same both ways. The largest palindrome made
   * from the product of two 2-digit numbers is 9009 = 91 � 99.
   *
   * Find the largest palindrome made from the product of two 3-digit numbers.
   */

  // Etsii kahden sis�kk�isen juoksevan numeron avulla tuloja,
  // jotka toteuttavat palindromin vaatimukset.
  // Lopuksi tallennetuista palindromeista haetaan suurin.

  def onPalindromi(numStr: String): Boolean = {
    val palindromi = (numStr == numStr.reverse)
    if (palindromi) true
    else false
  }

  def problem4(): Int = {
    val palindromit = for {
      x <- 999 to 100 by -1;
      y <- 999 to 100 by -1;
      val tulo = x * y;
      if (onPalindromi(tulo.toString))
    } yield tulo
    palindromit.max
  }

  /*
   * Special Pythagorean triplet
   *
   * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
   * which, a^2 + b^2 = c^2
   *
   * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
   *
   * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
   * Find the product abc.
   */

  // Etsii kahden sis�kk�isen juoksevan numeron avulla c:n arvoa,
  // joka toteuttaa tripletin vaatimukset.
  // Koska tiedet��n, ett� tuloksia voi olla vain yksi,
  // palautetaan listan ensimm�inen elementti

  def onTriplet(a: Int, b: Int, c: Int): Boolean = {
    val triplet = (a * a) + (b * b) == c * c
    if (triplet) true
    else false
  }

  def problem9(): Int = {
    val tulos = for {
      a <- 1 to 999;
      b <- 1 to 999;
      val c = 1000 - (a + b); if (onTriplet(a, b, c))
    } yield a * b * c
    tulos.head
  }

  /*
   * Maximum path sum I
   *
   * By starting at the top of the triangle below and moving to adjacent numbers
   * on the row below, the maximum total from top to bottom is 23.
   *
   *      3
   *     7 4
   *    2 4 6
   *   8 5 9 3
   *
   * That is, 3 + 7 + 4 + 9 = 23.
   *
   * Find the maximum total from top to bottom of the given triangle with 15
   * rows:
   */

  /* 
   * Etenee kolmion huipulta pohjalle.
   * Tarkistaa kaikki mahdolliset reitit ja etsii sen reitin,
   * joka tuottaa suurimman tuloksen
   */

  def problem18(triangle: List[List[Int]]): Int = maxPath(triangle)

  def maxPath(
    triangle: List[List[Int]],
    rivi: Int = 0,
    sarake: Int = 0): Int = {
    if (rivi == triangle.length) 0
    else {
      val nykyinen = triangle(rivi)(sarake)
      val reitti1 = maxPath(triangle, rivi + 1, sarake)
      val reitti2 = maxPath(triangle, rivi + 1, sarake + 1)
      val parasReitti = (reitti1 max reitti2)
      nykyinen + parasReitti
    }
  }

  /*
   * Maximum path sum II
   *
   * By starting at the top of the triangle below and moving to adjacent numbers
   * on the row below, the maximum total from top to bottom is 23.
   *
   *    3
   *   7 4
   *  2 4 6
   * 8 5 9 3
   *
   * That is, 3 + 7 + 4 + 9 = 23.
   *
   * Find the maximum total from top to bottom in the given triangle with
   * one-hundred rows.
   *
   * NOTE: This is a much more difficult version of Problem 18. It is not
   * possible to try every route to solve this problem, as there are 2^99
   * altogether! If you could check one trillion (10^12) routes every second it
   * would take over twenty billion years to check them all. There is an
   * efficient algorithm to solve it. ;o)
   */

  def problem67(triangle: List[List[Int]]): Int = ???

  def triangle(resource: String): List[List[Int]] = scala.io.Source
    .fromURL(getClass.getResource(resource))
    .getLines
    .toList
    .map(toListOfInts)

  def toListOfInts(s: String) = s.split(" ").map(_.toInt).toList
}
