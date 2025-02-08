package org.unibl.etf.services.impl;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Announcement;
import org.unibl.etf.models.dto.Promotion;
import org.unibl.etf.services.AnnouncementEntityService;
import org.unibl.etf.services.PromotionEntityService;
import org.unibl.etf.services.RssFeedService;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@Service
public class RssFeedServiceImpl implements RssFeedService {
    private final AnnouncementEntityService announcementEntityService;
    private final PromotionEntityService promotionEntityService;

    public RssFeedServiceImpl(AnnouncementEntityService announcementEntityService, PromotionEntityService promotionEntityService) {
        this.announcementEntityService = announcementEntityService;
        this.promotionEntityService = promotionEntityService;
    }

    public String generateAnnouncementsRss() throws NotFoundException {
        List<Announcement> announcements = announcementEntityService.getAll();
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("Announcements RSS Feed");
        feed.setLink("http://localhost:8000/api/rss-feed/announcements");
        feed.setDescription("Latest announcements from our system");

        List<SyndEntry> entries = new ArrayList<>();
        for (Announcement announcement : announcements) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle(announcement.getTitle());
            entry.setLink("http://localhost:8000/api/rss-feed/announcements/" + announcement.getId());
            entry.setPublishedDate(new Date());
            SyndContent description = new SyndContentImpl();
            description.setType("text/plain");
            description.setValue(announcement.getContent());
            entry.setDescription(description);
            entries.add(entry);
        }
        feed.setEntries(entries);

        try {
            SyndFeedOutput output = new SyndFeedOutput();
            return output.outputString(feed);
        } catch (FeedException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public String generatePromotionsRss() throws NotFoundException {
        List<Promotion> promotions = promotionEntityService.getAll();
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("Promotions RSS Feed");
        feed.setLink("http://localhost:8000/api/rss-feed/promotions");
        feed.setDescription("Latest promotions from our system");

        List<SyndEntry> entries = new ArrayList<>();
        for (Promotion promotion : promotions) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle(promotion.getTitle());
            entry.setLink("http://localhost:8000/api/rss-feed/promotions/" + promotion.getId());
            entry.setPublishedDate(new Date());
            SyndContent description = new SyndContentImpl();
            description.setType("text/plain");
            description.setValue(promotion.getDescription() + " Valid until: " + promotion.getValidUntil());
            entry.setDescription(description);
            entries.add(entry);
        }
        feed.setEntries(entries);

        try {
            SyndFeedOutput output = new SyndFeedOutput();
            return output.outputString(feed);
        } catch (FeedException e) {
            throw new RuntimeException("Error generating RSS feed", e);
        }
    }


}
