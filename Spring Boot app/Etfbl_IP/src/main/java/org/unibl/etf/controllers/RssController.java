package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Announcement;
import org.unibl.etf.models.dto.Promotion;
import org.unibl.etf.services.AnnouncementEntityService;
import org.unibl.etf.services.PromotionEntityService;
import org.unibl.etf.services.RssFeedService;

@RestController
@RequestMapping("/api/rss-feed")
@CrossOrigin
public class RssController {
    private final RssFeedService rssFeedService;
    private final AnnouncementEntityService announcementService;
    private final PromotionEntityService promotionService;
    public RssController(RssFeedService rssFeedService, AnnouncementEntityService announcementService, PromotionEntityService promotionService) {
        this.rssFeedService = rssFeedService;
        this.announcementService = announcementService;
        this.promotionService = promotionService;
    }
    @GetMapping("/announcements")
    public String getAnnouncementsFeed() throws NotFoundException {
        return rssFeedService.generateAnnouncementsRss();
    }
    @GetMapping("/announcements/{id}")
    public Announcement getAnnouncement(@PathVariable Integer id) throws NotFoundException {
        return announcementService.getById(id);
    }
    @GetMapping("/promotions")
    public String getPromotionsFeed() throws NotFoundException {
        return rssFeedService.generateAnnouncementsRss();
    }
    @GetMapping("/promotions/{id}")
    public Promotion getPromotion(@PathVariable Integer id) throws NotFoundException {
        return promotionService.getById(id);
    }
}
