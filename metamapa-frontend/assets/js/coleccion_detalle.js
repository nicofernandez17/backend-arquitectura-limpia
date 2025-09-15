// Array de hechos de la colección
const hechos = [
  { id: 1, titulo: "Inundación en Santa Fe", fecha: "2025-09-12", categoria: "inundacion", ubicacion: "Santa Fe", fuente: "diario-ejemplo", lat: -31.6333, lng: -60.7 },
  { id: 2, titulo: "Incendio en Córdoba", fecha: "2025-09-10", categoria: "incendio", ubicacion: "Córdoba", fuente: "agencia-noticias", lat: -31.4, lng: -64.1833 },
  // ... más hechos
];

let mapa;
let markers = [];

document.addEventListener("DOMContentLoaded", function () {
  // Inicializar mapa
  mapa = L.map("mapa-coleccion").setView([-31.4167, -64.1833], 5);
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "&copy; OpenStreetMap contributors"
  }).addTo(mapa);

  // Mostrar todos los hechos al cargar
  actualizarMapaYLista(hechos);
});

// Función para limpiar y agregar pines + actualizar listado
function actualizarMapaYLista(listaHechos) {
  // Limpiar pines anteriores
  markers.forEach(m => mapa.removeLayer(m));
  markers = [];

  // Agregar pines nuevos
  listaHechos.forEach(h => {
    const marker = L.marker([h.lat, h.lng])
      .addTo(mapa)
      .bindPopup(`<b>${h.titulo}</b><br><a href="hecho_detalle.html?id=${h.id}">Ver detalle</a>`);
    markers.push(marker);
  });

  // Actualizar listado
  const lista = document.getElementById("lista-hechos");
  lista.innerHTML = "";
  listaHechos.forEach(h => {
    lista.innerHTML += `
      <li class="list-group-item">
        <h5>${h.titulo}</h5>
        <p class="mb-1">Fuente: ${h.fuente}</p>
        <small>Fecha: ${h.fecha} | Categoría: ${h.categoria}</small>
        <br>
        <a href="hecho_detalle.html?id=${h.id}" class="btn btn-sm btn-outline-primary mt-2">Ver detalle</a>
      </li>
    `;
  });
}

// Modo navegación
const btnCurado = document.getElementById("modo-curado");
const btnIrrestricto = document.getElementById("modo-irrestricto");
let modo = "curado"; // valor inicial

btnCurado.classList.add("btn-primary");
btnIrrestricto.classList.add("btn-outline-primary");

btnCurado.addEventListener("click", () => {
  modo = "curado";
  btnCurado.classList.replace("btn-outline-primary", "btn-primary");
  btnIrrestricto.classList.replace("btn-primary", "btn-outline-primary");
  console.log("Modo seleccionado:", modo);
});

btnIrrestricto.addEventListener("click", () => {
  modo = "irrestricto";
  btnIrrestricto.classList.replace("btn-outline-primary", "btn-primary");
  btnCurado.classList.replace("btn-primary", "btn-outline-primary");
  console.log("Modo seleccionado:", modo);
});

// Filtros
document.getElementById("filtros-form").addEventListener("submit", function (e) {
  e.preventDefault(); // evita que la página se recargue

  const fecha = document.getElementById("filtro-fecha").value;
  const ubicacion = document.getElementById("filtro-ubicacion").value.toLowerCase();
  const categoria = document.getElementById("filtro-categoria").value;
  const fuente = document.getElementById("filtro-fuente").value;

  const hechosFiltrados = hechos.filter(h => {
    let match = true;
    if (fecha) match = match && h.fecha === fecha;
    if (ubicacion) match = match && h.ubicacion.toLowerCase().includes(ubicacion);
    if (categoria) match = match && h.categoria === categoria;
    if (fuente) match = match && h.fuente === fuente;
    return match;
  });

  actualizarMapaYLista(hechosFiltrados);
});
