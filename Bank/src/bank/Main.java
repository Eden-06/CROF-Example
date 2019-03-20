package bank;

public class Main {

	public static void main(String[] args) {
		Factory factory=Factory.INSTANCE;

		Model model=factory.createModel();

		//Bank
		Bank bank=model.createBank();
		bank.setName("DB");
		Person peter=model.createPerson();
		peter.setFirstName("Alice");
		Bank.Consultant con1=bank.bindConsultant(peter);
		con1.setPhone("0176******");
		Person klaus=model.createPerson();
		klaus.setFirstName("Bob");
		Bank.Customer cu1=bank.bindCustomer(klaus);
		Company company=model.createCompany();
		company.setName("Google");
		Bank.Customer cu2=bank.bindCustomer(company);
		con1.addAdvises(cu2);
				
		Account account1=model.createAccount();
		account1.setId(1);
		account1.setBalance(500.0);
		Bank.SavingsAccount sa=bank.bindSavingsAccount(account1);
		sa.setFee(0.05);
		cu2.addOwn_sa(sa);
		
		Account account2=model.createAccount();
		account1.setId(2);
		account2.setBalance(2500.0);
		Bank.CheckingAccount ca=bank.bindCheckingAccount(account2);
		ca.setLimit(500.0);
		cu1.addOwn_ca(ca);
		
		bank.transfer(1, (Account) ca, 500.0);
		
		factory.storeJson(model,"bank.croj");
		Model loadedBank=factory.loadJson("bank.croj");
		
		Transaction t=model.createTransaction();
		t.setAmount(500.0);
		t.setCreationTime(1000000);
		Bank.MoneyTransfer m=bank.bindMoneyTransfer(t);
		Transaction.Source src=t.bindSource(account1);
		Transaction.Target trg=t.bindTarget(account2);
		src.addTrans(trg);
		m.setExecution(1000001);
		


		System.out.println("Object Schizo Test");
		System.out.println(peter.isSame(cu1));
		System.out.println(peter.isSame(con1));
		System.out.println(peter.isSame(peter));
		System.out.println(peter.isSame(cu2));
		System.out.println(peter.isSame(klaus));


		System.out.println(factory.toJson(model));
		factory.storeJson(model,"./instance/model.croj");
		
		Model fromJson=factory.loadJson("./instance/model.croj");
		System.out.println(factory.toJson(fromJson));
	
	}
}

