package vecindapp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comercio)
class ComercioSpec extends HibernateSpec {

    Rubro rubro
    Direccion direccion
    def setup() {
        rubro = new Rubro(descripcion: 'Alimentos')
        direccion = new Direccion(calle: "calle falsa", altura: 100, codigoPostal: "A1234ABC"
          , municipio: new Municipio(nombre: "Tres de Febrero"
            , provincia: new Provincia(nombre: "Buenos Aires")))
    }

    def cleanup() {
    }

    void "test comercio valido"() {
        when:
          Comercio comercio = new Comercio(nombre: 'Pepe.sa', usuario: 'pepesa', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          !comercio.hasErrors()
          Comercio.count() == 1
    }

    void "test comercio sin nombre"() {
        when:
          Comercio comercio = new Comercio(usuario: 'pepesa', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('nombre')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio nombre vacio"() {
        when:
          Comercio comercio = new Comercio(nombre: '', usuario: 'pepesa', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('nombre')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio nombre mas largo de lo permitido"() {
        when:
          Comercio comercio = new Comercio(nombre: '0123456789012345678901234567890123456789012345678901234567890'
            , usuario: 'pepesa', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('nombre')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio nombre mas corto de lo permitido"() {
        when:
          Comercio comercio = new Comercio(nombre: '012'
            , usuario: 'pepesa', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('nombre')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio sin usuario"() {
        when:
          Comercio comercio = new Comercio(nombre: 'Pepe sa.', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('usuario')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio con usuario vacio"() {
        when:
          Comercio comercio = new Comercio(nombre: 'Pepe sa', usuario: '', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('usuario')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio con usuario mas largo de lo permitido"() {
        when:
          Comercio comercio = new Comercio(nombre: 'Pepe sa', usuario: '01234567891', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('usuario')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio con usuario mas corto de lo permitido"() {
        when:
          Comercio comercio = new Comercio(nombre: 'Pepe sa', usuario: '012', rubro: rubro, direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('usuario')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio sin rubro"() {
        when:
          Comercio comercio = new Comercio(nombre: 'Pepe sa', usuario: 'pepesa', direccion: direccion)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('rubro')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

    void "test comercio sin direccion"() {
        when:
          Comercio comercio = new Comercio(nombre: 'Pepe sa', usuario: 'pepesa', rubro: rubro)
          comercio.save()
        then:
          comercio.hasErrors()
          comercio.errors.getFieldError('direccion')
          comercio.errors.getErrorCount() == 1
          Comercio.count() == 0
    }

}
