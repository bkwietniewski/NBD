object Second {

  // 1. PatternMatching dni tygodnia
  def ex1(day: String): String = day match {
    case "Poniedziałek" | "Wtorek" | "Środa" | "Czwartek" | "Piątek" => "Praca"
    case "Sobota" | "Niedziela" => "Weekend"
    case _ => "Nie ma takiego dnia"
  }

  // 2. KontoBankowe z wplata i wyplata
  class KontoBankowe(private var stanKonta: Int) {
    def this() {
      this(0)
    }

    def wplata(kwota: Int): Unit = stanKonta += kwota

    def wyplata(kwota: Int): Unit = stanKonta -= kwota

    def pobierzStanKonta: Int = stanKonta
  }

  // 3. Przywitanie osoby przy pomocy PatternMatching
  case class Osoba(imie: String, nazwisko: String)

  def ex3(osoba: Osoba): String = osoba match {
    case Osoba("Jan", "Kowalski") => "Siemasz Janku Kowalski"
    case Osoba("Alojzy", "Jakistam") => "Witaj Alojzy"
    case Osoba("Jadwiga", "Rymcymcym") => "Jak mija dzień Jadwigo?"
    case _ => s"Cześć ${osoba.imie} ${osoba.nazwisko}"
  }

  // 4. Zastosowanie przekazanej jako parametr funkcji trzykrotnie
  def ex4(i: Int, funkcja: Int => Int): Int = funkcja(funkcja(funkcja(i)))

  // 5.
  // Ze względu, że występuje już taka klasa stosuję inną nazwę: Osoba -> Osoba2
  abstract class Osoba2(val imie: String, val nazwisko: String) {
    def podatek: Double
  }

  trait Student extends Osoba2 {
    override def podatek: Double = 0
  }

  trait Nauczyciel extends Pracownik {
    override def podatek: Double = 0.1 * pensja
  }

  trait Pracownik extends Osoba2 {
    var pensja: Double = 0.0
    override def podatek: Double = 0.2 * pensja
  }


  def main(args: Array[String]): Unit = {
    println("Zadanie 1.\nex1(\"Czwartek\"): " + ex1("Czwartek") + "\nex1(\"Sobota\"): " + ex1("Sobota") + "\nex1(\"Jakisstring\"): " + ex1("Jakisstring"))

    println("\nZadanie 2.")
    val kb1 = new KontoBankowe(1000)
    val kb2 = new KontoBankowe()
    println("Konto bankowe 1 - stan konta: " + kb1.pobierzStanKonta + "; Konto bankowe 2 - stan konta: " + kb2.pobierzStanKonta)
    println("Konto bankowe 1 - wyplata 300; Konto bankowe 2 - wplata 300")
    kb1.wyplata(300)
    kb2.wplata(300)
    println("Konto bankowe 1 - stan konta: " + kb1.pobierzStanKonta + "; Konto bankowe 2 - stan konta: " + kb2.pobierzStanKonta)

    println("\nZadanie 3.")
    val osoba1 = Osoba("Jan", "Kowalski")
    val osoba2 = Osoba("Jadwiga", "Rymcymcym")
    val osoba3 = Osoba("Alojzy", "Jakistam")
    val osoba4 = Osoba("Adam", "Nowak")
    println(ex3(osoba1) + "\n" + ex3(osoba2) + "\n" + ex3(osoba3) + "\n" + ex3(osoba4))

    println("\nZadanie 4.")
    println("ex4(2, x => x + x): " + ex4(2, x => x + x))
    println("ex4(2, x => x * x): " + ex4(2, x => x * x))

    println("\nZadanie 5.")
    val student = new Osoba2("Jan", "Student") with Student
    val nauczyciel = new Osoba2("Agnieszka", "Nauczyciel") with Nauczyciel
    val pracownik = new Osoba2("Adam", "Pracownik") with Pracownik
    val studentPracownik = new Osoba2("Adam", "StudentPracownik") with Student with Pracownik
    val pracownikStudent = new Osoba2("Adam", "PracownikStudent") with Pracownik with Student
    nauczyciel.pensja = 1000
    pracownik.pensja = 1000
    studentPracownik.pensja = 1000
    pracownikStudent.pensja = 1000
    println("Student podatek: " + student.podatek)
    println(s"Nauczyciel pensja: ${nauczyciel.pensja}, podatek: ${nauczyciel.podatek}")
    println(s"Pracownik pensja: ${pracownik.pensja}, podatek: ${pracownik.podatek}")
    println(s"StudentPracownik (trait with Student with Pracownik) pensja: ${studentPracownik.pensja}, podatek: ${studentPracownik.podatek}")
    println(s"PracownikStudent (trait with Pracownik with Student) pensja: ${pracownikStudent.pensja}, podatek: ${pracownikStudent.podatek}")
  }
}
