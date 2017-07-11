package vecindapp


import grails.rest.*
import grails.converters.*

class VecinoController extends RestfulController {
    static responseFormats = ['json', 'xml']
    VecinoController() {
        super(Vecino)
    }

    //http://localhost:8080/vecino/search?q=Orange
    def search(String q) {

        respond(Vecino.findAll{nombre==q})
    }
}
