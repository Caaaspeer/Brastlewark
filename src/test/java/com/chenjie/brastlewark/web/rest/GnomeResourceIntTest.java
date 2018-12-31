package com.chenjie.brastlewark.web.rest;

import com.chenjie.brastlewark.BrastlewarkApp;

import com.chenjie.brastlewark.domain.Gnome;
import com.chenjie.brastlewark.repository.GnomeRepository;
import com.chenjie.brastlewark.service.GnomeService;
import com.chenjie.brastlewark.service.dto.GnomeDTO;
import com.chenjie.brastlewark.service.mapper.GnomeMapper;
import com.chenjie.brastlewark.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


import static com.chenjie.brastlewark.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GnomeResource REST controller.
 *
 * @see GnomeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BrastlewarkApp.class)
public class GnomeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBMAIL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBMAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEIGHT = new BigDecimal(2);

    private static final String DEFAULT_HAIR_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_HAIR_COLOR = "BBBBBBBBBB";

    @Autowired
    private GnomeRepository gnomeRepository;

    @Autowired
    private GnomeMapper gnomeMapper;
    
    @Autowired
    private GnomeService gnomeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGnomeMockMvc;

    private Gnome gnome;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GnomeResource gnomeResource = new GnomeResource(gnomeService);
        this.restGnomeMockMvc = MockMvcBuilders.standaloneSetup(gnomeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gnome createEntity(EntityManager em) {
        Gnome gnome = new Gnome()
            .name(DEFAULT_NAME)
            .thumbmail(DEFAULT_THUMBMAIL)
            .weight(DEFAULT_WEIGHT)
            .height(DEFAULT_HEIGHT)
            .hair_color(DEFAULT_HAIR_COLOR);
        return gnome;
    }

    @Before
    public void initTest() {
        gnome = createEntity(em);
    }

    @Test
    @Transactional
    public void createGnome() throws Exception {
        int databaseSizeBeforeCreate = gnomeRepository.findAll().size();

        // Create the Gnome
        GnomeDTO gnomeDTO = gnomeMapper.toDto(gnome);
        restGnomeMockMvc.perform(post("/api/gnomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gnomeDTO)))
            .andExpect(status().isCreated());

        // Validate the Gnome in the database
        List<Gnome> gnomeList = gnomeRepository.findAll();
        assertThat(gnomeList).hasSize(databaseSizeBeforeCreate + 1);
        Gnome testGnome = gnomeList.get(gnomeList.size() - 1);
        assertThat(testGnome.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGnome.getThumbmail()).isEqualTo(DEFAULT_THUMBMAIL);
        assertThat(testGnome.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testGnome.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testGnome.getHair_color()).isEqualTo(DEFAULT_HAIR_COLOR);
    }

    @Test
    @Transactional
    public void createGnomeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gnomeRepository.findAll().size();

        // Create the Gnome with an existing ID
        gnome.setId(1L);
        GnomeDTO gnomeDTO = gnomeMapper.toDto(gnome);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGnomeMockMvc.perform(post("/api/gnomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gnomeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Gnome in the database
        List<Gnome> gnomeList = gnomeRepository.findAll();
        assertThat(gnomeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGnomes() throws Exception {
        // Initialize the database
        gnomeRepository.saveAndFlush(gnome);

        // Get all the gnomeList
        restGnomeMockMvc.perform(get("/api/gnomes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gnome.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbmail").value(hasItem(DEFAULT_THUMBMAIL.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].hair_color").value(hasItem(DEFAULT_HAIR_COLOR.toString())));
    }
    
    @Test
    @Transactional
    public void getGnome() throws Exception {
        // Initialize the database
        gnomeRepository.saveAndFlush(gnome);

        // Get the gnome
        restGnomeMockMvc.perform(get("/api/gnomes/{id}", gnome.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gnome.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.thumbmail").value(DEFAULT_THUMBMAIL.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.intValue()))
            .andExpect(jsonPath("$.hair_color").value(DEFAULT_HAIR_COLOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGnome() throws Exception {
        // Get the gnome
        restGnomeMockMvc.perform(get("/api/gnomes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGnome() throws Exception {
        // Initialize the database
        gnomeRepository.saveAndFlush(gnome);

        int databaseSizeBeforeUpdate = gnomeRepository.findAll().size();

        // Update the gnome
        Gnome updatedGnome = gnomeRepository.findById(gnome.getId()).get();
        // Disconnect from session so that the updates on updatedGnome are not directly saved in db
        em.detach(updatedGnome);
        updatedGnome
            .name(UPDATED_NAME)
            .thumbmail(UPDATED_THUMBMAIL)
            .weight(UPDATED_WEIGHT)
            .height(UPDATED_HEIGHT)
            .hair_color(UPDATED_HAIR_COLOR);
        GnomeDTO gnomeDTO = gnomeMapper.toDto(updatedGnome);

        restGnomeMockMvc.perform(put("/api/gnomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gnomeDTO)))
            .andExpect(status().isOk());

        // Validate the Gnome in the database
        List<Gnome> gnomeList = gnomeRepository.findAll();
        assertThat(gnomeList).hasSize(databaseSizeBeforeUpdate);
        Gnome testGnome = gnomeList.get(gnomeList.size() - 1);
        assertThat(testGnome.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGnome.getThumbmail()).isEqualTo(UPDATED_THUMBMAIL);
        assertThat(testGnome.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testGnome.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testGnome.getHair_color()).isEqualTo(UPDATED_HAIR_COLOR);
    }

    @Test
    @Transactional
    public void updateNonExistingGnome() throws Exception {
        int databaseSizeBeforeUpdate = gnomeRepository.findAll().size();

        // Create the Gnome
        GnomeDTO gnomeDTO = gnomeMapper.toDto(gnome);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGnomeMockMvc.perform(put("/api/gnomes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gnomeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Gnome in the database
        List<Gnome> gnomeList = gnomeRepository.findAll();
        assertThat(gnomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGnome() throws Exception {
        // Initialize the database
        gnomeRepository.saveAndFlush(gnome);

        int databaseSizeBeforeDelete = gnomeRepository.findAll().size();

        // Get the gnome
        restGnomeMockMvc.perform(delete("/api/gnomes/{id}", gnome.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Gnome> gnomeList = gnomeRepository.findAll();
        assertThat(gnomeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gnome.class);
        Gnome gnome1 = new Gnome();
        gnome1.setId(1L);
        Gnome gnome2 = new Gnome();
        gnome2.setId(gnome1.getId());
        assertThat(gnome1).isEqualTo(gnome2);
        gnome2.setId(2L);
        assertThat(gnome1).isNotEqualTo(gnome2);
        gnome1.setId(null);
        assertThat(gnome1).isNotEqualTo(gnome2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GnomeDTO.class);
        GnomeDTO gnomeDTO1 = new GnomeDTO();
        gnomeDTO1.setId(1L);
        GnomeDTO gnomeDTO2 = new GnomeDTO();
        assertThat(gnomeDTO1).isNotEqualTo(gnomeDTO2);
        gnomeDTO2.setId(gnomeDTO1.getId());
        assertThat(gnomeDTO1).isEqualTo(gnomeDTO2);
        gnomeDTO2.setId(2L);
        assertThat(gnomeDTO1).isNotEqualTo(gnomeDTO2);
        gnomeDTO1.setId(null);
        assertThat(gnomeDTO1).isNotEqualTo(gnomeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(gnomeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(gnomeMapper.fromId(null)).isNull();
    }
}
