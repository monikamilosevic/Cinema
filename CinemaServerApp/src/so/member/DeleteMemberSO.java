/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.member;

import db.DBBroker;
import domain.GeneralEntity;
import domain.Member;
import domain.Movie;
import domain.Projection;
import domain.Reservation;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author Monika
 */
public class DeleteMemberSO extends AbstractGenericOperation{
    
    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {
        Response res = new Response();
        try {
            Member m = (Member) ge;
            Reservation r = new Reservation(-1, 0, 0, 0, 0, new Projection(-1, null, 0,0,0, new Movie(-1, null, null), null), new Member(m.getId(), null, null, null, 0));
            
            List<GeneralEntity> list = r.getList(DBBroker.getInstance().select(r));
            
            for (GeneralEntity gen : list) {
                r = (Reservation) gen;
                int freeSeats = r.getProjection().getFreeSeats() + r.getTickets();
                r.getProjection().setFreeSeats(freeSeats);
                DBBroker.getInstance().update(r.getProjection());
            }
            
            DBBroker.getInstance().delete(ge);          
                       
            res.setData(null);
            res.setStatus(true);
            res.setMessage("System successfully deleted the member.");
        } catch (SQLException ex) {
            Logger.getLogger(DeleteMemberSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System failed to delete the member.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }
    
}
