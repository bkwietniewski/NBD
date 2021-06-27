import scala.annotation.tailrec

object First {

  val daysList: List[String] = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
  val productsMap: Map[String, Double] = Map("Truskawka" -> 4, "Jagoda" -> 1, "Udko" -> 1.5, "Arbuz" -> 5)
  val myTuple: (String, Int, Boolean) = Tuple3("Krotka", 4, false)
  val intsWith0: List[Int] = List(0, 1, 2, 0, 3, 4, 0, 5, 6, 0, 0, 7, 8, 0)
  val ints: List[Int] = List(-5, -6, 0, 3, 13, 12)

  // 1.a. złączenie dni tygodnia poprzez pętlę for
  def ex1a(days: List[String]): String = {
    var result = days.head
    for (d <- days.tail) {
      result += "," + d
    }
    result
  }

  // 1.b. złączenie dni tygodnia poprzez pętlę for wypisując tylko dni z nazwami zaczynającymi się na „P”
  def ex1b(days: List[String]): String = {
    var result = ""
    for (d <- days if d.startsWith("P")) {
      result += d + ","
    }
    if (result.takeRight(1) == ",") result = result.dropRight(1)
    result
  }

  // 1.c. złączenie dni tygodnia poprzez pętlę while
  def ex1c(days: List[String]): String = {
    var result = ""
    var list = days
    while (list.nonEmpty) {
      result += list.head + ","
      list = list.tail
    }
    if (result.takeRight(1) == ",") result = result.dropRight(1)
    result
  }

  // 2.a. złączenie dni tygodnia z wykorzystaniem funkcji rekurencyjnej
  def ex2a(days: List[String]): String = {
    if (days.isEmpty) return ""
    if (days.length == 1) return days.head
    days.head + "," + ex2a(days.tail)
  }

  // 2.b. złączenie dni tygodnia z wykorzystaniem funkcji rekurencyjnej wypisując elementy listy od końca
  def ex2b(days: List[String]): String = {
    if (days.isEmpty) return ""
    if (days.length == 1) return days.head
    ex2b(days.tail) + "," + days.head
  }

  // 3. złączenie dni tygodnia z wykorzystaniem rekurencji ogonowej
  def ex3(days: List[String]): String = {
    @tailrec
    def iter(list: List[String], result: String): String = {
      if (list.isEmpty) result
      else iter(list.tail, result + "," + list.head)
    }

    iter(days.tail, days.head)
  }

  // 4.a. złączenie dni tygodnia z wykorzystaniem metody foldl
  def ex4a(days: List[String]): String = days.tail.foldLeft(days.head)(_ + "," + _)

  // 4.b. złączenie dni tygodnia z wykorzystaniem metody foldr
  def ex4b(days: List[String]): String = days.init.foldRight(days.last)(_ + "," + _)

  // 4.c. złączenie dni tygodnia z wykorzystaniem metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P”
  def ex4c(days: List[String]): String = days.filter(d => d.startsWith("P")).tail.foldLeft(days.head)(_ + "," + _)

  // 5. wygenerowanie listy z 10% obniżką cen
  def ex5(products: Map[String, Double]): Map[String, Double] = products.mapValues(f => f * 0.9).toMap

  // 6. krotka z 3 wartościami różnych typów
  def ex6(tup: (_, _, _)): Unit = println(tup._1 + ", " + tup._2 + ", " + tup._3)

  // 7.1. prezentacja option na mapie
  def ex71(products: Map[String, Double]): Unit = {
    println("products.get(\"Truskawka\")\t\t\t" + products.get("Truskawka"))
    println("products.get(\"Banan\")\t\t\t\t" + products.get("Banan"))
    println("products.getOrElse(\"Truskawka\", 0)\t" + products.getOrElse("Truskawka", -5))
    println("products.getOrElse(\"Banan\", 0))\t\t" + products.getOrElse("Banan", -5))
  }

  // 7.2. prezentacja option na mapie - zamiana cen produktów, których pierwotna cena > 2
  def ex72(products: Map[String, Double]): Map[String, Double] = {
    val lessThan2 = products map (p => (p._1, p._2 match {
      case p._2 if p._2 <= 2 => Some(p._2)
      case _ => None
    }))
    lessThan2.filter(l => l._2.isEmpty).mapValues(_.getOrElse(0.99)).toMap
  }

  // 8. usuwanie zera z listy wartości całkowitych
  def ex8(lista: List[Int]): List[Int] = {
    lista match {
      case 0 :: tail => ex8(tail)
      case head :: tail => head :: ex8(tail)
      case Nil => lista
    }
  }

  // 9. elementy listy zwiększone o 1
  def ex9(lista: List[Int]): List[Int] = lista map (_ + 1)

  // 10. wartość bezwględna z przdziału <-5,12>
  def ex10(lista: List[Int]): List[Int] = lista.filter(n => n >= -5 && n <= 12) map (_.abs)


  def main(args: Array[String]): Unit = {
    println("Zadanie 1.a. " + ex1a(daysList))
    println("Zadanie 1.b. " + ex1b(daysList))
    println("Zadanie 1.c. " + ex1c(daysList))
    println("Zadanie 2.a. " + ex2a(daysList))
    println("Zadanie 2.b. " + ex2b(daysList))
    println("Zadanie 3. " + ex3(daysList))
    println("Zadanie 4.a. " + ex4a(daysList))
    println("Zadanie 4.b. " + ex4b(daysList))
    println("Zadanie 4.c. " + ex4c(daysList))
    println("Zadanie 5. " + ex5(productsMap))
    print("Zadanie 6. ")
    ex6(myTuple)
    println("Zadanie 7. ")
    ex71(productsMap)
    println("ex72(productsMap):\t\t" + ex72(productsMap))
    println("Zadanie 8. " + ex8(intsWith0))
    println("Zadanie 9. " + ex9(ex8(intsWith0)))
    println("Zadanie 10. " + ex10(ints))
  }
}

