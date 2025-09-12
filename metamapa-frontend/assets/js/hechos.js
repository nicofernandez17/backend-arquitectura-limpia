document.addEventListener('DOMContentLoaded', () => {
  // Crear mapa centrado en coordenadas iniciales
  const mapa = L.map('mapa-hechos').setView([-34.6037, -58.3816], 5); // ejemplo Argentina

  // Capa base
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; OpenStreetMap'
  }).addTo(mapa);

  // Datos de ejemplo de hechos
  const hechos = [
    { id: 1, tipo: 'lluvia', titulo: 'Lluvia fuerte en Buenos Aires', lat: -34.6037, lng: -58.3816, fecha: '2025-09-10' },
    { id: 2, tipo: 'inundacion', titulo: 'Inundación en Santa Fe', lat: -31.6333, lng: -60.7000, fecha: '2025-09-08' },
    { id: 3, tipo: 'incendio', titulo: 'Incendio forestal en Córdoba', lat: -31.4201, lng: -64.1888, fecha: '2025-09-09' },
    { id: 4, tipo: 'terremoto', titulo: 'Terremoto leve en San Juan', lat: -31.5375, lng: -68.5364, fecha: '2025-09-07' }
  ];

  let markers = [];

  function mostrarHechos(filtroTipo = 'todos') {
    // Limpiar marcadores existentes
    markers.forEach(m => mapa.removeLayer(m));
    markers = [];

    // Filtrar hechos
    const hechosFiltrados = filtroTipo === 'todos' ? hechos : hechos.filter(h => h.tipo === filtroTipo);

    // Agregar marcadores
    hechosFiltrados.forEach(h => {
      const marker = L.marker([h.lat, h.lng]).addTo(mapa);
      marker.bindPopup(`<strong>${h.titulo}</strong><br>Fecha: ${h.fecha}`);
      markers.push(marker);
    });
  }

  // Mostrar todos al cargar
  mostrarHechos();

  // Filtrar por tipo al hacer click en los botones
  document.querySelectorAll('[data-tipo]').forEach(btn => {
    btn.addEventListener('click', () => {
      mostrarHechos(btn.dataset.tipo);
    });
  });
});
