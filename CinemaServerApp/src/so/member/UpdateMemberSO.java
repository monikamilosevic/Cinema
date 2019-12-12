/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.member;

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
public class UpdateMemberSO extends AbstractGenericOperation {

    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {
        Response res = new Response();
        try {
            DBBroker.getInstance().update(ge);
            res.setData(null);
            res.setStatus(true);
            res.setMessage("System updated the member.");
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMemberSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't update the member.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }

}
