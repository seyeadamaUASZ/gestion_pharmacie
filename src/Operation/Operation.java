package Operation;

import java.util.Date;

public class Operation {
    protected  String typeOperation;
    protected Date dateOperation;

    public Operation(String typeOperation, Date dateOperation) {
        this.typeOperation = typeOperation;
        this.dateOperation = dateOperation;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Operation() {
    }
}
