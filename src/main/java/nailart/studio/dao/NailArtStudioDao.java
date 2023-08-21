package nailart.studio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nailart.studio.entity.NailArtStudio;

public interface NailArtStudioDao extends JpaRepository<NailArtStudio, Long> {

}
