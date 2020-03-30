package mx.edu.ittepic.ladm_u2_p3_lopezvelazquez

import kotlinx.android.synthetic.main.activity_main.*

class HiloControl (p:MainActivity) : Thread(){
    private var iniciado = false
    private var puntero = p
    private var pausa = true
    var jugador = Jugador()
    private var dados=Array(2,{0})

    override fun run() {
        super.run()
        iniciado = true
        while(iniciado){
            sleep(1200)
            if(!pausa){
                sleep(600)
                puntero.runOnUiThread {
                    puntero.estado.text="Esperando dados.."
                }
                dados[0]=jugador.tirarDados()
                dados[1]=jugador.tirarDados()
                puntero.runOnUiThread {
                    jugador.etiqueta!!.text = "${jugador.getNombre()} tirando.."
                }
                sleep(1200)
                jugador.agregarPuntaje(dados[0])
                jugador.agregarPuntaje(dados[1])
                puntero.runOnUiThread{
                    puntero.estado.text="Dado 1: ${dados[0]}, Dado2: ${dados[1]}"
                }
                sleep(600)
                puntero.runOnUiThread {
                    puntero.puntaje()
                    jugador.etiqueta!!.text = "${jugador.getNombre()} esperando"
                }
                pausar()
                when(jugador.getNombre()){
                    "J1"->{
                        puntero.jugador2!!.despausar()
                    }
                    "J2"->{
                        puntero.jugador3!!.despausar()
                    }
                    "J3"->{
                        puntero.jugador4!!.despausar()
                    }
                    "J4"->{
                        sleep(600)
                        puntero.runOnUiThread { puntero.estado.text="Turno finalizado" }
                        puntero.turnos--
                        if(puntero.turnos!=0){
                            puntero.jugador1!!.despausar()
                        }
                        else{
                            detener()
                            puntero.jugador1!!.detener()
                            puntero.jugador2!!.detener()
                            puntero.jugador3!!.detener()
                            sleep(600)
                            puntero.runOnUiThread { puntero.ganador() }
                        }

                    }
                }
                }
            }
        }

    fun estaIniciado(): Boolean {
        return iniciado
    }

    fun pausar() {
        pausa = true
    }

    fun despausar() {
        pausa = false
    }

    fun detener() {
        iniciado = false
    }
}