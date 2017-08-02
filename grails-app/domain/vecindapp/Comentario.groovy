package vecindapp

class Comentario {
    static belongsTo = [vecino: Vecino]
    Date fecha
    String texto
    Puntaje puntaje

    static constraints = {
        vecino nullable: false
        fecha nullable: false
        texto nullable: false, blank: false
        //puntaje nullable: false
    }
}
