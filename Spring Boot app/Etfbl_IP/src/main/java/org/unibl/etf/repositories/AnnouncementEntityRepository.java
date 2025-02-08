package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.models.entities.AnnouncementEntity;
@Repository
public interface AnnouncementEntityRepository extends JpaRepository<AnnouncementEntity,Integer> {
}
