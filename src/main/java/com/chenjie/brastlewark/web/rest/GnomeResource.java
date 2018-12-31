package com.chenjie.brastlewark.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.chenjie.brastlewark.service.GnomeService;
import com.chenjie.brastlewark.web.rest.errors.BadRequestAlertException;
import com.chenjie.brastlewark.web.rest.util.HeaderUtil;
import com.chenjie.brastlewark.web.rest.util.PaginationUtil;
import com.chenjie.brastlewark.service.dto.GnomeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Gnome.
 */
@RestController
@RequestMapping("/api")
public class GnomeResource {

    private final Logger log = LoggerFactory.getLogger(GnomeResource.class);

    private static final String ENTITY_NAME = "gnome";

    private final GnomeService gnomeService;

    public GnomeResource(GnomeService gnomeService) {
        this.gnomeService = gnomeService;
    }

    /**
     * POST  /gnomes : Create a new gnome.
     *
     * @param gnomeDTO the gnomeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gnomeDTO, or with status 400 (Bad Request) if the gnome has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gnomes")
    @Timed
    public ResponseEntity<GnomeDTO> createGnome(@RequestBody GnomeDTO gnomeDTO) throws URISyntaxException {
        log.debug("REST request to save Gnome : {}", gnomeDTO);
        if (gnomeDTO.getId() != null) {
            throw new BadRequestAlertException("A new gnome cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GnomeDTO result = gnomeService.save(gnomeDTO);
        return ResponseEntity.created(new URI("/api/gnomes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gnomes : Updates an existing gnome.
     *
     * @param gnomeDTO the gnomeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gnomeDTO,
     * or with status 400 (Bad Request) if the gnomeDTO is not valid,
     * or with status 500 (Internal Server Error) if the gnomeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gnomes")
    @Timed
    public ResponseEntity<GnomeDTO> updateGnome(@RequestBody GnomeDTO gnomeDTO) throws URISyntaxException {
        log.debug("REST request to update Gnome : {}", gnomeDTO);
        if (gnomeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GnomeDTO result = gnomeService.save(gnomeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gnomeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gnomes : get all the gnomes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gnomes in body
     */
    @GetMapping("/gnomes")
    @Timed
    public ResponseEntity<List<GnomeDTO>> getAllGnomes(Pageable pageable) {
        log.debug("REST request to get a page of Gnomes");
        Page<GnomeDTO> page = gnomeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gnomes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /gnomes/:id : get the "id" gnome.
     *
     * @param id the id of the gnomeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gnomeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/gnomes/{id}")
    @Timed
    public ResponseEntity<GnomeDTO> getGnome(@PathVariable Long id) {
        log.debug("REST request to get Gnome : {}", id);
        Optional<GnomeDTO> gnomeDTO = gnomeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gnomeDTO);
    }

    /**
     * DELETE  /gnomes/:id : delete the "id" gnome.
     *
     * @param id the id of the gnomeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gnomes/{id}")
    @Timed
    public ResponseEntity<Void> deleteGnome(@PathVariable Long id) {
        log.debug("REST request to delete Gnome : {}", id);
        gnomeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
