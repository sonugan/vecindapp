package vecindapp

class Comentario {
    static belongsTo = [vecino: Vecino]
    Date fecha
    String texto
    Puntaje puntaje

    static constraints = {
        vecino nullable: false
        fecha nullable: false
        texto nullable: false, blank: false, minSize: 2, maxSize: 1000
        //puntaje nullable: false
    }
}
