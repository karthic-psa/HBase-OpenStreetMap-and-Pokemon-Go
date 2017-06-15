//package XMLp;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

 
 public class Poke_query_parts {

	private static double univh_Lat = 29.7199489;
	private static double univh_Lon = -95.3422334;
	private static double RU_Lat = 29.7174;
	private static double RU_Lon = -95.4018;
	private static double TSU_Lat = 29.7218;
	private static double TSU_Lon = -95.3606;
	private static double univh_dwt_Lat = 29.7660;
	private static double univh_dwt_Lon = -95.3596;
	
	
	
	public static void part_1() throws Exception{
		int count=0;
		File fout = new File("part1.txt");
	    FileOutputStream fo = new FileOutputStream(fout);
	    BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(fo));
		try{
			File inputFile = new File("Houston Pokemon GO Map Pokestops and Gyms.kml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList folders = doc.getElementsByTagName("Folder");
			for (int i=0; i<folders.getLength(); i++){
				Node folder_node = folders.item(i);
				Element folder_element = (Element) folder_node;
				String type = folder_element.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
				if(!type.equals("Gyms")){
					type = "Pokestops";
				}
				NodeList children = folder_element.getElementsByTagName("Placemark");
				for (int j=0; j<children.getLength(); j++){
					Node child_node = children.item(j);
			        Element child = (Element) child_node;
					
			        String name = child.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
			        name = name.replace("'", "");
					String[] coords = child.getElementsByTagName("coordinates").item(0).getTextContent().replace("\n", "").split(",");
			        double lat = Double.parseDouble(coords[1]);
			        double lon = Double.parseDouble(coords[0]);
			       	double dist = spread(univh_Lat, univh_Lon, lat, lon);
			        if(dist<=5.0){
			        count+=1;
			        buff.write("Name: "+name+" Type: "+type+"\n");
			        }
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("Total near: "+count);
		System.out.println("Restuarants from file: "+37);
		buff.close();
	}
	
		public static void part_2() throws Exception{
				int UoH_cnt=0;
		int UoH_dt_cnt=0;
		int RU_cnt=0;
		int Tsu_cnt=0;
		try{
			File inputFile = new File("Houston Pokemon GO Map Pokestops and Gyms.kml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList folders = doc.getElementsByTagName("Folder");
			for (int i=0; i<1; i++){
				Node folder_node = folders.item(i);
				Element folder_element = (Element) folder_node;
				String type = folder_element.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
				
				if(!type.equals("Gyms")){
					type = "Pokestops";
				}
				NodeList children = folder_element.getElementsByTagName("Placemark");
				for (int j=0; j<children.getLength(); j++){
					Node child_node = children.item(j);
			        Element child = (Element) child_node;
					
			        String name = child.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
			        name = name.replace("'", "");
								        String[] coords = child.getElementsByTagName("coordinates").item(0).getTextContent().replace("\n", "").split(",");
			        double lat = Double.parseDouble(coords[1]);
			        double lon = Double.parseDouble(coords[0]);
			        double uoh_spread = spread(univh_Lat, univh_Lon, lat, lon);
			        double uoh_dt_spread = spread(univh_dwt_Lat, univh_dwt_Lon, lat, lon);
			        double RU_spread = spread(RU_Lat, RU_Lon, lat, lon);
			        double Tsu_spread = spread(TSU_Lat, TSU_Lon, lat, lon);
			        if(uoh_spread<=5.0){UoH_cnt+=1;}
			        if(uoh_dt_spread<=5.0){UoH_dt_cnt+=1;}
			        if(RU_spread<=5.0){RU_cnt+=1;}
			        if(Tsu_spread<=5.0){Tsu_cnt+=1;}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("University of Houston : "+UoH_cnt+" Pokestops");
		System.out.println("Rice University :  "+RU_cnt+" Pokestops");
		System.out.println("Texas Southern University :  "+Tsu_cnt+" Pokestops");
		System.out.println("University of Houston Down Town : "+UoH_dt_cnt+" Pokestops");
	}
	
	
	public static void part_3() throws Exception{

		int UoH_cnt=0;
		int UoH_dt_cnt=0;
		int RU_cnt=0;
		int Tsu_cnt=0;
		int count=0;
		int nxtcount=0;
		try{
	
			File inputFile = new File("Houston Pokemon GO Map Pokestops and Gyms.kml");
	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList folders = doc.getElementsByTagName("Folder");
			for (int i=0; i<folders.getLength(); i++){
				Node folder_node = folders.item(i);
				Element folder_element = (Element) folder_node;
			
				String type = folder_element.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
			
				if(!type.equals("Gyms")){
					type = "Pokestops";
				}
				NodeList children = folder_element.getElementsByTagName("Placemark");
				for (int j=0; j<children.getLength(); j++){
					if(type.equals("Pokestops")){
						count+=1;
					}
					else{
						nxtcount+=1;
					}
					
					Node child_node = children.item(j);
			        Element child = (Element) child_node;
					
			        String name = child.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
			        name = name.replace("'", "");
			
			        String[] coords = child.getElementsByTagName("coordinates").item(0).getTextContent().replace("\n", "").split(",");
			        double lat = Double.parseDouble(coords[1]);
			        double lon = Double.parseDouble(coords[0]);
			        
			        double uoh_spread = spread(univh_Lat, univh_Lon, lat, lon);
			        double uoh_dt_spread = spread(univh_dwt_Lat, univh_dwt_Lon, lat, lon);
			        double RU_spread = spread(RU_Lat, RU_Lon, lat, lon);
			        double Tsu_spread = spread(TSU_Lat, TSU_Lon, lat, lon);
			        if(uoh_spread<=5.0){UoH_cnt+=1;}
			        if(uoh_dt_spread<=5.0){UoH_dt_cnt+=1;}
			        if(RU_spread<=5.0){RU_cnt+=1;}
			        if(Tsu_spread<=5.0){Tsu_cnt+=1;}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("University of Houston - Gyms and Stops: "+UoH_cnt);
		System.out.println("Rice University - Gyms and Stops: "+RU_cnt);
		System.out.println("Texas Southern University - Gyms and Stops: "+Tsu_cnt);
		System.out.println("University of Houston Down Town - Gyms and Stops: "+UoH_dt_cnt);
		
		
	}
	
	
		public static double spread(double lat_1, double lon_1, double lat_2, double lon_2 ){
		double Rad_planet = 6371000; 
		
		double ReqLat = Math.toRadians((lat_2 - lat_1)); double delta_lon = Math.toRadians((lon_2 - lon_1));
	
		lat_1 = Math.toRadians(lat_1);
		lat_2 = Math.toRadians(lat_2);
		
		double init_part = Math.sin((ReqLat/2)) * Math.sin((ReqLat/2)) + Math.cos(lat_1) * Math.cos(lat_2) * Math.sin((delta_lon/2)) * Math.sin((delta_lon/2));
		double init_part_nxt = 2 * Math.atan2(Math.sqrt(init_part), Math.sqrt(1-init_part));
		double totMeters = Rad_planet * init_part_nxt;
		
		double totMiles = totMeters/1609.34;
	
		return totMiles;
	}
	

	public static void main(String[] args) throws Exception {
		
		System.out.println("Part 1: ");
		System.out.println("Total number of restaurants 5 miles from campus that have pokestops or gyms");
		part_1();
		System.out.println("\n\nPart 2: ");
		System.out.println("The University that has the largest number of Pokestops in Houston area within a 5 mile radius");
		part_2();
		System.out.println("\n\nPart 3: optional ");
		System.out.println("The University that has the most Pokemon GO regions within 5 miles radius");
		part_3();
		
	}
 }