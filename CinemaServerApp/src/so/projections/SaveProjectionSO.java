/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.projections;

import db.DBBroker;
import domain.Auditorium;
import domain.GeneralEntity;
import domain.Projection;
import domain.ProjectionAuditorium;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author Monika
 */
public class SaveProjectionSO extends AbstractGenericOperation {

    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {
        Response res = new Response();
        try {
            DBBroker.getInstance().insert(ge);
            Projection p = (Projection) ge;
            p.setId(DBBroker.getInstance().getID(ge));
            for (Auditorium a : p.getAuditoriums()) {
                ProjectionAuditorium pa = new ProjectionAuditorium(p.getId(), a.getId());
                DBBroker.getInstance().insert(pa);
            }
            res.setData(null);
            res.setStatus(true);
            res.setMessage("System successfully saved the projection.");
        } catch (SQLException ex) {
            Logger.getLogger(SaveProjectionSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't save the projection.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }
}
