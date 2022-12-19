import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestParseXML {
//https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
        File inputFile = new File("C:\\Users\\lookm\\git\\dataRepresentation\\datarepresentation\\src\\main\\resources\\com\\bpmn\\sample.bpmn2");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc =  dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        
        NodeList scriptTaskList =  doc.getElementsByTagName("bpmn2:scriptTask"); 
        Map<String, String> idNameOfScriptTaskMap = new HashMap<String, String> ();
        
        for (int temp = 0; temp < scriptTaskList.getLength(); temp++) {
        	
            Node nNode = scriptTaskList.item(temp);
//            System.out.println("\nCurrent Element :" + nNode.getNodeName());
              
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            	
               Element eElement = (Element) nNode;
               
//               System.out.println(eElement.getAttribute("id") + " -  " + eElement.getAttribute("name"));
               idNameOfScriptTaskMap.put(eElement.getAttribute("id") , eElement.getAttribute("name"));
 
            }
            
        }
        
        NodeList sequenceFlowList =  doc.getElementsByTagName("bpmn2:sequenceFlow"); 
        Map<String, String> sourceAndTargetOfSequenceFlowMap = new HashMap();
        
        for (int temp = 0; temp < sequenceFlowList.getLength(); temp++) {
        	
            Node nNode = sequenceFlowList.item(temp);
//            System.out.println("\nCurrent Element :" + nNode.getNodeName());
              
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            	
               Element eElement = (Element) nNode;
               sourceAndTargetOfSequenceFlowMap.put(eElement.getAttribute("sourceRef"), eElement.getAttribute("targetRef"));
//               System.out.println(eElement.getAttribute("sourceRef") + " -  " + eElement.getAttribute("targetRef")); 
 
            }
            
        }
        
        // Form the map of Keys (SequenceFlow) return an ordered list of the keys of the sciptTasks
        List<String> ordersKeys = new ArrayList<>();
        String scriptTaskKey = sourceAndTargetOfSequenceFlowMap.entrySet()
												                .stream()
												                .filter(entry -> entry.getKey().startsWith("StartEvent"))
												                .map(Map.Entry::getValue)
												                .collect(Collectors.toSet()).iterator().next();
        ordersKeys.add(scriptTaskKey);  
        while (!scriptTaskKey.startsWith("EndEvent")) {
        	scriptTaskKey =  sourceAndTargetOfSequenceFlowMap.get(scriptTaskKey);
        	ordersKeys.add(scriptTaskKey); 
        }
        ordersKeys.remove(ordersKeys.size()-1);
        
        
        // get the name of the orders keys of the scripts 
        List<String> ordersScriptTaskNameList = new ArrayList<>();
        for (String key : ordersKeys) {
        	ordersScriptTaskNameList.add(idNameOfScriptTaskMap.get(key));
        } 

        System.out.println(ordersScriptTaskNameList);
         
	}

	public SortedMap<String, String> getByPrefix(NavigableMap<String, String> myMap, String prefix) {
		return myMap.subMap(prefix, prefix + Character.MAX_VALUE);
	}
	
}
