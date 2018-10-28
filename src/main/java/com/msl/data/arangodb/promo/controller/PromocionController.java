package com.msl.data.arangodb.promo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.service.PromocionService;


@RestController
@RequestMapping("/promocion")
public class PromocionController {
	
	private static final Logger logger = LoggerFactory.getLogger("com.msl.data.arangodb.promo.controller.PromocionController");
	
	@Autowired
	PromocionService service;
	
	@GetMapping(path = "/findByid")
    public Optional<Promocion> findByid(@RequestParam(value="id", required=false, defaultValue="0") String id, Model model) {
        logger.debug("Buscando promocion por id...");
        return service.findByid(id);
    }
	
	@GetMapping(path = "/findByCodpromoci")
    public Promocion findByCodpromoci(@RequestParam(value="codpromoci", required=false, defaultValue="0") String codpromoci, Model model) {
        logger.debug("Buscando promocion por codpromoci...");
        return service.findByCodpromoci(codpromoci);
    }
	
    @PostMapping(path = "/save")
    public ResponseEntity<Promocion> save(@RequestBody Promocion promocion) {
    	Promocion savedProduct = this.service.save(promocion);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    
	@GetMapping(path = "/findPromocionesByCempresa")
    public Iterable<Promocion> findPromocionesByCempresa(@RequestParam(value="cempresa", required=false, defaultValue="0") String cempresa, Model model) {
        logger.debug("Buscando promociones por cempresa...");
        return service.findPromocionesByCempresa(cempresa);
    }
}