package org.unibl.etf.services;

import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Announcement;

import java.util.List;

public interface AnnouncementEntityService {
    List<Announcement> getAll();
    Announcement getById(Integer id) throws NotFoundException;
}
