package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Institucion)
class InstitucionSpec extends HibernateSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test institucion valida"() {
        when:
          Institucion institucion = new Institucion(nombre: 'Distrito 13')
          institucion.save()
        then:
          !institucion.hasErrors()
          Institucion.count()==1
    }

    void "test institucion sin nombre"() {
        when:
          Institucion institucion = new Institucion()
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('nombre')
          Institucion.count() == 0
    }

    void "test institucion con nombre vacio"() {
        when:
          Institucion institucion = new Institucion(nombre: '')
          institucion.save()
        then:
          institucion.hasErrors()
          institucion.errors.getFieldError('nombre')
          Institucion.count() == 0
    }
}
