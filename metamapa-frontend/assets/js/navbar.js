document.addEventListener("DOMContentLoaded", () => {
  const navbarDiv = document.getElementById("navbar");
  if (!navbarDiv) return; // seguridad por si falta el div

  // Inyectamos la estructura base de la navbar
  navbarDiv.innerHTML = `
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top">
      <div class="container">
        <a class="navbar-brand" href="index.html">MetaMapa</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">
          <ul class="navbar-nav ms-auto text-center" id="nav-items">
            <li class="nav-item my-2 w-100 w-md-auto">
              <a class="nav-link" href="index.html">Inicio</a>
            </li>
            <li class="nav-item my-2 w-100 w-md-auto">
              <a class="nav-link" href="colecciones.html">Colecciones</a>
            </li>
            <li class="nav-item my-2 w-100 w-md-auto">
              <a class="nav-link" href="hechos.html">Hechos</a>
            </li>
            <!-- Mis Hechos se agregará dinámicamente si está logeado -->
            <li class="nav-item my-2 w-100 text-center" id="auth-buttons">
              <!-- Estado logeado/no logeado se seteará con JS -->
            </li>
          </ul>
        </div>
      </div>
    </nav>
  `;

  // Simulamos un usuario logeado
  const usuario = {
    nombre: "Usuario Contribuyente",
    logeado: true
  };

  const navItems = document.getElementById("nav-items");
  const authButtons = document.getElementById("auth-buttons");

  if (usuario.logeado) {
    // Agregar botón "Mis Hechos"
    const misHechosItem = document.createElement("li");
    misHechosItem.className = "nav-item my-2 me-2 text-nowrap text-center";
    misHechosItem.innerHTML = `<a class="nav-link" href="hechos_contribuyente.html">Mis Hechos</a>`;
    navItems.insertBefore(misHechosItem, authButtons);

    // Botón de perfil con dropdown
    authButtons.innerHTML = `
      <div class="dropdown">
        <button class="btn btn-light dropdown-toggle d-flex mx-auto align-items-center"
                type="button" id="userDropdown"
                data-bs-toggle="dropdown" aria-expanded="false">
          <i class="bi bi-person-fill me-2"></i> ${usuario.nombre}
        </button>
        <ul class="dropdown-menu dropdown-menu-end text-center" aria-labelledby="userDropdown">
          <li><a class="dropdown-item" href="perfil.html">Perfil</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#" id="logout-btn">Logout</a></li>
        </ul>
      </div>
    `;

    // Evento logout
    document.getElementById("logout-btn").addEventListener("click", () => {
      alert("Cerrando sesión...");
      window.location.href = "index.html";
    });

  } else {
    authButtons.innerHTML = `<a class="btn btn-light ms-md-2 w-100" href="login.html">Ingresar</a>`;
  }

  // Ajustar el padding-top del body dinámicamente según altura de la navbar
  const navbar = document.querySelector(".navbar.fixed-top");
  if (navbar) {
    document.body.style.paddingTop = navbar.offsetHeight + "px";
  }
});

// Esto es para que al recargar la página no se me scrollee hacia abajo
if ("scrollRestoration" in history) {
  history.scrollRestoration = "manual";
}

