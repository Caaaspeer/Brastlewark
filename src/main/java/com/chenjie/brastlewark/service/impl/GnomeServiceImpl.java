package com.chenjie.brastlewark.service.impl;

import com.chenjie.brastlewark.service.GnomeService;
import com.chenjie.brastlewark.domain.Gnome;
import com.chenjie.brastlewark.repository.GnomeRepository;
import com.chenjie.brastlewark.service.dto.GnomeDTO;
import com.chenjie.brastlewark.service.mapper.GnomeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Gnome.
 */
@Service
@Transactional
public class GnomeServiceImpl implements GnomeService {

    private final Logger log = LoggerFactory.getLogger(GnomeServiceImpl.class);

    private final GnomeRepository gnomeRepository;

    private final GnomeMapper gnomeMapper;

    public GnomeServiceImpl(GnomeRepository gnomeRepository, GnomeMapper gnomeMapper) {
        this.gnomeRepository = gnomeRepository;
        this.gnomeMapper = gnomeMapper;
    }

    /**
     * Save a gnome.
     *
     * @param gnomeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GnomeDTO save(GnomeDTO gnomeDTO) {
        log.debug("Request to save Gnome : {}", gnomeDTO);
        Gnome gnome = gnomeMapper.toEntity(gnomeDTO);
        gnome = gnomeRepository.save(gnome);
        return gnomeMapper.toDto(gnome);
    }

    /**
     * Get all the gnomes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<GnomeDTO> findAll() {
        log.debug("Request to get all Gnomes");
        return gnomeRepository.findAll()
            // .map(gnomeMapper::toDto);
    }


    /**
     * Get one gnome by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GnomeDTO> findOne(Long id) {
        log.debug("Request to get Gnome : {}", id);
        return gnomeRepository.findById(id)
            .map(gnomeMapper::toDto);
    }

    /**
     * Delete the gnome by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gnome : {}", id);
        gnomeRepository.deleteById(id);
    }
}
