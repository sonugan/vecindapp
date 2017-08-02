package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Rubro)
class RubroSpec extends HibernateSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test rubro valido"() {
        when:
          Rubro rubro = new Rubro(descripcion: 'Cosmetica')
          rubro.save()
        then:
          !rubro.hasErrors()
          Rubro.count() == 1
    }

    void "test rubro sin descripcion"() {
        when:
          Rubro rubro = new Rubro()
          rubro.save()
        then:
          rubro.hasErrors()
          rubro.errors.getFieldError('descripcion')
          rubro.errors.getErrorCount() == 1
          Rubro.count() == 0
    }

    void "test rubro con descripcion vacia"() {
        when:
          Rubro rubro = new Rubro(descripcion: '')
          rubro.save()
        then:
          rubro.hasErrors()
          rubro.errors.getFieldError('descripcion')
          rubro.errors.getErrorCount() == 1
          Rubro.count() == 0
    }

    void "test rubro con descripcion mas larga de lo permitido"() {
        when:
          Rubro rubro = new Rubro(descripcion: '0123456789012345678901234567891')
          rubro.save()
        then:
          rubro.hasErrors()
          rubro.errors.getFieldError('descripcion')
          rubro.errors.getErrorCount() == 1
          Rubro.count() == 0
    }

    void "test rubro con descripcion mas corta de lo permitido"() {
        when:
          Rubro rubro = new Rubro(descripcion: '12')
          rubro.save()
        then:
          rubro.hasErrors()
          rubro.errors.getFieldError('descripcion')
          rubro.errors.getErrorCount() == 1
          Rubro.count() == 0
    }
}
