/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.reservations;

import db.DBBroker;
import domain.Auditorium;
import domain.GeneralEntity;
import domain.ProjectionAuditorium;
import domain.Reservation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author Monika
 */
public class GetReservationsSO extends AbstractGenericOperation {

    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {

        Response res = new Response();
        try {
            List<GeneralEntity> list = ge.getList(DBBroker.getInstance().select(ge));
            ArrayList<Reservation> listOfReservations = new ArrayList<>();

            for (GeneralEntity gen : list) {
                Reservation r = (Reservation) gen;
                ProjectionAuditorium pa = new ProjectionAuditorium(r.getProjection().getId(), -1);
                List<GeneralEntity> audit = pa.getList(DBBroker.getInstance().select(pa));
                List<Auditorium> auditoriums = new ArrayList<>();

                for (GeneralEntity general : audit) {
                    Auditorium a = (Auditorium) general;
                    auditoriums.add(a);
                }

                r.getProjection().setAuditoriums(auditoriums);
                listOfReservations.add(r);
            }

            res.setData(listOfReservations);
            res.setStatus(true);
            res.setMessage("System found all reservations for the given criteria.");
        } catch (SQLException ex) {
            Logger.getLogger(GetReservationsSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't find any reservations for the given criteria.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }

}
