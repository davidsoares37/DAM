package org.example.eventLogSystem
import jdk.jfr.internal.event.EventConfiguration.timestamp

sealed class Event {

    data class Login(val username: String, val timestamp: Long): Event()

    data class Purchase(val username: String, val amount: Double, val timestamp: Long): Event()

    data class Logout(val username: String, val timestamp: Long): Event()

    fun List<Event>.filterByUser(username: String): List<Event> {
        return this.filter {
            when (it) {
                is Event.Login -> it.username
                is Event.Purchase -> it.username
                is Event.Logout -> it.username
            } == username
        }
    }

    fun List<Event>.totalSpent(username: String): Double {
        val listPurchase = filterIsInstance<Purchase>()
        val sum = listPurchase.sumOf { it.amount }
        return sum
    }
}


/*

val events = listOf (
Event.Login (" alice ", 1 _000 ) ,
Event.Purchase (" alice ", 49.99 , 1 _100 ) ,
Event.Purchase ("bob ", 19.99 , 1 _200 ) ,
Event.Login ("bob ", 1 _050 ) ,
Event.Purchase (" alice ", 15.00 , 1 _300 ) ,
Event.Logout (" alice ", 1 _400 ) ,
Event.Logout ("bob ", 1 _500 )
)

EXPECTED OUTPUT:

[ LOGIN ] alice logged in at t =1000
[ PURCHASE ] alice spent $49 .99 at t =1100
[ PURCHASE ] bob spent $19 .99 at t =1200
[ LOGIN ] bob logged in at t =1050
[ PURCHASE ] alice spent $15 .0 at t =1300
[ LOGOUT ] alice logged out at t =1400
[ LOGOUT ] bob logged out at t =1500
Total spent by alice : $64 .99
Total spent by bob : $19 .99
Events for alice :
Login ( username =alice , timestamp =1000)
Purchase ( username =alice , amount =49.99 , timestamp =1100)
Purchase ( username =alice , amount =15.0 , timestamp =1300)
Logout ( username =alice , timestamp =1400)
*/



