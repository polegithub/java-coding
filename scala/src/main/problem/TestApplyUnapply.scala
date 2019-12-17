package main.problem

/**
  *
  * @author Eric Cheng
  */
object TestApplyUnapply {

}

object EMail {
  //apply方法用于无new构造对象
  def apply(user: String, domain: String) = user + "@" + domain

  //unapply方法用于在模式匹配中充当extractor
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

object ApplyAndUnapply {
  val email = EMail("zhouzhihubeyond", "sina.com")

  //下面的匹配会导致调用EMail.unapply(email)
  //  case EMail(user,domain) => println("user="+user+" domain="+domain)
}

class Person(val firstName: String, val secondName: String)

//在伴生对象中定义apply方法和unapply方法
object Person {
  def apply(firstName: String, secondName: String) = new Person(firstName, secondName)

  def unapply(person: Person): Option[(String, String)] = {
    if (person != null) Some(person.firstName, person.secondName)
    else None
  }
}


//val p=Person("摇摆少年梦","周")
//p match {
////析构出firstName，secondeName
//case Person(firstName,secondName) => println("firstName="+firstName+" secondName="+secondName)
//case _ => println("null object")
//}

//Twice用于匹配重复出现的字符串，它绑定的是一个变量
//即返回的类型是Option[String]
object Twice {
  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)
    if (half == s.substring(length)) Some(half) else None
  }
}

//未绑定任何变量，仅仅返回Boolean类型
object UpperCase {
  def unapply(s: String): Boolean = s.toUpperCase == s
}

object NonAndOneVariablePattern extends App {
  def userTwiceUpper(s: String) = s match {
    //下面的代码相当于执行了下面这条语句
    //UpperCase.unapply(Twich.unapply(EMail.unapply(s)))
    case EMail(Twice(x@UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ =>
      "no match"
  }

  val email = EMail("摇摆少年梦摇摆少年梦", "sina.com")
  println(userTwiceUpper(email))
}