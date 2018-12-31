package com.chenjie.brastlewark.service;

import com.chenjie.brastlewark.service.dto.GnomeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Gnome.
 */
public interface GnomeService {

    /**
     * Save a gnome.
     *
     * @param gnomeDTO the entity to save
     * @return the persisted entity
     */
    GnomeDTO save(GnomeDTO gnomeDTO);

    /**
     * Get all the gnomes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GnomeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" gnome.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GnomeDTO> findOne(Long id);

    /**
     * Delete the "id" gnome.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
