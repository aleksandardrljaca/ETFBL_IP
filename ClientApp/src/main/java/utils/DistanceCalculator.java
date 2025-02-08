package utils;

import models.dto.Location;

public class DistanceCalculator {
	public static int calculateDistance(Location start,Location end) {
        final int EARTH_RADIUS = 6371; // Polupreènik Zemlje u kilometrima

        // Pretvaranje stepeni u radijane
        double latDistance = Math.toRadians(end.getLatitude() - start.getLatitude());
        double lonDistance = Math.toRadians(end.getLongitude()-start.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(start.getLatitude())) * Math.cos(Math.toRadians(end.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        System.out.println("DISTANCA IZ DC JE "+(int) Math.round(EARTH_RADIUS * c));
        // Izraèunavanje udaljenosti i zaokruživanje na ceo broj
        return (int) Math.round(EARTH_RADIUS * c);
    }
}
