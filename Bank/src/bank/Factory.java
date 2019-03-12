package bank;



public interface Factory {
	Factory INSTANCE = bank.impl.FactoryImpl.init();
	Model createModel();
	void storeJson(Model model,String filePath);
	String toJson(Model model);
	Model loadJson(String filePath);
}
