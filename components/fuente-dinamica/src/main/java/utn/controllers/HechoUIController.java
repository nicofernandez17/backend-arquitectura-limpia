package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.domain.Revision;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoFormDTO;
import utn.models.dtos.HechoMapper;
import utn.services.HechoService;
import utn.services.RevisionService;

import java.util.Optional;

@RestController
@RequestMapping("/hechos")
public class HechoUIController {

    @Autowired
    private final HechoService hechosUIService;
    @Autowired
    private RevisionService revisionService;

    public HechoUIController(HechoService hechosUIService) {
        this.hechosUIService = hechosUIService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registrarHecho(@RequestBody HechoFormDTO hechoFormDTO) {
        hechosUIService.registrarHecho(HechoMapper.fromHechoForm(hechoFormDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body("Hecho registrado correctamente");
    }


    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Revision> actualizarHecho(
            @PathVariable String id,
            @ModelAttribute HechoFormDTO hechoFormDTO) {
        // aca cambiaron cosas, por si hay que buscar errores es un posible lugar
        Revision revision = revisionService.crearRevision(
                HechoMapper.aDominio(HechoMapper.fromHechoForm(hechoFormDTO)),
                id
        );
        return ResponseEntity.ok(revision);
    }

    /*

    @GetMapping("/{id}/archivo")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable Long id) {
        Optional<Hecho> hecho = hechosUIService.obtenerPorId(id);

        if (hecho.isEmpty() || hecho.get().getMultimediaArchivo() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + hecho.get().getMultimediaNombre() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(hecho.get().getMultimediaArchivo());
    }
    */

    // TODO Revisar porque lo modifiqué para que no me de error, pero esto seguramente ya no lo usemos
    /*@GetMapping("/{id}/archivo")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable Long id) {
        Optional<HechoDTO> hechoDTO = hechosUIService.obtenerPorId(id);

        if (hechoDTO.isEmpty() || hechoDTO.get().getMultimediaPath() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + hechoDTO.get().getMultimediaPath() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(hechoDTO.get().getMultimediaPath());
    }*/

}
