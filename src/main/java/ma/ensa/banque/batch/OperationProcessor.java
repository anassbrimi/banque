package ma.ensa.banque.batch;

import ma.ensa.banque.entities.Operation;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;


@Service("operationProcessor")
public class OperationProcessor implements ItemProcessor<Operation, Operation> {

	@Override
	public Operation process(final Operation operationInput) throws Exception {
		// TODO Auto-generated method stub
		Operation operationOutput = null;
		

			
			operationOutput = new Operation();
			operationOutput.setIdOp(operationInput.getIdOp());
			
		
		return operationOutput;
	}

}
