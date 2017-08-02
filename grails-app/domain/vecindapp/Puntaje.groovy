package vecindapp

class Puntaje {
    int cantidadPositivos
    int cantidadNegativos

    static constraints = {
        cantidadPositivos min: 0
        cantidadNegativos min: 0
    }
}
