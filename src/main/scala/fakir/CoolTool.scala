package fakir 

import java.io.File
import scala.math
import org.clapper.argot.ArgotConverters._
import org.clapper.argot.ArgotUsageException
import org.clapper.argot.ArgotParser
import com.typesafe.config.ConfigFactory

object CoolTool {
  // Argument specifications

  val parser = new ArgotParser(
    "test",
    preUsage=Some("ArgotTest: Version 0.1. Copyright (c) " +
                  "2014, Ratul Mukhopadhyay. Apache license.")
  )

  val iterations = parser.option[Int](List("i", "iterations"), "n",
                                      "Total iterations")
  val verbose = parser.flag[Int](List("v", "verbose"),
                                 List("q", "quiet"),
                                 "Increment (-v, --verbose) or " +
                                 "decrement (-q, --quiet) the " +
                                 "verbosity level.") {
    (onOff, opt) =>

    import scala.math

    val currentValue = opt.value.getOrElse(0)
    val newValue = if (onOff) currentValue + 1 else currentValue - 1
    math.max(0, newValue)
  }

  val noError = parser.flag[Boolean](List("n", "noerror"),
                                     "Do not abort on error.")
  val users = parser.multiOption[String](List("u", "user"), "username",
                                         "User to receive email. Email " +
                                         "address is queried from " +
                                         "database.")

  val email = parser.multiOption[String](List("e", "email"), "emailaddr",
                                         "Address to receive emailed " +
                                         "results.") {
    (s, opt) =>

    val ValidAddress = """^[^@]+@[^@]+\.[a-zA-Z]+$""".r
    ValidAddress.findFirstIn(s) match {
      case None    => parser.usage("Bad email address \"" + s +
                                   "\" for " + opt.name + " option.")
      case Some(_) => s
    }
  }

  val output = parser.parameter[String]("outputfile",
                                        "Output file to which to write.",
                                        false)

  val input = parser.multiParameter[File]("input",
                                          "Input files to read. If not " +
                                          "specified, use stdin.",
                                          true) {
    (s, opt) =>

    val file = new File(s)
    if (! file.exists)
      parser.usage("Input file \"" + s + "\" does not exist.")

    file
  }

  // The guts of the program (omitted here)
  def runCoolTool = {
    object TestApp extends App {
      val config = ConfigFactory.load()
      println(config.getString("example.greeting"))
    }
    
  }

  // Main program
  def main(args: Array[String]) {
    try {
      parser.parse(args)
      runCoolTool
    }

    catch {
      case e: ArgotUsageException => println(e.message)
    }
  }
}