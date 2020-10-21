public class GeoLocation {
	
	private double Latitude;
    private double Longitude;
    
    public GeoLocation(double latitude, double longitude) {
    	this.Latitude = latitude;
    	this.Longitude = longitude;
    }
    
    public void setLatitude(double latitude) {
    	this.Latitude = latitude;
    }
    
    public void setLongitude(double longitude) {
    	this.Latitude = longitude;
    }
    
    public double getLatitude() {
    	return this.Latitude;
    }
    
    public double getLongitude() {
    	return this.Longitude;
    }
    
    public double degreesToRadians(double degrees)
	{
	    final double degToRadFactor = Math.PI / 180;
	    return degrees * degToRadFactor;
	}

	public double radiansToDegrees(double radians)
	{
	    final double radToDegFactor = 180 / Math.PI;
	    return radians * radToDegFactor;
	}
	
	public double milesToKilometers(double miles) {
		final double miletoKmFactor = 1.609344;
		return miles * miletoKmFactor;
		
	}
	public GeoLocation findPointAt(double initialBearingDegrees, double distanceMiles)
	{
		double initialBearingRadians = degreesToRadians(initialBearingDegrees);
		double distanceKilometres = milesToKilometers(distanceMiles);
		
	    final double radiusEarthKilometres = 6372.795477598;
	    double distRatio = distanceKilometres / radiusEarthKilometres;
	    double distRatioSine = Math.sin(distRatio);
	    double distRatioCosine = Math.cos(distRatio);

	    double startLatRad = degreesToRadians(this.Latitude);
	    double startLonRad = degreesToRadians(this.Longitude);

	    double startLatCos = Math.cos(startLatRad);
	    double startLatSin = Math.sin(startLatRad);

	    double endLatRads = Math.asin((startLatSin * distRatioCosine) + (startLatCos * distRatioSine * Math.cos(initialBearingRadians)));
	    double endLonRads = startLonRad + Math.atan2(Math.sin(initialBearingRadians) * distRatioSine * startLatCos, distRatioCosine - startLatSin * Math.sin(endLatRads));

	    GeoLocation endPoint = new GeoLocation(radiansToDegrees(endLatRads), radiansToDegrees(endLonRads));
	    
	    return endPoint;
	}
}
