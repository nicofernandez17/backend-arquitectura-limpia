document.addEventListener('DOMContentLoaded', () => {
  const inputBusqueda = document.getElementById('busqueda-colecciones');
  const grid = document.getElementById('grid-colecciones');
  const cards = Array.from(grid.querySelectorAll('.col'));
  const botonesOrden = document.querySelectorAll('[data-orden]');

  let ordenActual = { alfabetico: 'asc' };
  let ultimoCriterio = 'alfabetico';

  function actualizarBotones(criterio) {
    botonesOrden.forEach(b => {
      const icono = b.querySelector('i');
      // Reset: flecha arriba y quitar btn-success
      icono.classList.remove('bi-arrow-down');
      icono.classList.add('bi-arrow-up');
      b.classList.remove('btn-primary', 'text-light');
    });

    // Botón activo
    const botonActivo = document.querySelector(`[data-orden="${criterio}"]`);
    botonActivo.classList.add('btn-primary', 'text-light');
    if(ordenActual[criterio] === 'desc'){
      botonActivo.querySelector('i').classList.remove('bi-arrow-up');
      botonActivo.querySelector('i').classList.add('bi-arrow-down');
    }
  }

  function ordenar(criterio) {
    const visibles = cards.filter(c => c.style.display !== 'none');

    visibles.sort((a, b) => {
      let valA, valB;
      switch(criterio){
        case 'alfabetico':
          valA = a.querySelector('.card-title').textContent.toLowerCase();
          valB = b.querySelector('.card-title').textContent.toLowerCase();
          break;
        case 'cantidad':
          valA = parseInt(a.querySelector('.card').dataset.cantidad || 0);
          valB = parseInt(b.querySelector('.card').dataset.cantidad || 0);
          break;
        case 'fecha':
          valA = new Date(a.querySelector('.card').dataset.fecha);
          valB = new Date(b.querySelector('.card').dataset.fecha);
          break;
      }
      if(valA < valB) return ordenActual[criterio] === 'asc' ? -1 : 1;
      if(valA > valB) return ordenActual[criterio] === 'asc' ? 1 : -1;
      return 0;
    });

    visibles.forEach(c => grid.appendChild(c));
    actualizarBotones(criterio);
  }

  // FILTRADO EN TIEMPO REAL
  inputBusqueda.addEventListener('input', () => {
    const texto = inputBusqueda.value.toLowerCase();
    cards.forEach(col => {
      const titulo = col.querySelector('.card-title').textContent.toLowerCase();
      col.style.display = titulo.includes(texto) ? '' : 'none';
    });
  });

  // CLICK EN BOTONES DE ORDEN
  botonesOrden.forEach(boton => {
    boton.addEventListener('click', () => {
      const criterio = boton.dataset.orden;

      if(ultimoCriterio !== criterio){
        ordenActual[criterio] = 'asc';
        ultimoCriterio = criterio;
      } else {
        ordenActual[criterio] = ordenActual[criterio] === 'asc' ? 'desc' : 'asc';
      }

      ordenar(criterio);
    });
  });

  // ORDEN INICIAL: alfabetico ascendente
  ordenar('alfabetico');
});
