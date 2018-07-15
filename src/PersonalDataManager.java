import java.awt.EventQueue;
import java.io.IOException;
import java.util.HashSet;
import java.util.Vector;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NsIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.sparql.core.ResultBinding;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;
//import org.apache.jena:jena-fuseki-basic;

import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


public class PersonalDataManager {
	
	
	// PPIoT ONTOLOGY is ONLINE
	private static final String ontologyURL = "http://pdm-aids.dibris.unige.it/PPIoT";
//	private static final String ontologyURL = "D:/work/workspace/PDM/src/PPIoT.owl";
	
	// Jena server is used for the user (in this example, locally, the file is also in UnserConditionsFinal.rdf)
//	private static final String UserConditions = "http://localhost:3030/UserConditions/";
	private static final String UserConditions = "D:/work/workspace/PDM/src/UserConditionsFinal.rdf";
	
	//TP STATEMENT FROM THIRD PARTIES
	private static final String TPStatements = "D:/work/workspace/PDM/src/TPdetails2.rdf";
	
	private static final String ontologyNS = "http://pdm-aids.unige.dibris.it/PPIoT#";
	


	private static final int MIN = 0;
	private static final int MAX = 1;
	
	private String targetItem = null;
	private String targetType = null;
	//private String conditionString = "http://vocab.deri.ie/ppo#PrivacyPreference";

	private Vector targetRequests = null;   // TP side
	private Vector userPrefs = null;  //user side list of prefs
	private Vector conditionNames = null;  //user side list of prefs
	private Vector childConditionNames = null;  //user side list of prefs
	private Vector tPchildconditionNames = null;
	private Vector conditionsOfAPref = null;
	private Vector tPconditionNames = null;
	private Vector conditionsOfATP = null;
	private Vector tPchildConditionsOfAPref= null;
	private Vector childConditionsOfAPref = null;
	private Vector dataSetsAppliedTo = null;  //user side
	private Vector tPAccess;
	private Vector tPPriority;
	private Vector UserAccess;
	private Vector UserPriority;
	public String tPrequest;
	private String childCondition = "http://vocab.deri.ie/ppo#hasChildCondition";
	private String hasLogicalOperator = "http://vocab.deri.ie/ppo#hasLogicalOperator";
	private String hasReason = "http://pdm-aids.unige.dibris.it/PPIoT#hasReason";
	private String hasRetentionPeriod = "http://pdm-aids.unige.dibris.it/PPIoT#hasRetentionPethod";
	private String hasMethod = "http://pdm-aids.unige.dibris.it/PPIoT#hasMethod";
	private String hasPersistence = "http://pdm-aids.unige.dibris.it/PPIoT#hasPersistence";
	private String allowsNegotiation = "http://pdm-aids.unige.dibris.it/PPIoT#allowsNegotiation";
	
	private String logicalOperator =null;
	
	
		
	/**
	 * @param args
	 */
	 
	public static void main(String[] args) {

		PersonalDataManager personalDataManager = new PersonalDataManager();
		personalDataManager.start();
	}
	
