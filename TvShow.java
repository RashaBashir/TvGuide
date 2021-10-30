import java.util.Scanner;

public class TvShow implements Watchable {
	//initialize the attributes...
			private String showID;
			private String showName;
			private double startTime;
			private double endTime;

			//default constructor..
			public TvShow() {
			}

			//parameterized constructor...
			public TvShow(String showID, String showName, double startTime, double endTime) {
				super();
				this.showID = showID;
				this.showName = showName;
				this.startTime = startTime;
				this.endTime = endTime;
			}

			// Copy constructor..
			public TvShow(TvShow sh, String showID) {
				this.showName = sh.showName;
				this.startTime = sh.startTime;
				this.endTime = sh.endTime;
				this.showID = showID;
			}
			//parameterized constructor..
			public TvShow(TvShow obj) {
				this.showName = obj.showName;
				this.startTime = obj.startTime;
				this.endTime = obj.endTime;
				this.showID = obj.showID;
			}
			//getters...
			public String getShowID() {
				return showID;
			}
			public String getShowName() {
				return showName;
			}
			public double getStartTime() {
				return startTime;
			}
			public double getEndTime() {
				return endTime;
			}

			//setters...
			public void setShowID(String showID) {
				this.showID = showID;
			}
			public void setShowName(String showName) {
				this.showName = showName;
			}
			public void setStartTime(double startTime) {
				this.startTime = startTime;
			}
			public void setEndTime(double endTime) {
				this.endTime = endTime;
			}
			//clone method

			public TvShow clone(TvShow ts) {
				System.out.println(" Please enter the Show ID :: ");
				Scanner sc = new Scanner(System.in);
				String showID = sc.nextLine();
				sc.close();
				return new TvShow(this, showID);
				
			}
			@Override 
			public String toString() {
				return "The TvvShow has the following information showID :: " + showID + "."+ "\n showName :: " + showName + "."+" \n startTime :: " + startTime + " endTime ::"  + endTime + "]";
			}
			@Override 
			public boolean equals(Object obj ) {
				// check if object is not null....
				if (obj != null) {
				}
				TvShow other = (TvShow) obj;
				if(this.showName.equals(other.showName)){
					if (this.showName.equals(other.showName)) {
						if(this.startTime == other.startTime) {
							if  (this.endTime == other.endTime) {
							}
						}
					}
				}
				System.out.println("The two shows are the same ::");
				return false ;
			}
			/*
			 * method isOnSameTime takes @param TVShow object
			 *  @return “Same time”, “Different time”, or “Some Overlap” .
			 * 
			 */
			   public String isOnSameTime(TvShow tvs) {
			       if(this.startTime ==  tvs.startTime && this.endTime ==  tvs.endTime)
			           return "Same time";
			       else if(( tvs.endTime < this.startTime || tvs.startTime> this.endTime )) {
			           return "Different time"; 
			       }
			       else {
			           return "Some overlap";
			       }
			   }

			  
			
}
