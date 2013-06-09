package net.vikasta.fakir

import akka.actor._
import akka.routing.RoundRobinRouter
import akka.util.Duration
import akka.util.duration._
import scala.collection.mutable.ListBuffer
import java.security.MessageDigest
import java.math.BigInteger


class ChordNode(bootstrapChordNode: ActorRef, fingerCount: Int) extends Actor {
  
  case class Finger(start: Int, node: ChordNode)
  
  
  var predecessor: ChordNode = null
  var successor: ChordNode = null
  var fingerTable = List[Finger]() 
  val id = sha1
   
  init()
  
  /*def sha1(input: String): String = {
        val mDigest = MessageDigest.getInstance("SHA1");
        val result = mDigest.digest(input.getBytes())
        val sb = new StringBuffer()
        for (i <- 0 to result.length-1) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
  }*/
  
  def sha1() = {
    val password = "YOUR FAVOURITE STRING HERE"
    val md = MessageDigest.getInstance("SHA-256")
    md.update(password.getBytes())
    val byteData = md.digest()
    val number = new BigInteger(1,byteData)
    println("Your integer : " + number.toString())
}
  
  def init() = {
    println("Entering ChordNode - init()")
    
    sha1()
    
    if(bootstrapChordNode == null)
    {
      println("Bootstrap Chord Node is null")
      
      predecessor = this
      successor = this
      //fingerTable = ListBuffer[Finger]() 
      for(i <- 0 to fingerCount-1)
      {
        println("before adding finger")
        fingerTable ::= new Finger(-1, null);// + fingerTable 
        println("after adding finger")
      }
      
    }
    else
    {
      
    }
    
    println("Exiting ChordNode - init()")
  }
  
  def calculatePiFor(start: Int, nrOfElements: Int): Double = {
      var acc = 0.0
      for (i ← start until (start + nrOfElements))
        acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
      acc
    }
 
    def receive = {
      case Simulator.Store => 
        println("\tChordNode received Simulator.Store message")
        sender ! Simulator.Status(false) 
      case Simulator.Retrieve  => 
        println("\tChordNode received Simulator.Retrieve message")
        sender ! Simulator.Status(false)
    }
    
    def store() = {
      
    }
    
    def retrieve() = {
      
    }
}

