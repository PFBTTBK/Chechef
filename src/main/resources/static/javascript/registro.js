import Pedido from './Pedido.js';

const eventSource = new EventSource("/conectarSSE");

eventSource.addEventListener("Pedido", function(event) {
    const pedido = Object.assign(new Pedido(), JSON.parse(event.data));
    añadirPedido(pedido);
});

eventSource.onerror = function (e){
    console.error("SSE Error", e);
}

function añadirPedido(pedido){
    const elemento = document.createElement('div');
    elemento.classList.add('pedido-bg', 'negrita');
    elemento.innerHTML = `<p class="pedido-nombre">${pedido.nombre ? pedido.nombre : ('Mesa: ' + pedido.mesa)}</p>`;
    pedido.elecciones.forEach(eleccion => {
        elemento.innerHTML += `
                <div class="eleccion-bg" data-responsable="${eleccion.articulo.responsable}">
                    <p class="negrita sangria">${eleccion.cantidad}</p>
                    <div>
                        <p>${eleccion.articulo.nombre}</p>
                        ${eleccion.decisiones != '' ? ('<p>' + eleccion.decisiones + '</p>') : ''}
                        ${eleccion.omisiones != '' ? ('<p>' + eleccion.omisiones + '</p>') : ''}
                    </div>
                </div>`;
    });
    document.querySelector('main').prepend(elemento);
}

function visibilidad(condicion){
    document.querySelectorAll('.eleccion-bg').forEach(elemento => {
        if (elemento.dataset.responsable === condicion) {
            elemento.classList.toggle('oculto');
        }});
}

Object.assign(window, {
    visibilidad
});