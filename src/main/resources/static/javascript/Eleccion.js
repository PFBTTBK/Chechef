export default class Eleccion{
        constructor(articulo, decisiones = [], omisiones = [], cantidad = null){
            this.articulo = articulo;
            
            this.decisiones = decisiones;
            this.omisiones = omisiones;
            
            this.cantidad = cantidad;
        }
}