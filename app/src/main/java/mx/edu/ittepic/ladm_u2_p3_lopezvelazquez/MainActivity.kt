package mx.edu.ittepic.ladm_u2_p3_lopezvelazquez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var jugador1 : HiloControl?=null
    var jugador2 : HiloControl?=null
    var jugador3 : HiloControl?=null
    var jugador4 : HiloControl?=null
    var turnos = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jugador1= HiloControl(this)
        jugador2= HiloControl(this)
        jugador3= HiloControl(this)
        jugador4= HiloControl(this)
        jugador1!!.jugador.setNombre("J1")
        jugador2!!.jugador.setNombre("J2")
        jugador3!!.jugador.setNombre("J3")
        jugador4!!.jugador.setNombre("J4")
        jugador1!!.jugador.etiqueta=estadoJ1
        jugador2!!.jugador.etiqueta=estadoJ2
        jugador3!!.jugador.etiqueta=estadoJ3
        jugador4!!.jugador.etiqueta=estadoJ4
        jugador1!!.jugador.etiqueta!!.setText("Jugador 1 esperando")
        jugador2!!.jugador.etiqueta!!.setText("Jugador 2 esperando")
        jugador3!!.jugador.etiqueta!!.setText("Jugador 3 esperando")
        jugador4!!.jugador.etiqueta!!.setText("Jugador 4 esperando")
        button.setOnClickListener {
                juego()
                button.visibility=View.GONE
        }
    }
    fun juego(){
            estado.setText("Iniciando Juego..")
            puntaje()
            jugador1!!.start()
            jugador2!!.start()
            jugador3!!.start()
            jugador4!!.start()
            jugador1!!.despausar()
    }
    fun puntaje(){
        puntajeJ1.text = "${jugador1!!.jugador.getPuntaje()}"
        puntajeJ2.text = "${jugador2!!.jugador.getPuntaje()}"
        puntajeJ3.text = "${jugador3!!.jugador.getPuntaje()}"
        puntajeJ4.text = "${jugador4!!.jugador.getPuntaje()}"
    }

    fun ganador(){
        var puntajes = listOf(
            "Jugador 1 " to "con " +jugador1!!.jugador.getPuntaje()+" puntos",
            "Jugador 2" to "con "+jugador2!!.jugador.getPuntaje()+" puntos",
            "Jugador 3" to "con "+ jugador3!!.jugador.getPuntaje()+" puntos",
            "Jugador 4" to "con "+jugador4!!.jugador.getPuntaje()+" puntos"
        )
        var ganador = puntajes.maxBy { it.second }
        estado.setText("Ganador ${ganador}")
    }
}


class Jugador(){
    private var puntaje=0
    private var nombre=""
    var etiqueta:TextView?=null
    fun setNombre(nombre:String){
        this.nombre=nombre
    }
    fun getNombre():String{
        return nombre;
    }
    fun getPuntaje():Int{
        return puntaje
    }
    fun agregarPuntaje(puntaje:Int){
        this.puntaje+=puntaje
    }
    fun tirarDados():Int{
        return (1..6).random()
    }
}