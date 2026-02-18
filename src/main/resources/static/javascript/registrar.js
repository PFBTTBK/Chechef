import Articulo from "./Articulo.js";
import Decision from "./Decision.js";
import Eleccion from "./Eleccion.js";
import Ingrediente from "./Ingrediente.js"
import Omision from "./Omision.js";
import Pedido from "./Pedido.js";
import Tipo from "./Tipo.js";

const pedido = new Pedido();
const relaciones = new Map();

function restar(elemento) {

}

function agregar(elemento) {
    pedido.elecciones.push(crearEleccion(elemento));
}

function crearEleccion(elemento) {
    const articuloHTML = elemento.parentElement.previousElementSibling.firstElementChild;
    const eleccion = new Eleccion(
            new Articulo(articuloHTML.getAttribute('Articulo'), articuloHTML.getAttribute('nombre'))
            );
    const ingredientes = elemento.closest('.art-bg-arriba').nextElementSibling;
    if (ingredientes !== null) {
        eleccion.omisiones = agregarOmisiones(ingredientes);
        eleccion.decisiones = agregarDecisiones(ingredientes);
    }
    modificarContador(eleccion.articulo.nombre, 1);
    buscarElemento(eleccion);
    return eleccion;
}

function modificarContador(nombre, adicion){
    const contador = document.getElementById('contador-' + nombre);
    contador.textContent = Number(contador.textContent) + adicion;
}

function buscarElemento(eleccion){
    const i = pedido.findIndex(eleccion);
    return i === -1 ? agregarElemento(eleccion) : sumarEleccion(i);
}

function agregarElemento(eleccion) {
    const indice = pedido.elecciones.length;
    relaciones.set(indice, [indice]);
    const registro = document.getElementById('registro');
    const elemento = document.createElement('div');
    elemento.id = 'elemento-' + indice;
    elemento.classList.add('pdp-actual-art');
    elemento.innerHTML = `
        <p id="contador-${indice}" class="pdp-actual-art-detalle pdp-actual-art-cant pdp-actual-btn" data-indice="${indice}" onclick="eliminarSelecciones(this)">1</p>
        <div class="pdp-actual-art-datelles pdp-actual-btn" data-indice="${indice}" onclick="eliminarSeleccion(this)">
            <p class="pdp-actual-art-detalle pdp-actual-art-nombre">${eleccion.articulo.nombre}</p>
            ${eleccion.decisiones?.length > 0 ? `<p class="pdp-actual-art-detalle pdp-actual-art-tipos">Con: ` + eleccion.decisiones.map(decision => decision.tipo.tipo).join(', ') + `</p>` : ``}
            ${eleccion.omisiones?.length > 0 ? `<p class="pdp-actual-art-detalle pdp-actual-art-omisiones">Sin: ` + eleccion.omisiones.map(omision => omision.ingrediente.nombre).join(', ') + `</p>` : ``}
        </div>
    `;
    registro.appendChild(elemento);
}

function sumarEleccion(i){
    relaciones.get(i).push(pedido.elecciones.length);
    const contador = document.getElementById('contador-' + i);
    contador.textContent = Number(contador.textContent) + 1;
}

function agregarOmisiones(ingredientes) {
    return [...ingredientes.children].filter(ingrediente => ingrediente.classList.contains('ing-omitido'))
            .map(omision => new Omision(new Ingrediente(
                        omision.getAttribute('ingrediente'),
                        omision.getAttribute('nombre')
                        )));
}

function agregarDecisiones(ingredientes) {
    return [...ingredientes.children]
            .filter(ingrediente => ingrediente.classList.contains('tipos-bg'))
            .map(tipos => [...tipos.children]
                        .filter(tipo => !tipo.classList.contains('ing-deseleccionado')))
            .flat().map(tipo => new Decision(new Tipo(
                tipo.getAttribute('tipo'),
                tipo.getAttribute('nombre')
                )));
}

function restablecer(elemento) {
    const hermano = Array.from(elemento.parentElement.children)
            .forEach(hermano => hermano.classList.remove('ing-omitido'));
}

function elegirIngrediente(elemento) {
    elemento.classList.toggle('ing-omitido');
}

function elegirTipo(elemento) {
    if (elemento.classList.contains('ing-deseleccionado')) {
        elemento.classList.remove('ing-deseleccionado');
        const hermanos = Array.from(elemento.parentElement.children)
                .filter(hermano => hermano !== elemento);
        hermanos.forEach(hermano => hermano.classList.add('ing-deseleccionado'));
    }
}

function enviar() {
    if(validar()) persistir();
}

function revisarCondiciones(){
    const elecciones = pedido.elecciones.filter(e => e !== null);
    return (document.getElementById('pdp-inp').value === '' ? 'Ingrese nombre o número de mesa.\n' : '') + 
            (elecciones.length < 1 ? 'Agregue artículos al pedido.\n' : '');
}

function validar(){
    const mensajes = revisarCondiciones();
    if(mensajes !== ''){
        alertar(mensajes, 1);
        return false;
    } else  {
        return true;
    }
}

function persistir() {
    completarPedido();
    fetch('/insertar', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(pedido)
    }).then(response => {
        if(response.ok) reiniciar();
    });
}

function reiniciar(){
    location.reload();
}

function completarPedido() {
    pedido.elecciones = pedido.elecciones.filter(e => e !== null);
    const cliente = document.getElementById('pdp-inp').value;
    if (isNaN(cliente)) {
        pedido.nombre = cliente;
        pedido.mesa = null;
    } else {
        pedido.nombre = null;
        pedido.mesa = cliente;
    }
    pedido.tiempo = new Date().toISOString();
}

function eliminarSelecciones(elemento){
    eliminarArticulos(elemento);
}

function eliminarSeleccion(elemento){
    if (relaciones.get(Number(elemento.dataset.indice)).length > 1) {
        const ultimo = relaciones.get(Number(elemento.dataset.indice)).pop();
        modificarContador(pedido.elecciones[ultimo].articulo.nombre, -1);
        pedido.elecciones[ultimo] = null;
        const contador = document.getElementById('contador-' + elemento.dataset.indice);
        contador.textContent = Number(contador.textContent) - 1;
    } else {
        eliminarArticulos(elemento);
    }
    
}

function eliminarArticulos(elemento){
    modificarContador(pedido.elecciones[elemento.dataset.indice].articulo.nombre, -(relaciones.get(Number(elemento.dataset.indice)).length));
    relaciones.get(Number(elemento.dataset.indice)).forEach(i => pedido.elecciones[i] = null);
    elemento.closest('.pdp-actual-art').remove();
}

function abrir(elemento) {
    const pdp = elemento.closest('.pdp');
    pdp.classList.toggle('registro-abierto');
    pdp.querySelector('.pdp-pedidos-bg').classList.toggle('registro-visible');
}

function alertar(mensaje, gravedad){
    const estados = new Map([
        [0, 'green'], 
        [1, 'yellow'], 
        [2, 'red']
    ]);

    const alerta = document.createElement('div');
    alerta.className = 'alert';
    alerta.textContent = mensaje;
    alerta.style.borderColor = estados.get(gravedad);
    
    const botonAlerta = document.createElement('button');
    botonAlerta.onclick = () => alerta.remove();
    alerta.appendChild(botonAlerta);

    document.body.appendChild(alerta);
}

Object.assign(window, {
    restar,
    agregar,
    restablecer,
    elegirIngrediente,
    elegirTipo,
    enviar, 
    eliminarSelecciones, 
    eliminarSeleccion, 
    abrir
});