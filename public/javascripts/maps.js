function myMap() {
  var mapCanvas = document.getElementById("map");
  var myCenter = new google.maps.LatLng(32.747007032, -97.093000504);
  var mapOptions = {
  center:myCenter,
  zoom:18,
  mapTypeId:google.maps.MapTypeId.SATELLITE,
  panControl: false,
      zoomControl: true,
      mapTypeControl: true,
      scaleControl: true,
      streetViewControl: true,
      overviewMapControl: false,
      rotateControl: true
  };
  var map = new google.maps.Map(mapCanvas,mapOptions);
  var marker = new google.maps.Marker({
    position: myCenter,
    animation: google.maps.Animation.BOUNCE
  });
  marker.setMap(map);
}

