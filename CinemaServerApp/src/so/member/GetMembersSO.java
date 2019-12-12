/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.member;

import db.DBBroker;
import domain.GeneralEntity;
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
public class GetMembersSO extends AbstractGenericOperation {

    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {
        Response res = new Response();
        try {
            List<GeneralEntity> list = ge.getList(DBBroker.getInstance().select(ge));
            res.setData(list);
            res.setStatus(true);
            res.setMessage("System found members for the given criteria.");
        } catch (SQLException ex) {
            Logger.getLogger(GetMembersSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System couldn't find members for the given criteria.");
        }
        return res;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }

}
