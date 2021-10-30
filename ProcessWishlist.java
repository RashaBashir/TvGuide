import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessWishlist  {
	//creating objects.....
	public static ShowList sl =new ShowList();
	public static ShowList sl2 =new ShowList();
	//initialize the attributes...
	//public static ArrayList<String> watching;
	//public static ArrayList<String> wishlist;

	public static void readGuide(){
		Scanner tvGuide = null;
		//sl = new ShowList();
		try{
			//open the file for reading...
			tvGuide = new Scanner(new FileInputStream("TVGuide.txt"));

			//Reading through the Tv guide file...
			while(tvGuide.hasNext()){
				//read the showID..
				String showID = tvGuide.next();
				//read the showName..
				String showName = tvGuide.next();
				//move to the next line...
				tvGuide.nextLine();
				//skip the letter 'S'....
				tvGuide.next(); 
				//read the start time of the show ... 
				double startTime = tvGuide.nextDouble();
				//move to the next line...
				tvGuide.nextLine();
				//skip the letter 'E'....
				tvGuide.next(); 
				//read the end time of the show ... 
				double endTime = tvGuide.nextDouble();

				if(tvGuide.hasNextLine()) {
					tvGuide.nextLine();
				}
				/*
				 * put @param showID
				 *  @param showName
				 *  @param startTime
				 *  @param endTime
				 *  in TVShow object
				 */
				TvShow show = new TvShow(showID,showName,startTime,endTime);
				//use contain method to check if already have this show
				//if the sl object does not have the show then we add it to the list 

				if(sl.contains(show.getShowID())){
					sl.addToStart(show);

					//if we already have the show the it is duplicate and we don't add it 
				}else{
					System.out.println("Duplicate  Tv shows Found :: " + show.getShowID() + "\n will not be added to the lsit .");
				}
			}
		}catch(FileNotFoundException e){
			e.getMessage();
			System.exit(0);
		}finally {
			if(tvGuide != null) 
				tvGuide.close();
		}

	}
	public static void Watch(){
		//create two  arraylsits ..
		ArrayList<String>	watching = new ArrayList<>();
		ArrayList<String> wishlist = new ArrayList<>();
		//Prompt user for interest file...
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the interested file :: ");
		String filename = sc.nextLine();
		try{
			Scanner interests = new Scanner(new FileInputStream(filename));
			//skip the first line ...
			//read the file...
			String ShID=interests.nextLine(); 
			
			//Loop through watching list whenever we have the word "wishlist" we end the while loop ... 
			while(interests.hasNextLine()){
				//!ShID.equalsIgnoreCase("Wishlist"
				 ShID = interests.nextLine();
				 if(ShID.equals("Wishlist")) break;
				watching.add(ShID);
				//System.out.println(watching);
				 
			}
			System.out.println(wishlist);
			//Loop the wishlist...
			while(interests.hasNextLine()){
				 ShID= interests.nextLine();
				 if(ShID.isBlank()) break;
				wishlist.add (ShID);	
				//System.out.println(wishlist);
			
			}
			//interests.close();
			//Check if each show in the wishlist arraylist is conflicting with each of the shows in the watching arraylist
			for(int i=0; i<watching.size(); i++){
				for(int j=0; j<wishlist.size(); j++){
					TvShow sh1 = sl.find(watching.get(i));
					System.out.println(sh1);
					TvShow sh2 = sl.find(wishlist.get(j));
					System.out.println(sh1);
					System.out.println(sh2);
					if(sh1.isOnSameTime((sh2)).equalsIgnoreCase("Different time")){
						System.out.println("User can watch show " + wishlist.get(j) + " as he/she is not watching anything else during that time");
					}else if(sh1.isOnSameTime((sh2)).equalsIgnoreCase("Same time")){
						System.out.println("User can't watch show " + wishlist.get(j) + " will begin another show at the same time.");
					}else if (sh1.isOnSameTime((sh2)).equalsIgnoreCase("Some overlap")) {
						System.out.println("User can't watch show " + wishlist.get(j) + "as he/she is not finished with a show he/she is watching... \n There is some overlap.. ");
					}
				}
				interests.close();
			}
		}catch (FileNotFoundException e){
			e.getMessage();
			System.exit(0);
		}
		
	}
/*
 * display method is to show the main menu ..
 */
	public static int displayMenu() {
		System.out.println("Please, enter one of the following options :: ");
		System.out.println("\t[1]. Open TVGuide and find what you can watch....... ");
		System.out.println("\t[2]. Explore Showlist... ");
		System.out.println("\t[3]. Test...");
		System.out.println("\t[4]. Exit...");
		System.out.print("YOUR OPTION :: ");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		switch (choice) {

		case 1:
			readGuide();
			Watch();
			displayMenu();
		case 2:
			exploreShows();
			displayMenu();
		case 3:
			//readGuide();
			DemoTvShow();
			//DemoShowList(sl,sl2);
			displayMenu();
		case 4:
			System.out.println("\n>>>> Thank you for using our guide <<<<");
			System.exit(0);
		default:
			System.out.println("\n Invalid option... option number from 1-4 ONLY \n");
			displayMenu();
		}
		return choice;
	}
	private static void DemoTvShow() {
		System.out.println("\n>>>>> Let's TEST :) <<<<<");
		System.out.println();
		System.out.println("\n<<<<<<<<< Testing TVShow Class >>>>>>>>");
		System.out.println();
		// Test Constructors
		//parameterized constructor....
		System.out.println("\n >>>>>>>>> Testing Constructor <<<<<<<<<\n");
		
		TvShow test1 = new TvShow("ABC20","Gray's Anatony",20.00,21.00);
		TvShow test2 = new TvShow("NBC21","Game of throns",21.00,22.00);
		System.out.println(test1);
		System.out.println(test2);
		//copy constructor....
		System.out.println("\n >>>>>>>>> Testing The Copy Constructor <<<<<<<<<\n");
		TvShow Copy = new TvShow(test2,"CBS21");
		System.out.println(Copy);
		// Test IsOnSameTime
		System.out.println("\n>>>>>>>>>>Testing IsOnSameTime Method<<<<<<<<<<<<<<<<<\n");
		System.out.println(test1.isOnSameTime(test2));
		System.out.println(test2.isOnSameTime(Copy));
		// Test equals method.....
		System.out.println("\n>>>>>>>>Testing Equals Method <<<<<<<<\n");
		System.out.println(test1.equals(test2));
		//Clone method....
	        System.out.println("\n>>>>>>>>>Testing Clone Method <<<<<<<"); 
	       // System.out.println(test1.clone());
	      //ShowList clonetest= new  sl.clone();
	
			// Testing Constructors....
		
			System.out.println("\n<<<<<<<<<<<<<<< Testing the ShowList Class :) >>>>>>>>>>>>>>>>>>\n");
			TvShow test3 = new TvShow("ABC20","Made In Quebec",20.00,21.00);
			TvShow test4 = new TvShow("NBC21","Friends",21.00,22.00);
			System.out.println("\n>>>>>>>>>>>>> addToStart methode <<<<<<<<<<<<<<<<<<<\n");
		
			TvShow ss = new TvShow();
			sl.addToStart(ss);

			System.out.println(sl);
			// Testing Constructors....
			System.out.println("\n>>>>>>>>>>>>> Copy Constructor <<<<<<<<<<<<<<<<<<<\n");
			
			ShowList s3 = new ShowList(sl);
			
			System.out.println(s3);
			
			
			// insertAtIndex method....
			System.out.println("\n>>>>>>>>>>>>>> Testing insertAtIndex Method <<<<<<<<<<<<<\n");
			sl.insertAtIndex(test4,1);
			System.out.println(sl);
			
			
			//deleteFromStart method.....
			System.out.println("\n>>>>>>>>>> Testing deleteFromStart Method <<<<<<<<<<<<<\n");
			sl.deleteFromStart();
			System.out.println(sl);
			
			
			//deleteAtIndex method....
			System.out.println("\n>>>>>>>>>> Testing deleteAtIndex Method <<<<<<<<<<<<<<<<<<\n");
			sl.deleteFromIndex(2);
			System.out.println(sl);
			
			
			//replaceAtIndex method...
			System.out.println("\n>>>>>>>>>> Testing replaceAtIndex Method <<<<<<<<<<<<<<<<<<<\n");
			sl.replaceAtIndex(test3,2);
			System.out.println(sl);
			System.out.println();
			
			
			
	}
			
	
	private static void exploreShows() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);

		//Prompt user to enter Show IDs sequentially
		System.out.println("Please enter The ShowID(s), enter 'OK' to end ");
		String ID = in.nextLine();
		ArrayList<String> DList = new ArrayList<>();
		//Adding user entered IDs into arraylist
		while(!ID.equalsIgnoreCase("OK")){
			DList.add(ID);
			ID = in.nextLine();
		}
		//Checking to see if ID is in ShowList
		for(int i=0; i<DList.size();i++){
			TvShow temp = sl.find(DList.get(i));
			if(temp== null)
				System.out.println(">>>> No show was found <<<<");
			else
				System.out.println(temp);
		}


	}
	public static void main(String[] args) {
		displayMenu();
		
/*		Scanner sc1 = new Scanner(System.in);
		//Prompt user to enter Show IDs sequentially
		System.out.println("Please enter show IDs:: 'insert Ok when you are done.. ");
		String line = sc1.nextLine();
		ArrayList<String> showIdList = new ArrayList<>();
		while(!line.equalsIgnoreCase("Ok")){
			showIdList.add(line);
			line = sc1.nextLine();
		}
		//search if  the show list has these show ids....
		for(int i=0; i<showIdList.size();i++){
			TvShow inShow = sl.find(showIdList.get(i));
			if(inShow == null)
				System.out.println("<<<<<<There is no show found>>>>>>");
			else
				System.out.println(inShow);
		} */
	}
	}
//C:\Users\rasha\eclipse-workspace\Assi#4\Interest.txt
