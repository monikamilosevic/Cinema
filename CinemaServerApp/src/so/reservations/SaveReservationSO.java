/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.reservations;

import db.DBBroker;
import domain.GeneralEntity;
import domain.Reservation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import so.projections.SaveProjectionSO;
import transfer.Response;

/**
 *
 * @author Monika
 */
public class SaveReservationSO extends AbstractGenericOperation {

    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {
        Response res = new Response();
        try {
            DBBroker.getInstance().insert(ge);
            Reservation r = (Reservation) ge;
            DBBroker.getInstance().update(r.getMember());
            DBBroker.getInstance().update(r.getProjection());
            res.setData(null);
            res.setStatus(true);
            res.setMessage("System successfully saved the reservation.");
        } catch (SQLException ex) {
            Logger.getLogger(SaveProjectionSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't save the reservation.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }

}
