package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Vecindario)
class VecindarioSpec extends HibernateSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test vecindario valido"() {
        when:
          Vecindario vecindario = new Vecindario(nombre:'un vecindario')
          vecindario.save()
        then:
          !vecindario.hasErrors()
    }

    void "test vecindario sin nombre"() {
        when:
          Vecindario vecindario = new Vecindario()
          vecindario.save()
        then:
          vecindario.hasErrors()
          vecindario.errors.getFieldError('nombre')
          Vecindario.count() == 0
    }

    void "test vecindario con nombre vacio"() {
        when:
          Vecindario vecindario = new Vecindario(nombre:'')
          vecindario.save()
        then:
          vecindario.hasErrors()
          vecindario.errors.getFieldError('nombre')
          Vecindario.count() == 0
    }
}
