export default class Pedido {

    constructor(nombre = '', mesa = 0, tiempo = null, elecciones= []) {
        this.nombre = nombre;
        this.mesa = mesa;
        this.tiempo = tiempo;
        this.elecciones = elecciones;
    }

    findIndex(eleccion) {
        return this.elecciones.findIndex(e => e !== null &&
                    e.articulo.idArticulo === eleccion.articulo.idArticulo &&
                    e.articulo.nombre === eleccion.articulo.nombre &&
                    e.decisiones.length === eleccion.decisiones.length &&
                    e.decisiones.every((v, i) => v.tipo.idTipo === eleccion.decisiones[i].tipo.idTipo &&
                                v.tipo.tipo === eleccion.decisiones[i].tipo.tipo
                    ) &&
                    e.omisiones.length === eleccion.omisiones.length &&
                    e.omisiones.every((v, i) =>
                        v.ingrediente.idIngrediente === eleccion.omisiones[i].ingrediente.idIngrediente &&
                                v.ingrediente.nombre === eleccion.omisiones[i].ingrediente.nombre
                    ));
    }

}