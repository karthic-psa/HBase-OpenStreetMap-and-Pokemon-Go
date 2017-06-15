//package XMLp;


import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;



public class XML_Parser_OP {
	
 	public static void streetmap() throws IOException{
			 File fout = new File("streetmap_parse.txt");
		     FileOutputStream fo = new FileOutputStream(fout);
		     BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(fo));
		try{
			File inputFile = new File("map.kml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("node");
			System.out.println("No: "+nodes.getLength());
			for (int i=0; i<nodes.getLength(); i++){
				Node node = nodes.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					if((i%1000)==0){
						System.out.println(i);
					}
					Element enode = (Element) node;
					String lat = enode.getAttribute("lat");
					String lon =  enode.getAttribute("lon");
					String id =  enode.getAttribute("id");
					String type = "not_an_amenity";
					for(int j=0; j<enode.getElementsByTagName("tag").getLength(); j++){
						String k = enode.getElementsByTagName("tag").item(j).getAttributes().item(0).getTextContent();
						if(k.equals("amenity")){
							type = enode.getElementsByTagName("tag").item(j).getAttributes().item(1).getTextContent();
							break;
						}
					}
			        buff.write("Got 'Streetmap_Table_K','element_streetmap - "+(i+1)+"','Type:Type','"+type+"'\n");
			        buff.write("Got 'Streetmap_Table_K','element_streetmap - "+(i+1)+"','ID:ID','"+id+"'\n");
			        buff.write("Got 'Streetmap_Table_K','element_streetmap - "+(i+1)+"','Coordinate:Lat','"+lat+"'\n");
			        buff.write("Got 'Streetmap_Table_K','element_streetmap - "+(i+1)+"','Coordinate:Lon','"+lon+"'\n");
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		buff.close();
	}
 
	public static void poke() throws Exception{
		 File fout = new File("poke_parse.txt");
	     FileOutputStream fo = new FileOutputStream(fout);
	     BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(fo));
		try{
			File inputFile = new File("Houston Pokemon GO Map Pokestops and Gyms.kml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList folders = doc.getElementsByTagName("Folder");
			int a = 1;
			for (int i=0; i<folders.getLength(); i++){
				Node folder_node = folders.item(i);
				Element folder_element = (Element) folder_node;
				String type = folder_element.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
				if(!type.equals("Gyms")){
					type = "Pokestops";
				}
				NodeList children = folder_element.getElementsByTagName("Placemark");
				for (int j=0; j<children.getLength(); j++){
					String row_name = "PokeElement" + a; 
					a += 1;
					Node child_node = children.item(j);
			        Element child = (Element) child_node;
			        String name = child.getElementsByTagName("name").item(0).getTextContent().replace("\n", "");
			        name = name.replace("'", "");
			        String[] coords = child.getElementsByTagName("coordinates").item(0).getTextContent().replace("\n", "").split(",");
			        buff.write("Got 'Pokes_K','"+row_name+"','Type:Type','"+type+"'\n");
			        buff.write("Got 'Pokes_K','"+row_name+"','Name:Name','"+name+"'\n");
			        buff.write("Got 'Pokes_K','"+row_name+"','Coordinate:Lat','"+coords[1]+"'\n");
			        buff.write("Got 'Pokes_K','"+row_name+"','Coordinate:Lon','"+coords[0]+"'\n");
			        buff.write("Got 'Pokes_K','"+row_name+"','Coordinate:Height','"+coords[2]+"'\n");
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		buff.close();
	}	
	

	public static void main(String[] args) throws Exception {
		System.out.println("Tables");
		System.out.println("Streetmap table");
		streetmap();
		System.out.println("Pokemon_go table");
		poke();
	}
}
