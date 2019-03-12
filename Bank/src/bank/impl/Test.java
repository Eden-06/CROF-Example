package bank.impl;


import bank.*;


public class Test {

	public static void main(String[] args) {
		Factory factory=Factory.INSTANCE;

		Model model=factory.createModel();

		//Bank
		Bank bank=model.createBank();
		bank.setName("DB");
		Person peter=model.createPerson();
		peter.setFirstName("Peter");
		Bank.Consultant con1=bank.bindConsultant(peter);
		con1.setPhone("0176******");
		Bank.Customer cu1=bank.bindCustomer(peter);
		System.out.println(con1.addAdvises(cu1));
		Person klaus=model.createPerson();
		klaus.setFirstName("Klaus");
		Bank.Customer cu2=bank.bindCustomer(klaus);
		Company company=model.createCompany();
		company.setName("Google");
		Bank.Customer cu3=bank.bindCustomer(company);
		System.out.println(con1.addAdvises(cu3));
		Account account1=model.createAccount();
		account1.setBalance(500.0);
		Bank.SavingsAccount sa=bank.bindSavingsAccount(account1);
		sa.setFee(0.05);
		
		Account account2=model.createAccount();
		account2.setBalance(2500.0);

		Bank.CheckingAccount ca=bank.bindCheckingAccount(account2);
		ca.setLimit(500.0);
		cu3.addOwn_sa(sa);
		cu2.addOwn_ca(ca);
		
		Transaction t=model.createTransaction();
		t.setAmount(500.0);
		t.setCreationTime(1000000);
		Bank.MoneyTransfer m=bank.bindMoneyTransfer(t);
		m.setExecution(1000001);
		Transaction.Source so=t.bindSource(account1);
		
		Transaction.Target ta=t.bindTarget(account2);
		so.addTrans(ta);


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

		
		//inheritance
		/*
		Comp c=model.createComp();
		c.setName("comp");
		SuperNatural sup=model.createSuperNatural();
		sup.setSupField("sup");
		Natural mid=model.createNatural();
		mid.setMidField("mid");
		mid.setSupField("supInMid");
		SubNatural sub=model.createSubNatural();
		sub.setMidField("midInSub");
		sub.setSupField("supInSub");
		Comp.Role ra=c.bindRole(sup);
		ra.setName("supRole");
		Comp.Role rb=c.bindRole(mid);
		rb.setName("midRole");
		Comp.Role rc=c.bindRole(sub);
		rc.setName("subRole");
		System.out.println(((SuperNaturalImpl)ra.getPlayer()).getSupField());
		System.out.println(((NaturalImpl)rb.getPlayer()).getSupField());
		System.out.println(((SubNaturalImpl)rc.getPlayer()).getSupField());

		factory.storeJson(model);
		Model fromJson=factory.loadJson("./instance/inheritance_1.croj");
		factory.storeJson(fromJson);
		
		*/
		
		/*
		//DataType
		Da da1=model.createDa();
		da1.setName("da1");
		Da da2=model.createDa();
		da2.setName("da2");
		
		
		Ct ct=model.createCt();
		ct.setName("ct");
		Na na1=model.createNa();
		na1.setName("na1");
		na1.setAttr(da1);
		
		Na na2=model.createNa();
		na2.setName("na2");
		na2.setAttr(da2);
		
		Ct.Ra ra1=ct.bindRa(ct, na1);
		ra1.setName("na1ra1");
		Ct.Ra ra2=ct.bindRa(ct, na2);
		ra2.setName("na2ra2");
		*/
/*
		//my.crom test unbind
		CompartmentImpl c1=(CompartmentImpl)model.createCompartment();
		c1.setName("Compartment_1");
		CompartmentImpl c2=(CompartmentImpl)model.createCompartment();
		c2.setName("Compartment_2");
		
		
		NaturalOneImpl none1=(NaturalOneImpl)model.createNaturalOne();
		NaturalOneImpl none2=(NaturalOneImpl)model.createNaturalOne();
		NaturalOneImpl none3=(NaturalOneImpl)model.createNaturalOne();
		none1.setName("none1");
		none2.setName("none2");
		none3.setName("none3");
		CompartmentImpl.NaturalOneRoleOne nonerone1=(CompartmentImpl.NaturalOneRoleOne)c1.bindRoleOne(none1);
		CompartmentImpl.NaturalOneRoleOne nonerone2=(CompartmentImpl.NaturalOneRoleOne)c1.bindRoleOne(none2);
		CompartmentImpl.NaturalOneRoleOne nonerone3=(CompartmentImpl.NaturalOneRoleOne)c1.bindRoleOne(none3);
		nonerone1.setName("n1r11");
		nonerone2.setName("n1r12");	
		nonerone3.setName("n1r13");
		c1.unbindRoleOne(none3);
		System.out.print(nonerone3);
*/		
		/*
		//my.crom
		CompartmentImpl c1=(CompartmentImpl)model.createCompartment();
		c1.setName("Compartment_1");
		CompartmentImpl c2=(CompartmentImpl)model.createCompartment();
		c2.setName("Compartment_2");
		
		
		NaturalOneImpl none1=(NaturalOneImpl)model.createNaturalOne();
		NaturalOneImpl none2=(NaturalOneImpl)model.createNaturalOne();
		none1.setName("none1");
		none2.setName("none2");
		CompartmentImpl.NaturalOneRoleOne nonerone1=(CompartmentImpl.NaturalOneRoleOne)c1.bindRoleOne(none1);
		CompartmentImpl.NaturalOneRoleOne nonerone2=(CompartmentImpl.NaturalOneRoleOne)c1.bindRoleOne(none2);
		nonerone1.setName("n1r11");
		nonerone2.setName("n1r12");		
		*/
		
/*
		//irreflexive
		Ct ct=model.createCt();
		ct.setName("ct");
		Na na1=model.createNa();
		na1.setName("na1");
		Na na2=model.createNa();
		na2.setName("na2");	
		Nb nb1=model.createNb();
		nb1.setName("nb1");
		Ct.Ra ra1=ct.bindRa( na1);
		ra1.setName("na1ra1");
		Ct.Rb rb1=ct.bindRb( na1);
		rb1.setName("na1rb1");
		Ct.Rb rb2=ct.bindRb( na2);
		rb2.setName("na2rb1");
		Ct.Rb rb3=ct.bindRb( nb1);
		rb3.setName("nb1rb3");
		
		System.out.println(ra1.addAdvices(rb1));
		System.out.println(rb2.addAdvices(ra1));
		System.out.println(ra1.addAdvices(rb3));
*/	
		
	}
}

