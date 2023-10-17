package OETPN;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFloat;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Project1Client {
	
	public static void main(String[] args)
	{
		PetriNet client = new PetriNet();
		client.PetriNetName = "ClientPetri";
		client.NetworkPort = 1080;
		
		DataFloat p0 = new DataFloat();
		p0.SetName("p0");
		p0.SetValue(1.0f);
		client.PlaceList.add(p0);
		
		DataFloat p1 = new DataFloat();
		p1.SetName("p1");
		client.PlaceList.add(p1);
		
		DataFloat p4 = new DataFloat();
		p4.SetName("p4");
		client.PlaceList.add(p4);
		
		DataFloat p5 = new DataFloat();
		p5.SetName("p5");
		client.PlaceList.add(p5);
		     
		DataFloat p6 = new DataFloat();
		p6.SetName("p6");
		client.PlaceList.add(p6);
		
		PetriTransition t1 = new PetriTransition(client);
		t1.TransitionName = "t1";
		t1.InputPlaceName.add("p0");
		t1.InputPlaceName.add("p1");
		
		Condition T1Ct1 = new Condition(t1,"p0", TransitionCondition.NotNull);
		Condition T1Ct2 = new Condition(t1, "p4", TransitionCondition.NotNull);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);
		
		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition = T1Ct1;
		grdT1.Activations.add(new Activation(t1,"p0",TransitionOperation.Move, "p4"));
		grdT1.Activations.add(new Activation(t1, "p1",TransitionOperation.SendOverNetwork, "p3"));
		
		
		PetriTransition t2 = new PetriTransition(client);
		t2.TransitionName = "t2";
		t2.InputPlaceName.add("p5");

		Condition T2Ct1 = new Condition(t2,"p5", TransitionCondition.NotNull);
		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition = T2Ct1;
		grdT2.Activations.add(new Activation(t2, "p5", TransitionOperation.Move, "p6"));
		
		System.out.println(client.PlaceList);
		System.out.println("Client Project 1 Started started \n");
		client.Delay = 3000;
		
		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = client;
		frame.setVisible(true);
		
		
		
	}

}
