// datos_prueba.js

const colecciones = [
  {
    id: 1,
    titulo: "Historia Argentina",
    descripcion: "Hechos históricos relevantes del siglo XIX y XX en Argentina.",
    fuente: "Archivo General de la Nación",
    hechos: [
      {
        id: 101,
        titulo: "Declaración de la Independencia",
        fecha: "1816-07-09",
        lugar: "Tucumán",
        descripcion: "Se declara la independencia de las Provincias Unidas del Río de la Plata.",
        fuente: "Congreso de Tucumán",
        lat: -26.8167,
        lng: -65.2167,
        imagen: "assets/img/declaracion_independencia.jpg"
      },
      {
        id: 102,
        titulo: "Batalla de Caseros",
        fecha: "1852-02-03",
        lugar: "Buenos Aires",
        descripcion: "Enfrentamiento entre las fuerzas de Rosas y Urquiza.",
        fuente: "Archivo Militar",
        lat: -34.6037,
        lng: -58.3816,
        imagen: "assets/img/batalla_caseros.jpg"
      }
    ]
  },
  {
    id: 2,
    titulo: "Ciencia y Tecnología",
    descripcion: "Avances científicos y tecnológicos relevantes a nivel mundial.",
    fuente: "UNESCO",
    hechos: [
      {
        id: 201,
        titulo: "Invención del Teléfono",
        fecha: "1876-03-10",
        lugar: "Boston, EE.UU.",
        descripcion: "Alexander Graham Bell realiza la primera transmisión de voz.",
        fuente: "Museo de Ciencia de Boston",
        lat: 42.3601,
        lng: -71.0589,
        imagen: "assets/img/invencion_telefono.jpg"
      },
      {
        id: 202,
        titulo: "Llegada del Hombre a la Luna",
        fecha: "1969-07-20",
        lugar: "Luna",
        descripcion: "Neil Armstrong se convierte en el primer ser humano en pisar la Luna.",
        fuente: "NASA",
        lat: 0,
        lng: 0,
        imagen: "assets/img/llegada_luna.jpg"
      },
      {
        id: 203,
        titulo: "Descubrimiento de la Penicilina",
        fecha: "1928-09-28",
        lugar: "Londres, Reino Unido",
        descripcion: "Alexander Fleming descubre la penicilina.",
        fuente: "Archivo de Medicina",
        lat: 51.5074,
        lng: -0.1278,
        imagen: "assets/img/penicilina.jpg"
      }
    ]
  },
  {
    id: 3,
    titulo: "Arte y Cultura",
    descripcion: "Obras artísticas y culturales que marcaron la historia.",
    fuente: "Museo del Prado",
    hechos: [
      {
        id: 301,
        titulo: "Pintura de Guernica",
        fecha: "1937-06-01",
        lugar: "París, Francia",
        descripcion: "Pablo Picasso pinta el Guernica como protesta contra la Guerra Civil Española.",
        fuente: "Museo Reina Sofía",
        lat: 48.8566,
        lng: 2.3522,
        imagen: "assets/img/guernica.jpg"
      },
      {
        id: 302,
        titulo: "Primera Película con Sonido",
        fecha: "1927-10-06",
        lugar: "Nueva York, EE.UU.",
        descripcion: "Estreno de 'The Jazz Singer', primera película con sonido sincronizado.",
        fuente: "Hollywood Archives",
        lat: 40.7128,
        lng: -74.0060,
        imagen: "assets/img/jazz_singer.jpg"
      }
    ]
  },
  {
    id: 4,
    titulo: "Desastres Naturales",
    descripcion: "Registro de eventos naturales que causaron impacto significativo.",
    fuente: "Protección Civil",
    hechos: [
      {
        id: 401,
        titulo: "Terremoto en San Juan",
        fecha: "1944-01-15",
        lugar: "San Juan, Argentina",
        descripcion: "Terremoto que destruye gran parte de la ciudad.",
        fuente: "Archivo Sísmico",
        lat: -31.5375,
        lng: -68.5364,
        imagen: "assets/img/terremoto_sanjuan.jpg"
      },
      {
        id: 402,
        titulo: "Inundación del Río Paraná",
        fecha: "1983-03-20",
        lugar: "Santa Fe, Argentina",
        descripcion: "Desborde del río que afectó varias localidades.",
        fuente: "Diario El Litoral",
        lat: -31.6333,
        lng: -60.7,
        imagen: "assets/img/inundacion_parana.jpg"
      }
    ]
  },
  {
    id: 5,
    titulo: "Política y Sociedad",
    descripcion: "Eventos políticos y sociales que marcaron épocas.",
    fuente: "Archivo Nacional",
    hechos: [
      {
        id: 501,
        titulo: "Revolución de Mayo",
        fecha: "1810-05-25",
        lugar: "Buenos Aires",
        descripcion: "Comienza el proceso de independencia argentina.",
        fuente: "Archivo General de la Nación",
        lat: -34.6037,
        lng: -58.3816,
        imagen: "assets/img/revolucion_mayo.jpg"
      }
    ]
  },
  {
    id: 6,
    titulo: "Exploraciones",
    descripcion: "Viajes y descubrimientos alrededor del mundo.",
    fuente: "National Geographic",
    hechos: [
      {
        id: 601,
        titulo: "Expedición de Darwin al Beagle",
        fecha: "1831-12-27",
        lugar: "Islas Malvinas",
        descripcion: "Charles Darwin estudia la fauna y flora local.",
        fuente: "National Museum",
        lat: -51.7963,
        lng: -59.5236,
        imagen: "assets/img/darwin_beagle.jpg"
      },
      {
        id: 602,
        titulo: "Primera Circunnavegación",
        fecha: "1519-09-20",
        lugar: "Sevilla, España",
        descripcion: "Expedición de Magallanes y Elcano completa la primera vuelta al mundo.",
        fuente: "Archivo Marítimo",
        lat: 37.3886,
        lng: -5.9823,
        imagen: "assets/img/circunnavegacion.jpg"
      }
    ]
  },
  {
    id: 7,
    titulo: "Innovaciones",
    descripcion: "Inventos y descubrimientos que cambiaron la historia.",
    fuente: "Museo de Ciencia",
    hechos: [
      {
        id: 701,
        titulo: "Invención de la Imprenta",
        fecha: "1440-01-01",
        lugar: "Maguncia, Alemania",
        descripcion: "Gutenberg inventa la imprenta con tipos móviles.",
        fuente: "Archivo Histórico Alemán",
        lat: 49.9929,
        lng: 8.2473,
        imagen: "assets/img/imprenta.jpg"
      },
      {
        id: 702,
        titulo: "Descubrimiento de América",
        fecha: "1492-10-12",
        lugar: "San Salvador, Bahamas",
        descripcion: "Cristóbal Colón llega al continente americano.",
        fuente: "Archivo Histórico Español",
        lat: 24.0630,
        lng: -74.5180,
        imagen: "assets/img/descubrimiento_america.jpg"
      }
    ]
  },
  {
    id: 8,
    titulo: "Deportes",
    descripcion: "Eventos deportivos históricos y destacados.",
    fuente: "Comité Olímpico Internacional",
    hechos: [
      {
        id: 801,
        titulo: "Primeros Juegos Olímpicos Modernos",
        fecha: "1896-04-06",
        lugar: "Atenas, Grecia",
        descripcion: "Se celebran los primeros Juegos Olímpicos modernos.",
        fuente: "Comité Olímpico Internacional",
        lat: 37.9838,
        lng: 23.7275,
        imagen: "assets/img/juegos_olimpicos.jpg"
      },
      {
        id: 802,
        titulo: "Maratón de Boston",
        fecha: "1897-04-19",
        lugar: "Boston, EE.UU.",
        descripcion: "Primer maratón oficial de Boston.",
        fuente: "Archivo Deportivo",
        lat: 42.3601,
        lng: -71.0589,
        imagen: "assets/img/maraton_boston.jpg"
      },
      {
        id: 803,
        titulo: "Copa Mundial 1930",
        fecha: "1930-07-13",
        lugar: "Montevideo, Uruguay",
        descripcion: "Primera edición de la Copa Mundial de Fútbol.",
        fuente: "FIFA Archives",
        lat: -34.9011,
        lng: -56.1645,
        imagen: "assets/img/copa_mundial_1930.jpg"
      }
    ]
  }
];
