package com.disin.acertijo.data.repository

class RepositoryPhrases {

    fun getAllPhrases(): ArrayList<String> {
        var list = ArrayList<String>()

        list.add("hable")
        list.add("Eddie")
        list.add("borde")
        list.add("Monte")
        list.add("Oeste")
        list.add("mueve")
        list.add("Jaime")
        list.add("Torre")
        list.add("suave")
        list.add("podre")
        list.add("bueno")
        list.add("tengo")
        list.add("puedo")
        list.add("mismo")
        list.add("mucho")
        list.add("hecho")
        list.add("mundo")
        list.add("nuevo")
        list.add("luego")
        list.add("tanto")
        list.add("amigo")
        list.add("claro")
        list.add("siglo")
        list.add("visto")
        list.add("dicho")
        list.add("grupo")
        list.add("punto")
        list.add("junto")
        list.add("serio")
        list.add("cinco")
        list.add("medio")
        list.add("unico")
        list.add("juego")
        list.add("largo")
        list.add("chico")
        list.add("libro")
        list.add("miedo")
        list.add("salvo")
        list.add("viejo")
        list.add("justo")
        list.add("llego")
        list.add("cargo")
        list.add("marzo")
        list.add("resto")
        list.add("julio")
        list.add("enero")
        list.add("junio")
        list.add("sitio")
        list.add("texto")
        list.add("Bueno")
        list.add("exito")
        list.add("campo")
        list.add("murio")
        list.add("Puedo")
        list.add("listo")
        list.add("fuego")
        list.add("disco")
        list.add("Pedro")
        list.add("negro")
        list.add("llevo")
        list.add("cielo")
        list.add("Reino")
        list.add("apoyo")
        list.add("suelo")
        list.add("trato")
        list.add("perro")
        list.add("quedo")
        list.add("gusto")
        list.add("Tengo")
        list.add("fondo")
        list.add("mando")
        list.add("sueño")
        list.add("salio")
        list.add("barco")
        list.add("Claro")
        list.add("acabo")
        list.add("radio")
        list.add("Unido")
        list.add("dando")
        list.add("deseo")
        list.add("logro")
        list.add("acabo")
        list.add("llamo")
        list.add("nacio")
        list.add("verlo")
        list.add("llamo")
        list.add("Pablo")
        list.add("Luego")
        list.add("vuelo")
        list.add("entro")
        list.add("novio")
        list.add("Museo")
        list.add("reino")
        list.add("pidio")
        list.add("tonto")
        list.add("Diego")
        list.add("video")
        list.add("brazo")
        list.add("lleno")
        list.add("llevo")
        list.add("Santo")
        list.add("banco")
        list.add("curso")
        list.add("grado")
        list.add("envio")
        list.add("hablo")
        list.add("Nuevo")
        list.add("lindo")
        list.add("hazlo")
        list.add("debio")

        for(i in 0 until list.size){
            list[i].lowercase()
        }


        //list with character no repeated
        val listModifiedNorepeatLetters = ArrayList<String>()
        for (b in 0 until list.size){
            if (!validateCharactersDuplicated(list[b])){
                listModifiedNorepeatLetters.add(list[b].lowercase())
            }
        }
        return listModifiedNorepeatLetters
    }


    fun validateCharactersDuplicated(phrase:String): Boolean {
        val str = phrase
        val carray = str.toCharArray()
        var control = false


        for (i in 0 until str.length) {
            for (j in i + 1 until str.length) {
                if (carray[i] == carray[j]) {
                    control = true
                    println(carray[j].toString() + "duplicado")
                    break
                }
            }
        }
        return control
    }


}