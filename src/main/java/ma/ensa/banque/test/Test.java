package ma.ensa.banque.test;


public class Test {

	public static void main(String[] args) {

//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				new String[] { "applicationContext.xml" });
//		IBanqueDao dao = (IBanqueDao) context.getBean("banqueDao");
//		IBanqueService service = (IBanqueService) context
//				.getBean("banqueService");
		// List<Compte> cpts = dao.getAllAccount();
		//
		// for (Compte c : cpts) {
		//
		// System.out.println("****************Compte ********************");
		// System.out.println(c.getDateCreation());
		// System.out.println(c.getSolde());
		// System.out.println(c.getClient().getNomPersonne());
		// System.out.println("*******************************************");
		// }
		//
		// service.verser(2000, (long) 4, (long) 10);
		// service.verser(2000, (long) 5, (long) 10);
		// service.retirer(100, (long) 4, (long) 10);
		// service.retirer(100, (long) 5, (long) 10);
		// service.virement(5000, (long) 5, (long) 4, (long) 10);
		//
		// List<Compte> cpts1 = dao.getAllAccount();
		//
		// for (Compte c : cpts1) {
		//
		// System.out.println("****************Compte ********************");
		// System.out.println(c.getDateCreation());
		// System.out.println(c.getSolde());
		// System.out.println(c.getClient().getNomPersonne());
		// System.out.println("*******************************************");
		// }
//
//		 List<Operation> ops = dao.getOperationsByAccount((long) 1);
//		 List<Operation> ops1 =ops.subList(0, 4);
//		 for(Operation op:ops){
//		 System.out.println("****************Operations ********************");
//		 System.out.println(op);
//		 System.out.println(op.getDateOperation());
//		 System.out.println(op.getMontant());
//		 System.out.println(op.getCompte().getClient().getNomPersonne()+" "
//		 +op.getCompte().getClient().getPrenomPersonne());
//		 System.out.println("***********************************************");
//		 }
//		 System.out.println("*****************************************************************************************************");
//		 for(Operation op:ops1){
//			 System.out.println("****************Operations ********************");
//			 System.out.println(op);
//			 System.out.println(op.getDateOperation());
//			 System.out.println(op.getMontant());
//			 System.out.println(op.getCompte().getClient().getNomPersonne()+" "
//			 +op.getCompte().getClient().getPrenomPersonne());
//			 System.out.println("***********************************************");
//			 }
//		Role r = dao.getRoleByRoleName("ROLE_ADMIN");
//		// System.out.println(r.getRoleName());
//		Role r2 = dao.getRoleByRoleName("ROLE_EMPL");
//		// System.out.println(r2.getRoleName());
//
//		dao.attributeRole(r, (long) 1);
//		dao.attributeRole("ROLE_EMPL", (long) 4);
//		Collection<Role> roles = dao.getRolesByPersonne((long) 4);
//		for(Role r : roles){
//			System.out.println(r.getRoleName());
//		}
//		dao.deleteClient((long) 2);
//		dao.deleteClient((long) 10);
		// List<Role>r= dao.getRolesByPersonne((long) 2);
		// for(Role role : r){
		// System.out.println("****************Roles ********************");
		// System.out.println(role.getRoleName());
		// System.out.println(role.getPersonne().getNomPersonne());
		// System.out.println("******************************************");
		//
		// }

		// Role r = dao.getRoleByRoleName("ROLE_ADMIN");
		// System.out.println(r.getIdRole());
//		dao.addClient(new Client("AIT AL BRIMI", "Anass", "0655258921", "N 330, Avenue 20 Aout, Taroudant", "anassbrimi1992@gmail.com", "123456789"));
//		dao.addEmploye(new Employe("KADIRI", "Khalid", "0600259715", "Agadir", "khalid.kadiri@gmail.com", "123456789", "CC"));
//		Compte c = new Compte(8500);
//		dao.addAccount(c, (long)8, (long)11);
		
//		service.retirer(2000, (long)2, (long)11);
//		Compte c = new Compte(1800);
//		dao.addAccount(c, (long)2, (long)4);
//		dao.deleteAccount((long) 1);
//	
//		service.verser(100, (long)1, (long)4);
//		
//		service.verser(8440, (long)1, (long)4);
//		
//		service.verser(684, (long)1, (long)4);
		
//	Client c = new Client("AIT AL BRIMI", "Anass", "0655258921", "Agadir", "anassbrimi992@gmail.com");
//	dao.addClient(c);
		
//		Compte cp = dao.getAccountById((long)1);
//		dao.addOperation(new Retrait(155, false), cp.getIdCompte(), (long)4);
//		cp.setSolde(cp.getSolde() + 155);
		

	}

}
