package org.unibl.etf.services;

import org.unibl.etf.exceptions.NotFoundException;

public interface RssFeedService {
    String generateAnnouncementsRss() throws NotFoundException;
    String generatePromotionsRss() throws NotFoundException;
}
