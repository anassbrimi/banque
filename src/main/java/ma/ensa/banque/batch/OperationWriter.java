package ma.ensa.banque.batch;

import java.util.List;

import ma.ensa.banque.entities.Operation;
import ma.ensa.banque.service.IBanqueService;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("operationWriter")
public class OperationWriter implements ItemWriter<Operation> {
	@Autowired
	private IBanqueService service;

	@Override
	public void write(List<? extends Operation> items) throws Exception {
		// TODO Auto-generated method stub
		List<Operation> ops = service.readAllOperationsByFailed();
		for(Operation op:ops){
			op.setStatut(true);
			op.getCompte().setSolde(op.getCompte().getSolde()+op.getMontant());
			service.mergeOperation(op);
		}
		
		
		for (Operation op : items) {
			service.verser(op.getMontant(), op.getCompte().getIdCompte(), op.getCompte().getEmploye().getIdPersonne());
		}
		
		

	}

}
