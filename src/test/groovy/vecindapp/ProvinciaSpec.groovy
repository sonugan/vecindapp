package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Provincia)
class ProvinciaSpec extends HibernateSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test provincia valida"() {
        when:
          Provincia provincia = new Provincia(nombre: "Buenos Aires")
          provincia.save()
        then:
          !provincia.hasErrors()
          Provincia.count()==1
    }

    void "test provincia sin nombre"() {
        when:
          Provincia provincia = new Provincia()
          provincia.save()
        then:
          provincia.hasErrors()
          provincia.errors.getFieldError('nombre')
          provincia.errors.getErrorCount() == 1
          Provincia.count() == 0
    }

    void "test provincia con nombre vacio"() {
        when:
          Provincia provincia = new Provincia(nombre: '')
          provincia.save()
        then:
          provincia.hasErrors()
          provincia.errors.getFieldError('nombre')
          provincia.errors.getErrorCount() == 1
          Provincia.count() == 0
    }

    void "test provincia con nombre duplicado"() {
        when:
          Provincia provincia = new Provincia(nombre: "Buenos Aires")
          provincia.save()

          Provincia provinciaDuplicada = new Provincia(nombre: "Buenos Aires")
          provinciaDuplicada.save()
        then:
          provinciaDuplicada.hasErrors()
          provinciaDuplicada.errors.getFieldError('nombre')
          provinciaDuplicada.errors.getErrorCount() == 1
          Provincia.count() == 1
    }
}
