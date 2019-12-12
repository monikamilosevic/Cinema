/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domain.GeneralEntity;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Response;

/**
 *
 * @author Monika
 */
public abstract class AbstractGenericOperation {

    public Response executeTransaction(GeneralEntity ge) {
        Response response = new Response();
        try {
            DBBroker.getInstance().openConnection();
            validate(ge);
            response = executeOperation(ge);
            DBBroker.getInstance().commit();
        } catch (Exception ex) {
            Logger.getLogger(AbstractGenericOperation.class.getName()).log(Level.SEVERE, null, ex);
            DBBroker.getInstance().rollback();
            response.setStatus(false);
            response.setMessage(ex.getMessage());
        } finally {
            try {
                DBBroker.getInstance().closeConnection();
            } catch (SQLException ex) {
                response.setStatus(false);
                response.setMessage(ex.getMessage());
            }
        }

        return response;
    }

    public abstract Response executeOperation(GeneralEntity ge) throws Exception;

    public abstract void validate(GeneralEntity ge) throws Exception;

}
