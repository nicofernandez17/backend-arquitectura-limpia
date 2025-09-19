document.addEventListener("DOMContentLoaded", () => {
  const listaSolicitudes = document.getElementById("listaSolicitudes");

  // Modal de Bootstrap
  const modalHecho = new bootstrap.Modal(document.getElementById("modalHecho"));
  const modalHechoTitulo = document.getElementById("modalHechoTitulo");
  const modalHechoColeccion = document.getElementById("modalHechoColeccion");
  const modalHechoCategoria = document.getElementById("modalHechoCategoria");
  const modalHechoUbicacion = document.getElementById("modalHechoUbicacion");
  const modalHechoFecha = document.getElementById("modalHechoFecha");
  const modalHechoDescripcion = document.getElementById("modalHechoDescripcion");
  const modalHechoFuente = document.getElementById("modalHechoFuente");
  const modalHechoImagen = document.getElementById("modalHechoImagen");

  function renderSolicitudes() {
    listaSolicitudes.innerHTML = "";

    solicitudes_eliminacion.forEach(solicitud => {
      const li = document.createElement("li");
      li.className = "list-group-item d-flex flex-column flex-md-row justify-content-between align-items-start align-items-md-center mb-2";

      li.innerHTML = `
        <div class="mb-2 mb-md-0">
          <h5 class="mb-1">${solicitud.hechoTitulo}</h5>
          <small class="text-muted">
            Fecha de solicitud: ${solicitud.fechaSolicitud} |
            Solicitante: ${solicitud.solicitante ? solicitud.solicitante : "Anónimo"}
          </small>
          <p class="mt-1 mb-0"><strong>Motivo:</strong> ${solicitud.motivo}</p>
        </div>

        <div class="row g-2 justify-content-md-end w-100">
          <div class="col-6 col-md-auto">
            <button class="btn btn-secondary w-100 btn-ver" title="Ver Hecho">
              <i class="bi bi-eye fs-5 mx-2"></i>
            </button>
          </div>
          <div class="col-6 col-md-auto">
            <button class="btn btn-primary w-100 btn-aprobar" title="Aprobar">
              <i class="bi bi-check-circle fs-5 mx-2"></i>
            </button>
          </div>
          <div class="col-6 col-md-auto">
            <button class="btn btn-danger w-100 btn-rechazar" title="Rechazar">
              <i class="bi bi-x-circle fs-5 mx-2"></i>
            </button>
          </div>
        </div>
      `;

      // Botón Ver Hecho
      li.querySelector(".btn-ver").addEventListener("click", () => {
        const hecho = colecciones
          .flatMap(c => c.hechos.map(h => ({ ...h, coleccion: c.titulo })))
          .find(h => h.id === solicitud.hechoId);

        if (!hecho) return alert("Hecho no encontrado.");

        modalHechoTitulo.textContent = hecho.titulo;
        modalHechoColeccion.textContent = hecho.coleccion;
        modalHechoCategoria.textContent = hecho.categoria;
        modalHechoUbicacion.textContent = hecho.lugar;
        modalHechoFecha.textContent = hecho.fecha;
        modalHechoDescripcion.textContent = hecho.descripcion;
        modalHechoFuente.textContent = hecho.fuente;
        modalHechoImagen.src = hecho.imagen;
        modalHecho.show();
      });

      // Botón Aprobar
      li.querySelector(".btn-aprobar").addEventListener("click", () => {
        console.log(`Aprobado: ${solicitud.hechoTitulo}`);
        li.remove();
      });

      // Botón Rechazar
      li.querySelector(".btn-rechazar").addEventListener("click", () => {
        console.log(`Rechazado: ${solicitud.hechoTitulo}`);
        li.remove();
      });

      listaSolicitudes.appendChild(li);
    });
  }

  renderSolicitudes();
});
