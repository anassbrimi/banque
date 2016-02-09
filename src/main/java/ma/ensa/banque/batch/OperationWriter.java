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
		
		
		for (Operation op : items) {
			Operation op1 = new Operation();
			op1 = service.readOperationById(op.getIdOp());
			op1.setStatut(true);
			service.mergeOperation(op1);
		}
		
		Filecontrole fc = new Filecontrole();
		fc.cleanFile();
		
		

	}

}
