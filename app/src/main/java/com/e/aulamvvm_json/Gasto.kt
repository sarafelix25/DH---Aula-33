package com.e.aulamvvm_json

data class Gasto(var id: Int, var desc: String, var valor: Double) {
    override fun toString(): String {
        return "Gasto(id=$id, desc='$desc', valor=$valor)"
    }
}