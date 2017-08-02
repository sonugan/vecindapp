package vecindapp

class Puntaje {
    int cantidadPositivos
    int cantidadNegativos

    static constraints = {
        cantidadPositivos min: 0
        cantidadNegativos min: 0
    }

    void sumar(){
        sumar(1)
    }

    void restar(){
        restar(1);
    }

    void sumar(int numero){
        if(numero<=0){
          throw new Exception("El puntaje a sumar debe ser mayor a cero")
        }
        cantidadPositivos+=numero
    }

    void restar(int numero){
        if(numero<=0){
          throw new Exception("El puntaje a restar debe ser mayor a cero")
        }
        cantidadNegativos+=numero
    }

    int getTotal(){
      return cantidadPositivos - cantidadNegativos
    }
}