	public void start() {
		
//		String serviceURI = "http://localhost:3030/ds/data";
//		DatasetAccessorFactory factory = null;
//		DatasetAccessor accessor;
//		accessor = factory.createHTTP(serviceURI);
		
		// to get the URI and type info PDMStatement		
		Model statementModel = getModel(TPStatements);
		if ( searchTPRequest(statementModel) == false ) {
			System.out.println("your request description is not complete! Call Dialogue Manager");
		}
		System.out.println("this URI is the TP request:");	System.out.println("<" + targetItem + ">");
	//	System.out.println("its type is given by the following class:");	System.out.println("<" + targetType + ">");
		
		
		// find all the requested parameters
		TPStatement aStatement = new TPStatement();
		
		targetRequests = getRequestAccess(statementModel,targetItem);
		if ( targetRequests.size() == 0 ) {System.out.println(" targetRequests.size"+ targetRequests.size());} //just to check if size is zero
		//System.out.println("targetRequests.size= "+ targetRequests.size());


		for ( int j = 0; j < targetRequests.size(); j ++ ) //System.out.println("j: " + j); 
		
		
			// loop for each Dataset#RequestAccess
		{		
		String targetRequest = (String)(targetRequests.elementAt(j));
		tPrequest=targetRequest; // System.out.println("request_for "+request_for);		
		aStatement.setRequest(targetRequest);
		//show("Request(target)",targetRequest);
		
		// get access and priority
		tPAccess=getAccess(statementModel,targetItem);
		tPPriority=getPriority(statementModel,targetItem);
	//	System.out.println("tPAccess: " + tPAccess);
	//	System.out.println("tPPriority: " + tPPriority);
	
		
		//TP Conditions (now works only for one condition)
		
		tPconditionNames=conditionList(statementModel,targetItem);
		//System.out.println("tPconditionNames: " + tPconditionNames);
		String tPconditionName = (String)(tPconditionNames.elementAt(0));
		//System.out.println("tPconditionName: " + tPconditionName);
		
		
		tPchildconditionNames=childConditionList(statementModel,targetItem);
		//System.out.println("tPchildconditionNames: " + tPchildconditionNames);
		String tPchildconditionName = (String)(tPchildconditionNames.elementAt(0));
		
		
		
		
		//TP list items inside a condition
		conditionsOfATP=getConditions(statementModel, tPconditionName);
		for ( int m = 0; m < conditionsOfATP.size(); m ++ ) {
			String currentTPcondition = (String)(conditionsOfATP.elementAt(m));
		//	System.out.println("currentTPcondition: " + currentTPcondition);
			
			if (currentTPcondition.equals(childCondition)){
				
				System.out.println("currentTPcondition: " + currentTPcondition);
//				logicalOperator = getLogicalOperator(ontModel, candidatePreference);
				tPchildConditionsOfAPref=getConditions(statementModel, (String)(conditionsOfATP.elementAt(m+1)));
			//	System.out.println("currentTPChildCondition: " + (String)(conditionsOfATP.elementAt(m+1)));
			//	System.out.println("tPchildConditionsOfAPref: " + tPchildConditionsOfAPref);
			} 
			}
		
		
		
		// query now for user condition given jth request
		UserPreference currentUserPreference = new UserPreference();
		
		while ( true ) {
			Model userPreferenceModel = getModel(UserConditions);  // create RDF model also for user conditions
			
			// only for TP but now for checking purposes
			if ( isnoNeedToConvert(userPreferenceModel) == false ) {System.out.println("need dialogue manager for conversion");
				continue;
			}
			
			// create ontology model for inferencing
			OntModel ontModel =	ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF,userPreferenceModel);
			FileManager.get().readModel(ontModel,ontologyURL);
			
			// find how many conditions a user has, number 1 check for all, number 2 directly what TP has asked //conditionItems = findConditions2(ontModel, request_for);
			userPrefs = findPreferences(ontModel);     // System.out.println("conditionItems ni"+conditionItems);		
			if ( userPrefs.size() == 0 ) {System.out.println(" userPrefs.size= "+ userPrefs.size()); //just to check if size is zero
				continue;
				}                              // System.out.println("conditionItems.size= "+ conditionItems.size());
			
			
			// now loop for every conditionItems, check if dataRequest is inside the conditionitems
			String[] preference = new String[25];	
			int counter=0;
			for ( int i = 0; i < userPrefs.size(); i ++ ) {  //System.out.println("i: " + i);
				
				String candidatePreference = (String)(userPrefs.elementAt(i));  System.out.println(" for candidatePreference: "+ candidatePreference);
				
				
				// get access and priority
				UserAccess=getAccess(ontModel,candidatePreference);
				UserPriority=getPriority(ontModel,candidatePreference);
			//	System.out.println("UserAccess: " + UserAccess);
			//	System.out.println("UserPriority: " + UserPriority);
				
				
				// for each condition how many are matching the request
				
				dataSetsAppliedTo = getAppliesToWhichDataset(ontModel,candidatePreference);
				if ( dataSetsAppliedTo.size() == 0 ) {System.out.println(" conditionsinaPref.size"+ dataSetsAppliedTo.size()); //just to check if size is zero
					continue;
				}
			//	System.out.println("dataSetsAppliedTo.size() "+ dataSetsAppliedTo.size());
			
				
				for ( int k = 0; k < dataSetsAppliedTo.size(); k ++ ) {
					String candidatedataSetAppliedTo = (String)(dataSetsAppliedTo.elementAt(k));
				
					
					// if the dataset requested has conditions set by user
				if (candidatedataSetAppliedTo.equals(tPrequest)) 				
				{ 
					//System.out.println("candidatedataSetAppliedTo=  " + candidatedataSetAppliedTo);
					
					//here extract the conditions for Pref 1
					conditionNames=conditionList(ontModel,candidatePreference);
					String conditionName = (String)(conditionNames.elementAt(0));
					childConditionNames=childConditionList(ontModel,candidatePreference);
//					String childConditionName = (String)(childConditionNames.elementAt(0));
				//	System.out.println("conditionNames: " + conditionNames);

					conditionsOfAPref=getConditions(ontModel, conditionName);
					for ( int l = 0; l < conditionsOfAPref.size(); l ++ ) {
						String currentCondition = (String)(conditionsOfAPref.elementAt(l));
				//		System.out.println("currentCondition: " + currentCondition);
						
						if (currentCondition.equals(childCondition)){
							
							System.out.println("childConditionNames: " + childConditionNames);
			//				logicalOperator = getLogicalOperator(ontModel, candidatePreference);
//							System.out.println("currentChildCondition: " + (String)(conditionsOfAPref.elementAt(l+1)));
//							System.out.println("conditionfOfAPref: " + conditionsOfAPref+ " l "+ l);
							childConditionsOfAPref=getConditions(ontModel, (String)(conditionsOfAPref.elementAt(l+1)));
							
			//				System.out.println("childConditionsOfAPref: " + childConditionsOfAPref);
						} 
						
					
					
					}
			//		System.out.println("candidatePreference= "+ candidatePreference+ "    conditionsOfAPref= "+ conditionsOfAPref);
			//		hasReason = getReason(ontModel, candidatePreference);
			//		System.out.println("hasReason: "+ hasReason + "  for candidatedataSetAppliedTo:"+ candidatedataSetAppliedTo);
					
					
					currentUserPreference.setRequest(candidatedataSetAppliedTo);
				
			//		System.out.println("\nWe found the user preference URI: \n" + candidatePreference + "\nwith settings requested by TP");
					currentUserPreference.clearAll();

					preference[counter]= candidatePreference; counter++;
			//		System.out.println(" match no. "+ counter+ " ");			
				
				}
			}
			}
			
			System.out.println("\nThe following "+ counter+ " user preference(s) has user conditions for \n \""+ tPrequest+ "\" asked by TP");
			int a,b,c;
			for(b=0;b<counter;b++){
		            System.out.println("User Preference: "+ preference[b]); }
			for(c=0;c<conditionsOfAPref.size();c=c+2){
	            System.out.println("condition: "+ (String)(conditionsOfAPref.elementAt(c)) + "\tvalue: "+ (String)(conditionsOfAPref.elementAt(c+1)));}
			for(a=0;a<childConditionsOfAPref.size();a=a+2){
	            System.out.println("child condition: "+ (String)(childConditionsOfAPref.elementAt(a)) + "\tvalue: "+ (String)(childConditionsOfAPref.elementAt(a+1)));}
			
			
			break;  // or next catalog file.
		

	}
	}
	
