package OETPN;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriTransition;
import DataObjects.DataFloat;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Project1Server {
	public static void main(String[] args)
	{
		 //Server Petri Net
		PetriNet server = new PetriNet();
		server.PetriNetName = "Server Petri";
		server.NetworkPort = 2080;

		DataFloat p0Prime = new DataFloat();
		p0Prime.SetName("p0Prime");
		server.PlaceList.add(p0Prime);

		DataFloat p1Prime = new DataFloat();
		p1Prime.SetName("p1Prime");
		server.PlaceList.add(p1Prime);

		DataFloat p2Prime = new DataFloat();
		p2Prime.SetName("p2Prime");
		server.PlaceList.add(p2Prime);

		DataFloat p3Prime = new DataFloat();
		p3Prime.SetName("p3Prime");
		server.PlaceList.add(p3Prime);

		PetriTransition t1Prime = new PetriTransition(server);
		t1Prime.TransitionName = "t1Prime";
		t1Prime.InputPlaceName.add("p0Prime");
		t1Prime.InputPlaceName.add("p1Prime");
		
		Condition T1PrimeCt1 = new Condition(t1Prime,"p0Prime", TransitionCondition.NotNull);
		Condition T1PrimeCt2 = new Condition(t1Prime,"p1Prime", TransitionCondition.NotNull);
		T1PrimeCt1.SetNextCondition(LogicConnector.AND, T1PrimeCt2);

		GuardMapping grdT1Prime = new GuardMapping();
		grdT1Prime.condition = T1PrimeCt1;
		grdT1Prime.Activations.add(new Activation(t1Prime, "p0Prime", TransitionOperation.SendOverNetwork, "p2Prime"));
		grdT1Prime.Activations.add(new Activation(t1Prime, "p1Prime", TransitionOperation.Prod, "p2Prime"));

		PetriTransition t2Prime = new PetriTransition(server);
		t2Prime.TransitionName = "t2Prime";
		t2Prime.InputPlaceName.add("p2Prime");

		Condition T2PrimeCt1 = new Condition(t2Prime, "p2Prime", TransitionCondition.NotNull);

		GuardMapping grdT2Prime = new GuardMapping();
		grdT2Prime.condition = T2PrimeCt1;
		grdT2Prime.Activations.add(new Activation(t2Prime, "p2Prime", TransitionOperation.SendOverNetwork, "p3"));
	}

}
