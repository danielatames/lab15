package com.fieldtech.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fieldtech.business.IncidenciaService;
import com.fieldtech.domain.Incidencia;

@RestController
@RequestMapping("/api/v1/incidencias")
@CrossOrigin(origins = "*")
public class IncidenciaController {

    private final IncidenciaService service;

    public IncidenciaController(IncidenciaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Incidencia>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Incidencia> crearIncidenciaConFoto(
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoria,
            @RequestParam("latitud") Double latitud,
            @RequestParam("longitud") Double longitud,
            @RequestParam("tecnicoCodigo") String tecnicoCodigo,
            @RequestParam(value = "foto", required = false) MultipartFile foto
    ) {
        Incidencia guardada = service.registrar(
                titulo, descripcion, categoria, latitud, longitud, tecnicoCodigo, foto
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }
}