	// go to interpretation
		
		
		//negotiation and recommendation
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
//after the RDF becomes a candidate, now this method is used to find all the candidate products inside the rdf
	
	
	private Vector findPreferences(Model m) {

		Vector candidates = new Vector();
		String queryString =  
//		    "SELECT ?candidate " +
//			"WHERE {" +
//			"   ?candidate <" + RDF.type + "> <" + targetType + ">. " +
//			"   }"; 
				
			    "SELECT ?candidate " +
				"WHERE {" +
				"   ?candidate <" + RDF.type + "> <http://vocab.deri.ie/ppo#PrivacyPreference>. " +
				"   }"; 
					
		
		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("candidate");
			if ( rn != null && rn.isAnon() == false ) {
				candidates.add(rn.toString());
				//show("location(target)",rn.toString());
			} 
		}
		qe.close();
		return candidates;
	}
	
	private Vector conditionList(Model m, String preference) {
		

		Vector candidates = new Vector();
		String queryString =  				
			    "SELECT ?candidate ?child" +
				"WHERE {" +
				" <"+preference+"> <http://vocab.deri.ie/ppo#hasCondition> ?candidate. " +
				"   }"; 
					
		
		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("candidate");
			if ( rn != null && rn.isAnon() == false ) {
				candidates.add(rn.toString());
		//		System.out.println("conditionList: "+candidates);
			} 
		}
		qe.close();
		return candidates;
	}
	
	private Vector getPriority(Model m, String preference) {
		

		Vector candidates = new Vector();
		String queryString =  				
			    "SELECT ?candidate ?child" +
				"WHERE {" +
				" <"+preference+"> <http://vocab.deri.ie/ppo#hasPriority> ?candidate. " +
				"   }"; 
					
		
		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("candidate");
			if ( rn != null && rn.isAnon() == false ) {
				candidates.add(rn.toString());
	//			System.out.println("getPriority: "+candidates);
			} 
		}
		qe.close();
		return candidates;
	}
	
	private Vector getAccess(Model m, String preference) {
		

		Vector candidates = new Vector();
		String queryString =  				
			    "SELECT ?candidate ?child" +
				"WHERE {" +
				" <"+preference+"> <http://vocab.deri.ie/ppo#hasAccess> ?candidate. " +
				"   }"; 
					
		
		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("candidate");
			if ( rn != null && rn.isAnon() == false ) {
				candidates.add(rn.toString());
	//			System.out.println("getAccess: "+candidates);
			} 
		}
		qe.close();
		return candidates;
	}
	
	
	private Vector childConditionList(Model m, String preference) {

		String hello= "http://www.w3.org/2002/07/owl#sameAs";
		String hello2= "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
		Vector candidates = new Vector();
		String queryString =  			
			    "SELECT ?value ?candidate" +
				"WHERE {" +
				" <"+preference+"> <http://vocab.deri.ie/ppo#hasCondition> ?candidate. " +
				" ?candidate <http://vocab.deri.ie/ppo#hasChildCondition> ?value. " +
	//			"   FILTER ( ?aa NOT IN (<"+hello+">, <"+hello2+">) ) " + 
				"   }"; 
					
		
		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("value");
			if ( rn != null && rn.isAnon() == false ) {
				candidates.add(rn.toString());
				System.out.println("childconditionList"+candidates);
			} 
		}
		qe.close();
		return candidates;
	}
	
	
	private Vector getConditions(Model m, String itemURI) {
		System.out.println("itemURI: "+ itemURI);
		
		String hello= "http://www.w3.org/2002/07/owl#sameAs";
		String hello2= "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
		
		Vector condition = new Vector();
		Vector mixed = new Vector();
		Vector values = new Vector();
				String queryString =  
					    "SELECT ?value ?cond " +
								"WHERE {" +
					"   <"+itemURI+"> ?cond ?value. " +
					"   FILTER ( ?cond NOT IN (<"+hello+">, <"+hello2+">) ) " + 
					"   }"; 	
				Query q = QueryFactory.create(queryString);
			//	QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/UserConditions/query",q);				
						QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("cond");
			RDFNode rn2 = (RDFNode)binding.get("value");
			if ( rn != null && rn.isAnon() == false ) {
				condition.add(rn.toString());
				mixed.add(rn.toString());
			//	System.out.println("conditions: "+condition);
			} 
			if ( rn2 != null && rn2.isAnon() == false ) {
				values.add(rn2.toString());
				mixed.add(rn2.toString());
			//	System.out.println("values: "+values);
			} 
		}
		qe.close();
		System.out.println("mixed: "+mixed);
		return mixed;
		
	}
	
	
	private Vector getChildConditions(Model m, String preferenceURI, String conditionName) {
		System.out.println("itemURI: "+ preferenceURI);
		
		String hello= "http://www.w3.org/2002/07/owl#sameAs";
		String hello2= "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
		
		Vector condition = new Vector();
		Vector values = new Vector();
				String queryString =  
					    "SELECT ?value ?cond ?tempvalue " +
								"WHERE {" +
					"   <"+preferenceURI+">  <http://vocab.deri.ie/ppo#hasChildCondition>  <"+conditionName+">. " +
					"   <"+conditionName+"> ?cond ?value. " +
					"   FILTER ( ?cond NOT IN (<"+hello+">, <"+hello2+">) ) " + 
					"   }"; 	
				 
		Query q = QueryFactory.create(queryString);
		//QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/UserConditions/query",q);	
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("cond");
			RDFNode rn2 = (RDFNode)binding.get("value");
			if ( rn != null && rn.isAnon() == false ) {
				condition.add(rn.toString());
		//		System.out.println("conditions: "+condition);
			} 
			if ( rn2 != null && rn2.isAnon() == false ) {
				condition.add(rn2.toString());
		//		System.out.println("values: "+values);
			} 
		}
		qe.close();
		return condition;
		
	}
	
	
	
