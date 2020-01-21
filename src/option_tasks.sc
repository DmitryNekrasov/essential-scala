def addOptions(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  for {
    a <- optionA
    b <- optionB
  } yield a + b

def addOptionsMap(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  optionA.flatMap(a => optionB.map(a + _))

def addThreeOptions(optionA: Option[Int], optionB: Option[Int], optionC: Option[Int]) =
  for {
    a <- optionA
    b <- optionB
    c <- optionC
  } yield a + b + c

def addThreeOptionsMap(optionA: Option[Int], optionB: Option[Int], optionC: Option[Int]) =
  optionA.flatMap { a =>
    optionB.flatMap { b =>
      optionC.map(a + b + _)
    }
  }

def divide(num: Int, den: Int): Option[Int] =
  if (den != 0) Some(num / den) else None

def divideOptions(optionNum: Option[Int], optionDen: Option[Int]): Option[Int] =
  for {
    num <- optionNum
    den <- optionDen
    res <- divide(num, den)
  } yield res

addOptions(Some(1), Some(7))
addOptions(None, Some(3))
addOptions(Some(5), None)
addOptions(None, None)

addOptionsMap(Some(3), Some(11))

addThreeOptions(Some(7), Some(5), Some(3))
addThreeOptions(None, Some(5), Some(3))

addThreeOptionsMap(Some(7), Some(5), Some(3))
addThreeOptionsMap(None, Some(5), Some(3))

divide(11, 3)
divide(11, 0)

divideOptions(Some(21), Some(0))