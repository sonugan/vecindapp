package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Vecino)
class VecinoSpec extends HibernateSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test vecino valido"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', apellido: 'Sanchez', usuario:'Pepe', fechaNacimiento: new Date())
          vecino.save()
        then:
          !vecino.hasErrors()
          Vecino.count()==1
    }

    void "test vecino sin nombre"() {
        when:
          Vecino vecino = new Vecino(apellido: 'Juarez', usuario:'Pepe', fechaNacimiento: new Date())
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('nombre')
          Vecino.count() == 0
    }

    void "test vecino con nombre vacio"() {
        when:
          Vecino vecino = new Vecino(nombre: '', apellido: 'Juarez', usuario:'Pepe', fechaNacimiento: new Date())
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('nombre')
          Vecino.count() == 0
    }

    void "test vecino sin apellido"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', usuario:'Pepe', fechaNacimiento: new Date())
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('apellido')
          Vecino.count() == 0
    }

    void "test vecino con apellido vacio"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', apellido: '', usuario:'Pepe', fechaNacimiento: new Date())
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('apellido')
          Vecino.count() == 0
    }

    void "test vecino sin fecha de nacimiento"() {
        when:
          Vecino vecino = new Vecino(nombre: 'Juan', apellido: 'Sanchez', usuario:'Pepe')
          vecino.save()
        then:
          vecino.hasErrors()
          vecino.errors.getFieldError('fechaNacimiento')
          Vecino.count() == 0
    }
}
