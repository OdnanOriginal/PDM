
public class TPStatement {
	
	public String location;
	private String announcement;
	private float minPrice;
	private float maxPrice;
	private float minSize;
	private float maxSize;
	private float minLocals;
	private float maxLocals;
	private float minRooms;
	private float maxRooms;
	private String feature;
	
	
	public TPStatement() {
		init();
	}

	public void clearAll() {
		init();
	}
	
	public void setRequest(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			location = dummyvariable.substring(0,dummyvariable.indexOf("^^")).trim();
		} else {
			location = dummyvariable.trim();
		}
	}
	
	public void setAnnouncement(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			announcement = dummyvariable.substring(0,dummyvariable.indexOf("^^")).trim();
		} else {
			announcement = dummyvariable.trim();
		}
	}
	public void setMinPrice(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			minPrice = 
				Float.valueOf((dummyvariable.substring(0,dummyvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			minPrice = Float.valueOf(dummyvariable.trim()).floatValue();
		}
	}
	
	public void setMaxPrice(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			maxPrice = 
				Float.valueOf((dummyvariable.substring(0,dummyvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			maxPrice = Float.valueOf(dummyvariable.trim()).floatValue();
		}
	}
	
	public void setMinSize(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			minSize = 
				Float.valueOf((dummyvariable.substring(0,dummyvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			minSize = Float.valueOf(dummyvariable.trim()).floatValue();
		}
	}
	
	public void setMaxSize(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			maxSize = 
				Float.valueOf((dummyvariable.substring(0,dummyvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			maxSize = Float.valueOf(dummyvariable.trim()).floatValue();
		}
	}
	
	public void setMinLocals(String dummvariable) {
		if ( dummvariable == null || dummvariable.length() == 0 ) {
			return;
		}
		if ( dummvariable.indexOf("^^") > 0 ) {
			minLocals = 
				Float.valueOf((dummvariable.substring(0,dummvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			minLocals = Float.valueOf(dummvariable.trim()).floatValue();
		}
	}
	
	public void setMaxLocals(String dummvariable) {
		if ( dummvariable == null || dummvariable.length() == 0 ) {
			return;
		}
		if ( dummvariable.indexOf("^^") > 0 ) {
			maxLocals = 
				Float.valueOf((dummvariable.substring(0,dummvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			maxLocals = Float.valueOf(dummvariable.trim()).floatValue();
		}
	}
	
	public void setMinRooms(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			minRooms = 
				Float.valueOf((dummyvariable.substring(0,dummyvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			minRooms = Float.valueOf(dummyvariable.trim()).floatValue();
		}
	}
	public void setMaxRooms(String dummvariable) {
		if ( dummvariable == null || dummvariable.length() == 0 ) {
			return;
		}
		if ( dummvariable.indexOf("^^") > 0 ) {
			maxRooms = 
				Float.valueOf((dummvariable.substring(0,dummvariable.indexOf("^^"))).trim()).floatValue();
		} else {
			maxRooms = Float.valueOf(dummvariable.trim()).floatValue();
		}
	}
	
	
	public void setFeature(String dummyvariable) {
		if ( dummyvariable == null || dummyvariable.length() == 0 ) {
			return;
		}
		if ( dummyvariable.indexOf("^^") > 0 ) {
			feature = dummyvariable.substring(0,dummyvariable.indexOf("^^")).trim();
		} else {
			feature = dummyvariable.trim();
		}
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getAnnouncement() {
		return announcement;
	}
	
	public float getMinPrice() {
		return minPrice;
	}
	
	public float getMaxPrice() {
		return maxPrice;
	}
	
	public float getMinSize() {
		return minSize;
	}
	
	public float getMaxSize() {
		return maxSize;
	}
	public float getMinLocals() {
		return minLocals;
	}
	
	public float getMaxLocals() {
		return maxLocals;
	}

	public float getMinRooms() {
		return minRooms;
	}
	
	public float getMaxRooms() {
		return maxRooms;
	}
	
	public String getFeature() {
		return feature;
	}
	
	// return true if cd is at least as good as the calling object
	 public boolean sameAs(TPStatement cd) {
		
	if ( cd.getMaxPrice() != 0 && maxPrice != 0 ) { // both are defined
		{//System.out.println(maxPrice);System.out.println(cd.getMaxPrice());
			if ( maxPrice < cd.getMaxPrice() ) 
				return false;
			}
		if ( cd.getMaxLocals() != 0 && maxLocals != 0 ) { // both are defined
			{//System.out.println(maxPrice);System.out.println(cd.getMaxPrice());
				if ( minLocals > cd.getMaxLocals() ) 
					return false;
				}}
		if ( cd.getMaxSize() != 0 && maxSize != 0 ) { // both are defined
			{//System.out.println(maxPrice);System.out.println(cd.getMaxPrice());
				if ( minSize > cd.getMaxSize()  ) //the bigger the better
					return false;
				}}
		}
		
		
			/*if ( compareRange(minSize,maxSize,
				cd.getMinSize(),cd.getMaxSize()) == false )  {
			return false;
		}
		
		if ( compareRange(minLocals,maxLocals,
				cd.getMinLocals(),cd.getMaxLocals()) == false ) {
			return false;
		}
		
		if ( compareRange(minRooms,maxRooms,
				cd.getMinRooms(),cd.getMaxRooms()) == false ) {
			return false;
		}*/
		
		return true;
	 }
	
	private boolean compareRange(float min0, float max0, float min1, float max1) {
		if ( min0 != 0 && max0 != 0 && min1 != 0 && max1 != 0 ) {
			if ( min0 < min1 || max0 > max1 ) {
				return false;
			}
		} else if ( min0 != 0 && min1 != 0 ) {
			if ( min0 > min1 ) {
				return false;
			}
		} else if ( max0 != 0 && max1 != 0 ) {
			if ( max0 < max1 ) {
				return false;
			}
		}
		return true;
	}
	
	private void init() {
	//	location= 0;
	//	announcement= 0;
		minPrice= 0;
		maxPrice= 0;
		minSize= 0;
		maxSize= 0;
		minLocals= 0;
		maxLocals= 0;
		minRooms= 0;
		maxRooms= 0;
	//	feature= 0;

	}
	
}
