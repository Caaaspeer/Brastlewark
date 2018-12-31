package com.chenjie.brastlewark.repository;

import com.chenjie.brastlewark.domain.Gnome;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Gnome entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GnomeRepository extends JpaRepository<Gnome, Long> {

}
