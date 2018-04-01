package com.msl.data.arangodb.got.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.got.entity.Location;
 
public interface LocationRepository extends ArangoRepository<Location> {
	GeoPage<Location> findByLocationNear(Point location, Pageable pageable);
	
	GeoResults<Location> findByLocationWithin(Point location, Distance distance);
	
	Iterable<Location> findByLocationWithin(Point location, Range<Double> distanceRange);
	
	Iterable<Location> findByLocationWithin(Polygon polygon);
}
