package com.msl.data.arangodb.got.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import com.msl.data.arangodb.got.entity.Location;
import com.msl.data.arangodb.got.repository.LocationRepository;

public class GeospatialRunner implements CommandLineRunner {

	@Autowired
	private LocationRepository repository;

	@Override
	public void run(final String... args) throws Exception {
		System.out.println("# Geospatial");

		repository.save(Arrays.asList(new Location("Dragonstone", new double[] { 55.167801, -6.815096 }),
				new Location("King's Landing", new double[] { 42.639752, 18.110189 }),
				new Location("The Red Keep", new double[] { 35.896447, 14.446442 }),
				new Location("Yunkai", new double[] { 31.046642, -7.129532 }),
				new Location("Astapor", new double[] { 31.50974, -9.774249 }),
				new Location("Winterfell", new double[] { 54.368321, -5.581312 }),
				new Location("Vaes Dothrak", new double[] { 54.16776, -6.096125 }),
				new Location("Beyond the wall", new double[] { 64.265473, -21.094093 })));
		
//		System.out.println("## Find the first 5 locations near 'Winterfell'");
//		GeoPage<Location> first5 = repository.findByLocationNear(new Point(-5.581312, 54.368321),
//		  new PageRequest(0, 5));
//		first5.forEach(System.out::println);
		 
//		System.out.println("## Find the next 5 locations near 'Winterfell' (only 3 locations left)");
//		GeoPage<Location> next5 = repository.findByLocationNear(new Point(-5.581312, 54.368321),
//		  new PageRequest(1, 5));
//		next5.forEach(System.out::println);
		
		System.out.println("## Find all locations within 50 kilometers of 'Winterfell'");
		GeoResults<Location> findWithing50kilometers = repository
		  .findByLocationWithin(new Point(-5.581312, 54.368321), new Distance(50, Metrics.KILOMETERS));
		findWithing50kilometers.forEach(System.out::println);
		
//		System.out.println("## Find all locations which are 40 to 50 kilometers away from 'Winterfell'");
//		Iterable<Location> findByLocationWithin = repository.findByLocationWithin(new Point(-5.581312, 54.368321),
//		  new Range<>(40000., 50000.));
//		findByLocationWithin.forEach(System.out::println);
		
//		System.out.println("## Find all locations within a given polygon");
//		Iterable<Location> withinPolygon = repository.findByLocationWithin(
//		  new Polygon(Arrays.asList(new Point(-25, 40), new Point(-25, 70), new Point(25, 70))));
//		withinPolygon.forEach(System.out::println);
	}
}
