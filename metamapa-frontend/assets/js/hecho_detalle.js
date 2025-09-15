document.addEventListener("DOMContentLoaded", () => {
  // 1. Obtener ID desde la URL
  const params = new URLSearchParams(window.location.search);
  const hechoId = parseInt(params.get("id"));

  // 2. Ejemplo de hechos (en el futuro: fetch a tu backend)
  const hechos = [
    {
      id: 1,
      titulo: "Inundación en Santa Fe",
      descripcion: "Las intensas lluvias provocaron desbordes en varias zonas urbanas.",
      categoria: "Inundación",
      ubicacion: "Santa Fe, Argentina",
      coords: [-31.6333, -60.7000],
      imagen: "assets/img/inundacion.webp",
      fuente: "https://ejemplo.com/inundacion-santafe"
    },
    {
      id: 2,
      titulo: "Incendio forestal en Córdoba",
      descripcion: "Un incendio de gran magnitud afectó 200 hectáreas en las sierras.",
      categoria: "Incendio",
      ubicacion: "Sierras de Córdoba, Argentina",
      coords: [-31.4201, -64.1888],
      imagen: "assets/img/incendio.webp",
      fuente: "https://ejemplo.com/incendio-cordoba"
    }
  ];

  // 3. Buscar el hecho
  const hecho = hechos.find(h => h.id === hechoId);
  if (!hecho) {
    document.querySelector("main").innerHTML = "<p class='text-center text-danger'>Hecho no encontrado</p>";
    return;
  }

  // 4. Insertar datos en el DOM
  document.querySelector("h1").textContent = hecho.titulo;
  document.querySelector(".text-muted span").textContent = hecho.categoria;
  document.querySelector("section .col-md-8 p").textContent = hecho.descripcion;
  document.querySelector("section .col-md-4 p").textContent = hecho.ubicacion;
  document.querySelector("section img").src = hecho.imagen;
  document.querySelector("section img").alt = "Imagen de " + hecho.titulo;
  document.querySelector("section a").href = hecho.fuente;

  // 5. Inicializar Leaflet en la ubicación del hecho
  const map = L.map("mapa-detalle").setView(hecho.coords, 13);

  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);

  L.marker(hecho.coords)
    .addTo(map)
    .bindPopup(`<b>${hecho.titulo}</b><br>${hecho.ubicacion}`);
});
