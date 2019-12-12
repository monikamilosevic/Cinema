/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DBBroker;
import domain.GeneralEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractGenericOperation;
import transfer.Response;

/**
 *
 * @author Monika
 */
public class LoginSO extends AbstractGenericOperation {

    @Override
    public Response executeOperation(GeneralEntity ge) throws Exception {
        Response response = new Response();
        try {
            List<GeneralEntity> list = ge.getList(DBBroker.getInstance().select(ge));
            if (list.isEmpty()) {
                throw new Exception("Login failed.");
            }
            response.setData(list);
            response.setStatus(true);
            response.setMessage("Login successfull.");
        } catch (Exception ex) {
            Logger.getLogger(LoginSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("System login failed.");
        }
        return response;
    }

    @Override
    public void validate(GeneralEntity ge) throws Exception {
    }

}
