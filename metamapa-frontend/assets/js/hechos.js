// assets/js/hechos.js

document.addEventListener("DOMContentLoaded", () => {
  // Inicializar el mapa
  const mapa = L.map("mapa-hechos").setView([-34.6037, -58.3816], 5); // Centro aprox. Argentina

  // Cargar tiles de OpenStreetMap
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: '&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
  }).addTo(mapa);

  // Datos de ejemplo (luego podés cargarlos desde tu backend)
  const hechos = [
    {
      id: 1,
      titulo: "Inundación en Santa Fe",
      descripcion: "Calles anegadas tras fuertes lluvias.",
      lat: -31.6333,
      lng: -60.7000
    },
    {
      id: 2,
      titulo: "Incendio en Córdoba",
      descripcion: "Fuerte incendio forestal en las sierras.",
      lat: -31.4201,
      lng: -64.1888
    },
    {
      id: 3,
      titulo: "Terremoto en Mendoza",
      descripcion: "Sismo leve sin víctimas reportadas.",
      lat: -32.8895,
      lng: -68.8458
    }
  ];

  // Agregar marcadores al mapa
  hechos.forEach(hecho => {
    const marcador = L.marker([hecho.lat, hecho.lng]).addTo(mapa);

    // Popup con botón de detalle
    const popupContent = `
      <div class="text-center">
        <h6>${hecho.titulo}</h6>
        <p>${hecho.descripcion}</p>
        <a href="hecho-detalle.html?id=${hecho.id}" class="btn btn-sm btn-primary w-100 text-white">
          Ver detalle
        </a>
      </div>
    `;

    marcador.bindPopup(popupContent);
  });
});
