package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Vecino)
class VecinoSpec extends HibernateSpec {
    Direccion direccion

    def setup() {
      direccion = new Direccion(calle: "calle falsa", altura: 100, codigoPostal: "A1234ABC"
        , municipio: new Municipio(nombre: "Tres de Febrero"
          , provincia: new Provincia(nombre: "Buenos Aires")))
    }

    def cleanup() {
    }

    void "test vecino valido"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', apellido: 'Sanchez', usuario:'Pepe'
            , fechaNacimiento: new Date()
            , direccion: direccion)
          vecino.save()
        then:
          !vecino.hasErrors()
          Vecino.count()==1
    }

    void "test vecino sin nombre"() {
        when:
          Vecino vecino = new Vecino(apellido: 'Juarez', usuario:'Pepe', fechaNacimiento: new Date()
            , direccion: direccion)
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('nombre')
          vecino.errors.getErrorCount() == 1
          Vecino.count() == 0
    }

    void "test vecino con nombre vacio"() {
        when:
          Vecino vecino = new Vecino(nombre: '', apellido: 'Juarez', usuario:'Pepe', fechaNacimiento: new Date()
            , direccion: direccion)
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('nombre')
          vecino.errors.getErrorCount() == 1
          Vecino.count() == 0
    }

    void "test vecino sin apellido"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', usuario:'Pepe', fechaNacimiento: new Date()
            , direccion: direccion)
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('apellido')
          vecino.errors.getErrorCount() == 1
          Vecino.count() == 0
    }

    void "test vecino con apellido vacio"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', apellido: '', usuario:'Pepe', fechaNacimiento: new Date()
            , direccion: direccion)
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('apellido')
          vecino.errors.getErrorCount() == 1
          Vecino.count() == 0
    }

    void "test vecino sin fecha de nacimiento"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', apellido: 'Sanchez', usuario:'Pepe'
            , direccion: direccion)
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('fechaNacimiento')
          vecino.errors.getErrorCount() == 1
          Vecino.count() == 0
    }
}
