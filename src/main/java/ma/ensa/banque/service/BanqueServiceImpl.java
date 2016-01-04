package ma.ensa.banque.service;

import ma.ensa.banque.dao.IBanqueDao;
import ma.ensa.banque.entities.Compte;
import ma.ensa.banque.entities.Retrait;
import ma.ensa.banque.entities.Versement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("banqueService")
@Transactional
public class BanqueServiceImpl implements IBanqueService {

	@Autowired
	public IBanqueDao dao;
//********************************************************************************************************************************************
// Gestions Des Operations
//********************************************************************************************************************************************
	@Override
	public void verser(double mt, Long idCompte, Long codeEmp) {
		Compte cp = dao.getAccountById(idCompte);
		dao.addOperation(new Versement(mt, true), idCompte, codeEmp);
		cp.setSolde(cp.getSolde() + mt);
	}

	@Override
	public void retirer(double mt, Long idCompte, Long codeEmp) {
		Compte cp = dao.getAccountById(idCompte);
		dao.addOperation(new Retrait(mt, true), idCompte, codeEmp);
		cp.setSolde(cp.getSolde() - mt);

	}

	@Override
	public void virement(double mt, Long idCompte1, Long idCompte2, Long codeEmp) {
		retirer(mt, idCompte1, codeEmp);
		verser(mt, idCompte2, codeEmp);

	}
//********************************************************************************************************************************************



}
