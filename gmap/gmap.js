$(document).ready(function() {

	var map1 = new gmap("map_canvas1");
	map1.addMarker(new google.maps.LatLng(48.8099767, 2.3911679), "info", function(){alert("coucou");});
	map1.addMarker(new google.maps.LatLng(50.8099767, 2.3911679), "info");
	
	//geocodeByPostalCode("75010");
});

function gmap(id) {

	var defaultCenter = new google.maps.LatLng(48.8099767, 2.3911679);
	var mapOptions = {
		zoom: 14,
		mapTypeControl: false,
		center: defaultCenter,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	var latLngBounds = null;
	this.map = new google.maps.Map(document.getElementById(id), mapOptions);

	this.addMarker = function(position, info, onclick) {
		var marker = new google.maps.Marker({
			position: position, 
			map: this.map
		});
		
		var infowindow = new google.maps.InfoWindow({
			content: info
		});

		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(this.map, marker);
			if (onclick != null) {
				onclick();
			}
		});
		this.updateLatLngBounds(position);
	};
	
	this.updateLatLngBounds = function(position) {
		if (latLngBounds == null) {
			latLngBounds = new google.maps.LatLngBounds(position);
		} else {
			latLngBounds.extend(position);
		}
		this.map.fitBounds(latLngBounds);
	};
	
	this.reset = function() {
		latLngBounds = null;
		this.map = new google.maps.Map(document.getElementById(id), mapOptions);
	};
};

function geocodeByPostalCode(postalCode) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({'address': postalCode, 'country': 'fr'}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
          if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
            result = results[0].geometry.location;
			alert(result);
			
			// do something...
          } else {
            console.info("No results found");
          }
        } else {
          console.error("Geocode was not successful for the following reason: " + status);
        }
	});
}