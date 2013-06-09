package net.vikasta.fakir

import akka.actor._
import akka.routing.RoundRobinRouter
import akka.util.Duration
import akka.util.duration._
import akka.pattern.ask
//import scala.concurrent.Future
import akka.util.Timeout

object Simulator extends App {

  startSimulation(noOfChordNodes = 4)
  
  sealed trait SimulatorMessage
  case class ShutdownGracefully extends SimulatorMessage
  case class Store extends SimulatorMessage
  case class Retrieve extends SimulatorMessage 
  case class Status(success: Boolean) extends SimulatorMessage
  
  
  def startSimulation(noOfChordNodes: Int) {
    
    val chordSystem = ActorSystem("ChordSystem")
 
    //implicit val timeout = Timeout(5 seconds)
	val chordNode1 = chordSystem.actorOf(Props(new ChordNode(null, 5)), name = "chordNode1") 
	
	val chordNode2 = chordSystem.actorOf(Props(new ChordNode(chordNode1, 5)), name = "chordNode2") 
	
	chordNode1 ! Store
	chordNode1 ! Retrieve
	
	implicit val timeout = Timeout(5 seconds)
	val future = chordNode1 ? Store 
	
	future onComplete 
									{
    									case status: Status  => println("future completed successfully")
    									//case Status(success)=> println("future failed")
									} 
 /* future onSuccess {
    case npe: NullPointerExcep => doSomething
    case 6 => doSomethingElse
    case SomeRegex(param) => doSomethingOther
    case _ => doAnything
  } // Applies the specified partial function to the result of the future when it is completed with an exception
*/
	
	//val result = Future { chordNode ? Store} andThen {case Failure(exception) ⇒ log(exception)}
	
	
	
    
	Thread.sleep(10000)
	println("\n\tChord system going down")
	chordSystem.shutdown()
	

  }


}