private Vector getAppliesToWhichDataset(Model m, String itemURI) {
		
		Vector dataSetItems = new Vector();
		String queryString =  
		    "SELECT ?value " +
			"WHERE {" +
			"   <" + itemURI + "> <http://vocab.deri.ie/ppo#appliesToDataset> ?value. " +
			"   }"; 
		
		Query q = QueryFactory.create(queryString);
	//	QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/UserConditions/query",q);	
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("value");
			if ( rn != null && rn.isAnon() == false ) {
				dataSetItems.add(rn.toString());
			} 
		}
		qe.close();
		return dataSetItems;		
	}
	
	

	private Vector getRequestAccess(Model m, String itemURI) {
		show("show itemURI",itemURI);
		
		Vector itemsInARequestAccess = new Vector();
		String queryString =  
		    "SELECT ?value " +
			"WHERE {" +
			"   <" + itemURI + "> <http://pdm-aids.unige.dibris.it/PPIoT#RequestAccess> ?value. " +
			"   }"; 
		
		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("value");
			if ( rn != null && rn.isAnon() == false ) {
				itemsInARequestAccess.add(rn.toString());
			} 
		}
		qe.close();
		return itemsInARequestAccess;	
	}
	
	
	
private String getReason(Model m, String itemURI) {
		
		String queryString =  
			    "SELECT ?value ?tempvalue " +
					"WHERE {" +
//						"   <" + itemURI + "> <http://pdm-aids.unige.dibris.it/PPIoT#hasRetentionPeriod> ?value. " +
//						"   }"; 
		//			"   ?candidate <" + RDF.type + ">  <http://vocab.deri.ie/ppo#userpp1>. " +
		"   <"+itemURI+"> <http://vocab.deri.ie/ppo#hasCondition> ?tempvalue. " +
		"   ?tempvalue <http://pdm-aids.unige.dibris.it/PPIoT#hasReason> ?value. " +
		"   }"; 				
		
		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("value");
			if ( rn != null && rn.isAnon() == false ) {
				return rn.toString();
			} 
		}
		qe.close();
		return null;
		
	}
		
	



	// to understand our search quest
	private boolean searchTPRequest(Model m) {
		
		String queryString =  
		    "SELECT ?subject ?predicate ?object " +
			"WHERE {" +
			"   ?subject <" + RDF.type + "> ?object. " + 
			"   }"; 

		Query q = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(q,m);
		ResultSet rs = qe.execSelect();
		
		// collect the data type property names
		while ( rs.hasNext() ) {
			ResultBinding binding = (ResultBinding)rs.next();
			RDFNode rn = (RDFNode)binding.get("subject");
			if ( rn != null ) {
				targetItem = rn.toString();
			}
			rn = (RDFNode)binding.get("object");
			if ( rn != null ) {
				targetType = rn.toString();
			}
		}
		qe.close();
		
		if ( targetItem == null || targetItem.length() == 0 ) {
			return false;
		}
		if ( targetType == null || targetType.length() == 0 ) {
			return false;
		}
		return true;
	}

	// read in a given RDF document
	private Model getModel(String rdfURL) {
		
		Model m = null;
		if ( rdfURL == null ) return m;
		
		try {
			m = ModelFactory.createDefaultModel();
			FileManager.get().readModel(m,rdfURL);
			// m.read(rdfURL);
		} catch (Exception e) {
			System.out.println("====> error rading foaf file at [" + rdfURL + "]");
			m = null;
		}
		return m;
	}
	
	
	//if the catalog document should be further investigated
	private boolean isnoNeedToConvert(Model m) {
		
		if ( m == null  ) {
			return false;
		}
		
		HashSet ns = new HashSet();
		this.collectNamespaces(m,ns);
		//return ns.contains(ontologyNS);
		return ns.contains("http://vocab.deri.ie/ppo#");
		
	}

	private void collectNamespaces(Model m,HashSet hs) {
		if ( hs == null || m == null ) {
			return;
		}
		NsIterator nsi = m.listNameSpaces();
		while ( nsi.hasNext() ) {
			hs.add(nsi.next().toString());
		}		
	}
	
	private void show(String t, String s) {
		if ( s != null && s.length() != 0 ) {
			System.out.println(t + " value is: " + s);
		} else {
			System.out.println(t + " value is not specified." );
		}
	}
	
}
