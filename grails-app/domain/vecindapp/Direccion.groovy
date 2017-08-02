package vecindapp

class Direccion {
    String calle
    int altura
    String piso
    String manzana
    String departamento
    String codigoPostal
    static belongsTo = [municipio: Municipio]

    static constraints = {
      calle nullable: false, blank: false
      altura min: 0
      piso nullable: true, blank: true
      manzana nullable: true, blank: true
      departamento nullable: true, blank: true
      codigoPostal nullable: false, blank: false, matches: "^[A-Z][0-9]{4,4}[A-Z]{3,3}"
      municipio nullable: false
    }
}
