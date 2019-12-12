/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.projections;

import db.DBBroker;
import domain.GeneralEntity;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author Monika
 */
public class DeleteProjectionsSO extends AbstractGenericOperation{

    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {
        Response res = new Response();
        try {
            DBBroker.getInstance().delete(ge);
            res.setData(null);
            res.setStatus(true);
            res.setMessage("System successfully deleted the projection.");
        } catch (SQLException ex) {
            Logger.getLogger(DeleteProjectionsSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't delete the projection.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }
    
}
