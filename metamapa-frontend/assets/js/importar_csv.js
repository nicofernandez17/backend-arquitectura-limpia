document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("formImportar");
  const inputArchivo = document.getElementById("archivoCSV");
  const infoArchivo = document.getElementById("infoArchivo");
  const nombreArchivo = document.getElementById("nombreArchivo");
  const tamanoArchivo = document.getElementById("tamanoArchivo");
  const mensajeCarga = document.getElementById("mensajeCarga");

  inputArchivo.addEventListener("change", () => {
    if (inputArchivo.files.length > 0) {
      const archivo = inputArchivo.files[0];
      nombreArchivo.textContent = archivo.name;
      tamanoArchivo.textContent = (archivo.size / 1024 / 1024).toFixed(2) + " MB";
      infoArchivo.style.display = "block";
    } else {
      infoArchivo.style.display = "none";
    }
  });

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (inputArchivo.files.length === 0) {
      mensajeCarga.innerHTML = `<div class="alert alert-danger">Por favor selecciona un archivo CSV.</div>`;
      return;
    }

    const archivo = inputArchivo.files[0];
    const formData = new FormData();
    formData.append("csv", archivo);

    mensajeCarga.innerHTML = `<div class="alert alert-info">Cargando archivo...</div>`;

    try {
      const response = await fetch("/api/hechos/importar", { // Ajusta la URL según tu backend
        method: "POST",
        body: formData
      });

      if (!response.ok) throw new Error("Error al importar el CSV");

      const result = await response.json();
      mensajeCarga.innerHTML = `<div class="alert alert-success">Archivo importado correctamente. ${result.insertados} hechos agregados.</div>`;
    } catch (error) {
      console.error(error);
      mensajeCarga.innerHTML = `<div class="alert alert-danger">Error al importar el archivo.</div>`;
    }
  });
});
