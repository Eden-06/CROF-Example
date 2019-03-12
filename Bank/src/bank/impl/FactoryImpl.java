package bank.impl;



import bank.*;
import bank.util.*;


public class FactoryImpl implements Factory{
	@Override
	public Model createModel() {
		return new ModelImpl();
	}



	public static Factory init(){
		return new FactoryImpl();	
	}


	@Override
	public void storeJson(Model model,String filePath){
		new Serializer().toJson(model,filePath);
	}

	@Override
	public String toJson(Model model){
		return new Serializer().toJson(model);
	}

	@Override
	public Model loadJson(String filePath){
		return new Deserializer().fromJson(filePath);
	}
}
